package Management;
public class Chicken implements Animal {
	public static int idCounter = 2999;
	public static int costOfMeal = 1;
	private int id;
	private int monthAge;
	private Date birthDate;// degistirilecek
	private char gender;
	private int quality;
	private boolean isIll;
	private int incubationCounter;
	public static int maxChickValueDuringIncubation = 6;

	public Chicken(int quality, int monthAge) {
		this.id = idCounter++;
		this.monthAge = monthAge;
		this.gender = 'f';
		this.quality = quality;
		this.isIll = false;
		Date birthDate = null;
		if (Farm.currentDate.getMonth() - monthAge > 0) {
			birthDate = new Date(1, Farm.currentDate.getMonth() - monthAge, Farm.currentDate.getYear());
		} else {
			birthDate = new Date(1, Farm.currentDate.getMonth() + 12 - monthAge,
					Farm.currentDate.getYear() - 1);
		}
		this.birthDate = birthDate;
	}

	public Chicken(int id, Date date, char gender, int quality, int monthAge, int incubationCounter) {
		if (id > idCounter) {
			idCounter = id + 1;
		}
		this.id = id;
		this.monthAge = monthAge;
		this.gender = gender;
		this.quality = quality;
		this.isIll = false;
		this.birthDate = date;
		this.incubationCounter = incubationCounter;
	}
	@Override
	public Product animalProduct() {
		if (this.incubationCounter == 0) {
			switch (this.quality) {
			case 1:
				return new Product("Egg", "Chicken", 3);
			case 2:
				return new Product("Egg", "Chicken", 2);
			case 3:
				return new Product("Egg", "Chicken", 1);
			}
		}
		return null;
	}

	@Override
	public void breeding() {
		if(this.incubationCounter == 0 && this.gender == 'f')
			this.incubationCounter = 1;
	}

	@Override
	public int valueOfAnimal() {
		if(this.incubationCounter == 0)
			switch (this.quality) {
			case 3:
				if (this.monthAge < 3)
					return 30;
				else if (this.monthAge < 6)
					return 40;
				else if (this.monthAge < 12)
					return 60;
				else
					return 30;
			case 2:
				if (this.monthAge < 3)
					return 40;
				else if (this.monthAge < 6)
					return 50;
				else if (this.monthAge < 12)
					return 70;
				else
					return 40;
			case 1:
				if (this.monthAge < 3)
					return 50;
				else if (this.monthAge < 6)
					return 60;
				else if (this.monthAge < 12)
					return 80;
				else
					return 50;
			}
		return 0;
	}

	public int getId() {
		return id;
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

	public void setMonthAge(int age) {
		this.monthAge = age;
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

	public void setIll(boolean isIll) {
		this.isIll = isIll;
	}

	public int getIncubationCounter() {
		return incubationCounter;
	}

	public int getMonthAge() {
		return monthAge;
	}

	public void setIncubationCounter(int incubationCounter) {
		this.incubationCounter = incubationCounter;
	}

	@Override
	public String display() {
		return id + ";" + birthDate.toString() + ";" + gender + ";" + quality + ";" + monthAge + ";" + incubationCounter;
	}


}