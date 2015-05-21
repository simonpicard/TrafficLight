package UI;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

public class Lane {
	
	private int nbLeft;
	private int nbUp;
	private int nbRight;
	private Direction orientation;
	private int leavingLeft;
	private int leavingUp;
	private int leavingRight;

	private ArrayList<Car> leftCars = new ArrayList<Car>();
	private ArrayList<Car> upCars = new ArrayList<Car>();
	private ArrayList<Car> rightCars = new ArrayList<Car>();
	
	private int carSize = 30; //square
	
	public Lane(Direction orientation){
		this.orientation = orientation;
		nbLeft = 0;
		nbUp = 0;
		nbRight = 0;
		leavingLeft = 0;
		leavingUp = 0;
		leavingRight = 0;
	}
	
	public void addCar(Way lane) throws SlickException{
		switch(lane){
		case LEFT:
			leftCars.add(new Car(4, 400, orientation));
			nbLeft ++;
			break;
		case MIDDLE:
			upCars.add(new Car(43, 400, orientation));
			nbUp++;
			break;
		case RIGHT:
			rightCars.add(new Car(83, 400, orientation));
			nbRight++;
			break;
		}
	}
	
	public void removeCar(Way lane) throws SlickException{
		switch(lane){
		case LEFT:
			if (nbLeft > 0){
				nbLeft--;
				leavingLeft++;
			}
			break;
		case MIDDLE:
			if (nbUp > 0){
				nbUp--;
				leavingUp++;
			}
			break;
		case RIGHT:
			if (nbRight > 0){
				nbRight--;
				leavingRight++;
			}
			break;
		}
	}
	
	public void update(){
		updateLeaving();
		updateLanes();
	}
	
	public void updateLanes(){
		int j = 0;
		for (int i = leftCars.size()-nbLeft; i < leftCars.size(); i++){
			if (leftCars.get(i).getY() > 208 + j*(carSize+2))
				leftCars.get(i).up();
			j++;
		}
		j = 0;
		for (int i = upCars.size()-nbUp; i < upCars.size(); i++){
			if (upCars.get(i).getY() > 208 + j*(carSize+2))
				upCars.get(i).up();
			j++;
		}
		j = 0;
		for (int i = rightCars.size()-nbRight; i < rightCars.size(); i++){
			if (rightCars.get(i).getY() > 208 + j*(carSize+2))
				rightCars.get(i).up();
			j++;
		}
		
	}
	
	public void updateLeaving(){
		for (int i = 0; i < leavingLeft; i++){
			if (leftCars.get(i).getY() > (-30-4) && ! leftCars.get(i).getTurned())
				leftCars.get(i).up();
			else if (leftCars.get(i).getY() == (-30-4) && ! leftCars.get(i).getTurned()){
				leftCars.get(i).turn(3);
				leftCars.get(i).left();
			}
			else if (leftCars.get(i).getX() > (-30-400))
				leftCars.get(i).left();
			else{
				leftCars.remove(i);
				leavingLeft--;
				i--;
			}
		}
		for (int i = 0; i < leavingRight; i++){
			if (rightCars.get(i).getY() > 83 && ! rightCars.get(i).getTurned())
				rightCars.get(i).up();
			else if (rightCars.get(i).getY() == (83) && ! rightCars.get(i).getTurned()){
				rightCars.get(i).turn(1);
				rightCars.get(i).right();
			}
			else if (rightCars.get(i).getX() < (400))
				rightCars.get(i).right();
			else{
				rightCars.remove(i);
				leavingRight--;
				i--;
			}
		}
		for (int i = 0; i < leavingUp; i++){
			if (upCars.get(i).getY() > -400)
				upCars.get(i).up();
			else{
				upCars.remove(i);
				leavingUp--;
				i--;
			}
		}
		
	}
	
	public void draw(){
		for (Car c: leftCars)
			c.draw();
		for (Car c: upCars)
			c.draw();
		for (Car c: rightCars)
			c.draw();
	}

}
