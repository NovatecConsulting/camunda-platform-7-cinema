package de.novatec.bpm.model;

public class UserAccount {

    private String userId;
    private String email;
    private String iban;
    private String bic;

    public UserAccount(String userId, String email, String iban, String bic) {
        this.userId = userId;
        this.email = email;
        this.iban = iban;
        this.bic = bic;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
