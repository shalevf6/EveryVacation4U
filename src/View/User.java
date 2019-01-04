package View;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {


    private StringProperty userName ;
    private StringProperty password ;
    private StringProperty birthDate;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty city;

    public User(){
        userName = new SimpleStringProperty();
        password = new SimpleStringProperty();
        birthDate = new SimpleStringProperty();
        firstName = new SimpleStringProperty();
        lastName = new SimpleStringProperty();
        city = new SimpleStringProperty();

    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public void setBirthDate(String birthDate) {
        this.birthDate.set(birthDate);
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public String getBirthDate() {
        return birthDate.get();
    }

    public StringProperty birthDateProperty() {
        return birthDate;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }
}
