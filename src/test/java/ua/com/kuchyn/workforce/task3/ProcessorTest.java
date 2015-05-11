package ua.com.kuchyn.workforce.task3;

import org.junit.Test;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * User: viktor
 * Date: 5/11/15
 */
public class ProcessorTest {

	@Test(timeout = 100)
	public void shouldAddRequestToQueue() throws Exception {
		CachedRequestHandler requestHandler = new CachedRequestHandler();
		Processor2<String> processor = new Processor2<String>(requestHandler, 1);

		processor.addRequest("Task1");
		requestHandler.countDownLatch.await();
		assertThat(requestHandler.getProcessedRequest(), hasItem("Task1"));
	}

	@Test
	public void shouldNotWaitAfterAddingRequest() throws Exception {
		CachedRequestHandler requestHandler = new CachedRequestHandler(10, 1);
		Processor2<String> processor = new Processor2<String>(requestHandler, 1);

		long millisBefore = System.currentTimeMillis();
		processor.addRequest("Task1");
		long duration = System.currentTimeMillis() - millisBefore;

		assertThat(duration, is(lessThan(10L)));
	}

	@Test(timeout = 100)
	public void shouldShutDownInGentleWay() throws Exception {
		CachedRequestHandler requestHandler = new CachedRequestHandler(20, 1);
		Processor2<String> processor = new Processor2<String>(requestHandler, 2);

		processor.addRequest("Task1");
		Thread.sleep(5);

		processor.shutDown(null);
		processor.addRequest("TaskAfterStop");
		requestHandler.countDownLatch.await();

		assertThat(processor.isShutDown(), is(true));
		assertThat(requestHandler.getProcessedRequest(), hasItem("Task1"));
		assertThat(requestHandler.getProcessedRequest().size(), is(1));
	}

	@Test(timeout = 100)
	public void shouldPutTasksToQueueWhenRequestsMoreThanMaxThreads() throws Exception {
		CachedRequestHandler requestHandler = new CachedRequestHandler(0, 2);
		Processor2<String> processor = new Processor2<String>(requestHandler, 1);

		processor.addRequest("Task1");
		processor.addRequest("Task2");
		requestHandler.countDownLatch.await();
		assertThat(requestHandler.getProcessedRequest(), hasItem("Task1"));
		assertThat(requestHandler.getProcessedRequest(), hasItem("Task2"));
	}

	@Test
	public void shouldFinishAllTasksAfterShutDown() throws Exception {
		CachedRequestHandler requestHandler = new CachedRequestHandler(10, 2);
		Processor2<String> processor = new Processor2<>(requestHandler, 1);

		processor.addRequest("Task1");
		processor.addRequest("Task2");
		processor.shutDown(null);
		requestHandler.countDownLatch.await();
		assertThat(requestHandler.getProcessedRequest(), hasItem("Task1"));
		assertThat(requestHandler.getProcessedRequest(), hasItem("Task2"));

	}
}

class CachedRequestHandler implements IRequestHandler<String> {

	private Set<String> processedRequest = new ConcurrentSkipListSet<>();
	private int millis = 0;
	CountDownLatch countDownLatch;

	CachedRequestHandler() {
		countDownLatch = new CountDownLatch(1);
	}

	CachedRequestHandler(int millis, int countDownSize) {
		countDownLatch = new CountDownLatch(countDownSize);
		this.millis = millis;
	}

	public Set<String> getProcessedRequest() {
		return processedRequest;
	}

	@Override
	public void processRequests(String o) throws Exception {
		processedRequest.add(o);
		System.out.println(o);
		Thread.sleep(millis);
		countDownLatch.countDown();
	}
}
