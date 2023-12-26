import java.sql.*;

public class Employee {

    public static void main(String[] args) throws Exception {
        try {
            readRecord();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    // read the data
    static void readRecord() throws Exception {
        String url = "jdbc:mysql://localhost:3306/MARK";
        String userName = "root";
        String passWord = "yogaprasanth9642";
        String query = "select * from Studentmark";
        Connection connection = DriverManager.getConnection(url, userName, passWord);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        
        while (resultSet.next()) {
            System.out.println("Id is " + resultSet.getInt(1) + " " +"Name is " + resultSet.getString(2) + " " + "GPA is " + resultSet.getFloat(3));
        }
        connection.close();
    }
    // inserting to the DB
    static void insertRecord() throws Exception {
        String url = "jdbc:mysql://localhost:3306/MARK";
        String userName = "root";
        String passWord = "yogaprasanth9642";
        String query = "insert into Studentmark values(1,'preethi',9.0)";
        Connection connection = DriverManager.getConnection(url, userName, passWord);
        Statement statement = connection.createStatement();
        int rows = statement.executeUpdate(query);
        
        System.out.print(rows);
        connection.close();
    }
    // insertion using variable
    static void insertVariables() throws Exception {
        String url = "jdbc:mysql://localhost:3306/MARK";
        String userName = "root";
        String passWord = "yogaprasanth9642";
        int id = 10;
        String name = "priya";
        float gpa = (float) 9.9;
        String query = "insert into Studentmark values("+id+",'"+name+"',"+gpa+")";
        Connection connection = DriverManager.getConnection(url, userName, passWord);
        Statement statement = connection.createStatement();
        int rows = statement.executeUpdate(query);
        
        System.out.print(rows);
        connection.close();
    }
    // insertion using "preparsed statement" used in large number of datas
    static void insertPrepared() throws Exception {
        String url = "jdbc:mysql://localhost:3306/MARK";
        String userName = "root";
        String passWord = "yogaprasanth9642";
        int id = 11;
        String name = "sunflower";
        float gpa = (float) 9.9;
        String query = "insert into Studentmark values(?,?,?)";
        Connection connection = DriverManager.getConnection(url, userName, passWord);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setFloat(3, gpa);
        
        int rows = preparedStatement.executeUpdate();

        System.out.print(rows);
        connection.close();
    }
    // deleting the row
    static void delete() throws Exception {
        String url = "jdbc:mysql://localhost:3306/MARK";
        String userName = "root";
        String passWord = "yogaprasanth9642";

        int id = 3;
        
        String query ="delete from Studentmark where stud_id = " +id;
        Connection connection = DriverManager.getConnection(url, userName, passWord);
        Statement statement = connection.createStatement();
        int rows = statement.executeUpdate(query);
        System.out.print(rows);
        connection.close();
    }
    static void update() throws Exception {
        String url = "jdbc:mysql://localhost:3306/MARK";
        String userName = "root";
        String passWord = "yogaprasanth9642";

        // int id = 3;
        
        String query = "update Studentmark set stud_id = ? where stud_name = 'sunflower'";
        Connection connection = DriverManager.getConnection(url, userName, passWord);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, 5);
        int rows = preparedStatement.executeUpdate();
        System.out.print(rows);
        connection.close();
    }
    static void sp() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/MARK";
        String userName = "root";
        String passWord = "yogaprasanth9642";
        Connection connection = DriverManager.getConnection(url, userName, passWord);
        CallableStatement statement = connection.prepareCall("{call Customers_de()}");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getFloat(3));
        }
    }
    static void sp2() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/MARK";
        String userName = "root";
        String passWord = "yogaprasanth9642";

        int id = 1;
        Connection connection = DriverManager.getConnection(url, userName, passWord);
        CallableStatement statement = connection.prepareCall("{call stud_ids(?)}");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getFloat(3));
        }
    }
    
}