package assignment8;

import edu.princeton.cs.introcs.StdDraw;
import support.cse131.NotYetImplementedException;

public class Nonzombie extends Entity {

	public static final double NONZOMBIE_SPEED = 0.01;

	/**
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Nonzombie(double x, double y) {
		super(x, y, false, NONZOMBIE_SPEED);
	}
	
	/**
	 * Create a Zombie object in place of the current Nonzombie 
	 * @return the new Zombie object
	 */
	public Zombie convert() {
		Zombie zombieSelf = new Zombie(super.getX(), super.getY());
		zombieSelf.setRadius(super.getRadius());
		super.wasConsumed();
		return zombieSelf;
	}
	
	/**
	 * Draw a Nonzombie
	 */
	public void draw() { //nonzombies will be black i've decided
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledCircle(super.getX(), super.getY(),super.getRadius());
	}

	/**
	 * Update the Nonzombie
	 * @param entities the array of Entity objects in the simulation, consumed or not
	 * @return the new Entity object to take the place of the current one
	 */
	public Entity update(Entity[] entities) {
		Zombie closest = super.findClosestZombie(entities);
		if (this.isTouching(closest)){
			if (Math.random()<=0.8){
				closest = this.convert();
				return closest;
			}
			else {
				super.wasConsumed();
				closest.consumeNonzombie();
			}
		}
		if (closest!=null){
			super.moveAwayFrom(closest);
		}
		super.checkBounds();
		return this;
	}


}
