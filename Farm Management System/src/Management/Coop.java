package Management;


import java.util.ArrayList;

public class Coop {
	public static int maxCapacity = 20;
	
	private int population; // tavuk 1 <<20>>
	private Worker worker;
	private ArrayList<Chicken> chickens;

	public Coop() {
		this.population = 0;
		this.chickens = new ArrayList<>();
	}

	public boolean isFull() {
		if (population < maxCapacity) {
			return false;
		}
		return true;
	}
	
	public boolean addChicken(Chicken chicken) {
		if (!isFull()) {
			chickens.add(chicken);
			this.population += 1;
			return true;
		}
		return false;
	}
	public boolean deleteChicken(int id) {
		for (Chicken chicken : chickens) {
			if (chicken.getId() == id) {
				chickens.remove(chicken);
				this.population -= 1;
				return true;
			}
		}return false;
	}

	public Animal returnInfoAnimal(int id) {
		for (Chicken chicken : chickens) {
			if (chicken.getId() == id) {
				return chicken;
			}
		}
		return null;

	}
	public boolean checkChickenGender(){
		for (Chicken chicken : chickens) {
			if(chicken.getGender() == 'f')
				return true;
		}
		return false;
	}

	public Worker getWorker() {
		return worker;
	}

	public int getPopulation() {
		return population;
	}

	public ArrayList<Chicken> getChickens() {
		return chickens;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

}
