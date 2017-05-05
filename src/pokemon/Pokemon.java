package pokemon;

public class Pokemon {
	enum Type { // define os tipos possíveis para os pokémons
		NORMAL, FIRE, FIGHTING,
		WATER, FLYING, GRASS,
		POISON, ELECTRIC, GROUND,
		PSYCHIC, ROCK, ICE,
		BUG, DRAGON, GHOST,
		DARK, STEEL, FAIRY;
	}
	
	enum Status {
		NORMAL, FAINTED;
	}
	
	final String name;
	final Type type;
	final int maxHP;
	//final Attack[] attacks;
	int damageTaken;
	
	public Pokemon(String name, Type type, int maxHP) {
		this.name = name;
		this.type = type;
		this.maxHP = maxHP;
	}
	
	// int hasWeakness(Pokemon attacker);
	
}

class Attack {
	final int damage;
	final int speed;
	
	public Attack(int damage, int speed) {
		this.damage = damage;
		this.speed = speed;
	}
}