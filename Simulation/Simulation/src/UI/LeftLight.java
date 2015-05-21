package UI;

import org.newdawn.slick.SlickException;

public class LeftLight extends TrafficLight {

	public LeftLight(int x, int y, Direction orientation) throws SlickException {
		super(x, y, orientation);
		// TODO Auto-generated constructor stub
	}

	protected void setPaths(){
		greenPath = "images/voitureGaucheVert.png";
		orangePath = "images/voitureGaucheOrange.png";
		redPath = "images/voitureGaucheRouge.png";
	}
}
