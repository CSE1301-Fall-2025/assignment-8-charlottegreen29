package assignment8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import edu.princeton.cs.introcs.StdDraw;
import support.cse131.NotYetImplementedException;
import support.cse131.Timing;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class ZombieSimulator {
	private static final String ZOMBIE_TOKEN_VALUE = "Zombie";
	private Entity[] entities; //was I not supposed to do this?


	/**
	 * Constructs a ZombieSimulator with an empty array of Entities.
	 */
	public ZombieSimulator(int n) {
		entities = new Entity[n];
	}

	/**
	 * @return the current array of entities 
	 */
	public Entity[] getEntities() {
		return entities;
	}

	/** 
	 * Reads an entire zombie simulation file from a specified ArgsProcessor, adding
	 * each Entity to the array of entities.
	 *
	 * Assume that N (the integer indicating how many entities are in the simulation) has already been read in
	 * and passed into the constructor.
	 *
	 * @param in Scanner to read the complete zombie simulation file format.
	 */
	public void readEntities(Scanner in) {
		String areZombiesText;
		for (int i=0; i<entities.length; i++){
			areZombiesText=in.next();
			boolean isAzombie;
			if (areZombiesText.equals("Zombie")){
				isAzombie=true;
			}
			else{
				isAzombie=false;
			}
			double x = in.nextDouble();
			double y = in.nextDouble();
			if (isAzombie){
				entities[i] = new Zombie(x, y);
			}
			else {
				entities[i] = new Nonzombie(x, y);
			}
		}
	}

	/**
	 * @return the number of zombies in entities.
	 */
	public int getZombieCount() {
		int numZombies = 0;
		for (Entity entity : entities){
			if (entity.isZombie()){
				numZombies++;
			}
		}
		return numZombies;
	}

	/**
	 * @return the number of nonzombies in entities.
	 */
	public int getNonzombieCount() {
		return entities.length - this.getZombieCount();
	}

	/**
	 * Draws a frame of the simulation.
	 */
	public void draw() {
		StdDraw.clear();

		// NOTE: feel free to edit this code to support additional features
		for (Entity entity : getEntities()) {
			entity.draw();
		}

		StdDraw.show(); // commit deferred drawing as a result of enabling double buffering
	}

	/**
	 * Updates the entities for the current frame of the simulation given the amount
	 * of time passed since the previous frame.
	 * 
	 * Note: some entities may be consumed and will not remain for future frames of
	 * the simulation.
	 * 
	 */
	public void update() {
		for (int i = 0; i<entities.length; i++){
			entities[i] = entities[i].update(entities); //is this right?
		}
	}

	/**
	 * Runs the zombie simulation.
	 * 
	 * @param args arguments from the command line
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		StdDraw.enableDoubleBuffering(); // reduce unpleasant drawing artifacts, speed things up

		JFileChooser chooser = new JFileChooser("zombieSims");
		chooser.showOpenDialog(null);
		File f = new File(chooser.getSelectedFile().getPath());
		Scanner in = new Scanner(f); //making Scanner with a File

		ZombieSimulator zombieSimulator = new ZombieSimulator(in.nextInt());
		zombieSimulator.readEntities(in);

		while (zombieSimulator.getNonzombieCount() >= 0) {

			zombieSimulator.update();
			zombieSimulator.draw();

			StdDraw.pause(20);

		}
	}
}
