package assign61;

import java.io.Serializable;
import java.util.HashMap;

public class Player implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Space currentSpace;
	private HashMap<String,GameObject> inventory;
	
	public Player(){
		inventory = new HashMap<String,GameObject>();
	}
	
	public void putGameObject (GameObject thisObject) {
		inventory.put(thisObject.getName(),thisObject);
	}
	
	public void removeGameObject (GameObject thisObject){
		inventory.remove(thisObject);
	}
	
	public HashMap<String,GameObject> getInventory() {
		return inventory;
	}
	
	public Space getCurrentSpace(){
		return currentSpace;
	}

	public void setCurrentSpace(Space currentSpace){
		this.currentSpace = currentSpace;
	}

	public void setLocation(Space next) {
		this.currentSpace = next;
	}
}
