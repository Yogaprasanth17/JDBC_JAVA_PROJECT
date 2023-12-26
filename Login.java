import java.util.*;
import java.sql.*;

public class Login {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your choice");
        System.out.println("1 for login\n2 for signup");
        int num = sc.nextInt();
        if (num == 1) {
            try {
                logIn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (num == 2) {
            try {
                signUp();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    // signUp
    static void signUp() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the user name");
        String userName = sc.next();
        System.out.println("Enter your password");
        String passWord = sc.next();
        System.out.println("Re-enter the password");
        String re_passWord = sc.next();
        if (!passWord.equals(re_passWord)) {
            signUp();
        }
        String url = "jdbc:mysql://localhost:3306/Exprement";
        String name = "root";
        String password = "yogaprasanth9642";
        String query = "insert into Login values(?,?)";
        Connection connection = DriverManager.getConnection(url,name,password);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, passWord);
        int rows = preparedStatement.executeUpdate();
        if (rows > 0) {
            System.out.println("Signuped sucessfully");
        }
    }
    //login
    static void logIn() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the user name");
        String userName = sc.next();
        System.out.println("Enter your password");
        String passWord = sc.next();
        String url = "jdbc:mysql://localhost:3306/Exprement";
        String name = "root";
        String password = "yogaprasanth9642";
        String query = "select * from Login";
        Connection connection = DriverManager.getConnection(url,name,password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        boolean login = false;
        while (resultSet.next()) {
            if (resultSet.getString(1).equals(userName) && resultSet.getString(2).equals(passWord)) {
                login = true;
                break;
            }
        }
        if (login) {
            System.out.println("User logined successfully");
        }
        else {
            System.out.println("User not found!!");
        }
    }
}
