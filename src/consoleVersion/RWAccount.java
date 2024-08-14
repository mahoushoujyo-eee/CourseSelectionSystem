package consoleVersion;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//这个类是读写account.txt文件的类
public class RWAccount {
    private static String accountPath = "data/account.txt";

    public static ArrayList<String> accounts = new ArrayList<>();
    public static ArrayList<String> passwords = new ArrayList<>();
    public static ArrayList<String> identities = new ArrayList<>();

    //初始化列表
    public static void readAll() throws FileNotFoundException {
        Scanner input = new Scanner(new File(accountPath));

        RWAccount.accounts.clear();
        RWAccount.passwords.clear();
        RWAccount.identities.clear();

        while (input.hasNext())
        {
            String[] temps = input.nextLine().split(" ");
            RWAccount.accounts.add(temps[0]);
            RWAccount.passwords.add(temps[1]);
            RWAccount.identities.add(temps[2]);
        }

        input.close();
    }


    //这是添加新账户信息的方法
    public static void add(String account, String password, String identity)
    {
        accounts.add(account);
        passwords.add(password);
        identities.add(identity);
    }

    //这是关闭读写将所有列表信息重新写到文件中的方法
    public static void close() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new File(accountPath));

        for (int i = 0; i < accounts.size(); i++)
        {
            printWriter.println(accounts.get(i) + " " + passwords.get(i) + " " + identities.get(i));
        }
        printWriter.close();
    }

    //这个方法是展示文件中特定账户学生的信息
    public static void showStudent(String account)//student
    {
        System.out.println(account.substring(0, account.length() - 8) + " " + account.substring(account.length() - 8) + " " + identities.get(accounts.indexOf(account)).substring(0,(identities.get(accounts.indexOf(account)).length())-7));
    }

    //这个方法是展示account文件中所有学生的信息
    public static void showStudents()
    {
        System.out.printf("%-10s%-15s%-10s\n", "No", "Name", "Major");
        for (int i = 0; i < accounts.size(); i++)
        {
            if(identities.get(i).endsWith("Student"))
            {
                System.out.printf("%-10s%-15s%-10s\n", accounts.get(i).substring(accounts.get(i).length() - 8),
                        accounts.get(i).substring(0, accounts.get(i).length() - 8), identities.get(i).substring(0, identities.get(i).length() - 7));
            }
        }
    }

    //最开始写的一个写入方法，是在程序运行中对文件进行写操作的方法，后来被废弃了但是没有删除
    public static void write(String account, String password, String identity) throws FileNotFoundException {
        String outputString = account + " " + password + " " + identity;
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(accountPath, true));

        printWriter.println(outputString);
        printWriter.close();
    }
}
