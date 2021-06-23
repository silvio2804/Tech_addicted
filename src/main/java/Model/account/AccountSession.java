package Model.account;

public class AccountSession {

    public AccountSession(String firstName, String lastName, int id, boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.isAdmin = isAdmin;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    private final String firstName, lastName;
    private final int id;
    private final boolean isAdmin;
}
