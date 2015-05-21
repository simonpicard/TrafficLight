package UI;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Pedestrian {

	private String path = "images/pedestrian.png";
	private Image pedestrianImage;
	private Direction orientation;

	private int x;
	private int y;
	private int ax; // absolute x;
	private int ay;
	private Boolean t1 = true;
	private Boolean t2 = true;
	private int turned = 0;

	public Pedestrian(int x, int y, Direction orientation)
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
		System.out.println(ax);
		System.out.println(ay);
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

	public Direction getOrientation() {
		return orientation;
	}

	public void setOrientation(Direction orientation) {
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

	public void turn(int direction) {

		pedestrianImage.rotate((direction) * 90);
		if (t2) {
			
			switch (direction) {
			case 1:
				System.out.println("y");
				x += 9;
				break;
			case 3:
				y += 9;
				break;
			}
			setAbsolute();
			draw();
			t2 = false;
		}
		turned++;
	}

	public int getTurned() {
		return turned;
	}

}
