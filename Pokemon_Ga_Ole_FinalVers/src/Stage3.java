import java.util.ArrayList;

public class Stage3 extends Stage{
	private static ArrayList<Pokemon> stage3attackerPokemon = new ArrayList<>();
	private static ArrayList<Pokemon> stage3userPokemon = new ArrayList<>();

	public Stage3() {
		super("Difficult", getPokemonlist().get(15).getName(), getPokemonlist().get(13).getName(), getPokemonlist().get(11).getName(), 80, stage3attackerPokemon, stage3userPokemon);
	
		stage3attackerPokemon.add(getPokemonlist().get(1));
		stage3attackerPokemon.add(getPokemonlist().get(15));
		stage3attackerPokemon.add(getPokemonlist().get(11));
		stage3attackerPokemon.add(getPokemonlist().get(9));
		
		stage3userPokemon.add(getPokemonlist().get(3));
		stage3userPokemon.add(getPokemonlist().get(7));
		stage3userPokemon.add(getPokemonlist().get(13));
		stage3userPokemon.add(getPokemonlist().get(15));
	}
}
