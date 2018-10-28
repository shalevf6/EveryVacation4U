package Model;

import java.sql.Connection;

public class Model {

    private Connection connection;

    public Model(Connection connection) {
        this.connection = connection;
    }

    boolean create(String userName, String password, String birthDate, String FirstName, String LastName, String city){

        return false;
    }

    boolean update(String fieldToChange, String newInput) {

        return false;
    }

    boolean read(String userName) {

        return false;
    }

    boolean delete(String userName) {

        return false;
    }
}
