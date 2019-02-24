import java.util.ArrayList;

public class Square {

	private String type;
	private String name;
	private String color;
	private Integer cost;
	private Integer rent;
	private ArrayList<String> occupants = new ArrayList<String>();
	//private boolean owned = false;
	//private HumanPlayer owner;
	//private ComputerPlayer owner;
	//private int houseMultiplier = 1;

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
	public String getColor() {
		return color;
	}
	public int getCost() {
		return cost;
	}
	public int getRent() {
		return rent;
	}

	public ArrayList<String> getOccupants() {
		return occupants;
	}

	public String listedOccupants() {
		String temp = "";
		for (String s: occupants) {
			temp = temp + " " + s;
		}
		return temp;
	}


	public String toString() {
		String temp = name;
		while (temp.length() < 13) {
			temp = " " + temp + " ";
			if (temp.length() == 13) {
				temp = temp +" ";
			}
		}
		return temp;
	}

	//public boolean getOwned() {
	//	return owned;
	//}

	//public void setOwned(boolean b) {
		//owned = b;
	//}

	//public HumanPlayer getOwner() {
		//return owner;
	//}

	//public void setOwner (HumanPlayer h) {
		//owner = h;
	//}
}