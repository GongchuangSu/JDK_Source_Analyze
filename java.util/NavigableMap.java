package java.util;

public interface NavigableMap<K,V> extends SortedMap<K,V> {
    // 返回一个键-值映射关系，它与严格小于给定键的最大键关联；
	// 如果不存在这样的键，则返回 null
    Map.Entry<K,V> lowerEntry(K key);

    // 返回严格小于给定键的最大键；
	// 如果不存在这样的键，则返回 null
    K lowerKey(K key);

    // 返回一个键-值映射关系，它与小于等于给定键的最大键关联；
	// 如果不存在这样的键，则返回 null
    Map.Entry<K,V> floorEntry(K key);

    // 返回小于等于给定键的最大键；
	// 如果不存在这样的键，则返回 null
    K floorKey(K key);

    // 返回一个键-值映射关系，它与大于等于给定键的最小键关联；
	// 如果不存在这样的键，则返回 null
    Map.Entry<K,V> ceilingEntry(K key);

    // 返回大于等于给定键的最小键；
	// 如果不存在这样的键，则返回 null
    K ceilingKey(K key);

    // 返回一个键-值映射关系，它与严格大于给定键的最小键关联；
	// 如果不存在这样的键，则返回 null
    Map.Entry<K,V> higherEntry(K key);

    // 返回严格大于给定键的最小键；
	// 如果不存在这样的键，则返回 null
    K higherKey(K key);

    // 返回一个与此映射中的最小键关联的键-值映射关系；
	// 如果映射为空，则返回 null
    Map.Entry<K,V> firstEntry();

    // 返回与此映射中的最大键关联的键-值映射关系；
	// 如果映射为空，则返回 null
    Map.Entry<K,V> lastEntry();

    // 移除并返回与此映射中的最小键关联的键-值映射关系；
	// 如果映射为空，则返回 null
    Map.Entry<K,V> pollFirstEntry();

    // 移除并返回与此映射中的最大键关联的键-值映射关系；
	// 如果映射为空，则返回 null
    Map.Entry<K,V> pollLastEntry();

    // 返回此映射中所包含映射关系的逆序视图
    NavigableMap<K,V> descendingMap();

    // 返回此映射中所包含键的 NavigableSet 视图
    NavigableSet<K> navigableKeySet();

    // 返回此映射中所包含键的逆序 NavigableSet 视图
    NavigableSet<K> descendingKeySet();

    // 返回此映射的部分视图，其键的范围从 fromKey 到 toKey
    NavigableMap<K,V> subMap(K fromKey, boolean fromInclusive,
                             K toKey,   boolean toInclusive);

    // 返回此映射的部分视图，其键小于（或等于，如果 inclusive 为 true）toKey
    NavigableMap<K,V> headMap(K toKey, boolean inclusive);

    // 返回此映射的部分视图，其键大于（或等于，如果 inclusive 为 true）fromKey
    NavigableMap<K,V> tailMap(K fromKey, boolean inclusive);

    // 返回此映射的部分视图，其键值的范围从 fromKey（包括）到 toKey（不包括）
    SortedMap<K,V> subMap(K fromKey, K toKey);

    // 返回此映射的部分视图，其键值严格小于 toKey
    SortedMap<K,V> headMap(K toKey);

    // 返回此映射的部分视图，其键大于等于 fromKey
    SortedMap<K,V> tailMap(K fromKey);
}
