package pokemon;

import events.*;

public class Versus extends Controller {
    private Trainer[] trainers;

    public Versus(Trainer[] trainers) {
        this.trainers = trainers;
    }

    private class Attack extends Event {
        private Pokemon attacker;
        private Pokemon defender;
        private int attackerTrainer;
        private int defenderTrainer;
        private int attack;

        public Attack(Pokemon atacante, int ataque, Pokemon alvo, int trainerID) {
            super(atacante.attacks[ataque].getPriority());
            this.attacker = atacante;
            this.attack = ataque;
            this.defender = alvo;
            this.attackerTrainer = (trainerID == trainers[0].id) ? trainers[0].id : trainers[1].id;
            this.defenderTrainer = (trainerID == trainers[0].id) ? trainers[1].id : trainers[0].id;
        }

        public void action() {
            if (!attacker.isDead()) {
                this.attacker.attack(this.attack, this.defender);
                System.out.println("O pokémon " + this.attacker.getName() + " atacou " + this.defender.getName() +
                        " e causou " + this.attacker.attacks[this.attack].getDamage() + " de dano!");
                System.out.println("Vida restante de " + this.defender.getName() + ": " + this.defender.getHP());
                addEvent(this.attackerTrainer, new Battle(this.attackerTrainer));
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
            int i;
            for (i = activePokemon; i < trainer.pokemons.length; i++) {
                if (!trainer.pokemons[i].isDead()) {
                    trainer.setActivePokemon(i);
                    System.out.println("O pokémon ativo agora é " + trainer.pokemons[trainer.getActivePokemon()].getName());
                    addEvent(trainer.id, new Battle(trainer.id));
                    break;
                }
            }
            if (i >= trainer.pokemons.length) {
                System.out.println(trainer.getName() == "wild" ? "O pokémon selvagem morreu!" : ("O treinador " + trainer.getName() + " não tem mais pokémons!"));
            }
        }
    }

    private class UseItem extends Event {
        private Trainer trainer;
        private int heal;

        public UseItem(Trainer trainer, int item) {
            super(200);
            this.trainer = trainer;
            this.heal = 50 + (int)(Math.random()*51); //minimo de heal = 50, maximo 100
        }

        public void action() {
            trainer.pokemons[trainer.activePokemon].healDamage(this.heal);
            System.out.println("O treinador " + trainer.getName() + " usou um item e curou " + this.heal + " de dano!");
            System.out.println(trainer.pokemons[trainer.getActivePokemon()].getName() + " está com " + this.trainer.pokemons[trainer.getActivePokemon()].getHP() + " de vida!");
            addEvent(trainer.id, new Battle(trainer.id));
        }
    }

    private class Run extends Event {
        private Trainer trainer;

        public Run(Trainer trainer) {
            super(500);
            this.trainer = trainer;
        }

        public void action() {
            System.out.println("O treinador " + trainer.getName() + " fugiu!");
        }
    }

    class Battle extends Event {
        private Trainer trainer1;
        private Trainer trainer2;
        private int trainerID;

        public Battle(int trainerID) {
            super(0);
            this.trainerID = trainerID;
            this.trainer1 = (trainers[0].id == trainerID) ? trainers[0] : trainers[1];
            this.trainer2 = (trainers[0].id == trainerID) ? trainers[1] : trainers[0];
        }

        public void action() {
            double n = Math.random();
            double m;
            if (n > 0.2) {
                m = (Math.random() * 10) % trainer1.pokemons[trainer1.getActivePokemon()].attacks.length;
                addEvent(this.trainerID, new Attack(this.trainer1.pokemons[this.trainer1.activePokemon],
                        (int) m, this.trainer2.pokemons[this.trainer2.activePokemon], this.trainerID));
            } else if (n > 0.1 && trainer1.getName() != "wild") {
                addEvent(this.trainerID, new UseItem(trainer1, 2));
            } else if (n > 0.01 && trainer1.getName() != "wild") {
                int pokemonsRestantes = 0;
                for (int i = 0; i < trainer1.pokemons.length; i++) {
                    if (!trainer1.pokemons[i].isDead()) {
                        pokemonsRestantes++;
                    }
                    if (pokemonsRestantes == 1) {
                        this.action();
                        return;
                    }
                }
                addEvent(this.trainerID, new ChangePokemon(trainer1));
            } else {
                addEvent(this.trainerID, new Run(trainer1));
            }
        }
    }

    public static void main(String[] args) {
        Versus vs = new Versus(new Trainer[2]);
        vs.trainers[0] = new Trainer("Anorak", 0,
                new Pokemon[] {
                    new Pokemon(Pokemon.pokedex.get("Blastoise")),
                }
        );
        vs.trainers[1] = new Trainer("Brosquinha",1,
                new Pokemon[] {
                    new Pokemon(Pokemon.pokedex.get("Charizard")),
                }
        );
        vs.addEvent(0, vs.new Battle(0));
        vs.addEvent(1, vs.new Battle(1));
        vs.run();
    }
}
