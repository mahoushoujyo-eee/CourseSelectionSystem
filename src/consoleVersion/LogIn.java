package consoleVersion;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static consoleVersion.RWAccount.*;

//这个是保存登录信息，为其他类返回登录信息的类
public class LogIn {
    public static String account, password, identity;

    public static void display(String identity)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please input your account:");
        account = scanner.nextLine();
        System.out.print("Please input your password:");
        password = scanner.nextLine();
        LogIn.identity = identity;
        //RWAccount.show();
    }

    //这个方法是监测提供的账户，密码， 身份是否跟文件内信息符合
    public static boolean inspect(boolean isRegister) throws FileNotFoundException {

        int indexOfAccount = accounts.indexOf(account);

        if (accounts.contains(account) && RWAccount.passwords.get(indexOfAccount).equals(password) && identities.get(indexOfAccount).endsWith(identity)) {
            identity = identities.get(indexOfAccount);
            return true;
        }
        else
        {
            if (!isRegister)
                System.out.println("Your account or password is false!");
            return false;
        }
    }

    public static boolean validateUsernamePassword(String username, String password)
    {
        // admins
        for (int i = 0; i < admins.length(); i++)
        {
            Administrator admin = admins.get(i);

            if (admin.getUsername().equals(username) && admin.getPassword().equals(password))
                return true;
        }

        return false;
    }


    //返回当前用户是否是管理员的布尔值
    public static boolean isAdministrator()
    {
        return identity.equals("Administrator");
    }

    //返回当前学生的专业
    public static String getMajor()
    {
        return identity.substring(0, identity.length() - 7);
    }

    public static Administrator adminLogin()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input your username:");
        String username = input.nextLine();
        System.out.println("Please input your password:");
        String password = input.nextLine();
        // ...

        // if username, password exists => login success
        // else => error message.
    }
}
