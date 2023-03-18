package Management;


import java.util.ArrayList;

public class Barn {
	public static int maxCapacity = 40;

	private int population; // inek 2 koyun 1 <<40>>
	private Worker[] workers;
	private ArrayList<Sheep> sheeps;
	private ArrayList<Cow> cows;

	public Barn() {
		this.population = 0;
		this.workers = new Worker[2];
		this.sheeps = new ArrayList<>();
		this.cows = new ArrayList<>();
	}
	public boolean checkCowGender(){
		for (Cow cow : cows) {
			if(cow.getGender() == 'f')
				return true;
		}
		return false;
	}
	public boolean checkSheepGender(){
		for (Sheep sheep : sheeps) {
			if(sheep.getGender() == 'f')
				return true;
		}
		return false;
	}
	public boolean isFull(char type) {

		if (type == 'c' && population < maxCapacity - 1) {
			return false;
		} else if (population < maxCapacity) {// type =='s'
			return false;
		}
		return true;

	}

	public boolean addSheep(Sheep sheep) {
		if (!isFull('s')) {
			sheeps.add(sheep);
			population += 1;
			return true;
		}
		return false;
	}

	public boolean addCow(Cow cow) {
		if (!isFull('c')) {
			cows.add(cow);
			population += 2;
			return true;
		}
		return false;
	}
	
	public boolean deleteSheep(int id) {
		for (Sheep sheep : sheeps) {
			if (sheep.getId() == id) {
				sheeps.remove(sheep);
				population -= 1;
				return true;
			}
		}return false;
	}

	public boolean deleteCow(int id) {
		for (Cow cow : cows) {
			if (cow.getId() == id) {
				cows.remove(cow);
				population -= 2;
				return true;
			}
		}return false;
	}

	public Animal returnInfoAnimal(int id) {
		Animal returnAnimal = null;
		for (Sheep sheep : sheeps) {
			if (sheep.getId() == id) {
				returnAnimal = sheep;
				break;
			}
		}
		if (returnAnimal == null) {
			for (Cow cow : cows) {
				if (cow.getId() == id) {
					returnAnimal = cow;
					break;
				}
			}
		}
		return returnAnimal;
	}

	public int getPopulation() {
		return population;
	}

	public ArrayList<Sheep> getSheeps() {
		return sheeps;
	}

	public ArrayList<Cow> getCows() {
		return cows;
	}
	public Worker[] getWorkers() {
		return workers;
	}
	public void setWorkers(Worker[] workers) {
		this.workers = workers;
	}
	
}
