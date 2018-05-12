package assign61;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

public class Space implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private HashMap<String,Space> nextSpaces;
	private HashMap<String,GameObject> inventory;

	public Space(String name, String description) {
		this.name = name;
		this.description = description;
		nextSpaces = new HashMap<String,Space>();
		inventory = new HashMap<String,GameObject>();
	}
	
	public void putNextPlace(String command, Space nextSpace){
		nextSpaces.put(command,nextSpace);
	}

	public Space getNext(String command){
		return nextSpaces.get(command);
	}
	
	public Set<String> getOptions(){
		return nextSpaces.keySet();
	}

	public void addGameObject (GameObject gameObject){
		this.inventory.put(gameObject.getName(), gameObject);
	}
	
	public GameObject getGameObject (String objectName) {
		GameObject thisObject = this.inventory.get(objectName);
		inventory.remove(objectName);
		return thisObject;
	}
	
	public String getDescription() {
		String itemsAvailable = "";
		for (String item:inventory.keySet()){
			itemsAvailable = itemsAvailable+item+" ";
		}
		if (itemsAvailable.length()>0) itemsAvailable = "Room has "+itemsAvailable;
		return this.description+"\n"+itemsAvailable;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
}
