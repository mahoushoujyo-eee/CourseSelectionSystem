package consoleVersion;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


//这个是管理注册功能的类
public class Register {
    private static String account;
    private static String password;
    private static ArrayList<String> administratorId;

    public static void display() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        do {
            System.out.print("Please input your account:");
            account = input.nextLine();
        }while (!checkAccountExist());
        System.out.print("Input your password:");
        password = input.nextLine();

        RWAccount.add(account, password, "Administrator");

        System.out.println("Register successfully");
    }

    //这里我写了一个并未使用的权限检验方法，是为了确定当前注册者是否有类似于邀请码的信息来通过检验
    public static boolean isEligible() throws FileNotFoundException {
        administratorId = ReadAdId.readId();

        System.out.println("Please input administrator id");
        if (administratorId.contains(new Scanner(System.in).nextLine()))
            return true;
        else
            return false;
    }


    //这里是检查注册账户是否是已存在账户
    private static boolean checkAccountExist() throws FileNotFoundException {

        //System.out.println(accounts);
        if (RWAccount.accounts.contains(account))
            return false;
        else
        {
            System.out.println("This account already exists");
            return true;
        }
    }

    //这是一个简单的提示消息，但是因为sout貌似没有返回值，所以我另写了一个带返回值的方法在判断中组合使用
//    private static boolean print()
//    {
//
//        return true;
//    }
}
