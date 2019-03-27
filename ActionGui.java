public class ActionGui {
	
	public void passGoMoney(Player player) {
		player.setPassedGo(false);
		player.changeBalance(200);
		System.out.println(player.getName() + " passed the [go] square, they will recieve $200 and their new balance is $" +
			player.getBalance() +".");
	}
}