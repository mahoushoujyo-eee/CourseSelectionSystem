package ReviewConsole.Business;

import ReviewConsole.Data.Administrator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AdministratorBusiness
{
    private static String path = "src/ReviewConsole/TxtData/Administrator.txt";
    private static ArrayList<Administrator> administrators = new ArrayList<>();

    public static boolean judgeAccountExist(Administrator administrator)
    {
        for (Administrator administratorData: administrators)
            if (administratorData.getAccount().equals(administrator.getAccount()))
                return true;
        return false;
    }

    public static boolean judgeAccountLegal(String account)
    {
        if (account.contains(" "))
            return false;
        else if (account.isEmpty())
            return false;
        else
            return true;
    }

    public static boolean judgePasswordLegal(String password)
    {
        if (password.contains(" "))
            return false;
        else if (password.isEmpty())
            return false;
        return true;
    }

    public static void addAdministrator(Administrator administrator)
    {
        administrators.add(administrator);
    }

    public static boolean judgeAccountAndPasswordMatch(Administrator administrator)
    {
        for (Administrator administratorData: administrators)
            if (administratorData.getAccount().equals(administrator.getAccount()) &&
            administratorData.getPassword().equals(administrator.getPassword()))
                return true;
        return false;
    }

    public static void initialize() throws FileNotFoundException
    {
        administrators.clear();

        Scanner input = new Scanner(new File(path));
        while (input.hasNext())
        {
            String[] administratorData = input.nextLine().split(" ");
            Administrator administrator = new Administrator();
            administrator.setAccount(administratorData[0]);
            administrator.setPassword(administratorData[1]);
            administrators.add(administrator);
        }
    }

    public static void close() throws FileNotFoundException
    {
        PrintWriter printWriter = new PrintWriter(path);

        for (Administrator administrator: administrators)
        {
            if (administrator != null)
                printWriter.println(administrator.getAccount() + " " + administrator.getPassword());
        }

        printWriter.close();
    }

}
