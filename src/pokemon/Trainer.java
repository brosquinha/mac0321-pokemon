package pokemon;

public class Trainer {
    String name;
    Pokemon[] pokemons;
    int activePokemon;
    int id;

    public Trainer(String name, int id, Pokemon[] pokemons) {
        this.name = name;
        this.pokemons = pokemons;
        this.id = id;
        this.activePokemon = 0;
    }

    void setActivePokemon(int activePokemon) {
        this.activePokemon = activePokemon;
    }

    public int getActivePokemon() {
        return activePokemon;
    }

    public int remainingPokemon() {
        int count = 0;
        for (Pokemon pokemon : this.pokemons) {
            if (pokemon.getHP() > 0) count++;
        }
        return count;
    }

    public String getName() {
        return name;
    }
}
