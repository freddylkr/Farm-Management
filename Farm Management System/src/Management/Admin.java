package Management;
import UserInterfaces.Login;

public class Admin {
	private String name;
	private String password;

	public Admin() {
		this.name = "deuceng";
		this.password = "farmland";
	}

	public void promote(int employeeId) {
		if (Login.farm.getVeterinary().getId() == employeeId) {
			Login.farm.getVeterinary().setSalary(Login.farm.getVeterinary().getSalary() * 5 / 4);
			return;
		}
		if (Login.farm.getWarehouse().getBazaar().getSalesPerson().getId() == employeeId) {
			Login.farm.getWarehouse().getBazaar().getSalesPerson()
					.setSalary(Login.farm.getWarehouse().getBazaar().getSalesPerson().getSalary() * 5 / 4);
			return;
		}

		for (Worker worker : Login.farm.getWorkers()) {
			if (worker.getId() == employeeId) {
				worker.setSalary(worker.getSalary() * 5 / 4);
				return;
			}

		}
	}

	public String getName() {
		return name;

	}

	public String getPassword() {
		return password;
	}

}
