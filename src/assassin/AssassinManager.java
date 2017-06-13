package assassin;
// Robert Simons
//This program executes a game called Assassin
import java.util.List;

public class AssassinManager {
	private AssassinNode killRing;
	private AssassinNode graveyard;

	// Initializes a new Assassin Manager over a list of people
	// and builds a kill ring of nodes in the same order
	public AssassinManager(List<String> names) {
	}

	
	// Prints the names of the people in the kill ring in the order
	// they are stalking each other
	public void printKillRing() {}

	
	// Prints the names of the people in the graveyard
	public void printGraveyard() {}

	// returns true if the kill ring contains the requested person
	public boolean killRingContains(String name) {
		return false;
	}

	// returns true if the graveyard contains the requested person
	public boolean graveyardContains(String name) {
		return false;
	}

	// Algorithm for computing if the specified list contains the
	// requested person
	private boolean checkList(String name, AssassinNode contains) {
		return false;
	}

	// returns true if the game is over
	public boolean isGameOver() {
		return false;
	}

	// returns the name of the winner
	public String winner() {
		return "BOOTH";
	}

	// kills the specified person, removing the name from the kill ring and
	// adding it to the graveyard
	public void kill(String name) {}

	//////// DO NOT MODIFY AssassinNode. You will lose points if you do.
	//////// ////////
	/**
	 * Each AssassinNode object represents a single node in a linked list for a
	 * game of Assassin.
	 */
	private static class AssassinNode {
		public final String name; // this person's name
		public String killer; // name of who killed this person (null if alive)
		public AssassinNode next; // next node in the list (null if none)

		/**
		 * Constructs a new node to store the given name and no next node.
		 */
		public AssassinNode(String name) {
			this(name, null);
		}

		/**
		 * Constructs a new node to store the given name and a reference to the
		 * given next node.
		 */
		public AssassinNode(String name, AssassinNode next) {
			this.name = name;
			this.killer = null;
			this.next = next;
		}
	}
}
