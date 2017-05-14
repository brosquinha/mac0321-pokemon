package pokemon;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Pokemon {

    static final Map<String, Pokemon> pokedex = new HashMap<String, Pokemon>();

    static {
        pokedex.put("Blastoise", new Pokemon("Blastoise", 300,
                new Attack[]{
                        new Attack("Hydro Pump", 100, 30),
                        new Attack("Water Gun", 60, 70),
                        new Attack("Slash",40, 80)
                }
        ));

        pokedex.put("Charizard", new Pokemon("Charizard", 250,
                new Attack[]{
                        new Attack("Fire Blast",120, 25),
                        new Attack("Slash",40, 80)
                }
        ));
        pokedex.put("Pikachu", new Pokemon("Pikachu", 180,
                new Attack[]{
                        new Attack("Thundershock",90, 60),
                        new Attack("Quick Attack",45, 100)
                }
        ));
        pokedex.put("Gyarados", new Pokemon("Gyarados", 150,
                new Attack[]{
                        new Attack("Draco Meteor",150, 10),
                        new Attack("Body Slam", 90, 50)
                }
        ));
        pokedex.put("Mewtwo", new Pokemon("Mewtwo", 250,
                new Attack[]{
                        new Attack("Psychic",130, 10),
                        new Attack("Confusion", 60, 50)
                }
        ));
        pokedex.put("Pidgey", new Pokemon("Pidgey", 140,
                new Attack[]{
                        new Attack("Gust", 70, 70)
                }
        ));
        pokedex.put("Butterfree", new Pokemon("Butterfree", 120,
                new Attack[]{
                        new Attack("Poison Powder", 120, 100),
                        new Attack("Wing Attack", 90, 80)
                }
        ));
        pokedex.put("Gardevoir", new Pokemon("Gardevoir", 220,
                new Attack[]{
                        new Attack("Psybeam", 90, 70),
                        new Attack("Moonblast", 310, 50),
                        new Attack("Psychic",130, 10)
                }
        ));
        pokedex.put("Magikarp", new Pokemon("Magikarp", 50,
                new Attack[]{
                        new Attack("Poison Powder", 0, 100)
                }
        ));
    }

    private final String name;
    private final int maxHP;
    final Attack[] attacks;
    private int damageTaken;

    public Pokemon(Pokemon pokemon) {
        this(pokemon.name, pokemon.maxHP, pokemon.attacks);
    }

    public Pokemon(String name, int maxHP, Attack[] attacks) {
        this.name = name;
        this.maxHP = maxHP;
        this.damageTaken = 0;
        this.attacks = attacks;
    }

    public void attack(int attack, Pokemon defender) {
        defender.takeDamage(this.attacks[attack].damage);
    }

    public void takeDamage(int damage) {
        this.damageTaken += damage;
    }

    public void healDamage(int heal) {
        this.damageTaken -= heal;
        if (this.damageTaken < 0) this.damageTaken = 0;
    }

    public boolean isDead() {
        return (this.damageTaken >= this.maxHP);
    }

    public int getHP() {

        if (this.maxHP - this.damageTaken < 0) return 0;
        else return this.maxHP - this.damageTaken;
    }

    static Pokemon getRandomPokemon() {
        Random generator = new Random();
        Object[] values = pokedex.values().toArray();
        return (Pokemon)values[generator.nextInt(values.length)];
    }

    public String getName() {
        return name;
    }
    static class Attack {
        private final String name;
        private final int damage;
        private final int priority;

        public Attack(String name, int damage, int priority) {
            this.name = name;
            this.damage = damage;
            this.priority = (priority > 100) ? 100 : ((priority < 0) ? 0 : priority);
        }

        public String getName() {
            return this.name;
        }

        public int getPriority() {
            return this.priority;
        }

        public int getDamage() {
            return damage;
        }
    }
}
