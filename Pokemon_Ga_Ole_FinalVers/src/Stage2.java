import java.util.ArrayList;

public class Stage2 extends Stage{
	private static ArrayList<Pokemon> stage2attackerPokemon = new ArrayList<>();
	private static ArrayList<Pokemon> stage2userPokemon = new ArrayList<>();
	
	public Stage2() {
		super("Medium", getPokemonlist().get(1).getName() , getPokemonlist().get(3).getName(), getPokemonlist().get(8).getName(), 60, stage2attackerPokemon, stage2userPokemon);
		
		stage2attackerPokemon.add(getPokemonlist().get(9));
		stage2attackerPokemon.add(getPokemonlist().get(8));
		stage2attackerPokemon.add(getPokemonlist().get(11));
		stage2attackerPokemon.add(getPokemonlist().get(10));
		
		stage2userPokemon.add(getPokemonlist().get(3));
		stage2userPokemon.add(getPokemonlist().get(13));
		stage2userPokemon.add(getPokemonlist().get(6));
		stage2userPokemon.add(getPokemonlist().get(4));
	}
}