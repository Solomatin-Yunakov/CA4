package DAOs;

/** OOP Feb 2024
 *
 * Data Access Object (DAO) for User table with MySQL-specific code
 * This 'concrete' class implements the 'UserDaoInterface'.
 *
 * The DAO will contain the SQL query code to interact with the database,
 * so, the code here is specific to a MySql database.
 * No SQL queries will be used in the Business logic layer of code, thus, it
 * will be independent of the database specifics. Changes to code related to
 * the database are all contained withing the DAO code base.
 *
 *
 * The Business Logic layer is only permitted to access the database by calling
 * methods provided in the Data Access Layer - i.e. by calling the DAO methods.
 * In this way, the Business Logic layer is seperated from the database specific code
 * in the DAO layer.
 */

import DTOs.Spending;

import Exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import DAOs.MySqlDao;
import java.sql.Date;
import java.sql.Date;
public  class MySqlSpendingsDao extends MySqlDao implements SpendingsDaoInterface {
    public Connection getConnection() throws DaoException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/CA4";
        String username = "root";
        String password = "";
        Connection connection = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to find driver class " + e.getMessage());
            System.exit(1);
        } catch (SQLException e) {
            System.out.println("Connection failed " + e.getMessage());
            System.exit(2);
        }
        return connection;
    }

    /**
     * Will access and return a List of all users in User database table
     *
     * @return List of User objects
     * @throws DaoException
     */
    @Override
    public List<Spending> findAllSpendings() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Spending> spendList = new ArrayList<>();


        try {
            //Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "SELECT * FROM SPENDING";
            preparedStatement = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int expenseid = resultSet.getInt("EXPENSE_ID");
                String title = resultSet.getString("TITLE");
                String category = resultSet.getString("CATEGORY");
                double amount = resultSet.getDouble("AMOUNT");
                Date dateIncurred = resultSet.getDate("DATE");
                Spending s = new Spending(expenseid, title, category, amount, dateIncurred);
                spendList.add(s);
            }
            double total = 0.0;
            for (Spending s : spendList) {
                total = total + s.getAmount();
            }
            System.out.println("Total Spendings: " + total);
        } catch (SQLException e) {
            throw new DaoException("findAllSpendings() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllSpending() " + e.getMessage());
            }
        }
        return spendList;     // may be empty
    }



    /*
     * Given a username and password, find the corresponding User
     * @param user_name
     * @param password
     * @return User object if found, or null otherwise
     * @throws DaoException
     */

    public Spending findAllByDate(Date date) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Spending spending = null;
        try {
            connection = this.getConnection();
            String query = "SELECT * FROM SPENDING WHERE DATE = ? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, date);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int expenseid = resultSet.getInt("EXPENSE_ID");
                double amount = resultSet.getDouble("AMOUNT");
                Date dateIncurred = resultSet.getDate("Date");
                String category = resultSet.getString("CATEGORY");
                String title = resultSet.getString("TITLE");

                spending = new Spending(expenseid, title, category, amount, dateIncurred);
            }
        } catch (SQLException e) {
            throw new DaoException("findUserByUsernamePassword() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findUserByUsernamePassword() " + e.getMessage());
            }
        }
        return spending;     // reference to User object, or null value
    }

    public boolean AddSpending(String title, String category, double amount, Date date) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.getConnection();

            // Get the highest EXPENSE_ID to generate a new ID
            String idQuery = "SELECT MAX(EXPENSE_ID) FROM SPENDING";
            PreparedStatement idStatement = connection.prepareStatement(idQuery);
            ResultSet idResult = idStatement.executeQuery();
            int getid = 1;
            if (idResult.next()) {
                getid = idResult.getInt(1) + 1;
            }


            // Corrected SQL insert query
            String query2 = "INSERT INTO SPENDING (EXPENSE_ID, TITLE, CATEGORY, AMOUNT, DATE) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setInt(1, getid);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, category);
            preparedStatement.setDouble(4, amount);
            preparedStatement.setDate(5, date);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException("Failed to add expense: " + e.getMessage());
        }

    }
    public boolean DeleteSpending(int id) throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.getConnection();  // Initialize connection

            String query = "DELETE FROM SPENDING WHERE EXPENSE_ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0; // Returns true if at least one row was deleted

        } catch (SQLException e) {
            throw new DaoException("Error deleting expense with ID " + id + ": " + e.getMessage());
        }
    }
}

