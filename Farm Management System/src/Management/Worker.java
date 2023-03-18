package Management;
public class Worker implements Employee {
	private String name;
	private String workingArea;
	private int id;
	private int salary;
	private boolean isWorking;

	public Worker(String name, int id, int salary) {
		this.name = name;
		this.id = id;
		this.salary = salary;
		this.isWorking = false;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getWorkingArea() {
		return workingArea;
	}

	public void setWorkingArea(String workingArea) {
		this.workingArea = workingArea;
	}

	public boolean isWorking() {
		return isWorking;
	}

	public void setWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}

	@Override
	public String display() {
		return name + ";" + id + ";" + salary;
	}
	
}
