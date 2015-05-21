package UI;

import org.newdawn.slick.SlickException;

public class Road {
	
	private PedestrianLight pedestrianTL;
	private LeftLight leftTL;
	private RightLight rightTL;
	private UpLight upTL;
	private Direction orientation;
	private Lane lane;
	private Sidewalk sidewalk;
	
	public Road(Direction orientation) throws SlickException{
		this.orientation = orientation;
		init();
	}
	
	private void init() throws SlickException{
		pedestrianTL = new PedestrianLight(134, 134, orientation);
		leftTL = new LeftLight(5, 100, orientation);
		upTL = new UpLight(44, 100, orientation);
		rightTL = new RightLight(83, 100, orientation);
		lane = new Lane(orientation);
		sidewalk = new Sidewalk(orientation);
	}
	
	public void draw(){
		pedestrianTL.draw();
		leftTL.draw();
		upTL.draw();
		rightTL.draw();
		lane.draw();
		sidewalk.draw();
	}
	
	public void update(){
		lane.update();
		sidewalk.update();
	}
	
	public void setLeft(LightEnum le){
		leftTL.setState(le);
	}
	
	public void setRight(LightEnum le){
		rightTL.setState(le);
	}
	
	public void setUp(LightEnum le){
		upTL.setState(le);
	}
	
	public void setPedestrian(LightEnum le){
		pedestrianTL.setState(le);
	}
	
	public void addCar(Way lane) throws SlickException{
		this.lane.addCar(lane);
	}
	
	public void removeCar(Way lane) throws SlickException{
		this.lane.removeCar(lane);
	}
	
	public void addPedestrian(Way lane) throws SlickException{
		this.sidewalk.addPedestrian(lane);
	}
	
	public void removePedestrian(Way lane) throws SlickException{
		this.sidewalk.removePedestrian(lane);
	}

}
