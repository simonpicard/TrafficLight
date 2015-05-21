package UI;

public class Util {
	
	public static int[] adjustPos(int x, int y, Direction orientation){
		int temp;
		//System.out.println(orientation);
		switch (orientation){
		case NORTH:
			x += 400;
			y += 400;
			break;
		case WEST:
			temp = y;
			y = 400+x;
			x = 400-temp;
			break;
		case SOUTH:
			y = 400-y;
			x = 400-x;
			break;
		case EAST:
			temp = y;
			y = 400-x;
			x = 400+temp;
			break;
		}
		int[] res = {x, y};
		return res;
	}

}
