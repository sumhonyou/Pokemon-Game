import java.util.ArrayList;
public class Stage {

		private String difficulties;
		private int DifficultiesValue;
		private String pokemon1;
		private String pokemon2;
		private String pokemon3;
		private ArrayList<Pokemon> attackerPokemon;
		private ArrayList<Pokemon> userPokemon;
		private static ArrayList<Pokemon> pokemonlist = new ArrayList<>();
		private static Database db = new Database();
			
		static {
			db.readPokemonFile(pokemonlist);
		}
		
		public Stage() {
			difficulties = null;
			DifficultiesValue = 0;
			pokemon1 = null;
			pokemon2 = null;
			pokemon3 = null;
			attackerPokemon = null;
			userPokemon = null;
			attackerPokemon = new ArrayList<>();
			userPokemon = new ArrayList<>();
		}
		
		public Stage(String difficulties,String pokemon1, String pokemon2, String pokemon3, int DifficultiesValue, ArrayList<Pokemon> attackerPokemon, ArrayList<Pokemon> userPokemon) {
			this.difficulties = difficulties;
			this.pokemon1 = pokemon1;
			this.pokemon2 = pokemon2;
			this.pokemon3 = pokemon3;
			this.DifficultiesValue = DifficultiesValue;
			this.attackerPokemon = attackerPokemon;	
			this.userPokemon = userPokemon;
		}
		
		public String getDifficulties() {
			return difficulties;
		}

		public String getPokemon1() {
			return pokemon1;
		}

		public String getPokemon2() {
			return pokemon2;
		}

		public String getPokemon3() {
			return pokemon3;
		}

		public int getDifficultiesValue() {
			return DifficultiesValue;
		}
		
		public ArrayList<Pokemon> getAttackerPokemon() {
			return attackerPokemon;
		}
		
		public ArrayList<Pokemon> getUserPokemon() {
			return userPokemon;
		}

		public static ArrayList<Pokemon> getPokemonlist() {
			return pokemonlist;
		}

		public String toString() {
			return String.format("Difficulties: %s\nPokemon 1: %s\nPokemon 2: %s\nPokemon 3: %s", difficulties, pokemon1, pokemon2, pokemon3);
		}
}