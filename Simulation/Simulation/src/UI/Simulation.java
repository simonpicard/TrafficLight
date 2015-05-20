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
    
    //orientation 0: nord; 1: ouest; 2: sud; 3:est

	public Simulation() {
        super("Simulation");
    }
	
    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        background = new Image("images/road.png");
        roads.add(new Road(0));
        roads.add(new Road(1));
        roads.add(new Road(2));
        roads.add(new Road(3));
    }
    
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.drawImage(background,0,0);
		//g.drawImage(pLightDown.getLight(), pLightDown.getX(), pLightDown.getY());
		for (Road r : roads)
			r.draw();

	}

    @Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
    }
    
	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub

	}

}
