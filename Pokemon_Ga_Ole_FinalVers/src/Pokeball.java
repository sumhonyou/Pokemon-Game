
public class Pokeball {
	//Declare attributes
	private String ballName;
	private double baseSuccessRate;
	
	//Constructor
	public Pokeball(String name, double baseSuccessRate) {
		ballName = name;
		this.baseSuccessRate = baseSuccessRate;
	}

	//Getter only
	public String getBallName() {
		return ballName;
	}

	public double getBaseSuccessRate() {
		return baseSuccessRate;
	}
	
	//Other method to determine whether the it catch successfully or not? 
	public boolean Catch() {
		double randomRate = Math.random(); //Create a random using static (0.0 to 1)
		if (randomRate <= getBaseSuccessRate()) {
			return true;
		}else {
			return false;
		}
	}

}