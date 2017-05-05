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
		attacks[0] = new Attack(20, 100);
		attacks[1] = new Attack(30, 70);
		attacks[2] = new Attack(80, 50);
		attacks[3] = new Attack(100, 30);
	};
	int damageTaken;
	Status status;

	public Pokemon(String name, Type type, int maxHP) {
		this.name = name;
		this.type = type;
		this.maxHP = maxHP;
		this.status = Status.NORMAL;
	}

	// int hasWeakness(Pokemon attacker);

	public void attack(int attack, Pokemon defender) {
		defender.setHP(defender.getHP() - this.attacks[attack].damage);
	}

	private class Attack {
		private int damage;
		private int speed;

		public Attack(int damage, int speed) {
			this.damagae = damage;
			this.speed = speed;
		}
	}
}
