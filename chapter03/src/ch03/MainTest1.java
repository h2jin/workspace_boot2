package ch03;

public class MainTest1 {

	public static void main(String[] args) {

		Televison televison = new Televison();
		Refrigerator refrigerator = new Refrigerator();
		ToyRobot toyRobot = new ToyRobot();

//		televison.turnOn();
//		refrigerator.turnOn();
//		toyRobot.turnOn();
//		System.out.println("===================");
//		televison.turnOff();
//		refrigerator.turnOff();
//		toyRobot.turnOff();

		Remotecontroller[] remotecontrollers = new Remotecontroller[3];
		remotecontrollers[0] = televison;
		remotecontrollers[1] = refrigerator;
		remotecontrollers[2] = toyRobot;

		for (int i = 0; i < remotecontrollers.length; i++) {
			remotecontrollers[i].turnOn();
		}

		System.out.println("====================");
		for (int i = 0; i < remotecontrollers.length; i++) {
			remotecontrollers[i].turnOff();
		}

	}

}
