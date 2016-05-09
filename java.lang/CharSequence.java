package java.lang;
public interface CharSequence {
	/**
	 *  返回字符序列的长度
	 */
	int length();
	
	/**
	 *  返回指定索引的char值
	 */
	char charAt(int index);
	
	/**
	 * 返回该序列的子序列
	 * @param start 开始位置的索引值(包括)
	 * @param end   结束位置的索引值(不包括)
	 * 说明：当start=end时，返回一个空序列
	 */
	CharSequence subSequence(int start, int end);
	
	/**
	 *  返回一个包含该序列中字符的字符串，该字符串与序列的顺序相同
	 */
	public String toString();
}