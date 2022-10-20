package ru.lab3.DAO;

import org.springframework.stereotype.Component;
import ru.lab3.Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/diploma";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "abyl";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> index() {
        List<User> user = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM public.\"User\"";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                User User = new User();

                User.setId(resultSet.getInt("id"));
                User.setName(resultSet.getString("name"));
                User.setEmail(resultSet.getString("email"));
                User.setAge(resultSet.getInt("age"));

                user.add(User);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }

    public void save(User user) {

        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO public.\"User\" VALUES(" + 1 + ",'" + user.getName() +
                    "'," + user.getAge() + ",'" + user.getEmail() + "')";

            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

}
