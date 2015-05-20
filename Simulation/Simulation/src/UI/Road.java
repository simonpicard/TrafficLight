package UI;

import org.newdawn.slick.SlickException;

public class Road {
	
	private PedestrianLight pedestrianTL;
	private LeftLight leftTL;
	private RightLight rightTL;
	private UpLight upTL;
	private int orientation;
	
	public Road(int orientation) throws SlickException{
		this.orientation = orientation;
		init();
	}
	
	private void init() throws SlickException{
		pedestrianTL = new PedestrianLight(134, 134, orientation);
		leftTL = new LeftLight(5, 100, orientation);
		upTL = new UpLight(44, 100, orientation);
		rightTL = new RightLight(83, 100, orientation);
	}
	
	public void draw(){
		pedestrianTL.draw();
		leftTL.draw();
		upTL.draw();
		rightTL.draw();
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

}
