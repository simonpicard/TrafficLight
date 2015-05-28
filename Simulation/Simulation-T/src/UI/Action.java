package UI;

public class Action {

	private String action;
	private Orientation o;
	private Direction d;
	private LightEnum le;
	
	public Action(String action, Orientation o, Direction d, LightEnum le) {
		super();
		this.action = action;
		this.o = o;
		this.d = d;
		this.le = le;
	}
	
	public Action(String action, Orientation o, Direction d) {
		super();
		this.action = action;
		this.o = o;
		this.d = d;
		this.le = null;
	}
	
	public Action(String action, Orientation o, LightEnum le) {
		super();
		this.action = action;
		this.o = o;
		this.d = null;
		this.le = le;
	}
	
	public Action(String action, LightEnum le) {
		super();
		this.action = action;
		this.o = null;
		this.d = null;
		this.le = le;
	}
	
	public Action(String action, Orientation o) {
		super();
		this.action = action;
		this.o = o;
		this.d = null;
		this.le = null;
	}
	
	public Action(String action) {
		super();
		this.action = action;
		this.o = null;
		this.d = null;
		this.le = null;
	}


    public String toString() {
    	String res = "{"+action;
    	if (o != null)
    		res += ", "+o.toString();
    	if (d != null)
    		res += ", "+d.toString();
    	if (le != null)
    		res += ", "+le.toString();
    	return res+"}";
    }

	public String getAction() {
		return action;
	}

	public Orientation getOrientation() {
		return o;
	}

	public Direction getDirection() {
		return d;
	}

	public LightEnum getLightColor() {
		return le;
	}
}
