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
	private ArrayList<Road> roads = new ArrayList<Road>();
	private int o = 0;

	// orientation 0: nord; 1: ouest; 2: sud; 3:est

	public Simulation() {
		super("Simulation");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.container = container;
		background = new Image("images/road.png");
		roads.add(new Road(Direction.NORTH));
		roads.add(new Road(Direction.WEST));
		roads.add(new Road(Direction.SOUTH));
		roads.add(new Road(Direction.EAST));
		setSpeed(10);
	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.drawImage(background, 0, 0);
		// g.drawImage(pLightDown.getLight(), pLightDown.getX(),
		// pLightDown.getY());
		for (Road r : roads)
			r.draw();

	}

	@Override
	public void keyReleased(int key, char c) {
		try {
			switch (key) {
			case Input.KEY_ESCAPE:
				container.exit();
				break;
			case Input.KEY_A: roads.get(o).addCar(Way.LEFT); break;
			case Input.KEY_Z: roads.get(o).addCar(Way.MIDDLE); break;
			case Input.KEY_E: roads.get(o).addCar(Way.RIGHT); break;
			case Input.KEY_Q: roads.get(o).removeCar(Way.LEFT); break;
			case Input.KEY_S: roads.get(o).removeCar(Way.MIDDLE); break;
			case Input.KEY_D: roads.get(o).removeCar(Way.RIGHT); break;
			case Input.KEY_R: roads.get(o).addPedestrian(Way.LEFT); break;
			case Input.KEY_T: roads.get(o).addPedestrian(Way.RIGHT); break;
			case Input.KEY_W: o++; o %= 4; break;
			}
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(GameContainer arg0, int delta) throws SlickException {
		for (Road r : roads)
			r.update();

	}

	private void setSpeed(int s) {

		container.setMaximumLogicUpdateInterval(s);
		container.setMinimumLogicUpdateInterval(s);

	}

}
