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
	final Attack[] attacks = {
		new Attack(20, 100),
		new Attack(30, 70),
		new Attack(80, 50),
		new Attack(100, 30),
	};
	int damageTaken;
	Status status;

	public Pokemon(String name, Type type, int maxHP) {
		this.name = name;
		this.type = type;
		this.maxHP = maxHP;
		this.status = Status.NORMAL;
		this.damageTaken = 0;
	}

	// int hasWeakness(Pokemon attacker);

	public void attack(int attack, Pokemon defender) {
		defender.takeDamage(this.attacks[attack].damage);
	}

	public int getHP() {
		return this.maxHP - this.damageTaken;
	}
	
	public void takeDamage(int damage) {
		this.damageTaken += damage;
	}
	private class Attack {
		private int damage;
		private int speed;

		public Attack(int damage, int speed) {
			this.damage = damage;
			this.speed = speed;
		}
	}
}
