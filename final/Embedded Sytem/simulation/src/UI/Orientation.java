package UI;

public enum Orientation {
	SOUTH, WEST, NORTH, EAST;
	
	private static Orientation[] vals = values();
    public Orientation next()
    {
        return vals[(this.ordinal()+1) % vals.length];
    }
}
