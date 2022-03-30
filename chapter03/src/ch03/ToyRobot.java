package ch03;

public class ToyRobot extends HomeAppliances implements Remotecontroller {

	String name;

	public ToyRobot() {
		this.name = "건담로봇";
	}

	@Override
	public void turnOn() {
		System.out.println("로봇 ON");

	}

	@Override
	public void turnOff() {
		System.out.println("로봇 OFF");

	}

}
