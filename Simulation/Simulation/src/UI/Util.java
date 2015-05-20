package UI;

public class Util {
	
	public static int[] adjustPos(int x, int y, int orientation){
		int temp;
		int height = 0;
		int width = 0;
		switch (orientation){
		case 0:
			x += 400;
			y += 400;
			break;
		case 1:
			temp = y;
			y = 400+x;
			x = 400-temp;
			break;
		case 2:
			y = 400-y;
			x = 400-x;
			break;
		case 3:
			temp = y;
			y = 400-x;
			x = 400+temp;
			break;
		}
		int[] res = {x, y};
		return res;
	}

}
