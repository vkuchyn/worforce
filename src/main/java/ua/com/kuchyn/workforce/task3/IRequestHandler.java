package ua.com.kuchyn.workforce.task3;

/**
 * User: viktor
 * Date: 5/11/15
 */
public interface IRequestHandler<T> {
	/**
	 * A thread-safe method to process a single request
	 *
	 * @param o - request object
	 */
	void processRequests(T o) throws Exception;
}
