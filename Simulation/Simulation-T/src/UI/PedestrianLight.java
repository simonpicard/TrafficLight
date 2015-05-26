package UI;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PedestrianLight {

	private int x;
	private int y;
	private Orientation orientation;
	private String redPath = "images/pietonRouge.png";
	private String greenPath = "images/pietonVert.png";
	private Image redLight;
	private Image greenLight;
	private LightEnum state;
	private Boolean t = true;
	
	
	public PedestrianLight(int x, int y, Orientation orientation) throws SlickException {
		super();
		int[] pos = Util.adjustPos(x,y,orientation);
		this.x = pos[0];
		this.y = pos[1];
		this.orientation = orientation;
		redLight = new Image(redPath);
		greenLight = new Image(greenPath);
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
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public Image getLight() {
		if (state == LightEnum.RED)
			return redLight;
		else
			return greenLight;
	}

	public void draw() {
		if (t){
			getLight().draw(x,y);
			rotateLights();
			t = false;
		}
		getLight().draw(x,y);
	}

	public void rotateLights(){
		redLight.setCenterOfRotation(0, 0);
		greenLight.setCenterOfRotation(0, 0);
		redLight.rotate(orientation.ordinal()*90);
		greenLight.rotate(orientation.ordinal()*90);
	}
}
