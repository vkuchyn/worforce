package ua.com.kuchyn.workforce.task2;

import java.util.LinkedList;
import java.util.List;

/**
 * Filter each 5th and 7th element
 * User: viktor
 * Date: 5/11/15
 */
public class FiveSevenElementArrayFilter implements ArrayFilter {
	@Override
	public <T> List<T> filterArray(T[] array) {
		List<T> result = new LinkedList<T>();
		for (int i = 0; i < array.length; i++) {
			if ((i + 1) % 5 == 0 || (i + 1) % 7 == 0) {
				result.add(array[i]);
			}
		}
		return result;
	}
}
