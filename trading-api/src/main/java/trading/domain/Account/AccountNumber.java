package trading.domain.Account;

public class AccountNumber {
    private static final int FIRST_ODD_NUMBER = 13;
    private static final int SECOND_ODD_NUMBER = 17;
    private final String accountNumber;

    public AccountNumber(String name, int accoundId) {
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

    public int getId() {
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

    private String makeId(String name, int accountId) {
        return this.makeInitials(name) + "-" + String.format("%04d", accountId);
    }
}
