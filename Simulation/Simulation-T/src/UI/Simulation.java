/**
 * 
 */
package UI;

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

	// orientation 0: nord; 1: ouest; 2: sud; 3:est

	public Simulation() {
		super("Simulation");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.container = container;
		background = new Image("images/roadT.png");
		cr = new CrossRoad();
		setSpeed(1);
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

	}

	private void setSpeed(int s) {

		container.setMaximumLogicUpdateInterval(s);
		container.setMinimumLogicUpdateInterval(s);

	}

}
