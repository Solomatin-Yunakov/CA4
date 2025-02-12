package com.dkit.oop.sd2.DAOs;

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
import DAOs.SpendingsDaoInterface;
import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlSpendingsDao extends com.dkit.oop.sd2.DAOs.MySqlDao implements SpendingsDaoInterface
{
    /**
     * Will access and return a List of all users in User database table
     * @return List of User objects
     * @throws DaoException
     */
    @Override
    public List<Spend> findAllSpendings() throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Spend> spendList = new ArrayList<>();

        try
        {
            //Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "SELECT * FROM USER";
            preparedStatement = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                int expenseid = resultSet.getInt("EXPENSE_ID");
                String title = resultSet.getString("TITLE");
                String category = resultSet.getString("CATEGORY");
                double amount = resultSet.getDouble("AMOUNT");
                Date dateIncurred = resultSet.getDate("DATE");
                Spend s = new Spend(expenseid, firstname, category, username, password);
                spendList.add(S);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllSpendings() " + e.getMessage());
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
                throw new DaoException("findAllSpending() " + e.getMessage());
            }
        }
        return spendList;     // may be empty
    }

/*/**
 * Given a username and password, find the corresponding User
 * @param user_name
 * @param password
 * @return User object if found, or null otherwise
 * @throws DaoException
 */
    /*@Override
    public User findUserByUsernamePassword(String user_name, String password) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user_name);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                int expenseid = resultSet.getInt("USER_ID");
                double amount = resultSet.get("AMOUNT");
                Date date = resultSet.getDate("Date");
                String category = resultSet.getString("LAST_NAME");
                String title = resultSet.getString("FIRST_NAME");

                user = new User(expenseid, title, category, amount, date);
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
        return user;     // reference to User object, or null value
    }
}

