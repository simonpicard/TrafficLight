package UI;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws SlickException {
		Parser p = new Parser("trace.formated", "traceOut.txt");
		new AppGameContainer(new Simulation(p.getActions()), 800, 800, false).start();

	}

}
