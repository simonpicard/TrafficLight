package UI;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

public class CrossRoad {
	
	private ArrayList<Road> roads = new ArrayList<Road>();
	private PedestrianLight pedestrianTL;
	private UpLight upTLE;
	private UpLight upTLS;
	private UpLight upTLW;
	
	public CrossRoad() throws SlickException{
		init();
	}
	

	
	private void init() throws SlickException{
		pedestrianTL = new PedestrianLight(55, -110, Orientation.SOUTH);
		upTLE = new UpLight(-85, 60, Orientation.EAST);
		upTLS = new UpLight(-12, -121, Orientation.SOUTH);
		upTLW = new UpLight(85-24, 60, Orientation.WEST);
		roads.add(new Road(Orientation.WEST));
		roads.add(new Road(Orientation.SOUTH));
		roads.add(new Road(Orientation.EAST));
	}
	
	public LightColor getState(Orientation o){
		switch(o){
		case EAST:
			return upTLE.getState();
		case SOUTH:
			return upTLS.getState();
		case WEST:
			return upTLW.getState();
		}
		return null;
	}
	

	
	public void draw(){
		pedestrianTL.draw();
		upTLE.draw();
		upTLS.draw();
		upTLW.draw();
		for (Road r : roads)
			r.draw();
	}
	
	public void update(){
		for (Road r : roads)
			r.update();
	}
	
	public void setTL(LightColor le, Orientation o){
		switch(o){
		case EAST:
			upTLE.setState(le);
			break;
		case SOUTH:
			upTLS.setState(le);
			break;
		case WEST:
			upTLW.setState(le);
			break;
		}
	}
	
	public void setPedestrian(LightColor le){
		pedestrianTL.setState(le);
	}
	
	public void addCar(Orientation o, Direction d) throws SlickException{
		switch (o){
		case WEST:
			roads.get(0).addCar(d);
			break;
		case SOUTH:
			roads.get(1).addCar(d);
			break;
		case EAST:
			roads.get(2).addCar(d);
			break;
		}
	}
	
	public void removeCar(Orientation o) throws SlickException{
		switch (o){
		case WEST:
			roads.get(0).removeCar();
			break;
		case SOUTH:
			roads.get(1).removeCar();
			break;
		case EAST:
			roads.get(2).removeCar();
			break;
		}
	}
	
	public void addPedestrian(Direction d) throws SlickException{
		roads.get(2).addPedestrian(d);
	}
	
	public void removePedestrian(Direction d) throws SlickException{
		roads.get(2).removePedestrian(d);
	}

}
