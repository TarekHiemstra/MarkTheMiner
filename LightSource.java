package assign61;

public abstract class LightSource extends GameObject implements OnOff  {
	
	public LightSource(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	String name;
	boolean lampOn;
}
