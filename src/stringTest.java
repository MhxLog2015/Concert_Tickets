import static org.junit.Assert.*;

import org.junit.Test;

public class stringTest {

	@Test
	public void test() {
		Login_emp junit = new Login_emp();
		String result = junit.user();
		assertEquals(null,result);
	}

}
