package ham;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Warrior_Test {
	
	Warrior w1 = new Warrior();
		
	@Test
	void test_crit() {
		int[] solution = w1.attack();
		
		int damage = solution[2];
		System.out.println(damage);
		assertTrue(damage > 17 && damage < 21);
	}
	
	@Test
	void test_cooldown_on() {
		w1.attack();
		int[] solution = w1.attack();
		
		int damage = solution[2];
		System.out.println(damage);
		assertTrue(damage < 11);
	}
	
	@Test
	void test_cooldown_off() {
		for (int i = 0; i<2; i++) {
		w1.attack();
		}
		int[] solution = w1.attack();		
		int damage = solution[2];
		System.out.println(damage);
		assertTrue(damage > 17 && damage < 21);
	}

}
