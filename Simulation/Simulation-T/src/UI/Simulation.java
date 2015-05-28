/**
 * 
 */
package UI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.LinkOption;
import java.util.ArrayList;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * @author Simon
 * 
 */
public class Simulation extends BasicGame {

	private GameContainer container;
	private Image background;
	private int o = 0;
	private CrossRoad cr;
	long tick = 0;
	long wait = 0;
	private ArrayList<ArrayList<Action>> actions;

	// orientation 0: nord; 1: ouest; 2: sud; 3:est

	public Simulation(ArrayList<ArrayList<Action>> a) {
		super("Simulation");
		actions = a;
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.container = container;
		background = new Image("images/roadT.png");
		cr = new CrossRoad();
		setSpeed(3);
		background.draw();
		
		
	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		cr.draw();
	}
	
	@Override
	public void update(GameContainer arg0, int delta) throws SlickException {
		cr.update();
		//System.out.print(tick);
		//System.out.print(" - ");
		//System.out.println(wait);
		if (tick >= wait){
			doAction();
		}
		tick++;
	}

	@Override
	public void keyReleased(int key, char c) {
		switch (key) {
		case Input.KEY_ESCAPE:
			container.exit();
			break;
		}

	}
	@Override
	public void keyPressed(int key, char c) {
		try {
			switch (key) {
			case Input.KEY_Z: cr.addCar(Orientation.EAST, Direction.LEFT); break;
			case Input.KEY_E: cr.addCar(Orientation.EAST, Direction.UP); break;
			case Input.KEY_S: cr.removeCar(Orientation.EAST); break;
			case Input.KEY_R: cr.addPedestrian(Direction.LEFT); break;
			case Input.KEY_T: cr.addPedestrian(Direction.RIGHT); break;
			case Input.KEY_F: cr.removePedestrian(Direction.LEFT); break;
			case Input.KEY_G: cr.removePedestrian(Direction.RIGHT); break;
			case Input.KEY_Y: cr.setTL(LightEnum.GREEN, Orientation.EAST); break;
			case Input.KEY_U: cr.setTL(LightEnum.RED, Orientation.EAST); break;
			}
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setSpeed(int s) {
		container.setMaximumLogicUpdateInterval(s);
		container.setMinimumLogicUpdateInterval(s);
	}
	
	private void doAction(){
		
	}
	
	
	public void simulate(ArrayList<String> query) throws SlickException, InterruptedException{
		//0: PROCESS
		//0: FROM
		//1: TO
		//2 : GUARD
		//3: SYNCHRONISATION
		//4: UPDATE
		//System.out.println(query);
		//System.out.println(query);
		switch (query.get(0)){
		case  "CarGeneratorEast":
			simulateCarGenerator(query, Orientation.EAST);
			break;
		case  "CarGeneratorSouth":
			simulateCarGenerator(query, Orientation.SOUTH);
			break;
		case  "CarGeneratorWest":
			simulateCarGenerator(query, Orientation.WEST);
			break;
		case "PedestrianGeneratorEast":
			simulatePedestrianGenerator(query);
			break;
		}
		
	}
	
	private void simulateCarGenerator(ArrayList<String> query, Orientation o) throws SlickException, InterruptedException{
		String tmp;
		switch (query.get(1)){
		case "AcceptCar":
			if (query.get(2).equals("AcceptCar")){
				tmp = query.get(5).split(",")[0];
				tmp = tmp.split(" ")[2];
				switch(tmp){
				case "L":
					cr.addCar(o, Direction.LEFT);
					break;
				case "U":
					cr.addCar(o, Direction.UP);
					break;
				case "R":
					cr.addCar(o, Direction.RIGHT);
					break;
				}
			}
			if (query.get(2).equals("CarCrossing")){
				cr.setTL(LightEnum.GREEN, o);
				cr.removeCar(o);
				wait = tick + 500;
			}
			break;
		}
	}
	
	private void simulatePedestrianGenerator(ArrayList<String> query) throws SlickException, InterruptedException{
		String tmp;
		switch (query.get(2)){
		case "PushButton":
			cr.addPedestrian(Direction.LEFT);
		break;
		case "Cross":
			cr.setPedestrian(LightEnum.GREEN);
			cr.removePedestrian(Direction.LEFT);
			wait = tick + 500;
		break;
		}
	}

}
