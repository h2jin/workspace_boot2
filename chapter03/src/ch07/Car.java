package ch07;

public class Car {

	private String carName;

	public Car(String carName) {
		this.carName = carName;
	}

	@Override
	public String toString() {
		return "Car [CarName=" + carName + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Car) {
			Car tempCar = (Car) obj;
			String carName = tempCar.carName;
			if (this.carName == tempCar.carName) {
				return true;
			} else {
				return false;
			}
		}
		return false;

	}

}
