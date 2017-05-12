package pokemon;
import events.*;

public class Versus extends Controller {
	private Pokemon pokemons1;
	private Pokemon pokemons2;
	
	private class Attack extends Event {
		private Pokemon attacker;
		private Pokemon defender;
		private int attack;
		
		public Attack(Pokemon atacante, int ataque, Pokemon alvo) {
			super(1);
			this.attacker = atacante;
			this.attack = ataque;
			this.defender = alvo;
		}
		public void action() {
		}
		public String description() {
			return "Pokemon ataca";
		}
	}
	
	/*
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
	*/
	public static void main (String[] args) {
		Trainer trainer1 = new Trainer();
		Trainer trainer2 = new Trainer();
		
	}
}
