package Model.account;

public class AccountSession {

    public AccountSession(Account account) {
        this.firstName = account.getName();
        this.lastName = account.getLastName();
        this.id = account.getId();
        this.isAdmin = account.isAdmin();
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

    @Override
    public String toString() {
        return "AccountSession{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                ", isAdmin=" + isAdmin +
                '}';
    }

    private final String firstName, lastName;
    private final int id;
    private final boolean isAdmin;
}
