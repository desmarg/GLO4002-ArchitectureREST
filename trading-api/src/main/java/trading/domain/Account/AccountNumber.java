package trading.domain.Account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AccountNumber {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String identifiants;

    private static final int FIRST_ODD_NUMBER = 13;
    private static final int SECOND_ODD_NUMBER = 17;

    public AccountNumber(String name, Long id) {
        this.identifiants = this.makeId(name, id);
    }

    public AccountNumber(String accountNumber) {
        this.identifiants = accountNumber;
    }

    public String makeInitials(String name) {
        return name.replaceAll("([^\\s])[^\\s]+", "$1").replaceAll("\\s", "").toUpperCase();
    }

    public String getIdentifiants() {
        return this.identifiants;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AccountNumber) {
            return this.identifiants.equals(((AccountNumber) obj).getIdentifiants());
        } else if (obj instanceof String) {
            return this.identifiants.equals(obj);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.FIRST_ODD_NUMBER * this.SECOND_ODD_NUMBER + this.identifiants.hashCode();
    }

    private String makeId(String name, Long accountId) {
        return this.makeInitials(name) + "-" + String.format("%04d", accountId);
    }
}
