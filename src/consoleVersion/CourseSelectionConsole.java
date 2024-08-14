package consoleVersion;

import java.io.FileNotFoundException;
import java.util.Scanner;

//这是该项目的主要调用方法的类
public class CourseSelectionConsole {
    public static void main(String[] args) throws FileNotFoundException {
        //在程序启动的时候把文本中所有信息读入链表
        RWAccount.readAll();
        RWCourse.readAll();
        RWSelectedCourse.readAll();

        //注册登录
        Scanner input = new Scanner(System.in);
        System.out.println("CourseSelectionSystem");

        boolean isRegister;//这个变量是判断这次进行的操作是否是注册，决定了循环结束后是否要因为没有查询的对应账户密码而输出报错信息
        do {
            isRegister = false;
            System.out.println("Please choose to log in or register");
            System.out.println("A) Log in   B) Register");
            String choose = input.nextLine();
            if (choose.equals("A"))
            {
                //简单的询问登录身份并且读取之后在登陆时进行核验
                System.out.println("Please choose one identity to continue:");
                System.out.println("A) Administrator   B) Student");
                String identity = "";
                switch (input.nextLine())
                {
                    case "A":identity = "Administrator";break;
                    case "B":identity = "Student";break;
                    default:
                        System.out.println("Your input is out of bound");
                }
                if (identity.equals(""))
                    continue;
                LogIn.display(identity);
            }
            else if (choose.equals("B")) {
                Register.display();
                isRegister = true;
            }
            else
            {
                System.out.println("Your input is a wrong message!");
                isRegister = true;//不是注册也要写true，是为了防止输出账号密码错误的报错信息
            }
        }while (!LogIn.inspect(isRegister));


        //根据是否是管理员的判断决定进入哪个分支
        if (LogIn.isAdministrator())
            AdministratorOperator.display();
        else
            StudentOperator.display();

        //最后在程序正常关闭时将链表写入文件
        RWAccount.close();
        RWCourse.close();
        RWSelectedCourse.close();
    }
}
