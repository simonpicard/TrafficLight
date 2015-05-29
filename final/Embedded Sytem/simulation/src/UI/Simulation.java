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
	private ArrayList<ArrayList<Action>> actions;
	private int current = 0;
	private Boolean go = false;
	private long tick = 0, wait = 0;

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
		setSpeed(5);

	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		background.draw();
		cr.draw();
	}

	@Override
	public void update(GameContainer arg0, int delta) throws SlickException {
		cr.update();
		// System.out.print(tick);
		// System.out.print(" - ");
		// System.out.println(wait);
		if (tick >= wait && current < actions.size() && go) {
			doActions();
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
		switch (key) {
		case Input.KEY_Z:
			go = true;
			current = 0;
			break;
		case Input.KEY_E:
			go = go?false:true;
			break;
		}

	}

	private void setSpeed(int s) {
		container.setMaximumLogicUpdateInterval(s);
		container.setMinimumLogicUpdateInterval(s);
	}

	private void doActions() throws SlickException {
		for (Action a : actions.get(current))
			doAction(a);
		current++;
		wait = tick + 500;
	}

	private void doAction(Action a) throws SlickException {
		switch (a.getAction()) {
		case "addCar":
			cr.addCar(a.getOrientation(), a.getDirection());
			break;
		case "removeCar":
			cr.removeCar(a.getOrientation());
			break;
		case "addPedestrian":
			cr.addPedestrian(Direction.LEFT);
			break;
		case "removePedestrian":
			cr.removePedestrian(Direction.LEFT);
			break;
		case "setTL":
			cr.setTL(a.getLightColor(), a.getOrientation());
			break;
		case "setPedestrian":
			cr.setPedestrian(a.getLightColor());
			break;
		}
	}

}
