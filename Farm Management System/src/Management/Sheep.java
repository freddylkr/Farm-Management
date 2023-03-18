package Management;
public class Sheep implements Animal {
	public static int idCounter = 1999;
	public static int costOfMeal = 3;
	private int id;
	private int age;
	private Date birthDate;
	private char gender;
	private int quality;
	private boolean isIll;
	private int pregnancyCounter;

	public Sheep(int quality, int age) {
		this.id = idCounter++;
		this.age = age;
		this.gender = 'f';
		this.quality = quality;
		this.isIll = false;
		this.birthDate = new Date(1, 1, Farm.currentDate.getYear() - age);
		this.pregnancyCounter = 0;
	}

	public Sheep(int id, Date birthDate, char gender, int quality, int age, int pregnancyCounter) {
		if (id > idCounter) {
			idCounter = id + 1;
		}
		this.id = id;
		this.age = age;
		this.gender = gender;
		this.quality = quality;
		this.isIll = false;
		this.birthDate = birthDate;
		this.pregnancyCounter = pregnancyCounter;
	}


	@Override
	public Product animalProduct() {
		if(Farm.currentDate.getDay() == 1 && Farm.currentDate.getMonth() == 1)
			switch (this.quality) {
			case 1:
				return new Product("Wool", "Sheep", 150);
			case 2:
				return new Product("Wool", "Sheep", 120);
			case 3:
				return new Product("Wool", "Sheep", 100);
			}
		
		if (this.pregnancyCounter == 0) {
			switch (this.quality) {
			case 1:
				return new Product("Milk", "Sheep", 15);
			case 2:
				return new Product("Milk", "Sheep", 12);
			case 3:
				return new Product("Milk", "Sheep", 10);
			}
		}
		return null;
	}
	@Override
	public void breeding() {
		if(this.pregnancyCounter == 0 && this.gender == 'f')
			this.pregnancyCounter = 1;
	}


	@Override
	public int valueOfAnimal() {
		if(this.pregnancyCounter == 0)
			switch (this.quality) {
			case 3:
				if (this.age < 1)
					return 700;
				else if (this.age < 3)
					return 1000;
				else if (this.age < 5)
					return 2000;
				else
					return 1500;
			case 2:
				if (this.age < 1)
					return 900;
				else if (this.age < 3)
					return 1500;
				else if (this.age < 5)
					return 2500;
				else
					return 2000;
			case 1:
				if (this.age < 1)
					return 1000;
				else if (this.age < 3)
					return 2000;
				else if (this.age < 5)
					return 3000;
				else
					return 2500;
			}
		return 0;
	}

	public int getId() {
		return id;
	}

	public int getAge() {
		return age;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public char getGender() {
		return gender;
	}

	public int getQuality() {
		return quality;
	}

	public boolean isIll() {
		return isIll;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}
	
	public void setIll(boolean ill) {
		this.isIll = ill;
	}

	public int getPregnancyCounter() {
		return pregnancyCounter;
	}

	public void setPregnancyCounter(int pregnancyCounter) {
		this.pregnancyCounter = pregnancyCounter;
	}

	@Override
	public String display() {
		return id + ";" + birthDate.toString() + ";" + gender + ";" + quality + ";" + age + ";" + pregnancyCounter;
	}

}
