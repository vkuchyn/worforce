package ua.com.kuchyn.workforce.task1;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * User: viktor
 * Date: 5/10/15
 */
public class FibonacciRecursiveTest {

	private FibonacciPrinter fibonacciPrinter;

	private ByteArrayOutputStream out;
	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		out = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(out);
		fibonacciPrinter = new RecursiveFibonacciPrinter(printStream);
	}

	@Test
	public void shouldPrintZeroForLengthOne() throws Exception {
		fibonacciPrinter.printFibonacciSequence(1);

		String printed = new String(out.toByteArray());
		assertThat(printed, is("0,"));
	}

	@Test
	public void shouldPrintZeroOneForLengthTwo() throws Exception {
		fibonacciPrinter.printFibonacciSequence(2);

		String printed = new String(out.toByteArray());
		assertThat(printed, is("0, 1,"));
	}

	@Test
	public void expectIllegalArgumentExceptionWhenLengthIsZero() throws Exception {
		ex.expect(IllegalArgumentException.class);

		fibonacciPrinter.printFibonacciSequence(0);

		String printed = new String(out.toByteArray());
		assertThat(printed, is("0,"));
	}

}
