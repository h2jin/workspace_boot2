package ch04;

public class MainTest {
	public static void main(String[] args) {
		
		Television television = new Television();
		Refrigerator refrigerator = new Refrigerator();
		ToyRobot robot = new ToyRobot();
		
		RemoteController[] remoteControllers = new RemoteController[3];
		remoteControllers[0] = television;
		remoteControllers[1] = refrigerator;
		remoteControllers[3] = robot;
		
		for(int i = 0; i< remoteControllers.length; i++) {
			remoteControllers[i].turnOn();
		}
		for(int i = 0; i< remoteControllers.length; i++) {
			remoteControllers[i].turnOff();
		}
		

	}
	
	
}
