package Management;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Farm {
	private Admin admin;
	private int money;
	private ArrayList<Barn> barns;// 5
	private ArrayList<Coop> coops;// 10
	private Warehouse warehouse;
	private Veterinary veterinary;
	private ArrayList<Worker> workers;
	private ArrayList<Customer> customers;
	public static Date currentDate;

	public Farm() throws IOException {
		this.admin = new Admin();
		this.barns = new ArrayList<Barn>();
		this.coops = new ArrayList<Coop>();
		this.customers = new ArrayList<Customer>();
		this.warehouse = new Warehouse();
		this.workers = new ArrayList<Worker>();
		readEmployees();
		readAnimals();
		readCustomers();
	}
	public void paySalaries() {
		this.money -= veterinary.getSalary();
		this.money -= warehouse.getBazaar().getSalesPerson().getSalary();
		for (Worker worker : workers) {
			if (worker.isWorking()) {
				this.money -= worker.getSalary();
			}
		}
	}

	public void nextMonth() {

		int totalDay = currentDate.numberOfDays();
		int day = currentDate.getDay();
		for (int i = 0; i <= totalDay - day; i++) {
			nextDay();
		}
	}

	public void nextDay() {
		int productsToBeSold = warehouse.getProducts().size() / 10;
		for (int i = 0; i < productsToBeSold; i++) {
			warehouse.getBazaar()
					.sellProduct(warehouse.getProducts().get((int) (Math.random() * warehouse.getProducts().size())));
		}
		for (int i = 0; i < warehouse.getProducts().size(); i++) {
			Product product = warehouse.getProducts().get(i);
			product.setDayCounter(product.getDayCounter() + 1);
		}
		this.warehouse.updateProducts();
		for (int i = 0; i < barns.size(); i++) {
			Barn barn = barns.get(i);
			for (int j = 0; j < barn.getCows().size(); j++) {
				Cow cow = barn.getCows().get(j);
				if (Date.equalsDay(currentDate, cow.getBirthDate())) {
					cow.setAge(cow.getAge() + 1);
				}
				if (cow.getAge() == 8 && cow.getPregnancyCounter() == 0) {
					sellAnimal(cow);
				}
				if (cow.getPregnancyCounter() != 0) {
					cow.setPregnancyCounter(cow.getPregnancyCounter() + 1);
					if (cow.getPregnancyCounter() == 270) {
						cow.setPregnancyCounter(0);
						int rnd = (int) (Math.random() * 2);
						if (rnd == 0) {
							addCow(new Cow(++Cow.idCounter, currentDate.copy(), 'f', cow.getQuality(), 0, 0));
						} else {
							addCow(new Cow(++Cow.idCounter, currentDate.copy(), 'm', cow.getQuality(), 0, 0));
						}
					}
				} else {
					warehouse.addProduct(cow.animalProduct());
				}
			}
			for (int j = 0; j < barn.getSheeps().size(); j++) {
				Sheep sheep = barn.getSheeps().get(j);
				if (Date.equalsDay(currentDate, sheep.getBirthDate())) {
					sheep.setAge(sheep.getAge() + 1);
				}
				if (sheep.getAge() == 7 && sheep.getPregnancyCounter() == 0) {
					sellAnimal(sheep);
				}
				if (sheep.getPregnancyCounter() != 0) {
					sheep.setPregnancyCounter(sheep.getPregnancyCounter() + 1);
					if (sheep.getPregnancyCounter() == 150) {
						sheep.setPregnancyCounter(0);
						int rnd = (int) (Math.random() * 2);
						if (rnd == 0) {
							addSheep(new Sheep(++Sheep.idCounter, currentDate.copy(), 'f', sheep.getQuality(), 0, 0));
						} else {
							addSheep(new Sheep(++Sheep.idCounter, currentDate.copy(), 'm', sheep.getQuality(), 0, 0));
						}

					}
				} else {
					warehouse.addProduct(sheep.animalProduct());
				}
			}
		}
		for (int i = 0; i < coops.size(); i++) {
			Coop coop = coops.get(i);
			for (int j = 0; j < coop.getChickens().size(); j++) {
				Chicken chicken = coop.getChickens().get(j);
				if (currentDate.getDay() == chicken.getBirthDate().getDay()) {
					chicken.setMonthAge(chicken.getMonthAge() + 1);
				}
				if (chicken.getMonthAge() == 18 && chicken.getIncubationCounter() == 0) {
					sellAnimal(chicken);
				}
				if (chicken.getIncubationCounter() != 0) {
					chicken.setIncubationCounter(chicken.getIncubationCounter() + 1);
					if (chicken.getIncubationCounter() == 21) {
						chicken.setIncubationCounter(0);
						for (int k = 0; k < Chicken.maxChickValueDuringIncubation; k++) {
							int rnd = (int) (Math.random() * 2);
							if (rnd == 0) {
								addChicken(new Chicken(++Chicken.idCounter, currentDate.copy(), 'f',
										chicken.getQuality(), 0, 0));
							} else {
								addChicken(new Chicken(++Chicken.idCounter, currentDate.copy(), 'm',
										chicken.getQuality(), 0, 0));
							}
						}
					}
				} else {
					warehouse.addProduct(chicken.animalProduct());
				}
			}
		}
		if (currentDate.getDay() == 1) {
			illness();
			paySalaries();
			if (currentDate.getMonth() % 2 == 1) {
				veterinary.inseminateForChicken();
			}
			if (currentDate.getMonth() % 6 == 1) {
				veterinary.inseminateForSheeps();
			}
			if (currentDate.getMonth() < 4) {
				veterinary.inseminateForCows();
			}
		} else if (currentDate.getDay() == 2) {
			veterinary.heal();
		}
		feed();
		updateOfAnimals();
		currentDate = currentDate.oneDayAfter();

	}

	public void exitAndSave() throws IOException {
		saveEmployees();
		saveAnimals();
		saveCustomers();
		warehouse.saveProducts();

	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public void saveCustomers() throws IOException {
		File file = new File("Database\\Customers.txt");
		FileWriter writer = new FileWriter(file);
		for (Customer customer : this.customers) {
			writer.write(customer.display() + "\n");
		}
		writer.close();

	}

	public void readCustomers() {
		File file = new File("Database\\Customers.txt");
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String[] str = scan.nextLine().split(" ");
				customers.add(new Customer(str[0], str[1]));
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Worker> getWorkers() {
		return workers;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void addChicken(Chicken chicken) {
		if (!isTherePlaceFor('t')) {
			if (!addCoops()) {
				return;
			}
		}
		for (int i = 0; i < coops.size(); i++) {
			if (!coops.get(i).isFull()) {
				coops.get(i).addChicken(chicken);
				break;
			}
		}
	}

	public void addSheep(Sheep sheep) {
		if (!isTherePlaceFor('s')) {
			if (!addBarns()) {
				return;
			}
		}
		for (int i = 0; i < barns.size(); i++) {
			if (!barns.get(i).isFull('s')) {
				barns.get(i).addSheep(sheep);
				break;
			}
		}
	}

	public void addCow(Cow cow) {
		if (!isTherePlaceFor('c')) {
			if (!addBarns()) {
				return;
			}
		}
		for (int i = 0; i < barns.size(); i++) {
			if (!barns.get(i).isFull('c')) {
				barns.get(i).addCow(cow);
				break;
			}
		}
	}

	public void sellAnimal(Animal animal) {
		money += animal.valueOfAnimal();
		deleteAnimal(animal.getId());
	}

	public void deleteAnimal(int id) {
		for (Barn barn : barns) {
			if (barn.deleteCow(id)) {
				return;
			}
			if (barn.deleteSheep(id)) {
				return;
			}
		}
		for (Coop coop : coops) {
			if (coop.deleteChicken(id)) {
				return;
			}
		}
	}

	public void illness() {
		for (Barn barn : barns) {
			int rnd1 = 0;
			int rnd2 = 0;
			if(barn.getCows() != null) {
				rnd1 = (int) (Math.random() * barn.getCows().size());
				rnd2 = (int) (Math.random() * barn.getCows().size());
				barn.getCows().get(rnd1).setIll(true);
				barn.getCows().get(rnd2).setIll(true);
			}
			if(barn.getSheeps() != null) {

			rnd1 = (int) (Math.random() * barn.getSheeps().size());
			rnd2 = (int) (Math.random() * barn.getSheeps().size());
			barn.getSheeps().get(rnd1).setIll(true);
			barn.getSheeps().get(rnd2).setIll(true);
			}
		}
		for (Coop coop : coops) {

			int rnd1 = (int) (Math.random() * coop.getChickens().size());
			int rnd2 = (int) (Math.random() * coop.getChickens().size());
			coop.getChickens().get(rnd1).setIll(true);
			coop.getChickens().get(rnd2).setIll(true);
		}
	}

	public ArrayList<Animal> listofAnimals() {
		ArrayList<Animal> animals = new ArrayList<>();
		for (Barn barn : barns) {
			for (Cow cow : barn.getCows()) {
				animals.add(cow);
			}
			for (Sheep sheep : barn.getSheeps()) {
				animals.add(sheep);
			}
		}
		for (Coop coop : coops) {
			for (Chicken chicken : coop.getChickens()) {
				animals.add(chicken);
			}
		}
		return animals;
	}

	public void feed() {
		for (Coop coop : coops) {
			this.money -= Chicken.costOfMeal * coop.getChickens().size();
		}
		for (Barn barn : barns) {
			this.money -= Cow.costOfMeal * barn.getCows().size();
			this.money -= Sheep.costOfMeal * barn.getSheeps().size();
		}
	}

	public void readEmployees() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Database\\Employees.txt")));
		String line = br.readLine();
		String[] splitLine = line.split(";");
		this.setVeterinary(
				new Veterinary(splitLine[0], Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2])));
		line = br.readLine();
		splitLine = line.split(";");
		this.warehouse.getBazaar().setSalesPerson(
				new SalesPerson(splitLine[0], Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2])));
		while ((line = br.readLine()) != null) {
			splitLine = line.split(";");
			this.workers.add(new Worker(splitLine[0], Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2])));
		}
		br.close();

	}

	public void saveEmployees() throws IOException {
		File file = new File("Database\\Employees.txt");
		FileWriter writer = new FileWriter(file);
		writer.write(veterinary.display() + "\n");
		writer.write(warehouse.getBazaar().getSalesPerson().display() + "\n");
		for (Worker worker : workers) {
			writer.write(worker.display() + "\n");

		}
		writer.close();
	}

	public void readAnimals() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Database\\Animals.txt")));
		String line = br.readLine();
		this.money = Integer.parseInt(line);
		Farm.currentDate = new Date(br.readLine());
		while ((line = br.readLine()) != null) {
			String[] splitAnimal = line.split(";");
			Date date = new Date(splitAnimal[1]);
			Animal animal = null;
			if (Integer.parseInt(splitAnimal[0]) >= 3000) {// chicken
				animal = new Chicken(Integer.parseInt(splitAnimal[0]), date, splitAnimal[2].charAt(0),
						Integer.parseInt(splitAnimal[3]), Integer.parseInt(splitAnimal[4]),
						Integer.parseInt(splitAnimal[5]));
				addChicken((Chicken) animal);
			} else if (Integer.parseInt(splitAnimal[0]) >= 2000) {// sheep
				animal = new Sheep(Integer.parseInt(splitAnimal[0]), date, splitAnimal[2].charAt(0),
						Integer.parseInt(splitAnimal[3]), Integer.parseInt(splitAnimal[4]),
						Integer.parseInt(splitAnimal[5]));
				addSheep((Sheep) animal);
			} else {// cow
				animal = new Cow(Integer.parseInt(splitAnimal[0]), date, splitAnimal[2].charAt(0),
						Integer.parseInt(splitAnimal[3]), Integer.parseInt(splitAnimal[4]),
						Integer.parseInt(splitAnimal[5]));
				addCow((Cow) animal);
			}

		}
		br.close();
	}

	public void saveAnimals() throws IOException {
		File file = new File("Database\\Animals.txt");
		FileWriter writer = new FileWriter(file);
		writer.write(money + "\n");
		writer.write(currentDate.toString() + "\n");
		for (Animal animal : listofAnimals()) {
			writer.write(animal.display() + "\n");
		}
		writer.close();
	}

	public boolean isTherePlaceFor(char type) {
		if (type == 'c') {
			for (int i = 0; i < barns.size(); i++) {
				if (!barns.get(i).isFull('c')) {
					return true;

				}
			}
		} else if (type == 's') {
			for (int i = 0; i < barns.size(); i++) {
				if (!barns.get(i).isFull('s')) {
					return true;

				}
			}
		} else {
			for (int i = 0; i < coops.size(); i++) {
				if (!coops.get(i).isFull()) {
					return true;

				}
			}
		}
		return false;
	}

	public boolean deleteBarns() {
		for (int i = 0; i < barns.size(); i++) {
			Barn barn = barns.get(i);
			if (barn.getPopulation() == 0) {
				barns.remove(barn);
				return true;
			}
		}
		return false;
	}

	public boolean addCoops() {
		if (coops.size() != 10) {
			Coop coop = new Coop();
			for (Worker worker : workers) {
				if (!worker.isWorking()) {
					worker.setWorkingArea(coops.size() + 1 + "C");
					worker.setWorking(true);
					coop.setWorker(worker);
					break;
				}
			}
			coops.add(coop);
			return true;
		} else {
			return false;
		}
	}

	public boolean addBarns() {
		if (barns.size() != 5) {
			int i = 0;
			Worker[] barnWorkers = new Worker[2];
			Barn barn = new Barn();
			for (Worker worker : this.workers) {
				if (!worker.isWorking()) {
					worker.setWorkingArea(barns.size() + 1 + "B");
					worker.setWorking(true);
					barnWorkers[i] = worker;
					i++;
					if (i == 2) {
						barn.setWorkers(barnWorkers);
						break;
					}
				}
			}
			barns.add(barn);
			return true;
		} else {
			return false;
		}
	}

	public void deleteCoops() {
		for (int i = 0; i < coops.size(); i++) {
			Coop coop = coops.get(i);
			if (coop.getPopulation() == 0) {
				coops.remove(coop);
			}
		}
	}

	public void updateOfAnimals() {
		while (true) {
			int counter1 = 0;
			for (int i = 0; i < coops.size(); i++) {
				counter1 += coops.get(i).getPopulation();
			}
			if (counter1 < (coops.size() - 1) * Coop.maxCapacity) {
				for (int i = 0; i < coops.get(coops.size() - 1).getChickens().size();) {
					for (int j = 0; j < coops.size(); j++) {
						if (coops.get(j).addChicken(coops.get(coops.size() - 1).getChickens().get(i))) {
							coops.get(coops.size() - 1)
									.deleteChicken(coops.get(coops.size() - 1).getChickens().get(i).getId());
							break;
						}
					}
				}
				deleteCoops();
			} else {
				break;
			}
			int counter2 = 0;
			for (int i = 0; i < barns.size(); i++) {
				counter2 += barns.get(i).getPopulation();
			}
			if (counter2 < (barns.size() - 1) * Barn.maxCapacity) {
				for (int i = 0; i < barns.get(barns.size() - 1).getCows().size();) {
					for (int j = 0; j < barns.size(); j++) {
						if (barns.get(j).addCow(barns.get(barns.size() - 1).getCows().get(i))) {
							barns.get(barns.size() - 1).deleteCow(barns.get(barns.size() - 1).getCows().get(i).getId());
							break;
						}
					}
				}
				for (int i = 0; i < barns.get(barns.size() - 1).getSheeps().size();) {
					for (int j = 0; j < barns.size(); j++) {
						if (barns.get(j).addSheep(barns.get(barns.size() - 1).getSheeps().get(i))) {
							barns.get(barns.size() - 1)
									.deleteSheep(barns.get(barns.size() - 1).getSheeps().get(i).getId());
							break;
						}
					}
				}
				boolean flag = this.deleteBarns();
				if (!flag) {
					break;
				}
			} else {
				break;
			}
		}
	}

	public Employee returnEmployee(int id) { // veteriner ve salesperson icin de arama yapilacak
		for (Barn barn : barns) {
			for (Worker worker : barn.getWorkers()) {
				if (worker.getId() == id) {
					return worker;
				}
			}
		}
		for (Coop coop : coops) {
			if (coop.getWorker().getId() == id) {
				return coop.getWorker();
			}
		}
		return null;
	}

	// money den dusus
	public void giveSalaryOfEmployee(ArrayList<Employee> employees) {
		for (int i = 0; i < employees.size(); i++) {
			money -= employees.get(i).getSalary();
		}
	}

	public int getMoney() {
		return money;
	}

	public ArrayList<Barn> getBarns() {
		return barns;
	}

	public ArrayList<Coop> getCoops() {
		return coops;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Veterinary getVeterinary() {
		return veterinary;
	}

	public void setVeterinary(Veterinary veterinary) {
		this.veterinary = veterinary;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}
}