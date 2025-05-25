package project_poo;
import java.util.HashMap;
public class Admin extends User {
	private HashMap<Integer, String> userDatabase;
	public Admin(String name, String surname , int id) {
		super(name,surname,id);
		this.userDatabase = new HashMap<>();
	}
}
