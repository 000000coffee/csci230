public class Animal extends Forest {
	protected String animalType;
	Animal() {
		this.animalType = null;
	}
	Animal(String type) {
		this.animalType = type;
	}
	public void printAnimal() {
		System.out.println(animalType);
	}
	public String getType() {
		return this.animalType;
	}
}
