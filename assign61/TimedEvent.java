package assign61;

import java.io.Serializable;

public class TimedEvent implements Runnable, Serializable {

	private String name;
	private double timeout;
	private TimedEventHandler handler;

	public TimedEvent(String name, double timeout, TimedEventHandler handler) {
		this.name = name;
		this.timeout = timeout;
		this.handler = handler;
	}

	public void run() {
		boolean running = true;
		while (running) {
			try {
				Thread.sleep((long) (timeout * 1000));
				handler.handleEvent(this.name);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(name + " was stopped");
				// e.printStackTrace();
				running = false;
			}
		}
	}
}
