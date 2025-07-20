
public class Screen {
	//Represents screen of the game console
		//display message without a carriage return
		public void displayMessage(String message) {
			System.out.print(message);
		}
		
		//display message with a carriage return
		public void displayMessageLine(String message) {
			System.out.println(message);
		}
		
		//display the pokemon names
		public void displayPokemon(Pokemon pokemon) {
			System.out.println(pokemon);
		}
		
		public void displayStage1Details(Stage1 message) {
			System.out.println(message + "\n");
		}
		
		public void displayStage2Details(Stage2 message) {
			System.out.println(message + "\n");
		}
		
		public void displayStage3Details(Stage3 message) {
			System.out.println(message + "\n");
		}
		
		public void displayErrorMessage(String message) {
			System.err.println(message);
		}
		
	}