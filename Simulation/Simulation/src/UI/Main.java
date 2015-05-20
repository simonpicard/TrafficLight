package UI;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws SlickException {
        new AppGameContainer(new Simulation(), 800, 800, false).start();

	}

}
