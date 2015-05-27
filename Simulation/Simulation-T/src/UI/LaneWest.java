package UI;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

public class LaneWest {
	
	private int nb;
	private Orientation orientation;
	private int leaving;

	private ArrayList<Car> cars = new ArrayList<Car>();
	private ArrayList<Direction> directions = new ArrayList<Direction>();
	
	private int carSize = 30; //square
	
	public LaneWest(Orientation orientation){
		this.orientation = orientation;
		nb = 0;
		leaving = 0;
	}
	
	public void addCar(Direction lane) throws SlickException{
		cars.add(new Car(4, 400, orientation));
		nb ++;
		directions.add(lane);
	}
	
	public void removeCar(Direction lane) throws SlickException{
		switch(lane){
		case LEFT:
			if (nb > 0){
				nb--;
				leaving++;
			}
			break;
		case UP:
			if (nb > 0){
				nb--;
				leaving++;
			}
			break;
		case RIGHT:
			if (nb > 0){
				nb--;
				leaving++;
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
		for (int i = cars.size()-nb; i < cars.size(); i++){
			if (cars.get(i).getY() > 45 + j*(carSize+2))
				cars.get(i).up();
			j++;
		}
		
	}
	
	public void updateLeaving(){
		for (int i = 0; i < leaving; i++){
			switch (directions.get(i)){
			case LEFT:
				if (cars.get(i).getY() > (-30-4) && ! cars.get(i).getTurned())
					cars.get(i).up();
				else if (cars.get(i).getY() == (-30-4) && ! cars.get(i).getTurned()){
					cars.get(i).turn(3);
					cars.get(i).left();
				}
				else if (cars.get(i).getX() > (-30-400))
					cars.get(i).left();
				else{
					cars.remove(i);
					directions.remove(i);
					leaving--;
					i--;
				}
				break;
				
			case UP:
				
			case RIGHT:
				
			case DOWN:
				
			}
		}
		for (int i = 0; i < leaving; i++){
			if (cars.get(i).getY() > 83 && ! cars.get(i).getTurned())
				cars.get(i).up();
			else if (cars.get(i).getY() == (83) && ! cars.get(i).getTurned()){
				cars.get(i).turn(1);
				cars.get(i).right();
			}
			else if (cars.get(i).getX() < (400))
				cars.get(i).right();
			else{
				cars.remove(i);
				leaving--;
				i--;
			}
		}
		for (int i = 0; i < leaving; i++){
			if (cars.get(i).getY() > -400)
				cars.get(i).up();
			else{
				cars.remove(i);
				leaving--;
				i--;
			}
		}
		
	}
	
	public void draw(){
		for (Car c: cars)
			c.draw();
		for (Car c: cars)
			c.draw();
		for (Car c: cars)
			c.draw();
	}

}
