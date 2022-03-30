package ch03;

public class Televison extends HomeAppliances implements Remotecontroller {

	@Override
	public void turnOn() {
		System.out.println("TV ON");

	}

	@Override
	public void turnOff() {
		System.out.println("TV OFF");

	}

}
