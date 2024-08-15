package consoleVersion;

import java.io.FileNotFoundException;
import java.util.Scanner;

//这是该项目的主要调用方法的类
public class CourseSelectionConsole {
    private static void initialize()
    {

    }

    private static void close()
    {

    }

    public static void main(String[] args) throws FileNotFoundException {
        // 所有的读取数据初始化的操作都放在这里
        initialize();

        //在程序启动的时候把文本中所有信息读入链表
        RWAccount.readAll();
        RWCourse.readAll();
        RWCourseSelection.readAll();

        Scanner input = new Scanner(System.in);
        System.out.println("CourseSelectionSystem");

        // 最一开始就应该问是管理员还是学生
        // 然后再问是登录还是注册
        // 登录后才是后续的操作

        boolean isRegister;//这个变量是判断这次进行的操作是否是注册，决定了循环结束后是否要因为没有查询的对应账户密码而输出报错信息
        do {
            isRegister = false;
            System.out.println("Please choose to log in or register"); //注册登录
            System.out.println("A) Log in   B) Register");
            String choose = input.nextLine();

            // 统一风格，全体if-else或全体switch-case
            if (choose.equals("A"))
            {
                //简单的询问登录身份并且读取之后在登陆时进行核验
                System.out.println("Please choose one identity to continue:");
                System.out.println("A) Administrator   B) Student");
                String identity = "";
                switch (input.nextLine())
                {
                    case "A":identity = "Administrator";break; // Login.adminLogin(); break;
                    case "B":identity = "Student";break; // Login.studentLogin(); break;
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


        // 没必要再问一遍了，前面问过了。
        //根据是否是管理员的判断决定进入哪个分支
        if (LogIn.isAdministrator())
            AdministratorOperator.display();
        else
            StudentOperator.display();

        //最后在程序正常关闭时将链表写入文件
        RWAccount.close();
        RWCourse.close();
        RWCourseSelection.close();

        close();
    }
}
