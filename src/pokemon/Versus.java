package pokemon;
import events;

public class Versus extends Controller {
	private Pokemon pokemons1;
	private Pokemon pokemons2;
	private Trainer trainer1;
	private Trainer trainer2;
	
	private class Attack extends Event {
		final int damage;
		final int speed;
		private Pokemon atacante;
		private Pokemon alvo;
		private int ataque;
		
		public Attack(long eventTime, Pokemon atacante, int ataque, Pokemon alvo) {
			super(eventTime);
			this.atacante = atacante;
			this.ataque = ataque;
			this.alvo = alvo;
		}
		public void action() {
			
		}
		public String description() {
			return "Pokemon ataca";
		}
	}
	
	private class ChangePokemon extends Event {
		private Trainer treinador;
		private int pokemonAtivo;
		public ChangePokemon(long eventTime, Trainer treinador, int pokemonAtivo) {
			super(eventTime);
			this.treinador = treinador;
		}
		public void action() {
			treinador.setPokemonAtivo(pokemonAtivo);
		}
		public String description() {
			return "Trocando pokemon";
		}
	}
	
	private class UseItem extends Event {
		private Trainer treinador;
		private int item;
		public UseItem(long eventTime, Trainer treinador, int item) {
			super(eventTime);
			this.treinador = treinador;
			this.item = item;
		}
		public void action() {
			treinador.useItem(item);
		}
		public String description() {
			return "Usando item de cura";
		}
	}
	
	private class Run extends Event {
		private Trainer treinador;
		public Run(long eventTime, Trainer treinador) {
			super(eventTime);
			this.treinador = treinador;
		}
		public void action() {
			//TODO: fugir
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
