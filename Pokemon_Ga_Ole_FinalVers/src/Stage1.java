import java.util.ArrayList;

public class Stage1 extends Stage{
	private static ArrayList<Pokemon> stage1attackerPokemon = new ArrayList<>();
	private static ArrayList<Pokemon> stage1userPokemon = new ArrayList<>();
	
		public Stage1() {
			super("Easy", getPokemonlist().get(0).getName(), getPokemonlist().get(2).getName(), getPokemonlist().get(4).getName(), 40, stage1attackerPokemon, stage1userPokemon);
			
			stage1attackerPokemon.add(getPokemonlist().get(0));
			stage1attackerPokemon.add(getPokemonlist().get(2));
			stage1attackerPokemon.add(getPokemonlist().get(4));
			stage1attackerPokemon.add(getPokemonlist().get(6));
			
			stage1userPokemon.add(getPokemonlist().get(2));
			stage1userPokemon.add(getPokemonlist().get(6));
			stage1userPokemon.add(getPokemonlist().get(8));
			stage1userPokemon.add(getPokemonlist().get(10));
		}
	}