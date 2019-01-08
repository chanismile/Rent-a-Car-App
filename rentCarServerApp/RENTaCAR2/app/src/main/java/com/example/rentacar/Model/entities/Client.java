package com.example.rentacar.Model.entities;

/**
 * Created by שרה ויסברגר on 11/03/2018.
 */

public class Client {
    private String lName;
    private String fName;
    private String clientId;
    private String phoneNumber;
    private String clientEmail;
    private String creditCard;
    //..................................constructors...............................................
    public Client() {
    }

    public Client(String lName, String fName, String id, String phoneNumber, String mail, String creditCard) {
        this.lName = lName;
        this.fName = fName;
        this.clientId = id;
        this.phoneNumber = phoneNumber;
        this.clientEmail = mail;
        this.creditCard = creditCard;
    }

    //....................................getters ans setters......................................
    public String getlName() {
        return lName;
    }

    public String getfName() {
        return fName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMail() {
        return clientEmail;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMail(String mail) {
        this.clientEmail = mail;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getId() {
        return clientId;
    }

    public void setId(String id) {
        this.clientId = id;
    }
    //............................Administration.......................................................
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (getId() != client.getId()) return false;
        if (getPhoneNumber() != client.getPhoneNumber()) return false;
        if (getCreditCard() != client.getCreditCard()) return false;
        if (getlName() != null ? !getlName().equals(client.getlName()) : client.getlName() != null)
            return false;
        if (getfName() != null ? !getfName().equals(client.getfName()) : client.getfName() != null)
            return false;
        return getMail() != null ? getMail().equals(client.getMail()) : client.getMail() == null;

    }



    @Override
    public String toString() {
        return "Client{" +
                "lName='" + lName + '\'' +
                ", fName='" + fName + '\'' +
                ", id=" + clientId +
                ", phoneNumber=" + phoneNumber +
                ", mail='" + clientEmail + '\'' +
                ", creditCard=" + creditCard +
                '}';

    }

    @Override
    public int hashCode() {
        int result = getlName() != null ? getlName().hashCode() : 0;
        result = 31 * result + (getfName() != null ? getfName().hashCode() : 0);
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        result = 31 * result + (getMail() != null ? getMail().hashCode() : 0);
        result = 31 * result + (getCreditCard() != null ? getCreditCard().hashCode() : 0);
        return result;
    }
}
