package ua.com.kuchyn.workforce.task3;

import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: viktor
 * Date: 5/11/15
 */
public class Processor<T> {

	private BlockingQueue<T> queue = new LinkedBlockingQueue<>();
	private final ProcessorThread processorThread;

	private Logger logger = Logger.getLogger(Processor.class.getCanonicalName());

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
		processorThread = new ProcessorThread(rh, maxThreads);
		processorThread.start();
	}

	/**
	 * Puts the request into a queue, does not wait
	 * for the request to complete
	 *
	 * @param o - request object
	 */
	public void addRequest(T o) {
		queue.add(o);
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

	private class ProcessorThread extends Thread{

		private final IRequestHandler<T> requestHandler;
		private final ExecutorService executorService;

		private ProcessorThread(IRequestHandler<T> requestHandler, int maxThreads) {
			this.requestHandler = requestHandler;
			executorService = new ThreadPoolExecutor(0, maxThreads,
					0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		}

		@Override
		public void run() {
			try {
				final T task = queue.take();
				executorService.submit(new Runnable() {
					@Override
					public void run() {
						try {
							requestHandler.processRequests(task);
						} catch (Exception e) {
							logger.log(Level.WARNING, "Task " + task.toString() + " failed with error " + e.getMessage(), e);
						}
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
