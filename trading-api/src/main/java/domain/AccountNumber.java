package domain;

public class AccountNumber {
    private Long id;
    public AccountNumber(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AccountNumber) {
            return this.id.equals(((AccountNumber)obj).getId());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }

    @Override
    public String toString(){
        return "Account: " + id.toString();
    }
}
