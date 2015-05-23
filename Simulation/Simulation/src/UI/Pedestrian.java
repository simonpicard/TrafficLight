package UI;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Pedestrian {

	private String path = "images/test.png";
	private Image pedestrianImage;
	private Orientation orientation;

	private int x;
	private int y;
	private int ax; // absolute x;
	private int ay;
	private int r = 0; // rotation x;
	private Boolean t1 = true;
	private Boolean t2 = true;
	private int turned = 0;

	public Pedestrian(int x, int y, Orientation orientation)
			throws SlickException {
		super();
		this.orientation = orientation;
		this.x = x;
		this.y = y;
		pedestrianImage = new Image(path);
		setAbsolute();
	}

	private void setAbsolute() {
		int[] pos = Util.adjustPos(x, y, orientation);
		this.ax = pos[0];
		this.ay = pos[1];
	}

	public int getX() {
		return x-(r==1 || r==2?9:0);
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y-(r==3 || r==2?9:0);
	}

	public void setY(int y) {

		this.y = y;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public void up() {
		y -= 1;
		setAbsolute();
	}

	public void down() {
		y += 1;
		setAbsolute();
	}

	public void right() {
		x += 1;
		setAbsolute();
	}

	public void left() {
		x -= 1;
		setAbsolute();
	}

	public void draw() {
		if (t1) {
			pedestrianImage.draw(ax, ay);
			rotatePedestrian();
			t1 = false;
		}
		pedestrianImage.draw(ax, ay);
	}

	private void rotatePedestrian() {
		pedestrianImage.setCenterOfRotation(0, 0);
		pedestrianImage.setRotation(orientation.ordinal() * 90);
	}

	public void turn(Direction d) {

		pedestrianImage.setCenterOfRotation(0, 0);
		pedestrianImage.rotate(d.ordinal() * 90);

		switch (d) {
		case RIGHT:
			switch (r){
			case 0: x += 9; break;
			case 1: y += 9; break;
			case 2: x -= 9; break;
			case 3: y -= 9; break;
			}
			r += 1;
			break;
		case LEFT:		
			switch (r){
			case 0: y += 9; break;
			case 1: x -= 9; break;
			case 2: y -= 9; break;
			case 3: x += 9; break;
		}
			r -= 1;
			break;
		}
		r = (r+ 4)%4;
		setAbsolute();
		draw();
		turned++;
	}

	public int getTurned() {
		return turned;
	}

}
