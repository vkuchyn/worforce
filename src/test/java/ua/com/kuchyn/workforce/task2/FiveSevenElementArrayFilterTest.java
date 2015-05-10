package ua.com.kuchyn.workforce.task2;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: viktor
 * Date: 5/11/15
 */
public class FiveSevenElementArrayFilterTest {
	@Test
	public void shouldFilterEmptyArray() throws Exception {
		ArrayFilter filter = new FiveSevenElementArrayFilter();
		List<String> filteredList = filter.filterArray(new String[] {});

		assertThat(filteredList, is(Collections.EMPTY_LIST));
	}

	@Test
	public void expectEmptyListWhenInputArraysLengthIsFour() throws Exception {
		ArrayFilter filter = new FiveSevenElementArrayFilter();
		List<String> filteredList = filter.filterArray(new String[] {"1", "2", "3", "4"});

		assertThat(filteredList, is(Collections.EMPTY_LIST));
	}

	@Test
	public void shouldFilterWhenInputArraysLengthIsSix() throws Exception {
		ArrayFilter filter = new FiveSevenElementArrayFilter();
		List<String> filteredList = filter.filterArray(new String[] {"1", "2", "3", "4", "5", "6"});

		assertThat(filteredList, is(Arrays.asList("5")));
	}

	@Test
	public void shouldFilterWhenInputArraysLengthIsEleven() throws Exception {
		ArrayFilter filter = new FiveSevenElementArrayFilter();
		List<String> filteredList = filter.filterArray(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"});

		assertThat(filteredList, is(Arrays.asList("5", "7", "10")));
	}

	@Test
	public void shouldFilterElevenElementsWhenArrayLengthIs35() throws Exception {
		ArrayFilter filter = new FiveSevenElementArrayFilter();
		List<String> filteredList = filter.filterArray(new String[35]);

		assertThat(filteredList.size(), is(11));


	}
}
