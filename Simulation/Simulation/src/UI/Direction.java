package UI;

public enum Direction {
	NORTH, WEST, SOUTH, EAST;
	
	private static Direction[] vals = values();
    public Direction next()
    {
        return vals[(this.ordinal()+1) % vals.length];
    }
}
