package pokemon;

import pokemon.Pokemon;

public class Trainer {
    Pokemon[] pokemons;
    int pokemonAtivo;
    int id;

    public Trainer(int id, Pokemon[] pokemons) {
        this.pokemons = pokemons;
        this.id = id;
        this.pokemonAtivo = 0;
    }

    void setPokemonAtivo(int pokemonAtivo) {
        this.pokemonAtivo = pokemonAtivo;
    }

    void useItem(int item) {

    }

}
