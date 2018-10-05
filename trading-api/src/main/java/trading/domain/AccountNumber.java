package trading.domain;

public class AccountNumber {
    private String id;

    public AccountNumber(String name, Long id) {
        this.id = this.makeId(name, id);
    }

    public AccountNumber(String accountNumber) {
        this.id = accountNumber;
    }

    public String getId() {
        return this.id;
    }

    public String makeId(String name, Long id) {
        return this.makeInitials(name) + "-" + String.format("%04d", id);
    }

    public String makeInitials(String name) {
        return name.replaceAll("([^\\s])[^\\s]+", "$1").replaceAll("\\s", "").toUpperCase();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AccountNumber) {
            return this.id.equals(((AccountNumber) obj).getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
