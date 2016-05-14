package java.util;

public class LinkedList<E>
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable
{
    transient int size = 0;

    // 第一个结点
    transient Node<E> first;

    // 最后一个结点
    transient Node<E> last;

    // 构造一个空列表
    public LinkedList() {
    }

    // 构造一个包含指定 Collection 中的元素的列表，
	// 这些元素按其 Collection 的迭代器返回的顺序排列
    public LinkedList(Collection<? extends E> c) {
        this();
        addAll(c);
    }

    // 返回此列表的第一个元素
    public E getFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.item;
    }

    // 返回此列表的最后一个元素
    public E getLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return l.item;
    }

    // 移除并返回此列表的第一个元素
    public E removeFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

    // 移除并返回此列表的最后一个元素
    public E removeLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return unlinkLast(l);
    }

    // 将指定元素插入此列表的开头
    public void addFirst(E e) {
        linkFirst(e);
    }

    // 将指定元素添加到此列表的结尾
    public void addLast(E e) {
        linkLast(e);
    }

    // 判断此列表包含指定元素，如果是，则返回true
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    // 返回此列表的元素个数
    public int size() {
        return size;
    }

    // 将指定元素添加到此列表的结尾
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    // 从此列表中移除首次出现的指定元素(如果存在返回true，否则返回false)
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    // 添加指定 collection 中的所有元素到此列表的结尾，
	// 顺序是指定 collection 的迭代器返回这些元素的顺序
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    // 将指定 collection 中的所有元素从指定位置开始插入此列表
    public boolean addAll(int index, Collection<? extends E> c) {
        checkPositionIndex(index);// 检查index的范围
		
        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0)          // 如果c为空，则返回false
            return false;

        Node<E> pred, succ;
        if (index == size) {      // 插入位置为列表末尾
            succ = null;
            pred = last;
        } else {
            succ = node(index);   // 插入位置不是列表末尾
            pred = succ.prev;
        }
		// 将元素添加到pred末尾
        for (Object o : a) {
            @SuppressWarnings("unchecked") E e = (E) o;
            Node<E> newNode = new Node<>(pred, e, null);
            if (pred == null)
                first = newNode;
            else
                pred.next = newNode;
            pred = newNode;
        }
		// 将剩余元素整合一起
        if (succ == null) {
            last = pred;
        } else {
            pred.next = succ;
            succ.prev = pred;
        }

        size += numNew;
        modCount++;
        return true;
    }

    // 从此列表中移除所有元素
    public void clear() {
        // Clearing all of the links between nodes is "unnecessary", but:
        // - helps a generational GC if the discarded nodes inhabit
        //   more than one generation
        // - is sure to free memory even if there is a reachable Iterator
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
        modCount++;
    }
	
	/********************** 位置访问操作 **************************/

    // 返回此列表中指定位置处的元素
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    // 将此列表中指定位置的元素替换为指定的元素
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldVal = x.item;
        x.item = element;
        return oldVal;
    }

    // 在此列表中指定的位置插入指定的元素
    public void add(int index, E element) {
        checkPositionIndex(index);

        if (index == size)     // 插入到末尾
            linkLast(element);
        else
            linkBefore(element, node(index));
    }

    // 移除此列表中指定位置处的元素
    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    // （包访问权限）返回指定位置上的结点（非空）
    Node<E> node(int index) {
        // assert isElementIndex(index);
        // 根据指定位置是在左半边还是在右半边，
		// 来决定是用正向寻找还是反向寻找
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
	
	/********************** Search操作 **************************/

    // 返回此列表中首次出现的指定元素的索引，
	// 如果此列表中不包含该元素，则返回 -1
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    // 返回此列表中最后出现的指定元素的索引，
	// 如果此列表中不包含该元素，则返回 -1
    public int lastIndexOf(Object o) {
        int index = size;
        if (o == null) {
            for (Node<E> x = last; x != null; x = x.prev) {
                index--;
                if (x.item == null)
                    return index;
            }
        } else {
            for (Node<E> x = last; x != null; x = x.prev) {
                index--;
                if (o.equals(x.item))
                    return index;
            }
        }
        return -1;
    }

	/********************** Queue操作 **************************/

    // 获取但不移除此列表的头（第一个元素）
    public E peek() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    // 获取但不移除此列表的头（第一个元素）
    public E element() {
        return getFirst();
    }

    // 获取并移除此列表的头（第一个元素）
    public E poll() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    // 获取并移除此列表的头（第一个元素）
    public E remove() {
        return removeFirst();
    }

    // 将指定元素添加到此列表的末尾（最后一个元素）
    public boolean offer(E e) {
        return add(e);
    }

	/********************** Deque操作 **************************/
	
    // 在此列表的开头插入指定的元素
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    // 在此列表末尾插入指定的元素
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    // 获取但不移除此列表的第一个元素
    public E peekFirst() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
     }

    // 获取但不移除此列表的最后一个元素
    public E peekLast() {
        final Node<E> l = last;
        return (l == null) ? null : l.item;
    }

    // 获取并移除此列表的第一个元素
    public E pollFirst() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    // 获取并移除此列表的最后一个元素
    public E pollLast() {
        final Node<E> l = last;
        return (l == null) ? null : unlinkLast(l);
    }

    // 将元素推入此列表所表示的堆栈（插入到列表的头）
    public void push(E e) {
        addFirst(e);
    }

    // 从此列表所表示的堆栈处弹出一个元素（获取并移除列表第一个元素）
    public E pop() {
        return removeFirst();
    }

    // 从此列表中移除第一次出现的指定元素（从头部到尾部遍历列表）
    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    // 从此列表中移除最后一次出现的指定元素（从头部到尾部遍历列表）
    public boolean removeLastOccurrence(Object o) {
        if (o == null) {
            for (Node<E> x = last; x != null; x = x.prev) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = last; x != null; x = x.prev) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    // 返回此列表中的元素的列表迭代器（按适当顺序），从列表中指定位置开始
    public ListIterator<E> listIterator(int index) {
        checkPositionIndex(index);
        return new ListItr(index);
    }

	// （私有类）结点结构
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * @since 1.6
     */
    public Iterator<E> descendingIterator() {
        return new DescendingIterator();
    }

    /**
     * Adapter to provide descending iterators via ListItr.previous
     */
    private class DescendingIterator implements Iterator<E> {
        private final ListItr itr = new ListItr(size());
        public boolean hasNext() {
            return itr.hasPrevious();
        }
        public E next() {
            return itr.previous();
        }
        public void remove() {
            itr.remove();
        }
    }

    @SuppressWarnings("unchecked")
    private LinkedList<E> superClone() {
        try {
            return (LinkedList<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    // 返回此 LinkedList 的浅表副本
    public Object clone() {
        LinkedList<E> clone = superClone();

        // Put clone into "virgin" state
        clone.first = clone.last = null;
        clone.size = 0;
        clone.modCount = 0;

        // Initialize clone with our elements
        for (Node<E> x = first; x != null; x = x.next)
            clone.add(x.item);

        return clone;
    }

    // 返回以适当顺序（从第一个元素到最后一个元素）包含此列表中所有元素的数组
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next)
            result[i++] = x.item;
        return result;
    }

    // 返回以适当顺序（从第一个元素到最后一个元素）包含此列表中所有元素的数组；
	// 返回数组的运行时类型为指定数组的类型。
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            a = (T[])java.lang.reflect.Array.newInstance(
                                a.getClass().getComponentType(), size);
        int i = 0;
        Object[] result = a;
        for (Node<E> x = first; x != null; x = x.next)
            result[i++] = x.item;

        if (a.length > size)
            a[size] = null;

        return a;
    }

	// 版本序列号
    private static final long serialVersionUID = 876323262645176354L;

    // java.io.Serializable的写入函数
    // 将LinkedList的“容量，所有的元素值”都写入到输出流中
    private void writeObject(java.io.ObjectOutputStream s)
        throws java.io.IOException {
        // Write out any hidden serialization magic
        s.defaultWriteObject();

        // Write out size
        s.writeInt(size);

        // Write out all elements in the proper order.
        for (Node<E> x = first; x != null; x = x.next)
            s.writeObject(x.item);
    }

    // java.io.Serializable的读取函数：根据写入方式反向读出
    // 先将LinkedList的“容量”读出，然后将“所有的元素值”读出
    @SuppressWarnings("unchecked")
    private void readObject(java.io.ObjectInputStream s)
        throws java.io.IOException, ClassNotFoundException {
        // Read in any hidden serialization magic
        s.defaultReadObject();

        // Read in size
        int size = s.readInt();

        // Read in all elements in the proper order.
        for (int i = 0; i < size; i++)
            linkLast((E)s.readObject());
    }
}
