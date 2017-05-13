package pokemon;

import events.*;

public class Versus extends Controller {
    private Trainer[] trainers = new Trainer[2];

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
            if (!attacker.isDead()) {
                this.attacker.attack(this.attack, this.defender);
                System.out.println("O pokémon " + this.attacker.getName() + " atacou " + this.defender.getName() +
                        " e causou " + this.attacker.attacks[this.attack].getDamage() + " de dano!");
                System.out.println("Vida restante de " + this.defender.getName() + ": " + this.defender.getHP());
                addEvent(this.attackerTrainer, new Batalhar(this.attackerTrainer));
            } else {
                ChangePokemon p = new ChangePokemon(trainers[this.attackerTrainer]);
                p.action();
            }
        }
    }

    private class ChangePokemon extends Event {
        private Trainer trainer;
        private int activePokemon;

        public ChangePokemon(Trainer trainer) {
            super(400);
            this.trainer = trainer;
            this.activePokemon = trainer.getActivePokemon();
        }

        public void action() {
            Pokemon p;
            System.out.println("Pokemon ativo: " + trainer.pokemons[trainer.activePokemon].getName());
            while (activePokemon++ + 1 < trainer.pokemons.length
                    && ((p = trainer.pokemons[activePokemon]) == null
                    || trainer.pokemons[activePokemon].isDead()));
            if (trainer.pokemons[activePokemon] != null && !trainer.pokemons[activePokemon].isDead()) {
                trainer.setActivePokemon(activePokemon);
                System.out.println("O pokémon ativo agora é " + trainer.pokemons[trainer.getActivePokemon()].getName());
                addEvent(trainer.id, new Batalhar(trainer.id));
            } else {
                System.out.println("O treinador " + trainer.getName() + " não possui mais pokémons!");
            }
        }
    }

    private class UseItem extends Event {
        private Trainer trainer;
        private int item;

        public UseItem(Trainer trainer, int item) {
            super(200);
            this.trainer = trainer;
            this.item = item;
        }

        public void action() {
            System.out.println("O treinador " + trainer.getName() + " usou um item!");
            addEvent(trainer.id, new Batalhar(trainer.id));
        }
    }

    private class Run extends Event {
        private Trainer trainer;

        public Run(Trainer trainer) {
            super(500);
            this.trainer = trainer;
        }

        public void action() {
            System.out.println("O trainer" + "fugiu!");
        }
    }

    private class Batalhar extends Event {
        private Trainer trainer1;
        private Trainer trainer2;
        private int trainerID;

        public Batalhar(int trainerID) {
            super(3);
            this.trainerID = trainerID;
            this.trainer1 = trainers[this.trainerID];
            this.trainer2 = (this.trainerID == 0) ? trainers[1] : trainers[0];
        }

        public void action() {
            double n = Math.random();
            double m;
            if (n > 0.2) {
                m = (Math.random() * 10) % 4;
                addEvent(this.trainerID, new Attack(this.trainer1.pokemons[this.trainer1.activePokemon],
                        (int) m, this.trainer2.pokemons[this.trainer2.activePokemon], this.trainerID));
            } else if (n > 0.1) {
                addEvent(this.trainerID, new UseItem(trainer1, 2));
            } else if (n > 0.01) {
                addEvent(this.trainerID, new ChangePokemon(trainer1));
            } else {
                addEvent(this.trainerID, new Run(trainer1));
            }
        }
    }

    public static void main(String[] args) {
        Versus vs = new Versus();
        Pokemon[] pokemons1 = {
                new Pokemon("Blastoise", 300)
        };
        Pokemon[] pokemons2 = {
                new Pokemon("Charizard", 300)
        };
        vs.trainers[0] = new Trainer("Anorak", 0, pokemons1);
        vs.trainers[1] = new Trainer("Brosquinha",1, pokemons2);
        vs.addEvent(0, vs.new Batalhar(0));
        vs.addEvent(1, vs.new Batalhar(1));
        vs.run();
    }
}
