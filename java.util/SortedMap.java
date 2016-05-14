package java.util;

public interface SortedMap<K,V> extends Map<K,V> {
	
	// 返回对此映射中的键进行排序的比较器
	// 如果此映射使用键的自然顺序，则返回 null
    Comparator<? super K> comparator();

    // 返回此映射的部分视图，其键值的范围从 fromKey（包括）到 toKey（不包括） 
    SortedMap<K,V> subMap(K fromKey, K toKey);

    // 返回此映射的部分视图，其键值严格小于 toKey
    SortedMap<K,V> headMap(K toKey);

    // 返回此映射的部分视图，其键大于等于 fromKey
    SortedMap<K,V> tailMap(K fromKey);

    // 返回映射中当前第一个（最低）键
    K firstKey();

    // 返回映射中当前最后一个（最高）键
    K lastKey();

    // 返回在此映射中所包含键的 Set 视图
    Set<K> keySet();

    // 返回在此映射中所包含值的 Collection 视图
    Collection<V> values();

    // 返回在此映射中包含的映射关系的 Set 视图
    Set<Map.Entry<K, V>> entrySet();
}
