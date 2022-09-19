package ham;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Orc_test {
	
	Orc o1 = new Orc();

	@Test
	void combo_test() {
		int average = 0;
		int sample_size = 1000;
		for (int i = 0; i < sample_size; i++) {
			int[] solution = o1.attack();
			average += solution[2]; 			
		}
		
		average /= sample_size;
		System.out.println("Average " + average);
		assertTrue(average >= 18 && average <=30);
	}

}
