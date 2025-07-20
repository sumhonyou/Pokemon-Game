import java.util.ArrayList;
import java.util.Random;
public class Battle {
	private Pokemon Pokemon1;
	private Pokemon Pokemon2;
	private Pokemon Pokemon3;
	private Pokemon Pokemon4;
	private Pokemon enemyPokemon1;
	private Pokemon enemyPokemon2;
	private Pokemon userPokemon1;
	private Pokemon userPokemon2;
	
	private final int HEADON = 30;
	private final int DODGE = 60;
	private final int Z_MOVE = 30;
	private final int DOUBLE_RUSH = 60;
	private int score;
	private final int LOSE =0;
	private final int WIN = 400;

	Random random = new Random();
	
	public Battle() {
		Pokemon1 = null;
		Pokemon2 = null;
		Pokemon3 = null;
		Pokemon4 = null;
		enemyPokemon1 = new Pokemon();
		enemyPokemon2 = new Pokemon();
		userPokemon1 = new Pokemon();
		userPokemon2 = new Pokemon();
	}

	public Battle(ArrayList<Pokemon> PokemonList) {
		Pokemon1 = PokemonList.get(0);
		Pokemon2 = PokemonList.get(1);
		Pokemon3 = PokemonList.get(2);
		Pokemon4 = PokemonList.get(3);
		enemyPokemon1 = new Pokemon();
		enemyPokemon2 = new Pokemon();
		userPokemon1 = new Pokemon();
		userPokemon2 = new Pokemon();
	}
	
	public Pokemon getEnemyPokemon1() {
		return enemyPokemon1;
	}

	public Pokemon getEnemyPokemon2() {
		return enemyPokemon2;
	}
	
	public Pokemon getUserPokemon1() {
		return userPokemon1;
	}

	public Pokemon getUserPokemon2() {
		return userPokemon2;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLose() {
		return LOSE;
	}

	public int getWin() {
		return WIN;
	}

	public void roar(){
		int roarLevel = 1 + random.nextInt(100);
		System.out.print("Your roar level is " + roarLevel + "\n");
		if(roarLevel <= 25) {
			enemyPokemon1.setName(Pokemon1.getName());
			enemyPokemon1.setHp(Pokemon1.getHp());
			enemyPokemon1.setDmgofAtkMove(Pokemon1.getDmgofAtkMove());
			enemyPokemon1.setValueofDefMove(Pokemon1.getValueofDefMove());
			
			enemyPokemon2.setName(Pokemon2.getName());
			enemyPokemon2.setHp(Pokemon2.getHp());
			enemyPokemon2.setDmgofAtkMove(Pokemon2.getDmgofAtkMove());
			enemyPokemon2.setValueofDefMove(Pokemon2.getValueofDefMove());
		}
		else if(roarLevel <=50) {
			enemyPokemon1.setName(Pokemon2.getName());
			enemyPokemon1.setHp(Pokemon2.getHp());
			enemyPokemon1.setDmgofAtkMove(Pokemon2.getDmgofAtkMove());
			enemyPokemon1.setValueofDefMove(Pokemon2.getValueofDefMove());
			
			enemyPokemon2.setName(Pokemon3.getName());
			enemyPokemon2.setHp(Pokemon3.getHp());
			enemyPokemon2.setDmgofAtkMove(Pokemon3.getDmgofAtkMove());
			enemyPokemon2.setValueofDefMove(Pokemon3.getValueofDefMove());
		}
		else if(roarLevel <=75) {
			enemyPokemon1.setName(Pokemon1.getName());
			enemyPokemon1.setHp(Pokemon1.getHp());
			enemyPokemon1.setDmgofAtkMove(Pokemon1.getDmgofAtkMove());
			enemyPokemon1.setValueofDefMove(Pokemon1.getValueofDefMove());
			
			enemyPokemon2.setName(Pokemon3.getName());
			enemyPokemon2.setHp(Pokemon3.getHp());
			enemyPokemon2.setDmgofAtkMove(Pokemon3.getDmgofAtkMove());
			enemyPokemon2.setValueofDefMove(Pokemon3.getValueofDefMove());
		}
		else if(roarLevel <= 100) {
			enemyPokemon1.setName(Pokemon3.getName());
			enemyPokemon1.setHp(Pokemon3.getHp());
			enemyPokemon1.setDmgofAtkMove(Pokemon3.getDmgofAtkMove());
			enemyPokemon1.setValueofDefMove(Pokemon3.getValueofDefMove());
			
			enemyPokemon2.setName(Pokemon4.getName());
			enemyPokemon2.setHp(Pokemon4.getHp());
			enemyPokemon2.setDmgofAtkMove(Pokemon4.getDmgofAtkMove());
			enemyPokemon2.setValueofDefMove(Pokemon4.getValueofDefMove());
		}
	}
	
	public void userPokemon(Pokemon UserPokemon1, Pokemon UserPokemon2) {
		userPokemon1.setName(UserPokemon1.getName());
		userPokemon1.setHp(UserPokemon1.getHp());
		userPokemon1.setDmgofAtkMove(UserPokemon1.getDmgofAtkMove());
		userPokemon1.setValueofDefMove(UserPokemon1.getValueofDefMove());
		
		userPokemon2.setName(UserPokemon2.getName());
		userPokemon2.setHp(UserPokemon2.getHp());
		userPokemon2.setDmgofAtkMove(UserPokemon2.getDmgofAtkMove());
		userPokemon2.setValueofDefMove(UserPokemon2.getValueofDefMove());
	}
	
	public void AttackEnemyPokemon1() {
		int x = 1 + random.nextInt(30);
		
		if(x<=15) {
			System.out.println("Sorry, you didn't get any additional attack skill.");
			Enemy1Def();
			int PokemonHpLeft = getEnemyPokemon1().getHp()  + getEnemyPokemon1().getValueofDefMove() - getUserPokemon1().getDmgofAtkMove();
			getEnemyPokemon1().setValueofDefMove((getEnemyPokemon1().getValueofDefMove() - getUserPokemon1().getDmgofAtkMove()));
			getEnemyPokemon1().setHp((PokemonHpLeft));
		}
		else if(x<=25) {
			System.out.println("Congratulationas, you got z-move!!");
			Enemy1Def();
			int PokemonHpLeft = getEnemyPokemon1().getHp()  + getEnemyPokemon1().getValueofDefMove() - getUserPokemon1().getDmgofAtkMove() - Z_MOVE;
			getEnemyPokemon1().setValueofDefMove((getEnemyPokemon1().getValueofDefMove() - getUserPokemon1().getDmgofAtkMove()) - Z_MOVE);
			getEnemyPokemon1().setHp((PokemonHpLeft));
		}
		else {
			System.out.println("Congratulationas, you got double rush!!");
			Enemy1Def();
			int PokemonHpLeft = getEnemyPokemon1().getHp()  + getEnemyPokemon1().getValueofDefMove() - getUserPokemon1().getDmgofAtkMove() - DOUBLE_RUSH;
			getEnemyPokemon1().setValueofDefMove((getEnemyPokemon1().getValueofDefMove() - getUserPokemon1().getDmgofAtkMove()) - DOUBLE_RUSH);
			getEnemyPokemon1().setHp((PokemonHpLeft));
		}
		
	}

	public void AttackEnemyPokemon2() {
		int x = 1 + random.nextInt(30);
		
		if(x<=15) {
			System.out.println("Sorry, you didn't get any additional attack skill.");
			Enemy2Def();
			int PokemonHpLeft = getEnemyPokemon2().getHp()  + getEnemyPokemon2().getValueofDefMove() - getUserPokemon2().getDmgofAtkMove();
			getEnemyPokemon2().setValueofDefMove((getEnemyPokemon2().getValueofDefMove() - getUserPokemon2().getDmgofAtkMove()));
			getEnemyPokemon2().setHp((PokemonHpLeft));
		}
		else if(x<=25) {
			System.out.println("Congratulationas, you got z-move!!");
			Enemy2Def();
			int PokemonHpLeft = getEnemyPokemon2().getHp()  + getEnemyPokemon2().getValueofDefMove() - getUserPokemon2().getDmgofAtkMove() - Z_MOVE;
			getEnemyPokemon2().setValueofDefMove((getEnemyPokemon2().getValueofDefMove() - getUserPokemon2().getDmgofAtkMove()) - Z_MOVE);
			getEnemyPokemon2().setHp((PokemonHpLeft));
		}
		else {
			System.out.println("Congratulationas, you got double rush!!");
			Enemy2Def();
			int PokemonHpLeft = getEnemyPokemon2().getHp()  + getEnemyPokemon2().getValueofDefMove() - getUserPokemon2().getDmgofAtkMove() - DOUBLE_RUSH;
			getEnemyPokemon2().setValueofDefMove((getEnemyPokemon2().getValueofDefMove() - getUserPokemon2().getDmgofAtkMove()) - DOUBLE_RUSH);
			getEnemyPokemon2().setHp((PokemonHpLeft));
		}
	}
	
	public void AttackUserPokemon1() {
		int x = 1 + random.nextInt(30);
		int chooseUserPokemon = 1 + random.nextInt(2);
		
		if(chooseUserPokemon == 1) {
			if(x<=15) {
				System.out.print("Enemy pokemon 1 didn't get any additional attack skill.\n");
				User1Def();
				int PokemonHpLeft = getUserPokemon1().getHp()  + getUserPokemon1().getValueofDefMove() - getEnemyPokemon1().getDmgofAtkMove();
				getUserPokemon1().setValueofDefMove((getUserPokemon1().getValueofDefMove() - getEnemyPokemon1().getDmgofAtkMove()));
				getUserPokemon1().setHp((PokemonHpLeft));
			}
			else if(x<=25) {
				System.out.print("Enemy pokemon 1 got z-move!!\n");
				User1Def();
				int PokemonHpLeft = getUserPokemon1().getHp()  + getUserPokemon1().getValueofDefMove() - getEnemyPokemon1().getDmgofAtkMove() - Z_MOVE;
				getUserPokemon1().setValueofDefMove((getUserPokemon1().getValueofDefMove() - getEnemyPokemon1().getDmgofAtkMove()) - Z_MOVE);
				getUserPokemon1().setHp((PokemonHpLeft));
			}
			else {
				System.out.print("Enemy pokemon 1 got double rush!!\n");
				User1Def();
				int PokemonHpLeft = getUserPokemon1().getHp()  + getUserPokemon1().getValueofDefMove() - getEnemyPokemon1().getDmgofAtkMove() - DOUBLE_RUSH;
				getUserPokemon1().setValueofDefMove((getUserPokemon1().getValueofDefMove() - getEnemyPokemon1().getDmgofAtkMove()) - DOUBLE_RUSH);
				getUserPokemon1().setHp((PokemonHpLeft));
			}
		}
		else {
			AttackUserPokemon2();
		}
	}
	
	public void AttackUserPokemon2() {
		int x = 1 + random.nextInt(30);
		int chooseUserPokemon = 1 + random.nextInt(2);
		
		if(chooseUserPokemon == 1) {
			if(x<=15) {
				System.out.print("Enemy pokemon 2 didn't get any additional attack skill.\n");
				User2Def();
				int PokemonHpLeft = getUserPokemon2().getHp()  + getUserPokemon2().getValueofDefMove() - getEnemyPokemon2().getDmgofAtkMove();
				getUserPokemon2().setValueofDefMove((getUserPokemon2().getValueofDefMove() - getEnemyPokemon2().getDmgofAtkMove()));
				getUserPokemon2().setHp((PokemonHpLeft));
			}
			else if(x<=25) {
				System.out.print("Enemy pokemon 2 got z-move!!\n");
				User2Def();
				int PokemonHpLeft = getUserPokemon2().getHp()  + getUserPokemon2().getValueofDefMove() - getEnemyPokemon2().getDmgofAtkMove() - Z_MOVE;
				getUserPokemon2().setValueofDefMove((getUserPokemon2().getValueofDefMove() - getEnemyPokemon2().getDmgofAtkMove()) - Z_MOVE);
				getUserPokemon2().setHp((PokemonHpLeft));
			}
			else {
				System.out.print("Enemy pokemon 2 got double rush!!\n");
				User2Def();
				int PokemonHpLeft = getUserPokemon2().getHp()  + getUserPokemon2().getValueofDefMove() - getEnemyPokemon2().getDmgofAtkMove() - DOUBLE_RUSH;
				getUserPokemon2().setValueofDefMove((getUserPokemon2().getValueofDefMove() - getEnemyPokemon2().getDmgofAtkMove()) - DOUBLE_RUSH);
				getUserPokemon2().setHp((PokemonHpLeft));
			}
		}
		else {
			AttackUserPokemon2();
		}
	}
	
	public void Enemy1Def() {
		int x = 1 + random.nextInt(30);
		if(x<=15) {
			System.out.print("Enemy defense....");
			System.out.println("Enemy pokemon 1 didn't get any additional defense.");
			getEnemyPokemon1().setValueofDefMove(getEnemyPokemon1().getValueofDefMove());
		}
		else if(x<=25) {
			System.out.print("Enemy defense....");
			System.out.println("Enemy pokemon 1 got a head on chance for defense.");
			getEnemyPokemon1().setValueofDefMove(getEnemyPokemon1().getValueofDefMove() + HEADON);
		}
		else {
			System.out.print("Enemy defense....");
			System.out.println("Enemy pokemon 1 got a dodge chance for defense");
			getEnemyPokemon1().setValueofDefMove(getEnemyPokemon1().getValueofDefMove() +DODGE);
		}
	}
	
	public void Enemy2Def() {
		int x = 1 + random.nextInt(30);
		if(x<=15) {
			System.out.print("Enemy defense....");
			System.out.println("Enemy pokemon 2 didn't get any additional defense.");
			getEnemyPokemon2().setValueofDefMove(getEnemyPokemon2().getValueofDefMove());
		}
		else if(x<=25) {
			System.out.print("Enemy defense....");
			System.out.println("Enemy pokemon 2 got a head on chance for defense.");
			getEnemyPokemon2().setValueofDefMove(getEnemyPokemon2().getValueofDefMove() + HEADON);
		}
		else {
			System.out.print("Enemy defense....");
			System.out.println("Enemy pokemon2 got a dodge chance for defense");
			getEnemyPokemon2().setValueofDefMove(getEnemyPokemon2().getValueofDefMove() + DODGE);
		}
	}
	
	public void User1Def() {
		int x = 1 + random.nextInt(30);
		if(x<=15) {
			System.out.print("User defense....");
			System.out.println("You didn't get any additional defense.");
			getUserPokemon1().setValueofDefMove(getUserPokemon1().getValueofDefMove());
		}
		else if(x<=25) {
			System.out.print("User defense....");
			System.out.println("You got a head on chance for defense.");
			getUserPokemon1().setValueofDefMove(getUserPokemon1().getValueofDefMove() + HEADON);
		}
		else {
			System.out.print("User defense....");
			System.out.println("You got a dodge chance for defense");
			getUserPokemon1().setValueofDefMove(getUserPokemon1().getValueofDefMove() + DODGE);
		}
	}
	
	public void User2Def() {
		int x = 1 + random.nextInt(30);
		if(x<=15) {
			System.out.print("User defense....");
			System.out.println("You didn't get any additional defense.");
			getUserPokemon2().setValueofDefMove(getUserPokemon2().getValueofDefMove());
		}
		else if(x<=25) {
			System.out.print("User defense....");
			System.out.println("You got a head on chance for defense.");
			getUserPokemon2().setValueofDefMove(getUserPokemon2().getValueofDefMove() + HEADON);
		}
		else {
			System.out.print("User defense....");
			System.out.println("You got a dodge chance for defense");
			getUserPokemon2().setValueofDefMove(getUserPokemon2().getValueofDefMove() + DODGE);
		}
	}
	
	public int checkEnemyPokemon1Hp() {
		if(getEnemyPokemon1().getHp()<=0) {
			return 0;
		}
		else {
			System.out.print("Enemy attack...");
		}
		return 1;
	}
	
	public int checkEnemyPokemon2Hp() {
		if(getEnemyPokemon2().getHp() <= 0) {
			return 0;
		}
		else {
			System.out.print("Enemy attack...");
		}
		return 1;
	}
	
	public int checkUserPokemon1Hp() {
		if(getUserPokemon1().getHp() <= 0) {
			return 0;
		}
		else {
			System.out.print("User attack...");
		}
		return 1;
	}
	
	public int checkUserPokemon2Hp() {
		if(getUserPokemon2().getHp() <= 0) {
			return 0;
		}
		else {
			System.out.print("User attack...");
		}
		return 1;
	}
	
	public void calculateScore(int GameStatus, int StageDifficulties) {
		int point = GameStatus;
		int Stage = StageDifficulties;
		int score = point + Stage;
		setScore(score);
	}
}
