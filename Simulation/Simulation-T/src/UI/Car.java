package UI;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import java.util.Random;

public class Car {
	
	private int x;
	private int y;
	private int ax; //absolute x;
	private int ay;
	private Orientation orientation;
	private String[] path = {"images/car2.png", "images/car3.png", "images/car4.png", "images/car5.png", "images/car6.png", "images/car7.png"};
	private Image carImage;
	private Boolean t = true;
	private Boolean turned = false;
	
	public Car(int x, int y, Orientation orientation) throws SlickException {
		super();
		this.x = x;
		this.y = y;
		this.orientation = orientation;
		setAbsolute();
		Random r = new Random();
		carImage = new Image(path[r.nextInt(6)]);
	}
	
	private void setAbsolute(){
		int[] pos = Util.adjustPos(x, y, orientation);
		this.ax = pos[0];
		this.ay = pos[1];
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
	public Orientation getOrientation() {
		return orientation;
	}
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	
	public void up(){
		y -= 1;
		setAbsolute();
	}
	
	public void right(){
		x += 1;
		setAbsolute();
	}
	
	public void left(){
		x -= 1;
		setAbsolute();
	}
	
	public void draw(){
		if (t){
			carImage.draw(ax,ay);
			rotateCar();
			t = false;
		}
		carImage.draw(ax, ay);
	}
	
	private void rotateCar(){
		carImage.setCenterOfRotation(0, 0);
		carImage.setRotation(orientation.ordinal()*90);
	}
	
	public void turn(int direction){
		//carImage.setCenterOfRotation(carImage.getWidth()/2, carImage.getHeight()/2);
		carImage.rotate((direction)*90);
		switch(direction){
		case 1:
			x += 30;
			break;
		case 3:
			y += 30;
			break;
		}
		setAbsolute();
		turned = true;
	}
	
	public Boolean getTurned(){
		return turned;
	}

}
