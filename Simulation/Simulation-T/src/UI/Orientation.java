package UI;

public enum Orientation {
	NORTH, WEST, SOUTH, EAST;
	
	private static Orientation[] vals = values();
    public Orientation next()
    {
        return vals[(this.ordinal()+1) % vals.length];
    }
}
