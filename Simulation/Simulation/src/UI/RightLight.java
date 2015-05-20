package UI;

import org.newdawn.slick.SlickException;

public class RightLight extends TrafficLight {

	public RightLight(int x, int y, int orientation) throws SlickException {
		super(x, y, orientation);
		// TODO Auto-generated constructor stub
	}

	protected void setPaths(){
		greenPath = "images/voitureDroiteVert.png";
		orangePath = "images/voitureDroiteOrange.png";
		redPath = "images/voitureDroiteRouge.png";
	}
}
