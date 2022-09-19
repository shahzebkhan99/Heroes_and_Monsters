package ham;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Cell_Test {

	Cell c1 = new Cell();
	
	@Test
	void test_revive() {
		
		int hp = c1.get_current_hp();
		c1.hit(15);
		
		if (hp  <= 10 ) {
			System.out.println("Cell Super Heals");
			hp = 40;			
		}
		
		assertTrue(hp == 40);
		
		
	}

}
