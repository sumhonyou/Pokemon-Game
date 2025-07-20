import java.util.ArrayList;
import java.util.*;
import java.util.Random;
import java.util.Scanner;

public class UserAccount {
	
	private int userID;
	private int score;
	private static final int USER_ID_LENGTH = 6;
	private Database userProfile;
	private static ArrayList<UserAccount> userlist;
	
	public UserAccount() {
		userID = 0;
		score = 0;
		userProfile = new Database();
		userlist = new ArrayList<>();
		userProfile.readUserTextFile(userlist);
	}
	
	public UserAccount(int userID, int score) {
		this.userID = userID;
		this.score = score;
	}
	
	public int getUserID(){
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<UserAccount> getUserlist() {
		return userlist;
	}

	// Check if a user ID exists in the database
	public boolean checkUserID(int userID) {
		for(UserAccount user: userlist) {
			if(userID == user.getUserID()) {
				return true;
			}
		}
		return false;
	}

	
	// Generate a random 6-digits user ID that is not existing before
	public int generateRandomUserID() {
		Random random = new Random();
		int randomUserID;
		do{
			// Generate random 6-digits user ID from 100000 to 999999
			randomUserID = 100000 + random.nextInt(900000);
		}while(checkUserID(randomUserID));
		return randomUserID;
	}
	
	// Validate if the user ID is a 6-digits number
	public static boolean validUserID(String userID) {
		if(userID.length() != USER_ID_LENGTH) {
			return false;
		}
		
		try {
			Integer.parseInt(userID);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
	
	// Register a new user account
	public int newUserRegistration() {
		int newUserID = generateRandomUserID();
		setUserID(newUserID);
		System.out.println("Congratulations on joining Pokemon Ga-Ole game, new user! Your user ID is " + getUserID() + ".\n");
		return getUserID();
	}
	
	// User login
	public int userLogin(Scanner scanner) {
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		System.out.println("|           User Login            |");
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		
		while(true) {
			System.out.print("Enter user ID: ");
			String input = scanner.nextLine().trim().toLowerCase();
			
			if(input.equals("new")){
				return newUserRegistration();
			}
			
			if(validUserID(input)) {
				int inputID = Integer.parseInt(input);
				
				if(checkUserID(inputID)) {
					setUserID(inputID);
					System.out.println("Welcome back to Pokemon Ga-Ole game, user " + inputID + "! Enjoy your game!\n");
					return getUserID();
				}
				else {
					System.out.println("User ID " + inputID + " is not found! Please enter you user ID again or enter 'new' to register!");
				}
			}
				
			else {
				System.out.println("Invalid user ID format! Please enter a 6-digits number or enter 'new' to register!");
			}
		}
	}
	
    public static Comparator<UserAccount> ScoreComparator = new Comparator<UserAccount>() {
        public int compare(UserAccount u1, UserAccount u2) {
            return Integer.compare(u2.getScore(), u1.getScore()); // Descending order
        }
    };
	
	@Override
	public String toString() {
		return String.format("UserAccount [userID=%s, score=%s]", userID, score);
	}
	
	
}
