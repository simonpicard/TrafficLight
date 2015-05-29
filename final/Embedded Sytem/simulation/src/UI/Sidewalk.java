package UI;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sidewalk {

	private int nbLeft;
	private int nbRight;
	private Orientation orientation;
	private int leavingLeft;
	private int leavingRight;

	private ArrayList<Pedestrian> leftPedestrians = new ArrayList<Pedestrian>();
	private ArrayList<Pedestrian> rightPedestrians = new ArrayList<Pedestrian>();

	private int pedestrianSize = 9; // square

	public Sidewalk(Orientation orientation) {
		this.orientation = orientation;
		nbLeft = 0;
		nbRight = 0;
		leavingLeft = 0;
		leavingRight = 0;
	}

	public void addPedestrian(Direction lane) throws SlickException {
		switch (lane) {
		case LEFT:
			leftPedestrians.add(new Pedestrian(-55, 400, orientation));
			nbLeft++;
			break;
		case RIGHT:
			rightPedestrians.add(new Pedestrian(37, 400, orientation));
			nbRight++;
			break;
		}
	}

	public void removePedestrian(Direction lane) throws SlickException {
		switch (lane) {
		case LEFT:
			if (nbLeft > 0) {
				nbLeft--;
				leavingLeft++;
			}
			break;
		case RIGHT:
			if (nbRight > 0) {
				nbRight--;
				leavingRight++;
			}
			break;
		}
	}

	public void update() {
		updateLeaving();
		updateLanes();
	}

	public void updateLanes() {
		int j = 0;

		for (int i = leftPedestrians.size() - nbLeft; i < leftPedestrians
				.size(); i++) {
			if (leftPedestrians.get(i).getY() > 47 + (j-1<0?0:j-1) * (pedestrianSize + 2) && leftPedestrians.get(i).getTurned() == 0)
				leftPedestrians.get(i).up();
			else if (leftPedestrians.get(i).getY() == 47 && leftPedestrians.get(i).getTurned() == 0)
				leftPedestrians.get(i).turn(Direction.RIGHT);
			else if (leftPedestrians.get(i).getX() < -46 && leftPedestrians.get(i).getTurned() == 1 && j == 0)
				leftPedestrians.get(i).right();
			j++;
		}

		j = 0;
		for (int i = rightPedestrians.size() - nbRight; i < rightPedestrians
				.size(); i++) {
			if (rightPedestrians.get(i).getY() > 79 + j * (pedestrianSize + 2) && rightPedestrians.get(i).getTurned() == 0)
				rightPedestrians.get(i).up();
			else if (rightPedestrians.get(i).getY() == 79 && rightPedestrians.get(i).getTurned() == 0)
				rightPedestrians.get(i).turn(Direction.LEFT);
			j++;
		}

	}

	public void updateLeaving() {
		for (int i = 0; i < leavingLeft; i++) {
			if (leftPedestrians.get(i).getY() > 47 && leftPedestrians.get(i).getTurned() == 0)
				leftPedestrians.get(i).up();
			else if (leftPedestrians.get(i).getY() == 47 && leftPedestrians.get(i).getTurned() == 0)
				leftPedestrians.get(i).turn(Direction.RIGHT);
			else if (leftPedestrians.get(i).getX() < 45 && leftPedestrians.get(i).getTurned() == 1)
				leftPedestrians.get(i).right();
			else if (leftPedestrians.get(i).getX() == 45 && leftPedestrians.get(i).getTurned() == 1)
				leftPedestrians.get(i).turn(Direction.RIGHT);
			else if (leftPedestrians.get(i).getY() < 400 && leftPedestrians.get(i).getTurned() == 2)
				leftPedestrians.get(i).down();
			else if (leftPedestrians.get(i).getY() == 400 && leftPedestrians.get(i).getTurned() == 2){
				leavingLeft--;
				leftPedestrians.remove(i);
				i--;
			}
		}
		for (int i = 0; i < leavingRight; i++) {
			if (rightPedestrians.get(i).getY() > 79 && rightPedestrians.get(i).getTurned() == 0)
				rightPedestrians.get(i).up();
			else if (rightPedestrians.get(i).getY() == 79 && rightPedestrians.get(i).getTurned() == 0)
				rightPedestrians.get(i).turn(Direction.LEFT);
			else if (rightPedestrians.get(i).getX() > -46 && rightPedestrians.get(i).getTurned() == 1)
				rightPedestrians.get(i).left();
			else if (rightPedestrians.get(i).getX() == -46 && rightPedestrians.get(i).getTurned() == 1)
				rightPedestrians.get(i).turn(Direction.LEFT);
			else if (rightPedestrians.get(i).getY() < 400  && rightPedestrians.get(i).getTurned() == 2)
				rightPedestrians.get(i).down();
			else if (rightPedestrians.get(i).getY() == 400 && rightPedestrians.get(i).getTurned() == 2){
				leavingRight--;
				rightPedestrians.remove(i);
				i--;
			}
		}

	}

	public void draw() {
		for (Pedestrian p : leftPedestrians)
			p.draw();
		for (Pedestrian p : rightPedestrians)
			p.draw();
	}

}
