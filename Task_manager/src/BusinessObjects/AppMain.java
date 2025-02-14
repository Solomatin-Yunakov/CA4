package BusinessObjects;

/** OOP Feb 2022
 * This AppMain demonstrates the use of a Data Access Object (DAO)
 * to separate Business logic from Database specific logic.
 * It uses Data Access Objects (DAOs),
 * Data Transfer Objects (DTOs), and  a DAO Interface to define
 * a contract between Business Objects and DAOs.
 *
 * "Use a Data Access Object (DAO) to abstract and encapsulate all
 * access to the data source. The DAO manages the connection with
 * the data source to obtain and store data" Ref: oracle.com
 *
 * Here, we use one DAO per database table.
 *
 * Use the SQL script "CreateSpendingss.sql" included with this project
 * to create the required MySQL Spendings_database and Spendings table.
 */

import DAOs.MySqlSpendingsDao;
import DAOs.SpendingsDaoInterface;
import Exceptions.DaoException;

import java.sql.Connection;
import java.util.List;
import DAOs.MySqlDao;
import DTOs.Spending;

public class AppMain
{
    public static void main(String[] args)
    {

       // MySqlSpendingsDao problem= new MySqlSpendingsDao.getConnection();

        SpendingsDaoInterface ISpendingsDao = new MySqlSpendingsDao();  //"ISpendingsDao" -> "I" stands for for

//        // Notice that the SpendingsDao reference is an Interface type.
//        // This allows for the use of different concrete implementations.
//        // e.g. we could replace the MySqlSpendingsDao with an OracleSpendingsDao
//        // (accessing an Oracle Database)
//        // without changing anything in the Interface.
//        // If the Interface doesn't change, then none of the
//        // code in this file that uses the interface needs to change.
//        // The 'contract' defined by the interface will not be broken.
//        // This means that this code is 'independent' of the code
//        // used to access the database. (Reduced coupling).
//
//        // The Business Objects require that all Spendings DAOs implement
//        // the interface called "SpendingsDaoInterface", as the code uses
//        // only references of the interface type to access the DAO methods.

        try
        {
            System.out.println("\nCall findAllSpendingss()");

            List<Spending> spendings = ISpendingsDao.findAllSpendings();     // call a method in the DAO

            if( spendings.isEmpty() )
                System.out.println("There are no Spendingss");
            else {
                for (Spending Spendings : spendings)
                    System.out.println("Spendings: " + Spendings.toString());
            }

            // test dao - with Spendingsname and password that we know are present in the database
            System.out.println("\nCall: findSpendingsBySpendingsnamePassword()");
            String date = "";
            String title = "";
            String category = "";
            Double amount = 0.0;

            Spending SpendingsByDate = ISpendingsDao.findAllByDate(date);

            if( SpendingsByDate != null ) // null returned if Spendingsid and password not valid
                System.out.println("Spendings found: " + SpendingsByDate);
            else
                System.out.println("Spendingsname with that date not found");

            // test dao - with an invalid Spendingsname (i.e. not in database)
            date = "madmax";
            category = "thunderdome";
            SpendingsByDate = ISpendingsDao.findAllByDate(date);
            if(SpendingsByDate != null)
                System.out.println("Spendingsname: " + date + " was found: " + SpendingsByDate);
            else
                System.out.println("Spendingsname: " + date + ", password: " + SpendingsByDate +" is not valid.");
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }
}
