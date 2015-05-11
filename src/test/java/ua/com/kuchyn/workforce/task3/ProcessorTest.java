package ua.com.kuchyn.workforce.task3;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItem;
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

}

class CachedRequestHandler implements IRequestHandler<String> {

	private Set<String> processedRequest = new HashSet<String>();

	public Set<String> getProcessedRequest() {
		return processedRequest;
	}

	@Override
	public void processRequests(String o) throws Exception {
		System.out.println(o);
		processedRequest.add(o);
	}
}
