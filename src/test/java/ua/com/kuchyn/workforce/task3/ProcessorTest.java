package ua.com.kuchyn.workforce.task3;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * User: viktor
 * Date: 5/11/15
 */
public class ProcessorTest {

	@Test
	public void shouldAddRequestToQueue() throws Exception {
		CachedRequestHandler requestHandler = new CachedRequestHandler();
		Processor<String> processor = new Processor<String>(requestHandler, 1);

		processor.addRequest("Task1");

		assertThat(requestHandler.getProcessedRequest(), hasItem("Task1"));
	}

	@Test
	public void shouldNotWaitAfterAddingRequest() throws Exception {
		CachedRequestHandler requestHandler = new CachedRequestHandler(10);
		Processor<String> processor = new Processor<String>(requestHandler, 1);

		long millisBefore = System.currentTimeMillis();
		processor.addRequest("Task1");
		long duration = System.currentTimeMillis() - millisBefore;

		assertThat(duration, is(lessThan(10L)));
	}
}

class CachedRequestHandler implements IRequestHandler<String> {

	private Set<String> processedRequest = new HashSet<String>();
	private int millis = 0;

	CachedRequestHandler() {
	}

	CachedRequestHandler(int millis) {
		this.millis = millis;
	}

	public Set<String> getProcessedRequest() {
		return processedRequest;
	}

	@Override
	public void processRequests(String o) throws Exception {
		System.out.println(o);
		Thread.sleep(millis);
		processedRequest.add(o);
	}
}
