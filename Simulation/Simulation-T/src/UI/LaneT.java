package UI;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

public class LaneT {
	
	private int nb;
	private Orientation orientation;
	private int leaving;
	
	private int offset = 0;

	private ArrayList<Car> cars = new ArrayList<Car>();
	private ArrayList<Direction> directions = new ArrayList<Direction>();
	
	private int carSize = 30; //square
	
	public LaneT(Orientation orientation){
		this.orientation = orientation;
		nb = 0;
		leaving = 0;
		if (orientation == Orientation.EAST)
			offset = 49;
	}
	
	public void addCar(Direction lane) throws SlickException{
		if (orientation == Orientation.WEST && lane != Direction.LEFT || orientation == Orientation.SOUTH && lane != Direction.UP || orientation == Orientation.EAST && lane != Direction.RIGHT){
			cars.add(new Car(4, 400+nb*(carSize+2), orientation));
			nb ++;
			directions.add(lane);
		}
	}
	
	public void removeCar() throws SlickException{
		if (nb > 0){
			nb--;
			leaving++;
		}
	}
	
	public void update(){
		updateLeaving();
		updateLanes();
	}
	
	public void updateLanes(){
		int j = 0;
		for (int i = cars.size()-nb; i < cars.size(); i++){
			if (cars.get(i).getY() > 45 + offset + j*(carSize+2))
				cars.get(i).up();
			j++;
		}
		
	}
	
	public void updateLeaving(){
		for (int i = 0; i < leaving; i++){
			switch (directions.get(i)){
			case RIGHT:
				if (cars.get(i).getY() > (4) && ! cars.get(i).getTurned())
					cars.get(i).up();
				else if (cars.get(i).getY() == (4) && ! cars.get(i).getTurned()){
					cars.get(i).turn(1);
					cars.get(i).right();
				}
				else if (cars.get(i).getX() < (400))
					cars.get(i).right();
				else{
					cars.remove(i);
					directions.remove(i);
					leaving--;
					i--;
				}
				break;
			case UP:
				if (cars.get(i).getY() > -400)
					cars.get(i).up();
				else{
					cars.remove(i);
					directions.remove(i);
					leaving--;
					i--;
				}
				break;
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
				
			}
		}/*
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
		}*/
		
	}
	
	public void draw(){
		for (Car c: cars)
			c.draw();
	}


}
