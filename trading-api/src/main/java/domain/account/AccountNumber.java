package domain.account;

public class AccountNumber {
    private Long id;

    public AccountNumber() {
        this.id = 0L;
    }

    public AccountNumber(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void incrementId() {
        this.id++;
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
