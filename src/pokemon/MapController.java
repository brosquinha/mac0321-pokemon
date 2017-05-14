package pokemon;
import com.sun.jdi.event.*;
import events.*;
import events.Event;

public class MapController extends Controller {

    private Trainer[] trainers = new Trainer[2];
    public class Move extends Event {
        Map map;
        Trainer trainer;
        public Move(Map map, Trainer trainer) {
            super(1000);
            this.map = map;
            this.trainer = trainer;
        }

        @Override
        public void action() {
            map.move(trainer.id);
            if (map.isOnGrass(trainer)) {
                double r = Math.random();
                if (r > 0.8) {
                    Versus vs = new Versus();
                    Trainer wild = new Trainer("wild", (trainer.id == 0) ? 1 : 0, new Pokemon[]{
                            new Pokemon(Pokemon.pokedex.get("Pikachu"))
                    });
                    vs.addEvent(trainer.id, vs.new Batalhar(trainer.id));
                    vs.addEvent(wild.id, vs.new Batalhar(wild.id));
                    vs.run();
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
                }
        );
        mpc.trainers[1] = new Trainer("Brosquinha",1,
                new Pokemon[] {
                        new Pokemon(Pokemon.pokedex.get("Charizard")),
                }
        );
        Map map = new Map(mpc.trainers);

        mpc.addEvent(mpc.trainers[0].id, mpc.new Move(map, mpc.trainers[0]));
        mpc.addEvent(mpc.trainers[1].id, mpc.new Move(map, mpc.trainers[1]));
        mpc.run();
    }
}
