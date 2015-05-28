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
	private String pathIn, pathOut;
	private InputStream ips; 
	private InputStreamReader ipsr;
	private static BufferedReader br;
	long tick = 0;
	long wait = 0;
	boolean[] lights = {false, false, false, false};

	// orientation 0: nord; 1: ouest; 2: sud; 3:est

	public Simulation(String in, String out) {
		super("Simulation");
		pathIn = in;
		pathOut = out;
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.container = container;
		background = new Image("images/roadT.png");
		cr = new CrossRoad();
		setSpeed(3);
		Util.replaceRegex(pathIn, pathOut);
		try {
			ips = new FileInputStream(pathOut);
			ipsr=new InputStreamReader(ips);
			br=new BufferedReader(ipsr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//parseFile(pathOut);
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.drawImage(background, 0, 0);
		// g.drawImage(pLightDown.getLight(), pLightDown.getX(),
		// pLightDown.getY());
		cr.draw();
		

	}

	@Override
	public void keyReleased(int key, char c) {
		switch (key) {
		case Input.KEY_ESCAPE:
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
/*
	@Override
	public void keyPressed(int key, char c) {
		try {
			switch (key) {
			case Input.KEY_A: roads.get(o).addCar(Direction.LEFT); break;
			case Input.KEY_Z: roads.get(o).addCar(Direction.UP); break;
			case Input.KEY_E: roads.get(o).addCar(Direction.RIGHT); break;
			case Input.KEY_Q: roads.get(o).removeCar(Direction.LEFT); break;
			case Input.KEY_S: roads.get(o).removeCar(Direction.UP); break;
			case Input.KEY_D: roads.get(o).removeCar(Direction.RIGHT); break;
			case Input.KEY_R: roads.get(o).addPedestrian(Direction.LEFT); break;
			case Input.KEY_T: roads.get(o).addPedestrian(Direction.RIGHT); break;
			case Input.KEY_F: roads.get(o).removePedestrian(Direction.LEFT); break;
			case Input.KEY_G: roads.get(o).removePedestrian(Direction.RIGHT); break;
			case Input.KEY_W: o++; o %= 4; break;
			}
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/

	@Override
	public void update(GameContainer arg0, int delta) throws SlickException {
		cr.update();
		//System.out.print(tick);
		//System.out.print(" - ");
		//System.out.println(wait);
		if (tick >= wait){
			if (lights[Orientation.NORTH.ordinal()]){
				cr.setPedestrian(LightEnum.RED);
				lights[Orientation.NORTH.ordinal()] = false;
			}
			if (lights[Orientation.EAST.ordinal()]){
				cr.setTL(LightEnum.RED, Orientation.EAST);
				lights[Orientation.EAST.ordinal()] = false;
			}
			if (lights[Orientation.SOUTH.ordinal()]){
				cr.setTL(LightEnum.RED, Orientation.SOUTH);
				lights[Orientation.SOUTH.ordinal()] = false;
			}
			if (lights[Orientation.WEST.ordinal()]){
				cr.setTL(LightEnum.RED, Orientation.WEST);
				lights[Orientation.WEST.ordinal()] = false;
			}
			simule();
		}
		tick++;

	}

	private void setSpeed(int s) {
		container.setMaximumLogicUpdateInterval(s);
		container.setMinimumLogicUpdateInterval(s);
	}
	
	public void simule(){
		try{ 
			String ligne;
			ArrayList<String> query = new ArrayList<String>();
			String[] tmp1;
			String[] tmp2;
			String[] tmp3;
			if ((ligne= br.readLine())!=null){
				tmp1 = ligne.split(" -> ");
				tmp2 = tmp1[1].split(" \\{");
				tmp3 = tmp2[1].split(";");
				query.add(tmp1[0].split("\\.")[0]);
				query.add(tmp1[0].split("\\.")[1]);
				query.add(tmp2[0].split("\\.")[1]);
				query.add(tmp3[0]);
				query.add(tmp3[1].substring(1));
				query.add(tmp3[2].substring(1));
				simulate(query);
				query.clear();
			}
			else
				return;
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
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
				lights[o.ordinal()] = true;
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
			lights[Orientation.NORTH.ordinal()] = true;
		break;
		}
	}

}
