package ua.com.kuchyn.workforce.task3;

/**
 * User: viktor
 * Date: 5/11/15
 */
public class Processor<T> {
	private final IRequestHandler<T> requestHandler;
	private final int maxThreads;

	/**
	 * Processes requests from the queue with no more than
	 * maxThreads threads
	 * For each request object calls
	 * IRequestHandler<T>.processRequest(o) only once in a separate thread
	 * When the queue is empty and all processing is finished
	 * no threads exist.
	 *
	 * @param rh         - an object that handles requests
	 * @param maxThreads - total number of threads
	 */
	public Processor(IRequestHandler<T> rh, int maxThreads) {

		this.requestHandler = rh;
		this.maxThreads = maxThreads;
	}

	/**
	 * Puts the request into a queue, does not wait
	 * for the request to complete
	 *
	 * @param o - request object
	 */
	public void addRequest(T o) {

		try {
			requestHandler.processRequests(o);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * OPTIONAL
	 * Asynchronous shutdown, returns immediately.
	 * Instructs the processor to stop accepting requests
	 * and finish existing tasks
	 *
	 * @param o – if not null, notifies all waiting threads on
	 *          this object upon successful shutdown
	 */
	public void shutDown(Object o) {

	}

	/**
	 * OPTIONAL
	 *
	 * @returns true if the processor is shut down
	 */
	public boolean isShutDown() {
		throw new UnsupportedOperationException();
	}
}
