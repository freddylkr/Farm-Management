package Management;
import java.util.ArrayList;

import UserInterfaces.Login;

public class Veterinary implements Employee {
	private String name;
	private int id;
	private int salary;
	private ArrayList<Integer> vaccinatedAnimals;

	public Veterinary(String name, int id, int salary) {
		this.name = name;
		this.id = id;
		this.salary = salary;
		vaccinatedAnimals = new ArrayList<>();
	}

	public void heal() {
		vaccinatedAnimals = new ArrayList<>();
		for (Animal animal : Login.farm.listofAnimals()) {
			if (animal.isIll()) {
				animal.setIll(false);
				vaccinatedAnimals.add(animal.getId());
			}
		}

	}

	public void inseminateForChicken() {
		for (Coop coop : Login.farm.getCoops()) {
			int rnd = 0;
			do {
				rnd = (int) (Math.random() * coop.getPopulation());
			} while (coop.checkChickenGender() && coop.getChickens().get(rnd).getGender() == 'm');
			coop.getChickens().get(rnd).breeding();
		}
	}

	public void inseminateForSheeps() {
		for (Barn barn : Login.farm.getBarns()) {
			int rnd;
			do {
				rnd = (int) (Math.random() * barn.getSheeps().size());
			} while (barn.checkSheepGender() && barn.getSheeps().get(rnd).getGender() == 'm');
			barn.getSheeps().get(rnd).breeding();
		}
	}

	public void inseminateForCows() {
		for (Barn barn : Login.farm.getBarns()) {
			int rnd;
			do {
				rnd = (int) (Math.random() * barn.getCows().size());
			} while (barn.checkCowGender() && barn.getCows().get(rnd).getGender() == 'm');
			barn.getCows().get(rnd).breeding();
		}
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getSalary() {
		return salary;
	}

	public ArrayList<Integer> getAnimalsID() {
		return vaccinatedAnimals;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public void setAnimalsID(ArrayList<Integer> animalsID) {
		this.vaccinatedAnimals = animalsID;
	}

	@Override
	public String display() {
		return name + ";" + id + ";" + salary;
	}

}
