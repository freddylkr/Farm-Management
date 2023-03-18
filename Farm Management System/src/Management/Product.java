package Management;
public class Product {
	private String productName;
	private String animalType;
	private int dayCounter;
	private int price;

	public Product(String productName, String animalType, int price) {
		this.productName = productName;
		this.animalType = animalType;
		this.dayCounter = 0;
		this.price = price;
	}

	public Product(String productName, String animalType, int dayCounter, int price) {
		this.productName = productName;
		this.animalType = animalType;
		this.dayCounter = dayCounter;
		this.price = price;
	}

	public String getAnimalType() {
		return animalType;
	}

	public String getProductName() {
		return productName;
	}

	public int getPrice() {
		return price;
	}

	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDayCounter() {
		return dayCounter;
	}

	public void setDayCounter(int dayCounter) {
		this.dayCounter = dayCounter;
	}

	public String display() {
		return productName + ";" + animalType + ";" + dayCounter + ";" + price;
	}
}