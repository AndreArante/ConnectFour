package Model;

public class Player {
	private PlayerType type;
	private String name;
	
	public Player(String name, PlayerType type) {
		this.name = name;
		this.type = type;
	}
	public PlayerType getType() {
		return type;
	}
	public void setType(PlayerType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
