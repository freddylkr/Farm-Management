package Management;

public class Customer {
	private String name;
	private String password;
	
	public Customer( String name, String password) {
		this.name = name;
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String display() {
		return name + " " + password;
	}
}
	