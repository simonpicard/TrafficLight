package UI;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sidewalk {

	private int nbLeft;
	private int nbRight;
	private Direction orientation;
	private int leavingLeft;
	private int leavingRight;

	private ArrayList<Pedestrian> leftPedestrians = new ArrayList<Pedestrian>();
	private ArrayList<Pedestrian> rightPedestrians = new ArrayList<Pedestrian>();

	private int pedestrianSize = 9; // square

	public Sidewalk(Direction orientation) {
		this.orientation = orientation;
		nbLeft = 0;
		nbRight = 0;
		leavingLeft = 0;
		leavingRight = 0;
	}

	public void addPedestrian(Way lane) throws SlickException {
		switch (lane) {
		case LEFT:
			leftPedestrians.add(new Pedestrian(125, 400, orientation.next()));
			nbLeft++;
			break;
		case RIGHT:
			rightPedestrians.add(new Pedestrian(125, 400, orientation));
			nbRight++;
			break;
		}
	}

	public void removePedestrian(Way lane) throws SlickException {
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
		// updateLeaving();
		updateLanes();
	}

	public void updateLanes() {
		int j = 0;
		for (int i = leftPedestrians.size() - nbLeft; i < leftPedestrians
				.size(); i++) {
			//System.out.println(leftPedestrians.get(i).getTurned());
			if (leftPedestrians.get(i).getY() > 125 + j * (pedestrianSize + 2) && leftPedestrians.get(i).getTurned() == 0)
				leftPedestrians.get(i).up();
			else if (leftPedestrians.get(i).getY() == 125 && leftPedestrians.get(i).getTurned() == 0)
				leftPedestrians.get(i).turn(1);
			else if (leftPedestrians.get(i).getX() < 185 + j * (pedestrianSize + 2) && leftPedestrians.get(i).getTurned() == 1)
				leftPedestrians.get(i).right();
			else if (leftPedestrians.get(i).getX() == 185 && leftPedestrians.get(i).getTurned() == 1)
				leftPedestrians.get(i).turn(3);
			else if (leftPedestrians.get(i).getY() > 116 + j * (pedestrianSize + 2) && leftPedestrians.get(i).getTurned() == 2)
				leftPedestrians.get(i).up();
			j++;
		}
		j = 0;
		for (int i = rightPedestrians.size() - nbRight; i < rightPedestrians
				.size(); i++) {
			if (rightPedestrians.get(i).getY() > 185 + j * (pedestrianSize + 2))
				rightPedestrians.get(i).up();
			else if (rightPedestrians.get(i).getY() == 185 && leftPedestrians.get(i).getTurned() == 0)
				rightPedestrians.get(i).turn(1);
			j++;
		}

	}

	/*
	 * public void updateLeaving(){ for (int i = 0; i < leavingLeft; i++){ if
	 * (leftCars.get(i).getY() > (-30-4) && ! leftCars.get(i).getTurned())
	 * leftCars.get(i).up(); else if (leftCars.get(i).getY() == (-30-4) && !
	 * leftCars.get(i).getTurned()){ leftCars.get(i).turn(3);
	 * leftCars.get(i).left(); } else if (leftCars.get(i).getX() > (-30-400))
	 * leftCars.get(i).left(); else{ leftCars.remove(i); leavingLeft--; i--; } }
	 * for (int i = 0; i < leavingRight; i++){ if (rightCars.get(i).getY() > 83
	 * && ! rightCars.get(i).getTurned()) rightCars.get(i).up(); else if
	 * (rightCars.get(i).getY() == (83) && ! rightCars.get(i).getTurned()){
	 * rightCars.get(i).turn(1); rightCars.get(i).right(); } else if
	 * (rightCars.get(i).getX() < (400)) rightCars.get(i).right(); else{
	 * rightCars.remove(i); leavingRight--; i--; } } for (int i = 0; i <
	 * leavingUp; i++){ if (upCars.get(i).getY() > -400) upCars.get(i).up();
	 * else{ upCars.remove(i); leavingUp--; i--; } }
	 */

	public void draw() {
		for (Pedestrian p : leftPedestrians)
			p.draw();
		for (Pedestrian p : rightPedestrians)
			p.draw();
	}

}
