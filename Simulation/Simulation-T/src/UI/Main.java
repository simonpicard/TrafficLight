package UI;

import org.newdawn.slick.AppGameContainer;
import Parser.Parser;
import org.newdawn.slick.SlickException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws SlickException {
        new AppGameContainer(new Simulation("../Parser/trace.formated", "../Parser/traceOut.txt"), 800, 800, false).start();
		//Util.replaceRegex("../Parser/trace.formated", "../Parser/traceOut.txt");

	}

}
