package ham;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Cleric_Test {

	Cleric c1 = new Cleric();
		
	@Test
	void test_attack_damage() {
		int[] solution = c1.attack();
		int max = solution.length - 1;
		assertTrue(solution[max] == 0);
	}
	
	@Test
	void test_attack_healing() {
		c1.hit(9);
		c1.attack();
		int chp = c1.get_current_hp();		
		assertTrue(chp > 11 && chp <16);
	}
	
	
	
}
