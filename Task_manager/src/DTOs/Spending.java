package DTOs;
import java.sql.Date;


/**                                                     OOP Feb 2022
 *  Data Transfer Object (DTO)
 *
 * This POJO (Plain Old Java Object) is called the Data Transfer Object (DTO).
 * (or, alternatively, the Model Object or the Value Object).
 * It is used to transfer data between the DAO and the Business Objects.
 * Here, it represents a row of data from the Spending database table.
 * The DAO creates and populates a Spending object (DTO) with data retrieved from
 * the resultSet and passes the Spending object to the Business Layer.
 *
 * Collections of DTOs( e.g. ArrayList<Spending> ) may also be passed
 * between the Data Access Layer (DAOs) and the Business Layer objects.
 */

public class Spending
{
    private int expensesid;
    private String title;
    private String category;
    private double amount;
    private Date dateIncurred;

    public Spending(int expensesid, String title, String category, double amount, Date dateIncurred)
    {
        this.expensesid = expensesid;
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.dateIncurred = dateIncurred;
    }

    public Spending(String title, String category, double amount, Date dateIncurred)
    {
        this.expensesid = 0;
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.dateIncurred = dateIncurred;
    }

    public Spending()
    {
    }

    public int getExpensesidgetExpensesid()
    {
        return expensesid;
    }

    public void setExpensesid(int expensesid)
    {
        this.expensesid = expensesid;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setamount(double amount)
    {
        this.amount = amount;
    }

    public Date getDateIncurred()
    {
        return dateIncurred;
    }

    public void setDateIncurred(Date dateIncurred)
    {
        this.dateIncurred = dateIncurred;
    }

    @Override
    public String toString()
    {
        return "Spending{" + "expensesid=" + expensesid + ", title=" + title + ", category=" +
                category + ", amount=" + amount + ", dateIncurred=" + dateIncurred + '}';
    }

}
