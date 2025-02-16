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

public class Earnings
{
    private int earningid;
    private String title;

    private double amount;
    private Date dateIncurred;

    public Earnings(int earningid, String title,  double amount, Date dateIncurred)
    {
        this.earningid = earningid;
        this.title = title;

        this.amount = amount;
        this.dateIncurred = dateIncurred;
    }

    public Earnings(String title,  double amount, Date dateIncurred)
    {
        this.earningid = 0;
        this.title = title;

        this.amount = amount;
        this.dateIncurred = dateIncurred;
    }

    public Earnings()
    {
    }

    public int getEarningid()
    {
        return earningid;
    }

    public void setEarningid(int earningid)
    {
        this.earningid = earningid;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
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
        return "Spending{" + "earningid=" + earningid + ", title=" + title + ", amount=" + amount + ", dateIncurred=" + dateIncurred + '}';
    }

}
