package assign61;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

	public static GameController myGameController;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		myGameController = new GameController();
		myGameController.start();
	}

	public static void saveGame() {
		try {
			FileOutputStream out = new FileOutputStream("MarkTheMiner.ser");
			ObjectOutputStream obout = new ObjectOutputStream(out);
			obout.writeObject(myGameController);
			obout.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not open dogsmain.ser");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error writing into file");
			e.printStackTrace();
		}
	}

	public static GameController restoreGame() {
		try {
			FileInputStream in = new FileInputStream("MarkTheMiner.ser");
			ObjectInputStream obin = new ObjectInputStream(in);
			obin.close();
			return (GameController) obin.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("Could not open dogsmain.ser");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error reading file");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Error reading object");
			e.printStackTrace();
		}
		return null;
	}
}
