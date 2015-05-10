package ua.com.kuchyn.workforce.task2;

import java.util.List;

/**
 * User: viktor
 * Date: 5/11/15
 */
public interface ArrayFilter {

	public <T extends Object> List<T> filterArray(T[] array);
}
