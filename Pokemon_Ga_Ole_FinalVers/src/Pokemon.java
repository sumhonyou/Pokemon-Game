public class Pokemon {
	    private String name;
	    private int Hp;
	    private String atkMove;
	    private String defMove;
	    private int dmgofAtkMove;
	    private int valueofDefMove;
	    private String element;
	    

	    public Pokemon() {
	    	name = null;
	    	Hp = 0;
	    	atkMove = null;
	    	defMove = null;
	    	dmgofAtkMove = 0;
	    	valueofDefMove = 0;
	    	element = null;
	    }
	    
	    public Pokemon(String name, int Hp, String atkMove, String defMove, 
	    				int dmgofAtkMove, int valueofDefMove, String element) {
	    	this.name = name;
	    	this.Hp = Hp;
	    	this.atkMove = atkMove;
	    	this.defMove = defMove;
	    	this.dmgofAtkMove = dmgofAtkMove;
	    	this.valueofDefMove = valueofDefMove;
	    	this.element = element;
	    }
	    
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getHp() {
			return Hp;
		}

		public void setHp(int hp) {
			Hp = hp;
		}

		public String getAtkMove() {
			return atkMove;
		}

		public void setAtkMove(String atkMove) {
			this.atkMove = atkMove;
		}

		public String getDefMove() {
			return defMove;
		}

		public void setDefMove(String defMove) {
			this.defMove = defMove;
		}

		public int getDmgofAtkMove() {
			return dmgofAtkMove;
		}

		public void setDmgofAtkMove(int dmgofAtkMove) {
			this.dmgofAtkMove = dmgofAtkMove;
		}

		public int getValueofDefMove() {
			return valueofDefMove;
		}

		public void setValueofDefMove(int valueofDefMove) {
			this.valueofDefMove = valueofDefMove;
		}

		public String getElement() {
			return element;
		}

		public void setElement(String element) {
			this.element = element;
		}

		@Override
		public String toString() {
			return String.format(
					"Name: %s, Hp: %d, atkMove: %s, defMove: %s, dmgofAtkMove: %d, valueofDefMove: %d, element: %s",
					name, Hp, atkMove, defMove, dmgofAtkMove, valueofDefMove, element);
		}
	}