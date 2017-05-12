package pokemon;
import events.*;

public class Versus extends Controller {
	private Trainer[] trainers;
	
	private class Attack extends Event {
		private Pokemon attacker;
		private Pokemon defender;
		private int attackerTrainer;
		private int defenderTrainer;
		private int attack;
		
		public Attack(Pokemon atacante, int ataque, Pokemon alvo, int idTrainer) {
			super(atacante.attacks[ataque].getPriority());
			this.attacker = atacante;
			this.attack = ataque;
			this.defender = alvo;
			if (trainers[0].id == idTrainer)
				this.attackerTrainer = 0;
			else
				this.attackerTrainer = 1;
			this.defenderTrainer = (this.attackerTrainer == 0) ? 1 : 0;
		}
		public void action() {
			if (!attacker.isDead())
				this.attacker.attack(this.attack, this.defender);
			else {
				ChangePokemon p = new ChangePokemon(trainers[this.defenderTrainer]);
				p.action();
			}
		}
		public String description() {
			return "Pokemon ataca";
		}
	}
	
	private class ChangePokemon extends Event {
		private Trainer treinador;
		private int pokemonAtivo = 0;
		public ChangePokemon(Trainer treinador) {
			super(400);
			this.treinador = treinador;
		}
		public void action() {
			Pokemon p;
			while ((p = treinador.pokemons[pokemonAtivo])== null || treinador.pokemons[pokemonAtivo].isDead()) {
				pokemonAtivo++;
			}
			treinador.setPokemonAtivo(pokemonAtivo);
		}
		public String description() {
			return "Trocando pokemon";
		}
	}
	
	private class UseItem extends Event {
		private Trainer treinador;
		private int item;
		public UseItem(int prioridade, Trainer treinador, int item) {
			super(prioridade);
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
		public Run(int prioridade, Trainer treinador) {
			super(prioridade);
			this.treinador = treinador;
		}
		public void action() {
			
		}
		public String description() {
			return "Fugindo de batalha";
		}
	}
	
	private class Batalhar extends Event {
		private Trainer trainer1;
		private Trainer trainer2;
		
		public Batalhar(Trainer trainer1, Trainer trainer2) {
			super(0);
			this.trainer1 = trainer1;
			this.trainer2 = trainer2;
		}
		public void action() {
			double n = Math.random();
			addEvent(new Attack(this.trainer1.pokemons[this.trainer1.pokemonAtivo], 
					2, this.trainer2.pokemons[this.trainer2.pokemonAtivo], 1));
			// Instead of hard-wiring, you could parse
			// configuration information from a text
			// file here:
			/*rings = 5;
			addEvent(new ThermostatNight(tm));
			addEvent(new LightOn(tm + 1000));
			addEvent(new LightOff(tm + 2000));
			addEvent(new WaterOn(tm + 3000));
			addEvent(new WaterOff(tm + 8000));
			addEvent(new Bell(tm + 9000));
			addEvent(new ThermostatDay(tm + 10000));
			// Can even add a Restart object!
			addEvent(new Restart(tm + 20000));*/
		}
		public String description() {
			return "Iniciando batalha";
		}
	}
	
	public static void main (String[] args) {
		Versus vs = new Versus();
		Pokemon[] pokemons1 = {
			new Pokemon("Blastoise", 300),
		};
		vs.trainers[0] = new Trainer(1, pokemons1);
		vs.trainers[1] = new Trainer(2, pokemons1);
		vs.addEvent(vs.new Batalhar(vs.trainers[0], vs.trainers[1]));
	}
}
