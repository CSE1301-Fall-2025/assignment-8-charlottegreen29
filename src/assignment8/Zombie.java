package assignment8;

import edu.princeton.cs.introcs.StdDraw;
import support.cse131.NotYetImplementedException;

public class Zombie extends Entity {

	public static final double ZOMBIE_SPEED = 0.011;

	/**
	 * Create a new Zombie object
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Zombie(double x, double y){
		super(x,y,true,ZOMBIE_SPEED);
	}

	/**
	 * Grow the Zombie after consuming a Nonzombie
	 */
	public void consumeNonzombie(){
		if (super.getRadius()*1.2<0.02){
			super.setRadius(super.getRadius()*1.2);
		}
		else {
			super.setRadius(0.02); //I don't have an if statement that checks if it touched a nonzombie; should I have one?
		}
	}

	/**
	 * Draw the Zombie
	 */
	public void draw() {
		StdDraw.setPenColor(StdDraw.GREEN); //Zombies are green. Duh
		StdDraw.filledCircle(super.getX(), super.getY(),super.getRadius());
	}


	/**
	 * Update the Zombie
	 * @param entities the array of Entity objects in the simulation, consumed or not
	 * @return the new Entity object to take the place of the current one
	 */
	public Entity update(Entity[] entities) { //i had to make findClosest public in order to use it here
		Entity closest = super.findClosest(entities, false, true);
		if (closest!=null){
			super.moveToward(closest);
		}
		super.checkBounds();
		return this; // did I do this right?
	}
}
