package java.lang;

public final class StringBuilder
    extends AbstractStringBuilder
    implements java.io.Serializable, CharSequence
{

    /** use serialVersionUID for interoperability */
    static final long serialVersionUID = 4383685877147921099L;

    /**
     * 构造一个不带任何字符的字符串生成器，其初始容量为16个字符
     */
    public StringBuilder() {
        super(16);
    }

    /**
     * 构造一个不带任何字符的字符串生成器，其初始容量由capacity参数设定
	 * 说明：其capacity参数值不能小于0，否则会抛出异常NegativeArraySizeException
     */
    public StringBuilder(int capacity) {
        super(capacity);
    }

    /**
	 * 构造一个字符串生成器，并初始化指定的字符串内容。其初始容量为指定字符串的
	 *     长度加上16
     * 说明：其str不能为null，否则会抛出异常NullPointerException
     */
    public StringBuilder(String str) {
        super(str.length() + 16);
        append(str);
    }

    /**
	 * 构造一个字符串生成器，其包含与指定的字符序列相同的字符。其初始容量为字符
	 *     序列的长度加上16
     * 说明：其seq不能为null，否则会抛出异常NullPointerException
     */
    public StringBuilder(CharSequence seq) {
        this(seq.length() + 16);
        append(seq);
    }

	/** 
	 * 追加Object参数的字符串表示形式到此序列 
	 * 说明：如果obj为null，则追加"null"；如果obj不为null，则追加obj.toString()
	 */
    public StringBuilder append(Object obj) {
        return append(String.valueOf(obj));
    }
    
	/** 
	 * 将指定的字符串追加到此序列 
	 * 说明：如果str为null，则追加"null"
	 */
    public StringBuilder append(String str) {
        super.append(str);
        return this;
    }

    /** 将指定的StringBuilder参数追加到此序列 */
    private StringBuilder append(StringBuilder sb) {
        if (sb == null)                     // 如果sb为null，则追加"null"
            return append("null");
        int len = sb.length();
        int newcount = count + len;         // count为之前的字符长度
        if (newcount > value.length)        // 若新的字符长度newcount大于存储容量
            expandCapacity(newcount);       // 则进行扩容
        sb.getChars(0, len, value, count);  // 将sb复制到原字符序列的尾部
        count = newcount;                   // 更新字符长度
        return this;
    }

    /** 将指定的StringBuffer追加到此序列 */ 
    public StringBuilder append(StringBuffer sb) {
        super.append(sb);
        return this;
    }

    /** 将指定的CharSequence追加到此序列 */
    public StringBuilder append(CharSequence s) {
        if (s == null)
            s = "null";
        if (s instanceof String)
            return this.append((String)s);
        if (s instanceof StringBuffer)
            return this.append((StringBuffer)s);
        if (s instanceof StringBuilder)
            return this.append((StringBuilder)s);
        return this.append(s, 0, s.length());
    }

    /** 将指定的CharSequence子序列追加到此序列 */
    public StringBuilder append(CharSequence s, int start, int end) {
        super.append(s, start, end);
        return this;
    }
	
    /** 将char数组参数的字符串表示形式追加到此序列 */
    public StringBuilder append(char[] str) {
        super.append(str);
        return this;
    }

    /** 将char数组参数的子数组的字符串表示形式追加到此序列 */
    public StringBuilder append(char[] str, int offset, int len) {
        super.append(str, offset, len);
        return this;
    }
    
	/**
	 * 将boolean参数的字符串表示形式追加到此序列
	 * 说明：若b为true，则追加"true"；若b为false，则追加"false"
	 */
    public StringBuilder append(boolean b) {
        super.append(b);
        return this;
    }

	/** 将char字符的字符串表示形式追加到此序列 */
    public StringBuilder append(char c) {
        super.append(c);
        return this;
    }

	/** 将int参数的字符串表示形式追加到此序列 */
    public StringBuilder append(int i) {
        super.append(i);
        return this;
    }
	
    /** 将int参数的字符串表示形式追加到此序列 */
    public StringBuilder append(long lng) {
        super.append(lng);
        return this;
    }
    
	/** 将float参数的字符串表示形式追加到此序列 */
    public StringBuilder append(float f) {
        super.append(f);
        return this;
    }

	/** 将double参数的字符串表示形式追加到此序列 */
    public StringBuilder append(double d) {
        super.append(d);
        return this;
    }

    /** 将codePoint参数的字符串表示形式追加到此序列 */
    public StringBuilder appendCodePoint(int codePoint) {
        super.appendCodePoint(codePoint);
        return this;
    }

    /** 
	 * 移除此序列的子字符串中的字符 
	 * 说明：包含起始索引但不包括结束索引
	 */
    public StringBuilder delete(int start, int end) {
        super.delete(start, end);
        return this;
    }

    /** 移除此序列指定位置上的字符 */
    public StringBuilder deleteCharAt(int index) {
        super.deleteCharAt(index);
        return this;
    }

    /** 
	  * 使用给定的String中的字符替换此序列的子字符串中的字符
	  * 该子字符串从指定的start处开始，一直到索引end-1处的字符
	  */
    public StringBuilder replace(int start, int end, String str) {
        super.replace(start, end, str);
        return this;
    }

    /**
     * 将str数组参数的子数组的字符串表示形式插入到此序列中
	 * 子数组从指定的offset(包括offset)开始，包括len个char
	 * 子数组的字符将被插入到index所指示的位置
     */
    public StringBuilder insert(int index, char[] str, int offset,
                                int len)
    {
        super.insert(index, str, offset, len);
        return this;
    }

    /** 将Object参数的字符串表示形式插入到此字符序列中 */
    public StringBuilder insert(int offset, Object obj) {
        return insert(offset, String.valueOf(obj));
    }

    /** 将字符串插入到此序列 */
    public StringBuilder insert(int offset, String str) {
        super.insert(offset, str);
        return this;
    }

    /** 将char数组参数的字符串表示形式插入此序列中 */
    public StringBuilder insert(int offset, char[] str) {
        super.insert(offset, str);
        return this;
    }

    /** 将指定的CharSequence插入此序列中 */
    public StringBuilder insert(int dstOffset, CharSequence s) {
        if (s == null)
            s = "null";
        if (s instanceof String)
            return this.insert(dstOffset, (String)s);
        return this.insert(dstOffset, s, 0, s.length());
    }

    /** 将指定 CharSequence 的子序列插入此序列中 */
    public StringBuilder insert(int dstOffset, CharSequence s,
                                int start, int end)
    {
        super.insert(dstOffset, s, start, end);
        return this;
    }

    /** 将 boolean 参数的字符串表示形式插入此序列中 */
    public StringBuilder insert(int offset, boolean b) {
        super.insert(offset, b);
        return this;
    }

    /** 将 char 参数的字符串表示形式插入此序列中 */
    public StringBuilder insert(int offset, char c) {
        super.insert(offset, c);
        return this;
    }

    /** 将 int 参数的字符串表示形式插入此序列中 */
    public StringBuilder insert(int offset, int i) {
        return insert(offset, String.valueOf(i));
    }

    /** 将 long 参数的字符串表示形式插入此序列中 */
    public StringBuilder insert(int offset, long l) {
        return insert(offset, String.valueOf(l));
    }

    /** 将 float 参数的字符串表示形式插入此序列中 */
    public StringBuilder insert(int offset, float f) {
        return insert(offset, String.valueOf(f));
    }

    /** 将 double 参数的字符串表示形式插入此序列中 */
    public StringBuilder insert(int offset, double d) {
        return insert(offset, String.valueOf(d));
    }

    /** 返回指定子字符串在此字符串中第一次出现处的索引 */
    public int indexOf(String str) {
        return indexOf(str, 0);
    }

    /** 返回指定子字符串在此字符串中第一次出现处的索引，从指定的索引开始*/
    public int indexOf(String str, int fromIndex) {
        return String.indexOf(value, 0, count,
                              str.toCharArray(), 0, str.length(), fromIndex);
    }

    /** 返回最右边出现的指定子字符串在此字符串中的索引 */
    public int lastIndexOf(String str) {
        return lastIndexOf(str, count);
    }

    /** 从指定位置开始，返回最右边出现的指定子字符串在此字符串中的索引 */
    public int lastIndexOf(String str, int fromIndex) {
        return String.lastIndexOf(value, 0, count,
                              str.toCharArray(), 0, str.length(), fromIndex);
    }

	/** 将此字符序列用其反转形式取代 */
    public StringBuilder reverse() {
        super.reverse();
        return this;
    }

	/** 返回此序列中数据的字符串表示形式 */
    public String toString() {
        // Create a copy, don't share the array
        return new String(value, 0, count);
    }

    /**
     * Save the state of the <tt>StringBuilder</tt> instance to a stream
     * (that is, serialize it).
     *
     * @serialData the number of characters currently stored in the string
     *             builder (<tt>int</tt>), followed by the characters in the
     *             string builder (<tt>char[]</tt>).   The length of the
     *             <tt>char</tt> array may be greater than the number of
     *             characters currently stored in the string builder, in which
     *             case extra characters are ignored.
     */
    private void writeObject(java.io.ObjectOutputStream s)
        throws java.io.IOException {
        s.defaultWriteObject();
        s.writeInt(count);
        s.writeObject(value);
    }

    /**
     * readObject is called to restore the state of the StringBuffer from
     * a stream.
     */
    private void readObject(java.io.ObjectInputStream s)
        throws java.io.IOException, ClassNotFoundException {
        s.defaultReadObject();
        count = s.readInt();
        value = (char[]) s.readObject();
    }

}
