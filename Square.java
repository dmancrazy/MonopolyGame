public class Square {

	private String type;
	private String name;
	private String color;
	private Integer cost;
	private Integer rent;

	public Square(String n, String t, String c, int aCost , int r) {
		name = n;
		type = t;
		color = c;
		cost = aCost;
		rent = r;
	}

	public String getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	
	public int getCost() {
		return cost;
	}
	
	public int getRent() {
		return rent;
	}

	public String toString() {
		String temp = name; 
		for (int i = name.length(); i < 14; i++){
			temp += " ";
		}
		
		return temp;
	}

}