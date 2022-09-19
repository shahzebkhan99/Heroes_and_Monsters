package ham;

public abstract class Hero extends Dungeon_Chracter {
	
	private double chance_to_block;
	
	public Hero() {
		super();
		this.chance_to_block = 0;
	}

	public Hero(String name, int max_hp, int y_coord, int x_coord, double base_chance,
			int do_damage_min,
			int do_damage_max, int direction, double chance_to_block) {
		
		super(name, max_hp, y_coord, x_coord, base_chance, 
				do_damage_min, do_damage_max, direction);
		
		this.chance_to_block = chance_to_block;		
	}
	
	@Override
	public void hit(int damage) {
		if (damage > 0) {
			
			if (super.successful_action(chance_to_block)) {
				System.out.println("Damage blocked");
				damage = 0;
			}			
		}
		super.hit(damage);
	}
	
	public void hero_stuff() {
		System.out.println("hero");
	}

}
