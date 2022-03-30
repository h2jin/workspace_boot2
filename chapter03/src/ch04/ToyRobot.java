package ch04;

public class ToyRobot implements RemoteController{

	@Override
	public void turnOn() {
		System.out.println("로봇 ON");
		
	}

	@Override
	public void turnOff() {
		System.out.println("로봇 Off");
		
	}

}
