package pokemon;
import events;

public class Versus extends Controller {
	private Pokemon[] pokemons1;
	private Pokemon[] pokemons2;
	private Trainer trainer1;
	private Trainer trainer2;

	private class Attack extends Event {
		final int damage;
		final int speed;

		public Attack(long eventTime) {
			super(eventTime);
		}
		public void action() {
			
		}
		public String description() {
			return "Pokemon ataca";
		}
	}

	private class ChangePokemon extends Event {
		public ChangePokemon(long eventTime) {
			super(eventTime);
		}
		public void action() {
			// TODO Auto-generated method stub

		}
		public String description() {
			return "Trocando pokemon";
		}
	}

	private class UseItem extends Event {
		public UseItem(long eventTime) {
			super(eventTime);
		}
		public void action() {

		}
		public String description() {
			return "Usando item de cura";
		}
	}

	private class Run extends Event {
		public Run(long eventTime) {
			super(eventTime);
		}
		public void action() {

		}
		public String description() {
			return "Fugindo de batalha";
		}
	}
	public static void main (String[] args) {
		trainer1 = new Trainer();
		trainer2 = new Trainer();

	}
}
