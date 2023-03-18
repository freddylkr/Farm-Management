package Management;
public class SalesPerson implements Employee {
	private String name;
	private int id;
	private int salary;

	public SalesPerson(String name, int id, int salary) {
		this.name = name;
		this.id = id;
		this.salary = salary;
	}

	public boolean controlOfProduct(Product product) {
        if (product.getProductName().equals("Egg") && product.getDayCounter() == 30) {
            return false;
        } else if (product.getProductName().equals("Milk") && product.getDayCounter() == 5) {
            return false;
        }
        return true;
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

	@Override
	public String display() {
		return name + ";" + id + ";" + salary;
	}
}