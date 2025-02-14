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

public  class MySqlSpendingsDao extends MySqlDao implements SpendingsDaoInterface {
    public Connection getConnection() throws DaoException
    {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/user_database";
        String username = "root";
        String password = "";
        Connection connection = null;

        try
        {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Failed to find driver class " + e.getMessage());
            System.exit(1);
        }
        catch (SQLException e)
        {
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
                String dateIncurred = resultSet.getString("DATE");
                Spending s = new Spending(expenseid, title, category, amount, dateIncurred);
                spendList.add(s);
            }
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

    public Spending findAllByDate(String date) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Spending spending = null;
        try
        {
            connection = this.getConnection();
            String query = "SELECT * FROM SPENDING WHERE DATE = ? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, date);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                int expenseid = resultSet.getInt("USER_ID");
                double amount = resultSet.getDouble("AMOUNT");
                String day = resultSet.getString("Date");
                String category = resultSet.getString("LAST_NAME");
                String title = resultSet.getString("FIRST_NAME");

                spending = new Spending(expenseid, title, category, amount, date);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findUserByUsernamePassword() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findUserByUsernamePassword() " + e.getMessage());
            }
        }
        return spending;     // reference to User object, or null value
    }
}

