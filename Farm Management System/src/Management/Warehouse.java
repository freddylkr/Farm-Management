package Management;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import UserInterfaces.Login;

public class Warehouse {
	private ArrayList<Product> products;
	private Bazaar bazaar;

	public Warehouse() throws IOException {
		this.products = new ArrayList<Product>();
		bazaar = new Bazaar();
		readProducts();
	}

	public void readProducts() throws IOException {
		File file = new File("Database\\Products.txt");
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNext()) {
				String[] prod = scanner.nextLine().split(";");
				Product productoffile = new Product(prod[0], prod[1], Integer.parseInt(prod[2]), Integer.parseInt(prod[3]));
				this.products.add(productoffile);
			}
		}
	}

	public void updateProducts() {
		for (int i = 0; i < this.products.size(); i++) {
			Product product = products.get(i);
			if (!bazaar.salesPerson.controlOfProduct(product)) {
				deleteProduct(product);
			}
		}
	}

	public ArrayList<Product> searchProduct(String str) throws IOException {
		ArrayList<Product> totalproducts = new ArrayList<Product>();
		for (Product product : products) {
			if (product.getProductName().equals(str))
				totalproducts.add(product);
		}

		return totalproducts;
	}

	public void saveProducts() throws IOException {
		File file = new File("Database\\Products.txt");
		FileWriter writer = new FileWriter(file);
		for (Product product : this.products) {
			writer.write(product.display() + "\n");
		}
		writer.close();
	}

	public void addProduct(Product product) {
		this.products.add(product);
	}

	public void deleteProduct(Product product) {
		for (int i = 0; i < this.products.size(); i++) {
			Product prod = products.get(i);
			if (prod == product) {
				this.products.remove(product);
			}
		}
	}

	public Bazaar getBazaar() {
		return bazaar;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public class Bazaar {
        private SalesPerson salesPerson;


        public void sellProduct(Product product) {
            Login.farm.setMoney(Login.farm.getMoney() + product.getPrice());
            deleteProduct(product);
        }

        public boolean productCheck(Product requestedProduct){
            for (Product product : products) {
                if (product == requestedProduct) {
                    return true;
                }
            }
            return false;
        }

        public void purchase(ArrayList<Product> requestedProducts) {
            	for (Product product : requestedProducts) {
                    sellProduct(product);
            }
        }

        public SalesPerson getSalesPerson() {
            return salesPerson;
        }

        public void setSalesPerson(SalesPerson salesPerson) {
            this.salesPerson = salesPerson;
        }
    }
}