import java.sql.*;


public class SqlDatabase {

    public static void main(String[] args) {
        connectToDatabase();
    }

    public static void connectToDatabase() {
        try {
            startConnection();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void startConnection() throws Exception {
        String url = "jdbc:mysql://192.168.99.100:3306/testdb";
        String user = "root";
        String password = "password";

        loadDriver();
        Connection connection = establishConnection(url, user, password);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM example");

        System.out.println("Results from Query...");
        result.next();
        System.out.println(result.getInt("id") + " " +
                result.getString("name") + " " +
                result.getInt("age"));

        closeConnection(connection);
    }

    public static void loadDriver() throws Exception {
        System.out.println("Attempting to load Driver...");
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded!");
    }

    public static Connection establishConnection(String url, String user, String password) throws Exception {
        System.out.println("Attempting to connect to Database...");
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("Database connected!");
        return connection;
    }

    public static void closeConnection(Connection connection) throws Exception {
        connection.close();
    }
}
