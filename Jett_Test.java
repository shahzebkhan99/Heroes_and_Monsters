package ham;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Jett_Test {
	
	Jett j1 = new Jett();

	@Test
	void test_absorbtion() {
		for (int i = 0; i < 20; i++) {
			j1.hit(5);
		}
		assertTrue(j1.is_alive());
	}

}
