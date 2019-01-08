package com.example.rentacar.Model.entities;

/**
 * Created by שרה ויסברגר on 09/04/2018.
 */

public class User
{
    private  String userName;
    private  String password;
    private  String userEmail;
    private  String userId;
    private String hint;
    //..................................constructors...............................................

    public User(){}

    public User(String userName, String password, String email, String id, String hint) {
        this.userName = userName;
        this.password = password;
        this.userEmail = email;
        this.userId = id;
        this.hint=hint;

    }
    //....................................getters ans setters......................................

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return userEmail;
    }

    public void setEmail(String email) {
        this.userEmail = email;
    }

    public String getId() {
        return userId;
    }

    public void setId(String id) {
        this.userId = id;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }


    //............................Administration.......................................................


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getUserName() != null ? !getUserName().equals(user.getUserName()) : user.getUserName() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null)
            return false;
        if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null) return false;
        return getHint() != null ? getHint().equals(user.getHint()) : user.getHint() == null;

    }

    @Override
    public int hashCode() {
        int result = getUserName() != null ? getUserName().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getHint() != null ? getHint().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + userEmail + '\'' +
                ", id='" + userId + '\'' +
                ", hint='" + hint + '\'' +
                '}';
    }
}
