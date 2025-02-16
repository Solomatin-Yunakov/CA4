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
import DAOs.MySqlEarningsDao;
import DAOs.EarningsDaoInterface;
import Exceptions.DaoException;

import java.sql.Connection;
import java.util.List;
import DAOs.MySqlDao;
import DTOs.Spending;
import DTOs.Earnings;
import java.sql.Date;

public class AppMain {
    public static void main(String[] args) {

        // MySqlSpendingsDao problem= new MySqlSpendingsDao.getConnection();

        SpendingsDaoInterface ISpendingsDao = new MySqlSpendingsDao();
        EarningsDaoInterface IEarningsDao = new MySqlEarningsDao();//"ISpendingsDao" -> "I" stands for for

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

        try {
            System.out.println("\nCall findAllSpendingss()");

            List<Spending> spendings = ISpendingsDao.findAllSpendings();     // call a method in the DAO

            if (spendings.isEmpty())
                System.out.println("There are no Spendingss");
            else {
                for (Spending Spendings : spendings)
                    System.out.println("Spendings: " + Spendings.toString());
            }

            // test dao - with Spendingsname and password that we know are present in the database
            System.out.println("\nCall: findSpendingsBySpendingsnamePassword()");
            Date date = new Date((2004 / 5 / 23));
            String title = "";
            String category = "";
            Double amount = 0.0;


            // test dao - with an invalid Spendingsname (i.e. not in database)
            date = new Date((2012 / 2 / 4));


            String titleToAdd = "fefef";
            String categoryToAdd = "grbr";
            double amountToAdd = 344.3;
            Date dateToAdd = new Date((2004 / 01 / 22));
            boolean addNewSpend = ISpendingsDao.AddSpending(titleToAdd, categoryToAdd, amountToAdd, dateToAdd);     // call a method in the DAO


            int idtodeleteSpendindg = 4;
            boolean DeleteById = ISpendingsDao.DeleteSpending(idtodeleteSpendindg);     // call a method in the DAO

            if (DeleteById) {
                System.out.println("Expense deleted successfully.");
            } else {
                System.out.println("No expense found with that ID.");
            }


            // test dao - with Spendingsname and password that we know are present in the database


            List<Earnings> earnings = IEarningsDao.findAllEarnings();     // call a method in the DAO

            if (earnings.isEmpty())
                System.out.println("There are no Earningss");
            else {
                for (Earnings Earning : earnings)
                    System.out.println("Earnings : " + Earning.toString());
            }

            // test dao - with Spendingsname and password that we know are present in the database

            date = new Date((2004 / 5 / 23));
            title = "";
            amount = 0.0;


            // test dao - with an invalid Spendingsname (i.e. not in database)
            date = new Date((2012 / 2 / 4));


            amountToAdd = 344.3;
            Date dateToAddErnings = new Date((2004 / 01 / 22));
            boolean addNewEarn = IEarningsDao.AddEarnings(titleToAdd, amountToAdd, dateToAddErnings);     // call a method in the DAO

            if (earnings.isEmpty())
                System.out.println("There are no Earnings");
            else {

                System.out.println("Sucsess Added Earnings.");
            }
            int idtodeleteEarn = 5;
            boolean DeleteEarningss = IEarningsDao.DeleteEarnings(idtodeleteEarn);     // call a method in the DAO

            if (DeleteEarningss) {
                System.out.println("Expense deleted successfully.");
            } else {
                System.out.println("No expense found with that ID.");
            }


        /* critical bug with calling list, in my head i have this logic how its sdhould works
        // test dao - with Spendingsname and password that we know are present in the database
            Date datetofind=new Date((2004/01/22));
         List<Earnings> earningsbydate= IEarningsDao.findAllByDateEarnings(datetofind);     // call a method in the DAO but i cant solve this bug i dont know what to do, sorry

            if( earnings.isEmpty() )
                System.out.println("There are no Earningss");
            else {
                for (Earnings Earning : earnings)
                    System.out.println("Earnings : " + Earning.toString());
            }

            List<Spending> spendingsbydate = ISpendingsDao.findAllByDate(datetofind);
            // call a method in the DAO but i cant solve this bug i dont know what to do, sorry

            if( spendingsbydate.isEmpty() )
                System.out.println("There are no Spendingss");
            else {
                for (Spending Spendings : spendingsbydate)
                    System.out.println("Spendings: " + spendingsbydate.toString());
            }
            System.out.println("Spendings And Earnings : " + spendingsbydate.toString()+earningsbydate);
            double gettotal = IEarningsDao.gettotalEarings();
            System.out.println("Total Earnings : " + gettotal);
            double gettotalSpend = ISpendingsDao.gettotalSpendings();
            System.out.println("Total Earnings : " + gettotalSpend);
            double totalbalance=gettotal-gettotalSpend;
            System.out.println("Total BALANCE : " + totalbalance);
        }

        catch( DaoException e )
        {
            e.printStackTrace();
        }
*/

        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
