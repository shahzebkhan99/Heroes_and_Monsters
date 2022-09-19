package ham;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Super_Test {

	SuperChicken s1 = new SuperChicken();
	
	@Test
	void test_super_punch() {
		int[] solution = s1.attack();
		
		int damage = solution [2];
		System.out.println(damage);
		assertTrue (damage == 25);
	}

}
