package DAOs;

/** OOP Feb 2022
 * UserDaoInterface
 *
 * Declares the methods that all UserDAO types must implement,
 * be they MySql User DAOs or Oracle User DAOs etc...
 *
 * Classes from the Business Layer (users of this DAO interface)
 * should use reference variables of this interface type to avoid
 * dependencies on the underlying concrete classes (e.g. MySqlUserDao).
 *
 * More sophisticated implementations will use a factory
 * method to instantiate the appropriate DAO concrete classes
 * by reading database configuration information from a
 * configuration file (that can be changed without altering source code)
 *
 * Interfaces are also useful when testing, as concrete classes
 * can be replaced by mock DAO objects.
 */

import DTOs.Earnings;
import DTOs.Spending;
import Exceptions.DaoException;

import java.sql.Date;
import java.util.List;
import java.sql.Date;

public interface EarningsDaoInterface
{
    public List<Earnings> findAllEarnings() throws DaoException;

    public Earnings findAllByDateEarnings(Date date) throws DaoException;
    public boolean AddEarnings(String title, double amount, Date date) throws DaoException;
    public boolean DeleteEarnings(int id) throws DaoException;
    public double gettotalEarings() throws DaoException;
}

