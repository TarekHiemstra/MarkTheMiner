package assign61;

import java.util.HashSet;
import java.util.Set;

public class GameCommands {

	private Player myPlayer;
	public static final String ENDCOMMAND  ="end";
	public static final String SAVECOMMAND = "save";
	public static final String TAKECOMMAND = "take";

	public GameCommands(Player myPlayer) {
		// TODO Auto-generated constructor stub
		this.myPlayer = myPlayer;
	}

	public Set<String> getValidCommands() {
		HashSet<String> hs= new HashSet<String>(myPlayer.getCurrentSpace().getOptions());
		hs.add(ENDCOMMAND);
		hs.add(SAVECOMMAND);
		hs.add(TAKECOMMAND);

		//name of an object in player's inventory can be used as the first part of a command
		for (String key:myPlayer.getInventory().keySet()) {
			hs.add(key);
		}
		return hs;
	}
}
