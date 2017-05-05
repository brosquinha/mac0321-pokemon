package pokemon;

public class Versus extends Controller {
	private Pokemon[] pokemons1;
	private Pokemon[] pokemons2;
	private Trainer trainer1;
	private Trainer trainer2;
	
	private class Attack extends Event {
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
	
}
