package trading.domain;

public class AccountNumber {
    private String id;

    public AccountNumber(String name, Long accoundId) {
        this.id = this.makeId(name, accoundId);
    }

    public AccountNumber(String accountNumber) {
        this.id = accountNumber;
    }

    public String makeId(String name, Long accountId) {
        return this.makeInitials(name) + "-" + String.format("%04d", accountId);
    }

    public String makeInitials(String name) {
        return name.replaceAll("([^\\s])[^\\s]+", "$1").replaceAll("\\s", "").toUpperCase();
    }

    public String getId() {
        return this.id;
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
