import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
public class Database {
	private String PokemontextFile;
	private String UserTextFile;
	private String TopScoreList;
	Scanner input;	
	
	public Database() {
		PokemontextFile = "C:\\Users\\sumho\\OneDrive - Sunway Education Group\\Test file\\Pokemon Ga Ole - G23\\FinalVersion\\POKEMON.txt";
		UserTextFile = "C:\\Users\\sumho\\OneDrive - Sunway Education Group\\Test file\\Pokemon Ga Ole - G23\\FinalVersion\\TopScoreList.txt";
		TopScoreList = "C:\\Users\\sumho\\OneDrive - Sunway Education Group\\Test file\\Pokemon Ga Ole - G23\\FinalVersion\\UserProfile.txt";
	}
	
	public String getPokemonTextFile() {
		return PokemontextFile;
	}

	public String getUserTextFile() {
		return UserTextFile;
	}
	
	public String getTopScoreList() {
		return TopScoreList;
	}

	//read pokemon file method 
    public void readPokemonFile(ArrayList<Pokemon> pokemonlist) {
		String line;

		try (BufferedReader br = new BufferedReader(new FileReader(getPokemonTextFile()))) {
			while ((line = br.readLine()) != null) {
				String[] details = line.split("\\|");
					if (details.length != 7) {
							System.err.println("Skipping invalid line: " + line);
						continue; // Skip lines that do not have exactly 7 attributes
					}
		try {
			String name = details[0];
			int hp = Integer.parseInt(details[1]);
			String atkMove = details[2];
			String defMove = details[3];
			int dmgOfAtkMove = Integer.parseInt(details[4]);
			int valueOfDefMove = Integer.parseInt(details[5]);
			String element = details[6];
			
			switch (element) {
				case "Fire":
					pokemonlist.add(new FirePokemon(name, hp, atkMove, defMove, dmgOfAtkMove, valueOfDefMove));
				 break;
				 
				case "Water":
					pokemonlist.add(new WaterPokemon(name, hp, atkMove, defMove, dmgOfAtkMove, valueOfDefMove));
				 break;
				 
				case "Ice":
					pokemonlist.add(new IcePokemon(name, hp, atkMove, defMove, dmgOfAtkMove, valueOfDefMove));
				 break;
				 
				case "Grass":
					pokemonlist.add(new GrassPokemon(name, hp, atkMove, defMove, dmgOfAtkMove, valueOfDefMove));
				 break;
				 
				case "Lightning": 
					pokemonlist.add(new LightningPokemon(name, hp, atkMove, defMove, dmgOfAtkMove, valueOfDefMove));
				 break;
				 
				case "Poison":
					pokemonlist.add(new PoisonPokemon(name, hp, atkMove, defMove, dmgOfAtkMove, valueOfDefMove));
					break;
					
				case "Bug":
					pokemonlist.add(new BugPokemon(name, hp, atkMove, defMove, dmgOfAtkMove, valueOfDefMove));
					break;
					
				case "Ground":
					pokemonlist.add(new GroundPokemon(name, hp, atkMove, defMove, dmgOfAtkMove, valueOfDefMove));
					break;
					
				default:
					System.err.println("Unknown element: " + element);
				 break;
				}
			} catch (NumberFormatException e) { //when parse a string into numeric type but dont have the appropriate format of numeric type 
				System.err.println("Error parsing numbers from line: " + line + " - " + e.getMessage());
				}
			}
		}
		catch (IOException e) {
			System.err.println("Error reading file: " + e.getMessage());
		}
    }
    
    public void writeUserTextFile(ArrayList<UserAccount> userlist) {
    	try(BufferedWriter writer = new BufferedWriter(new FileWriter(getUserTextFile(), true))) { //true will write in the end instead of begining
    		for(UserAccount user: userlist) { 
    			writer.write("UserID: " + user.getUserID() + " " + user.getScore());
    			writer.newLine();
    		}
		}
		catch (IOException e) {
			System.out.println("Error writing data to the file: " + e.getMessage());
		}
    }

    	
	public void readUserTextFile(ArrayList<UserAccount> userlist) {
		String line;

		try (BufferedReader br = new BufferedReader(new FileReader(getUserTextFile()))) {
			while ((line = br.readLine()) != null) {
				String[] userdetails = line.split("\\s+");
					if (userdetails.length != 3) {
							System.err.println("Skipping invalid line: " + line);
						continue; // Skip lines that do not have exactly 7 attributes
					}
		try {
			int userid = Integer.parseInt(userdetails[1]);
			int score = Integer.parseInt(userdetails[2]);
			userlist.add(new UserAccount(userid, score));
			} 
		catch (NumberFormatException e) {
				System.err.println("Error parsing numbers from line: " + line + " - " + e.getMessage());
				}
			}
		}

		catch (IOException e) {
			System.err.println("Error reading file: " + e.getMessage());
		}
	}
	
	public void clearUserTextFile() {
		try (PrintWriter writer = new PrintWriter(new File(getUserTextFile()))) {
            writer.print("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
	
	public void writeTopScoreList(ArrayList<UserAccount> topScoreList) {
    	try(BufferedWriter writer = new BufferedWriter(new FileWriter(getTopScoreList(), true))) {
    		for(UserAccount user: topScoreList) {
    			writer.write("UserID: " + user.getUserID() + " " + user.getScore());
    			writer.newLine();
    		}
		}
		catch (IOException e) {
			System.out.println("Error writing data to the file: " + e.getMessage());
		}
    }
	
	public void readTopScoreList(ArrayList<UserAccount> topScoreList) {
		String line;

		try (BufferedReader br = new BufferedReader(new FileReader(getTopScoreList()))) {
			while ((line = br.readLine()) != null) {
				String[] userdetails = line.split("\\s+");
					if (userdetails.length != 3) {
							System.err.println("Skipping invalid line: " + line);
						continue; // Skip lines that do not have exactly 7 attributes
					}
		try {
			int userid = Integer.parseInt(userdetails[1]);
			int score = Integer.parseInt(userdetails[2]);
			topScoreList.add(new UserAccount(userid, score));
			} 
		catch (NumberFormatException e) {
				System.err.println("Error parsing numbers from line: " + line + " - " + e.getMessage());
				}
			}
		}

		catch (IOException e) {
			System.err.println("Error reading file: " + e.getMessage());
		}
	}
	
	public void clearTopScoreList() {
		try (PrintWriter writer = new PrintWriter(new File(getTopScoreList()))) {
            writer.print("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}    	
    	


