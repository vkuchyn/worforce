package ua.com.kuchyn.workforce.task3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: viktor
 * Date: 5/11/15
 */
public class Processor<T> {

	private Logger logger = Logger.getLogger(Processor.class.getCanonicalName());
	private volatile boolean stop = false;
	private final ExecutorService executorService;
	private IRequestHandler<T> requestHandler;

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
		requestHandler = rh;
		executorService = new ThreadPoolExecutor(0, maxThreads,
				0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

	}

	/**
	 * Puts the request into a queue, does not wait
	 * for the request to complete
	 *
	 * @param o - request object
	 */
	public void addRequest(final T o) {
		if (!stop) {
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					try {
						requestHandler.processRequests(o);
					} catch (Exception e) {
						logger.log(Level.WARNING, "Task " + o.toString() + " failed with error " + e.getMessage(), e);
					}
				}
			});
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
		stop = true;
		executorService.shutdown();
	}

	/**
	 * OPTIONAL
	 *
	 * @returns true if the processor is shut down
	 */
	public boolean isShutDown() {
		return stop;
	}


}
