package com.example.rentacar.model.entities;

/**
 * Created by שרה ויסברגר on 11/03/2018.
 */

public class Client
{
    private String lName;
    private String fName;
    private String clientId;
    private String phoneNumber;
    private String clientEmail;
    private String creditCard;
    private String clientPassword;
    //..................................constructors...............................................
    public Client() {
    }



    public Client(String lName, String fName, String clientId, String phoneNumber, String mail, String creditCard, String clientPassword) {
        this.lName = lName;
        this.fName = fName;
        this.clientId = clientId;
        this.phoneNumber = phoneNumber;
        this.clientEmail = mail;
        this.creditCard = creditCard;
        this.clientPassword=clientPassword;

    }

    //....................................getters ans setters......................................

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }

    //............................Administration.......................................................


    @Override
    public String toString() {
        return "Client{" +
                "lName='" + lName + '\'' +
                ", fName='" + fName + '\'' +
                ", clientId='" + clientId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", creditCard='" + creditCard + '\'' +
                ", clientPassword='" + clientPassword + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (getlName() != null ? !getlName().equals(client.getlName()) : client.getlName() != null)
            return false;
        if (getfName() != null ? !getfName().equals(client.getfName()) : client.getfName() != null)
            return false;
        if (getClientId() != null ? !getClientId().equals(client.getClientId()) : client.getClientId() != null)
            return false;
        if (getPhoneNumber() != null ? !getPhoneNumber().equals(client.getPhoneNumber()) : client.getPhoneNumber() != null)
            return false;
        if (getClientEmail() != null ? !getClientEmail().equals(client.getClientEmail()) : client.getClientEmail() != null)
            return false;
        if (getCreditCard() != null ? !getCreditCard().equals(client.getCreditCard()) : client.getCreditCard() != null)
            return false;
        return getClientPassword() != null ? getClientPassword().equals(client.getClientPassword()) : client.getClientPassword() == null;

    }

    @Override
    public int hashCode() {
        int result = getlName() != null ? getlName().hashCode() : 0;
        result = 31 * result + (getfName() != null ? getfName().hashCode() : 0);
        result = 31 * result + (getClientId() != null ? getClientId().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        result = 31 * result + (getClientEmail() != null ? getClientEmail().hashCode() : 0);
        result = 31 * result + (getCreditCard() != null ? getCreditCard().hashCode() : 0);
        result = 31 * result + (getClientPassword() != null ? getClientPassword().hashCode() : 0);
        return result;
    }
}
