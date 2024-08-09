import java.util.HashMap;

public class User extends Person {
    private HashMap<String, String> rates = new HashMap<>();

    public User(String id, String name, String surname, String country) {
        super(id, name, surname, country);
    }

    public HashMap<String, String> getRates() {
        return rates;
    }

    public void setRates(HashMap<String, String> rates) {
        this.rates = rates;
    }
}
