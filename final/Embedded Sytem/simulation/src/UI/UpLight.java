package UI;

import org.newdawn.slick.SlickException;

public class UpLight extends TrafficLight {

	public UpLight(int x, int y, Orientation orientation) throws SlickException {
		super(x, y, orientation);
		// TODO Auto-generated constructor stub
	}

	protected void setPaths(){
		greenPath = "images/voitureRondVert.png";
		orangePath = "images/voitureRondOrange.png";
		redPath = "images/voitureRondRouge.png";
	}
}
