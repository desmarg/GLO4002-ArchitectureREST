package trading.domain.Account;

public class AccountNumber {
    private static final Integer FIRST_ODD_NUMBER = 13;
    private static final Integer SECOND_ODD_NUMBER = 17;
    private final String accountNumber;

    public AccountNumber(String name, Integer accoundId) {
        this.accountNumber = this.makeId(name, accoundId);
    }

    public AccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String makeInitials(String name) {
        return name.replaceAll("([^\\s])[^\\s]+", "$1").replaceAll("\\s", "").toUpperCase();
    }

    public String getString() {
        return this.accountNumber;
    }

    public Integer getId() {
        String[] words = this.accountNumber.split("-");
        return Integer.parseInt(words[1]);

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AccountNumber) {
            return this.accountNumber.equals(((AccountNumber) obj).getString());
        } else if (obj instanceof String) {
            return this.accountNumber.equals(obj);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.FIRST_ODD_NUMBER * this.SECOND_ODD_NUMBER + this.accountNumber.hashCode();
    }

    private String makeId(String name, Integer accountId) {
        return this.makeInitials(name) + "-" + String.format("%04d", accountId);
    }
}
