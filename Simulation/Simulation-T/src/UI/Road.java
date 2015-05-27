package UI;

import org.newdawn.slick.SlickException;

public class Road {
	
	private Orientation orientation;
	private LaneT lane;
	private Sidewalk sidewalk;
	
	public Road(Orientation orientation) throws SlickException{
		this.orientation = orientation;
		init();
	}
	
	private void init() throws SlickException{
		lane = new LaneT(orientation);
		if (orientation == Orientation.WEST)
			sidewalk = new Sidewalk(orientation);
	}
	
	public void draw(){
		lane.draw();
		if (orientation == Orientation.WEST)
			sidewalk.draw();
	}
	
	public void update(){
		lane.update();
		if (orientation == Orientation.WEST)
			sidewalk.update();
	}
	
	public void addCar(Direction lane) throws SlickException{
		this.lane.addCar(lane);
	}
	
	public void removeCar() throws SlickException{
		this.lane.removeCar();
	}
	
	public void addPedestrian(Direction lane) throws SlickException{
		if (orientation == Orientation.WEST)
			this.sidewalk.addPedestrian(lane);
	}
	
	public void removePedestrian(Direction lane) throws SlickException{
		if (orientation == Orientation.WEST)
			this.sidewalk.removePedestrian(lane);
	}

}
