package UI;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class TrafficLight {

	protected String greenPath;
	protected String orangePath;
	protected String redPath;
	protected int x;
	protected int y;
	protected Orientation orientation;
	protected Image greenLight;
	protected Image orangeLight;
	protected Image redLight;
	protected LightEnum state;
	private boolean t = true;
	
	
	public TrafficLight(int x, int y, Orientation orientation) throws SlickException {
		super();
		setPaths();
		int[] pos = Util.adjustPos(x,y,orientation);
		this.x = pos[0];
		this.y = pos[1];
		this.orientation = orientation;
		redLight = new Image(redPath);
		greenLight = new Image(greenPath);
		orangeLight = new Image(orangePath);
		redLight.setCenterOfRotation(0, 0);
		greenLight.setCenterOfRotation(0, 0);
		orangeLight.setCenterOfRotation(0, 0);
		rotateLights();
		state = LightEnum.RED;
	}
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public LightEnum getState() {
		return state;
	}

	public void setState(LightEnum state) {
		this.state = state;
	}

	public Orientation getOrientation() {
		return orientation;
	}
	private void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public Image getLight() {
		switch(state){
		case RED:
			return redLight;
		case ORANGE:
			return orangeLight;
		case GREEN:
			return greenLight;
		}
		return greenLight;
	}

	protected void rotateLights(){
		redLight.setCenterOfRotation(0, 0);
		greenLight.setCenterOfRotation(0, 0);
		orangeLight.setCenterOfRotation(0, 0);
		redLight.setRotation(orientation.ordinal()*90);
		greenLight.setRotation(orientation.ordinal()*90);
		orangeLight.setRotation(orientation.ordinal()*90);
	}

	public void draw() {
		if (t){
			getLight().draw(x,y);
			rotateLights();
			//t = false;
		}
		getLight().draw(x,y);
	}

	protected abstract void setPaths();

}
