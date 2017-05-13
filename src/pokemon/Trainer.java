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

    public String getName() {
        return name;
    }
}
