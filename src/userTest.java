import static org.junit.Assert.*;

import org.junit.Test;

public class userTest {

	
	@Test
	public void userTest1() {
		EMP junit = new EMP();
		int result = junit.num1;
		assertEquals(0,result);
	}
		
	@Test
	public void userTest6() {
		EMP junit = new EMP();
		int result = junit.num6;
		assertEquals(0,result);
	}

}