import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

//This is the controller class
public class Game {
	private Screen screen;
	private Input input;
	private Stage1 stage1;
	private Stage2 stage2;
	private Stage3 stage3;
	private Battle battle;
	private Database db;
	private Scanner scanner;
	private UserAccount usr;
	
	Random random;
	int chooseBall;
	boolean userExist = false;//create a false boolean variable to check if the user data exist in the userlist arraylist or not.
	String pokemonCatchSuccess;
	
	ArrayList<Pokemon> pokemonlist = new ArrayList<Pokemon>(); //Create array list for pokemon objects
	ArrayList<Pokeball> ball = new ArrayList<>(); //Create array list for pokeball objects
	ArrayList<UserAccount> userlist = new ArrayList<>(); //create array list for users objects
	ArrayList<UserAccount> previoustopScoreList = new ArrayList<>();//create array list for top scorers
	ArrayList<UserAccount> currenttopScoreList = new ArrayList<>();//create array list for top scorers
	
	//Declaration for pokemon switch case
	private final int POKEMON1 = 1;
	private final int POKEMON2 = 2;
	private final int POKEMON3 = 3;
	//Create integer variable called pokemon to be put into switch case for choosing pokemon to catch
	private int pokemon;
	
	//Empty constructor
	public Game() {
		screen = new Screen();
		input = new Input();
		scanner = new Scanner(System.in);
		
		db = new Database();
		db.readPokemonFile(pokemonlist);//read pokemon text file and insert the data into pokemonlist arraylist
		db.readUserTextFile(userlist);//read user text file and insert the data into userlist arraylist
		db.readTopScoreList(previoustopScoreList);//read the top score list text file and insert the data into topScoreList arraylist

		stage1 = new Stage1();//create object stage1 for Stage1 class
		stage2 = new Stage2();//create object stage2 for Stage2 class
		stage3 = new Stage3();//create object stage3 for Stage3 class
		
		ball.add(new Normalball());//add normal ball into ball arraylist created above
		ball.add(new Greatball());//add great ball into ball arraylist created above
		ball.add(new Ultraball());//add ultra ball into ball arraylist created above
		ball.add(new Masterball());//add master ball into ball arraylist created above
		
		random = new Random();
	}
	
	//Create start method to be called in the driver class to start the game
		public void start() {
		int user = 0;//create variable user and give it a value 0
		usr = new UserAccount();
		while(user == 0) {//Always start the game with the user id which is 0
			System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
			System.out.println("| Welcome to Pokemon Ga-Ole Game! |");
			System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
			System.out.print("Are you a new user? (Y/N): ");//Ask the user whether he/she is new user or existing user
			String option = input.getInputStringNextLine().trim().toLowerCase();
			
			switch(option) {
			case "y"://if it is a new user, call the newUserRegistration method from the userAccount class
				user = usr.newUserRegistration();
				screen.displayMessageLine("**********************************************");
				screen.displayMessageLine("************Previous Top Scorers************");
				screen.displayMessageLine("**********************************************");
				for (int i = 0; i < Math.min(5, previoustopScoreList.size()); i++) {//for loop to display the previous top 5 users' userid and their respective score.
		            screen.displayMessageLine((i+1) + ".UserID: " + previoustopScoreList.get(i).getUserID() + "   Score: " + previoustopScoreList.get(i).getScore());
		        }
				screen.displayMessage(" ");
				db.clearUserTextFile();
				db.clearTopScoreList();
				break;
			case "n"://if it is an existing user, caal the userLogin method from the userAccount class
				user = usr.userLogin(scanner);
				screen.displayMessageLine("**********************************************");
				screen.displayMessageLine("**************Previous Top Scorers************");
				screen.displayMessageLine("**********************************************");
				for (int i = 0; i < Math.min(5, previoustopScoreList.size()); i++) {//for loop to display the previous top 5 users' userid and their respective score.
		            screen.displayMessageLine((i+1) + ".UserID: " + previoustopScoreList.get(i).getUserID() + "   Score: " + previoustopScoreList.get(i).getScore());
		        }
				screen.displayMessage(" ");
				db.clearUserTextFile();
				db.clearTopScoreList();
				break;
			
			default://if the user enter values other than y or n, display error message and prompt user to enter the option again.
				System.err.println("Invalid option! Please enter either 'Y' or 'N'!");
				break;
			}
		}

		
		
		
//***********************************************************************************		
	//***********************************************************************************
	screen.displayMessageLine("\nPlease choose one of the stage to continue.");
	screen.displayMessage("1. ");
    screen.displayStage1Details(stage1);//display stage1 details
    screen.displayMessage("2. ");
    screen.displayStage2Details(stage2);//display stage2 details
    screen.displayMessage("3. ");
    screen.displayStage3Details(stage3);//display stage3 details
    
    int stage = 0;
    boolean validInput = false;
    		while (true) {
    		screen.displayMessage("Choose one of the stage[1-3]: ");//prompt the user to choose one of the stage to begin the game
            stage = input.getInputInteger();
            if (stage >= 1 && stage <= 3) {
            	chooseBall = random.nextInt(4);//create a random variable for randomly choosing pokeball for users.
                switch (stage) {//create a switch case for each stage
                    case 1://case for stage 1
                        validInput = true;
                        screen.displayStage1Details(stage1);//display stage1 details
                        screen.displayMessageLine(" ");

                        while (true) {
                            screen.displayErrorMessage("Choose 1 pokemon to catch: ");//prompt user to choose 1 out of 3 pokemon in stage 1
                            pokemon = input.getInputInteger();

                            if (pokemon >= 1 && pokemon <= 3) {
                                switch (pokemon) {//create a switch case for each pokemon
                                    case POKEMON1://since pokemon1 to pokemon3 has the same code, we can reduce the number of lines of code
                                        		  //by writing it at the pokemon3 case only.
                                    case POKEMON2:

                                    case POKEMON3:
                                        pokemonCatchSuccess = ball.get(chooseBall).Catch() ? "Success" : "Unsuccessful";//use the random variable chooseball to determine which
                                        //index of ball get by the user and call the catch
                                        //method from Pokeball class to determine the success rate
                                        //of catching the pokemon chose.
                                        screen.displayMessageLine("\nYou have gotten [" + ball.get(chooseBall).getBallName() + "]");//display ball name
                                        screen.displayMessageLine(pokemonCatchSuccess);
                                        break;
                                }

                                if (pokemonCatchSuccess.equals("Success")) {
                                    screen.displayMessageLine("Congratulations, you have gotten a new pokemon card!\n\n");//display this message if user
                                    //successfully caught the pokemon

                                    screen.displayMessageLine("!!! Battle Time !!!\n");//After successfully catching the pokemon, it's battle time
                                    battle = new Battle(stage1.getAttackerPokemon());//Insert the stage1 attacker pokemon arraylist into the battle class complete constructor
                                    screen.displayMessageLine("!!! Pokemon roar !!!");//Pokemon will roar once finish catching the wild pokemon
                                    battle.roar();//Call the roar method from battle class to determine the roar level and the roar level will determine which two attacker
                                    //pokemon will appear for fight.

                                    //get the attacker pokemons name
                                    screen.displayErrorMessage("[" + battle.getEnemyPokemon1().getName() + " and " + battle.getEnemyPokemon2().getName() + " has appeared for fight]");

                                    screen.displayMessageLine("\nThis is your pokemon:");//Provide 4 user pokemons for user to chose

                                    for(int i=0; i<stage1.getUserPokemon().size();i++) {
                                    	screen.displayMessageLine((i+1) + ". " + stage1.getUserPokemon().get(i));
                                    }

                                    int i = 0;
                                    int y = 0;
                                    while (true) {
                                        screen.displayMessage("Choose a pokemon to go for fight[1-4]: ");//prompt user to choose first pokemon
                                        i = input.getInputInteger();
                                        if (i >= 1 && i <= 4) {
                                            break;
                                        } else {
                                            screen.displayErrorMessage("Invalid number. Please input number between 1 and 4!\n");
                                        }
                                    }

                                    while (true) {
                                        screen.displayMessage("Choose another pokemon for fight[1-4]: ");//prompt user to choose second pokemon
                                        y = input.getInputInteger();
                                        if ((i != y) && (y >= 1 && y <= 4)) {
                                            break;
                                        } else {
                                            screen.displayErrorMessage("\nPlease input number between 1 to 4, except " + i + ".");
                                        }
                                    }

                                    screen.displayMessageLine("");
                                    screen.displayMessageLine("!!! Ready for attack !!!\n");//its time to attack once user choose two pokemons to go for attack
                                    battle.userPokemon(stage1.getUserPokemon().get(i - 1), stage1.getUserPokemon().get(y - 1));//call the first and second pokemon that user
                                    //choose into the userPokemon method from battle class

                                    //exit the loop if either the attacker or user pokemon hp is below zero
                                    while ((battle.getUserPokemon1().getHp() > 0 && battle.getUserPokemon2().getHp() > 0 && battle.getEnemyPokemon1().getHp() > 0 && battle.getEnemyPokemon2().getHp() > 0)) {
                                        int choice = 0;
                                        while (true) {
                                            screen.displayMessageLine("Choose 1st or 2nd enemy pokemon to attack[1/2]: ");//prompt user to choose whether to attack enemy 1st or 2nd pokemon
                                            choice = input.getInputInteger();
                                            screen.displayMessage("");
                                            if (choice >= 1 && choice <= 2) {
                                                screen.displayMessageLine("");

                                                switch (choice) {
                                                case 1://case for enemy first pokemon
                                                	if(battle.checkUserPokemon1Hp() == 1 || battle.checkUserPokemon2Hp() == 1) {
                                                		battle.AttackEnemyPokemon1();
                                                        if (battle.checkEnemyPokemon1Hp() == 1) {
                                                            battle.AttackUserPokemon1();
                                                        		if(battle.checkUserPokemon1Hp() == 0) {
                                                        			screen.displayErrorMessage("[User Pokemon 1 fainted]");
                                                        		}
                                                        		if(battle.checkUserPokemon2Hp() == 0) {
                                                        			screen.displayErrorMessage("[User Pokemon 2 fainted]");
                                                        		}
                                                        	}
                                                        else {
                                                            screen.displayErrorMessage("[Enemy Pokemon 1 fainted.]");
                                                        }
                                                	}
                                                	else if(battle.checkUserPokemon1Hp()==0 && battle.checkEnemyPokemon2Hp()==0) {
                                                		battle.AttackEnemyPokemon1();
                                                		if (battle.checkEnemyPokemon1Hp() == 1) {
                                                            battle.AttackUserPokemon1();
                                                            if(battle.checkUserPokemon1Hp() == 0) {
                                                    			screen.displayErrorMessage("[User Pokemon 1 fainted]");
                                                    		}
                                                    		if(battle.checkUserPokemon2Hp() == 0) {
                                                    			screen.displayErrorMessage("[User Pokemon 2 fainted]");
                                                    		}
                                                        } else {
                                                            screen.displayErrorMessage("[Enemy Pokemon 1 fainted.]");
                                                        }
                                                	}
                                                    break;
                                                case 2://case for enemy second pokemon
                                                	if(battle.checkUserPokemon2Hp() == 1 || battle.checkUserPokemon1Hp() == 1) {
                                                		battle.AttackEnemyPokemon2();
                                                        if (battle.checkEnemyPokemon2Hp() == 1) {
                                                            battle.AttackUserPokemon1();
                                                            if(battle.checkUserPokemon1Hp() == 0) {
                                                    			screen.displayErrorMessage("[User Pokemon 1 fainted]");
                                                    		}
                                                    		if(battle.checkUserPokemon2Hp() == 0) {
                                                    			screen.displayErrorMessage("[User Pokemon 2 fainted]");
                                                    		}
                                                        } else {
                                                            screen.displayErrorMessage("[Enemy Pokemon 2 Fainted]");
                                                        }
                                                	}
                                                	else if(battle.checkUserPokemon2Hp()==0 && battle.checkEnemyPokemon1Hp()==0) {
                                                		battle.AttackEnemyPokemon2();
                                                		if (battle.checkEnemyPokemon2Hp() == 1) {
                                                            battle.AttackUserPokemon1();
                                                            if(battle.checkUserPokemon1Hp() == 0) {
                                                    			screen.displayErrorMessage("[User Pokemon 1 fainted]");
                                                    		}
                                                    		if(battle.checkUserPokemon2Hp() == 0) {
                                                    			screen.displayErrorMessage("[User Pokemon 2 fainted]");
                                                    		}
                                                        } else {
                                                            screen.displayErrorMessage("[Enemy Pokemon 2 fainted.]");
                                                        }
                                                	}
                                                    break;
                                                }

                                                //if enemy pokemon hp is below zero, it means the pokemon got defeated
                                                if (battle.getEnemyPokemon1().getHp() <= 0 && battle.getEnemyPokemon2().getHp() <= 0) {
                                                    screen.displayMessageLine("\nYou Won!");//alert user that he/she won the game
                                                    battle.calculateScore(battle.getWin(), stage1.getDifficultiesValue());//calculate the score for the game
                                                    screen.displayMessageLine("Your score is " + battle.getScore());//display the score
                                                    for (UserAccount userdata : userlist) {//an enhanced for loop to loop over all userdata(object) across the userlist arraylist
                                                        if (usr.getUserID() == userdata.getUserID()) {//check if the user id exist in the arraylist or not
                                                            userdata.setScore(userdata.getScore() + battle.getScore());//if the user id exist, add the latest score with 
                                                            //the previous score of the user.
                                                            userExist = true;//return true to tell the system that the user data exist in the arraylist
                                                            break;
                                                        }
                                                    }
                                                    if (!userExist) {//if the user data does not exist in the arraylist, 
                                                        //add the data by calling the complete constructor of userAccount class
                                                        //and insert the arguments into the complete construtor and add the data into the arraylist.
                                                        userlist.add(new UserAccount(user, (usr.getScore() + battle.getScore())));
                                                    }
                                                    db.writeUserTextFile(userlist);//write the user data into the user text file.
                                                    screen.displayMessageLine("\n\n!!! Catch Time !!!");//after defeating the enemy pokemon, it's time to catch the pokemon
                                                    pokemonCatchSuccess = ball.get(chooseBall).Catch() ? "Successful" : "Unsuccessful";//determine whether the catch is successful/unsuccessful
                                                    screen.displayMessageLine(pokemonCatchSuccess);
                                                    screen.displayErrorMessage("[You have " + (pokemonCatchSuccess).toLowerCase() + "ly use " + ball.get(chooseBall).getBallName() + " to catch " + battle.getEnemyPokemon1().getName() + " and " + battle.getEnemyPokemon2().getName() + "]");
                                                    break;
                                                }
                                                //if the user pokemon hp is below 0, prompt that the user has lose
                                                else if (battle.getUserPokemon1().getHp() <= 0 && battle.getUserPokemon2().getHp() <= 0) {
                                                    screen.displayMessageLine("\nYou lose!");
                                                    battle.calculateScore(battle.getLose(), stage1.getDifficultiesValue());//calculate the score if the user lose.
                                                    screen.displayMessageLine("Your score is " + battle.getScore());//display the score.
                                                    for (UserAccount userdata : userlist) {//an enhanced for loop to loop over all userdata(object) across the userlist arraylist
                                                        if (usr.getUserID() == userdata.getUserID()) {//check if the user id exist in the arraylist or not
                                                            userdata.setScore(userdata.getScore() + battle.getScore());//if the user id exist, add the latest score with 
                                                            //the previous score of the user.
                                                            userExist = true;//return true to tell the system that the user data exist in the arraylist
                                                            break;
                                                        }
                                                    }
                                                    if (!userExist) {//if the user data does not exist in the arraylist, 
                                                        //add the data by calling the complete constructor of userAccount class
                                                        //and insert the arguments into the complete construtor and add the data into the arraylist.
                                                        userlist.add(new UserAccount(user, (usr.getScore() + battle.getScore())));
                                                    }
                                                    db.writeUserTextFile(userlist);//write the user data into the user text file.
                                                    break;
                                                }
                                            } else {
                                                screen.displayErrorMessage("\nInvalid number. Please input number between 1 and 2!");
                                            }
                                        }
                                    }
                                } else {//if the user unsuccessfully catch the pokemon since the catch attempt at the beginning of the game,
                                    //prompt user to try again next time.
                                    screen.displayMessageLine("Try again next time\n");
                                    db.writeUserTextFile(userlist);//save all the user data from the user arraylist
                                    //into the user text file since we deleted it when the user start the game.
                                }
                                break;
                                //The concept for both stage 2 and stage 3 is the same as the concept explained above.   
                            } else {
                                screen.displayErrorMessage("\nInvalid number. Please input number between 1 and 3!");
                            }
                        }
                        break;
		//*********************************************************************************************			
                    case 2:
                    	validInput = true;
                        screen.displayStage2Details(stage2);
                        screen.displayMessageLine(" ");

                        while (true) {
                            screen.displayErrorMessage("Choose 1 pokemon to catch: ");
                            pokemon = input.getInputInteger();

                            if (pokemon >= 1 && pokemon <= 3) {
                                switch (pokemon) {
                                    case POKEMON1:
                                    case POKEMON2:

                                    case POKEMON3:
                                        pokemonCatchSuccess = ball.get(chooseBall).Catch() ? "Success" : "Unsuccessful";
                                        
                                        screen.displayMessageLine("\nYou have gotten [" + ball.get(chooseBall).getBallName() + "]");
                                        screen.displayMessageLine(pokemonCatchSuccess);
                                        break;
                                }

                                if (pokemonCatchSuccess.equals("Success")) {
                                    screen.displayMessageLine("Congratulations, you have gotten a new pokemon card!\n\n");
                                    screen.displayMessageLine("!!! Battle Time !!!\n");
                                    battle = new Battle(stage2.getAttackerPokemon());
                                    screen.displayMessageLine("!!! Pokemon roar !!!");
                                    battle.roar();
                                    screen.displayErrorMessage("[" + battle.getEnemyPokemon1().getName() + " and " + battle.getEnemyPokemon2().getName() + " has appeared for fight]");

                                    screen.displayMessageLine("\nThis is your pokemon:");
                                    
                                    for(int i=0; i<stage2.getUserPokemon().size();i++) {
                                    	screen.displayMessageLine((i+1) + ". " + stage2.getUserPokemon().get(i));
                                    }
                                    int i = 0;
                                    int y = 0;
                                    while (true) {
                                        screen.displayMessage("Choose a pokemon to go for fight[1-4]: ");
                                        i = input.getInputInteger();
                                        if (i >= 1 && i <= 4) {
                                            break;
                                        } else {
                                            screen.displayErrorMessage("Invalid number. Please input number between 1 and 4!\n");
                                        }
                                    }

                                    while (true) {
                                        screen.displayMessage("Choose another pokemon for fight[1-4]: ");
                                        y = input.getInputInteger();
                                        if ((i != y) && (y >= 1 && y <= 4)) {
                                            break;
                                        } else {
                                            screen.displayErrorMessage("\nPlease input number between 1 to 4, except " + i + ".");
                                        }
                                    }

                                    screen.displayMessage("");
                                    screen.displayMessageLine("!!! Ready for attack !!!\n");
                                    battle.userPokemon(stage2.getUserPokemon().get(i - 1), stage2.getUserPokemon().get(y - 1));
                                    
                                    while ((battle.getUserPokemon1().getHp() > 0 && battle.getUserPokemon2().getHp() > 0 && battle.getEnemyPokemon1().getHp() > 0 && battle.getEnemyPokemon2().getHp() > 0)) {
                                        int choice = 0;
                                        while (true) {
                                            screen.displayMessage("Choose 1st or 2nd enemy pokemon to attack[1/2]: ");
                                            choice = input.getInputInteger();
                                            screen.displayMessage("");
                                            if (choice >= 1 && choice <= 2) {
                                                screen.displayMessageLine("");

                                                switch (choice) {
                                                    case 1://case for enemy first pokemon
                                                    	if(battle.checkUserPokemon1Hp() == 1 || battle.checkUserPokemon2Hp() == 1) {
                                                    		battle.AttackEnemyPokemon1();
                                                            if (battle.checkEnemyPokemon1Hp() == 1) {
                                                                battle.AttackUserPokemon1();
                                                            		if(battle.checkUserPokemon1Hp() == 0) {
                                                            			screen.displayErrorMessage("[User Pokemon 1 fainted]");
                                                            		}
                                                            		if(battle.checkUserPokemon2Hp() == 0) {
                                                            			screen.displayErrorMessage("[User Pokemon 2 fainted]");
                                                            		}
                                                            	}
                                                            else {
                                                                screen.displayErrorMessage("[Enemy Pokemon 1 fainted.]");
                                                            }
                                                    	}
                                                    	else if(battle.checkUserPokemon1Hp()==0 && battle.checkEnemyPokemon2Hp()==0) {
                                                    		battle.AttackEnemyPokemon1();
                                                    		if (battle.checkEnemyPokemon1Hp() == 1) {
                                                                battle.AttackUserPokemon1();
                                                                if(battle.checkUserPokemon1Hp() == 0) {
                                                        			screen.displayErrorMessage("[User Pokemon 1 fainted]");
                                                        		}
                                                        		if(battle.checkUserPokemon2Hp() == 0) {
                                                        			screen.displayErrorMessage("[User Pokemon 2 fainted]");
                                                        		}
                                                            } else {
                                                                screen.displayErrorMessage("[Enemy Pokemon 1 fainted.]");
                                                            }
                                                    	}
                                                        break;
                                                    case 2://case for enemy second pokemon
                                                    	if(battle.checkUserPokemon2Hp() == 1 || battle.checkUserPokemon1Hp() == 1) {
                                                    		battle.AttackEnemyPokemon2();
                                                            if (battle.checkEnemyPokemon2Hp() == 1) {
                                                                battle.AttackUserPokemon1();
                                                                if(battle.checkUserPokemon1Hp() == 0) {
                                                        			screen.displayErrorMessage("[User Pokemon 1 fainted]");
                                                        		}
                                                        		if(battle.checkUserPokemon2Hp() == 0) {
                                                        			screen.displayErrorMessage("[User Pokemon 2 fainted]");
                                                        		}
                                                            } else {
                                                                screen.displayErrorMessage("[Enemy Pokemon 2 Fainted]");
                                                            }
                                                    	}
                                                    	else if(battle.checkUserPokemon2Hp()==0 && battle.checkEnemyPokemon1Hp()==0) {
                                                    		battle.AttackEnemyPokemon2();
                                                    		if (battle.checkEnemyPokemon2Hp() == 1) {
                                                                battle.AttackUserPokemon1();
                                                                if(battle.checkUserPokemon1Hp() == 0) {
                                                        			screen.displayErrorMessage("[User Pokemon 1 fainted]");
                                                        		}
                                                        		if(battle.checkUserPokemon2Hp() == 0) {
                                                        			screen.displayErrorMessage("[User Pokemon 2 fainted]");
                                                        		}
                                                            } else {
                                                                screen.displayErrorMessage("[Enemy Pokemon 2 fainted.]");
                                                            }
                                                    	}
                                                    	break;
                                                }

                                                if (battle.getEnemyPokemon1().getHp() <= 0 && battle.getEnemyPokemon2().getHp() <= 0) {
                                                    screen.displayMessageLine("\nYou Won!");
                                                    battle.calculateScore(battle.getWin(), stage1.getDifficultiesValue());
                                                    screen.displayMessageLine("Your score is " + battle.getScore());
                                                    for (UserAccount userdata : userlist) {
                                                        if (usr.getUserID() == userdata.getUserID()) {
                                                            userdata.setScore(userdata.getScore() + battle.getScore());
                                                            userExist = true;
                                                            break;
                                                        }
                                                    }
                                                    if (!userExist) {
                                                        userlist.add(new UserAccount(user, (usr.getScore() + battle.getScore())));
                                                    }
                                                    db.writeUserTextFile(userlist);
                                                    screen.displayMessageLine("\n\n!!! Catch Time !!!");
                                                    pokemonCatchSuccess = ball.get(chooseBall).Catch() ? "Successful" : "Unsuccessful";
                                                    screen.displayMessageLine(pokemonCatchSuccess);
                                                    screen.displayErrorMessage("[You have " + (pokemonCatchSuccess).toLowerCase() + "ly use " + ball.get(chooseBall).getBallName() + " to catch " + battle.getEnemyPokemon1().getName() + " and " + battle.getEnemyPokemon2().getName() + "]");
                                                    break;
                                                }
                                               
                                                else if (battle.getUserPokemon1().getHp() <= 0 && battle.getUserPokemon2().getHp() <= 0) {
                                                    screen.displayMessageLine("\nYou lose!");
                                                    battle.calculateScore(battle.getLose(), stage2.getDifficultiesValue());
                                                    screen.displayMessageLine("Your score is " + battle.getScore());
                                                    for (UserAccount userdata : userlist) {
                                                        if (usr.getUserID() == userdata.getUserID()) {
                                                            userdata.setScore(userdata.getScore() + battle.getScore());
                                                            userExist = true;
                                                            break;
                                                        }
                                                    }
                                                    if (!userExist) {
                                                        userlist.add(new UserAccount(user, (usr.getScore() + battle.getScore())));
                                                    }
                                                    db.writeUserTextFile(userlist);
                                                    break;
                                                }
                                            } else {
                                                screen.displayErrorMessage("Invalid number. Please input number between 1 and 2!\n");
                                            }
                                        }
                                    }
                                } else {
                                    screen.displayMessageLine("Try again next time");
                                    db.writeUserTextFile(userlist);
                                }
                                break; 
                            } else {
                                screen.displayErrorMessage("Invalid number. Please input number between 1 and 3!\n");
                            }
                        }
                        break;

						
					
		//*********************************************************************************************			
		
                    case 3:
                    	validInput = true;
                        screen.displayStage3Details(stage3);
                        screen.displayMessageLine(" ");

                        while (true) {
                            screen.displayErrorMessage("Choose 1 pokemon to catch: ");
                            pokemon = input.getInputInteger();

                            if (pokemon >= 1 && pokemon <= 3) {
                                switch (pokemon) {
                                    case POKEMON1:
                                    case POKEMON2:

                                    case POKEMON3:
                                        pokemonCatchSuccess = ball.get(chooseBall).Catch() ? "Success" : "Unsuccessful";
                                        
                                        screen.displayMessageLine("\nYou have gotten [" + ball.get(chooseBall).getBallName() + "]");
                                        screen.displayMessageLine(pokemonCatchSuccess);
                                        break;
                                }

                                if (pokemonCatchSuccess.equals("Success")) {
                                    screen.displayMessageLine("Congratulations, you have gotten a new pokemon card!\n\n");
                                    screen.displayMessageLine("!!! Battle Time !!!\n");
                                    battle = new Battle(stage3.getAttackerPokemon());
                                    screen.displayMessageLine("!!! Pokemon roar !!!");
                                    battle.roar();
                                    screen.displayErrorMessage("[" + battle.getEnemyPokemon1().getName() + " and " + battle.getEnemyPokemon2().getName() + " has appeared for fight]");

                                    screen.displayMessageLine("\nThis is your pokemon:");
                                    for(int i=0; i<stage3.getUserPokemon().size();i++) {
                                    	screen.displayMessageLine((i+1) + ". " + stage3.getUserPokemon().get(i));
                                    }

                                    int i = 0;
                                    int y = 0;
                                    while (true) {
                                        screen.displayMessage("Choose a pokemon to go for fight[1-4]: ");
                                        i = input.getInputInteger();
                                        if (i >= 1 && i <= 4) {
                                            break;
                                        } else {
                                            screen.displayErrorMessage("Invalid number. Please input number between 1 and 4!\n");
                                        }
                                    }

                                    while (true) {
                                        screen.displayMessage("Choose another pokemon for fight[1-4]: ");
                                        y = input.getInputInteger();
                                        if ((i != y) && (y >= 1 && y <= 4)) {
                                            break;
                                        } else {
                                            screen.displayErrorMessage("\nPlease input number between 1 to 4, except " + i + ".");
                                        }
                                    }

                                    screen.displayMessage("");
                                    screen.displayMessageLine("!!! Ready for attack !!!\n");
                                    battle.userPokemon(stage3.getUserPokemon().get(i - 1), stage3.getUserPokemon().get(y - 1));
                                    
                                    while ((battle.getUserPokemon1().getHp() > 0 && battle.getUserPokemon2().getHp() > 0 && battle.getEnemyPokemon1().getHp() > 0 && battle.getEnemyPokemon2().getHp() > 0)) {
                                        int choice = 0;
                                        while (true) {
                                            screen.displayMessage("Choose 1st or 2nd enemy pokemon to attack[1/2]: ");
                                            choice = input.getInputInteger();
                                            screen.displayMessage("");
                                            if (choice >= 1 && choice <= 2) {
                                                screen.displayMessageLine("");

                                                switch (choice) {
                                                    case 1://case for enemy first pokemon
                                                    	if(battle.checkUserPokemon1Hp() == 1 || battle.checkUserPokemon2Hp() == 1) {
                                                    		battle.AttackEnemyPokemon1();
                                                            if (battle.checkEnemyPokemon1Hp() == 1) {
                                                                battle.AttackUserPokemon1();
                                                            		if(battle.checkUserPokemon1Hp() == 0) {
                                                            			screen.displayErrorMessage("[User Pokemon 1 fainted]");
                                                            		}
                                                            		if(battle.checkUserPokemon2Hp() == 0) {
                                                            			screen.displayErrorMessage("[User Pokemon 2 fainted]");
                                                            		}
                                                            	}
                                                            else {
                                                                screen.displayErrorMessage("[Enemy Pokemon 1 fainted.]");
                                                            }
                                                    	}
                                                    	else if(battle.checkUserPokemon1Hp()==0 && battle.checkEnemyPokemon2Hp()==0) {
                                                    		battle.AttackEnemyPokemon1();
                                                    		if (battle.checkEnemyPokemon1Hp() == 1) {
                                                                battle.AttackUserPokemon1();
                                                                if(battle.checkUserPokemon1Hp() == 0) {
                                                        			screen.displayErrorMessage("[User Pokemon 1 fainted]");
                                                        		}
                                                        		if(battle.checkUserPokemon2Hp() == 0) {
                                                        			screen.displayErrorMessage("[User Pokemon 2 fainted]");
                                                        		}
                                                            } else {
                                                                screen.displayErrorMessage("[Enemy Pokemon 1 fainted.]");
                                                            }
                                                    	}
                                                        break;
                                                    case 2://case for enemy second pokemon
                                                    	if(battle.checkUserPokemon2Hp() == 1 || battle.checkUserPokemon1Hp() == 1) {
                                                    		battle.AttackEnemyPokemon2();
                                                            if (battle.checkEnemyPokemon2Hp() == 1) {
                                                                battle.AttackUserPokemon1();
                                                                if(battle.checkUserPokemon1Hp() == 0) {
                                                        			screen.displayErrorMessage("[User Pokemon 1 fainted]");
                                                        		}
                                                        		if(battle.checkUserPokemon2Hp() == 0) {
                                                        			screen.displayErrorMessage("[User Pokemon 2 fainted]");
                                                        		}
                                                            } else {
                                                                screen.displayErrorMessage("[Enemy Pokemon 2 Fainted]");
                                                            }
                                                    	}
                                                    	else if(battle.checkUserPokemon2Hp()==0 && battle.checkEnemyPokemon1Hp()==0) {
                                                    		battle.AttackEnemyPokemon2();
                                                    		if (battle.checkEnemyPokemon2Hp() == 1) {
                                                                battle.AttackUserPokemon1();
                                                                if(battle.checkUserPokemon1Hp() == 0) {
                                                        			screen.displayErrorMessage("[User Pokemon 1 fainted]");
                                                        		}
                                                        		if(battle.checkUserPokemon2Hp() == 0) {
                                                        			screen.displayErrorMessage("[User Pokemon 2 fainted]");
                                                        		}
                                                            } else {
                                                                screen.displayErrorMessage("[Enemy Pokemon 2 fainted.]");
                                                            }
                                                    	}
                                                    	break;
                                                }

                                                if (battle.getEnemyPokemon1().getHp() <= 0 && battle.getEnemyPokemon2().getHp() <= 0) {
                                                    screen.displayMessageLine("\nYou Won!");
                                                    battle.calculateScore(battle.getWin(), stage3.getDifficultiesValue());
                                                    screen.displayMessageLine("Your score is " + battle.getScore());
                                                    for (UserAccount userdata : userlist) {
                                                        if (usr.getUserID() == userdata.getUserID()) {
                                                            userdata.setScore(userdata.getScore() + battle.getScore());
                                                            userExist = true;
                                                            break;
                                                        }
                                                    }
                                                    if (!userExist) {
                                                        userlist.add(new UserAccount(user, (usr.getScore() + battle.getScore())));
                                                    }
                                                    db.writeUserTextFile(userlist);
                                                    screen.displayMessageLine("\n\n!!! Catch Time !!!");
                                                    pokemonCatchSuccess = ball.get(chooseBall).Catch() ? "Successful" : "Unsuccessful";
                                                    screen.displayMessageLine(pokemonCatchSuccess);
                                                    screen.displayErrorMessage("[You have " + (pokemonCatchSuccess).toLowerCase() + "ly use " + ball.get(chooseBall).getBallName() + " to catch " + battle.getEnemyPokemon1().getName() + " and " + battle.getEnemyPokemon2().getName() + "]");
                                                    break;
                                                }
                                               
                                                else if (battle.getUserPokemon1().getHp() <= 0 && battle.getUserPokemon2().getHp() <= 0) {
                                                    screen.displayMessageLine("\nYou lose!");
                                                    battle.calculateScore(battle.getLose(), stage3.getDifficultiesValue());
                                                    screen.displayMessageLine("Your score is " + battle.getScore());
                                                    for (UserAccount userdata : userlist) {
                                                        if (usr.getUserID() == userdata.getUserID()) {
                                                            userdata.setScore(userdata.getScore() + battle.getScore());
                                                            userExist = true;
                                                            break;
                                                        }
                                                    }
                                                    if (!userExist) {
                                                        userlist.add(new UserAccount(user, (usr.getScore() + battle.getScore())));
                                                    }
                                                    db.writeUserTextFile(userlist);
                                                    break;
                                                }
                                            } else {
                                                screen.displayErrorMessage("Invalid number. Please input number between 1 and 2!\n");
                                            }
                                        }
                                    }
                                } else {
                                    screen.displayMessageLine("Try again next time");
                                    db.writeUserTextFile(userlist);
                                }
                                break; 
                            } else {
                                screen.displayErrorMessage("\nInvalid number. Please input number between 1 and 3!");
                            }
                        }
                        break;

							
					
		//**************************************************************************************************			
					
				default:
					screen.displayErrorMessage("Please input the correct number.\n");
					break;
		
				}
			}
			else {
				screen.displayErrorMessage("Invalid number. Please input the correct number from 1 to 3.\n");
			}
        	//**************************************************************************************************
    		//Sort the user data(objects) in the araylist based on the score in descending order.
    		if(validInput) {
    		Collections.sort(userlist, UserAccount.ScoreComparator);
    		currenttopScoreList.clear();
            // Print top 5 scores
    		screen.displayMessageLine(" ");
    		screen.displayMessageLine(" ");
    		screen.displayMessageLine(" ");
    		screen.displayMessageLine("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    		screen.displayMessageLine("|           Leader Board          |");
    		screen.displayMessageLine("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            screen.displayMessageLine("Top 5 Scores:");
            for (int i = 0; i < Math.min(5, userlist.size()); i++) {//for loop to display the top 5 users' userid and their respective score.
            	currenttopScoreList.add(userlist.get(i));//add the top scorer's name into the arraylist.
                screen.displayMessageLine((i+1) + ".UserID: " + userlist.get(i).getUserID() + "   Score: " + userlist.get(i).getScore());
            }
            db.clearTopScoreList();
            db.writeTopScoreList(currenttopScoreList);//add the top scorer's arraylist into the top score list text file.
    		break;
    		}
    	}
    }
}