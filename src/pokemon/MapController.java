package pokemon;
import events.*;
import events.Event;

public class MapController extends Controller {
    private Trainer[] trainers = new Trainer[2];

    public class Move extends Event {
        Map map;
        Trainer trainer;
        public Move(Map map, Trainer trainer) {
            super(0);
            this.map = map;
            this.trainer = trainer;
        }

        @Override
        public void action() {
            map.move(trainer);
            if (map.isOnGrass(trainer)) {
                double r = Math.random();
                if ((r > 0.8 && trainer.getName() != "wild") || (r > 0.95 && trainer.getName() == "wild")) {
                    Trainer wild = new Trainer("wild", (trainer.id == 0) ? 1 : 0, new Pokemon[]{
                            new Pokemon(Pokemon.getRandomPokemon())
                    });
                    Versus vs = new Versus(new Trainer[]{trainer, wild});
                    vs.addEvent(trainer.id, vs.new Battle(trainer.id));
                    vs.addEvent(wild.id, vs.new Battle(wild.id));
                    System.out.println("O treinador " + trainer.getName() + " encontrou o pokémon " + wild.pokemons[wild.getActivePokemon()].getName());
                    vs.run();
                    if (trainer.remainingPokemon() == 0) {
                        System.out.println("O treinador " + trainer.getName() + " não possui mais pokémons!");
                        System.out.println("Fim da partida!");
                        return;
                    }
                    System.out.println();
                    addEvent(trainer.id, new Move(this.map, this.trainer));
                } else {
                    addEvent(trainer.id, new Move(this.map, this.trainer));
                }
            } else {
                addEvent(trainer.id, new Move(this.map, this.trainer));
            }
        }
    }

    public static void main(String[] args) {
        MapController mpc = new MapController();
        mpc.trainers[0] = new Trainer("Anorak", 0,
                new Pokemon[] {
                        new Pokemon(Pokemon.pokedex.get("Blastoise")),
                        new Pokemon(Pokemon.pokedex.get("Gardevoir")),
                        new Pokemon(Pokemon.pokedex.get("Pidgey"))
                }
        );
        mpc.trainers[1] = new Trainer("Brosquinha",1,
                new Pokemon[] {
                        new Pokemon(Pokemon.pokedex.get("Charizard")),
                        new Pokemon(Pokemon.pokedex.get("Mewtwo")),
                        new Pokemon(Pokemon.pokedex.get("Pikachu"))
                }
        );
        Map map = new Map(mpc.trainers);

        mpc.addEvent(mpc.trainers[0].id, mpc.new Move(map, mpc.trainers[0]));
        mpc.addEvent(mpc.trainers[1].id, mpc.new Move(map, mpc.trainers[1]));
        mpc.run();
    }
}
