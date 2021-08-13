package spring_prac.dao;

import org.springframework.stereotype.Component;
import spring_prac.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class PersonService {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/social";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "pqqk9a9s";

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

    public List<User> getAll(){
        List<User> users = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setAge(resultSet.getInt("age"));

                users.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public User getUser(int id){
        User resUser = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE id=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            resUser = new User();

            resUser.setId(resultSet.getInt("id"));
            resUser.setName(resultSet.getString("name"));
            resUser.setEmail(resultSet.getString("email"));
            resUser.setAge(resultSet.getInt("age"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resUser;
//        return users.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(User user){
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO person VALUES(" + user.getId() + ",'" + user.getName() + "'," + user.getAge() + ",'" + user.getEmail() + "')";
            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
