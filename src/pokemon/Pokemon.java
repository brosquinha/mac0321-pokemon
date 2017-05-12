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
	final int maxHP;
	final Attack[] attacks = {
		new Attack(20, 100),
		new Attack(30, 70),
		new Attack(80, 50),
		new Attack(100, 30),
	};
	int damageTaken;
	Status status;

	public Pokemon(String name, int maxHP) {
		this.name = name;
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
	public boolean isDead() {
		return (this.damageTaken >= this.maxHP);
	}
	class Attack {
		private int damage;
		private int priority;

		public Attack(int damage, int priotity) {
			this.damage = damage;
			this.priority = (priority > 100) ? 100 : ((priority < 0) ? 0 : priority);
		}
		public int getPriority() {
			return this.priority;
		}
	}
}
