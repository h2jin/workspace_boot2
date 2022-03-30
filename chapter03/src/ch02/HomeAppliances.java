package ch02;

public abstract class HomeAppliances {

	int width;
	int height;
	String color;

	public abstract void turnOn();

	public abstract void turnOff();
	// 비워두고 각각의 객체에서 오버라이딩하면 됨.

}
