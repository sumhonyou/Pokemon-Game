import java.util.InputMismatchException;
import java.util.Scanner;
public class Input {
		private Scanner Input;//reads data from the console
		
		public Input() {
			Input = new Scanner(System.in);//initialize the Scanner
		}
		
		public int getInputInteger() {
			while(true) {
				try {
					return Input.nextInt();//return integer value entered by user
				}
				catch(InputMismatchException e){
					System.err.print("Invalid input. Please enter a number: ");
	                Input.next(); // Clear the invalid input
	               

				}
			}
			
		}
		
		public String getInputString() {
			return Input.next();//return String value entered by user
		}
		
		public String getInputStringNextLine() {
			return Input.nextLine();
		}
	}