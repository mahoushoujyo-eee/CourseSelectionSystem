package consoleVersion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class StudentOperator {
    public static void display()
    {
        String major = LogIn.getMajor();
        Scanner input = new Scanner(System.in);

        System.out.println("Hello," + major + " student!");
        boolean isContinue;
        do {
            isContinue = true;

            showAllCourse();
            showSelectedCourse();
            System.out.println("Please choose to add or delete course: ");
            System.out.println("A) add    B) delete   C) change password");
            System.out.println("D) cancel");
            switch (input.nextLine())
            {
                case "A":addCourse();break;
                case "B":deleteCourse();break;
                case "C":changPassword();break;
                case "D":isContinue = false;break;
                default:
                    System.out.println("Your input is out of bound!");
            }

        }while (isContinue);
    }

    //展示被选课程
    private static void showSelectedCourse()
    {
        System.out.println("There are your selected courses");
        System.out.printf("%-10s%-10s%-10s  %s\n", "Course", "Selected", "Capacity", "Majors");
        for (int i = 0; i < RWSelectedCourse.studentAccounts.size(); i++)
        {
            if (RWSelectedCourse.studentAccounts.get(i).equals(LogIn.account))
                RWCourse.showCourse(RWSelectedCourse.courseNames.get(i));
        }
    }

    //展示所有可选课程
    private static void showAllCourse()
    {
        System.out.println("There are all courses you can choose");
        System.out.printf("%-10s%-10s%-10s  %s\n", "Course", "Selected", "Capacity", "Majors");
        for (int i = 0; i < RWCourse.names.size(); i++)
        {
            ArrayList<String> majors = new ArrayList<>(Arrays.asList(RWCourse.majors.get(i)));
            if (majors.contains(LogIn.identity.substring(0, LogIn.identity.length() - 7)))
                RWCourse.showCourse(RWCourse.names.get(i));
        }
    }

    //重载方法：为管理员替选提供的方法
    public static void showSelectedCourse(String account)
    {
        System.out.println("There are your selected courses");
        System.out.printf("%-10s%-10s%-10s  %s\n", "Course", "Selected", "Capacity", "Majors");
        for (int i = 0; i < RWSelectedCourse.studentAccounts.size(); i++)
        {
            if (RWSelectedCourse.studentAccounts.get(i).equals(account))
                RWCourse.showCourse(RWSelectedCourse.courseNames.get(i));
        }
    }

    //重载方法：为管理员替选提供的方法
    public static void showAllCourse(String identity)
    {
        System.out.println("There are all courses you can choose");
        System.out.printf("%-10s%-10s%-10s  %s\n", "Course", "Selected", "Capacity", "Majors");
        for (int i = 0; i < RWCourse.names.size(); i++)
        {
            ArrayList<String> majors = new ArrayList<>(Arrays.asList(RWCourse.majors.get(i)));
            if (majors.contains(identity.substring(0, identity.length() - 7)))
                RWCourse.showCourse(RWCourse.names.get(i));
        }
    }

    private static void addCourse()
    {

        if (RWSelectedCourse.nowSelectionOfStudent(LogIn.account) >= 3)
        {
            System.out.println("Your cannot select more course");
            return;
        }


        Scanner input = new Scanner(System.in);
        System.out.print("Please input a course name:");
        String courseName = input.nextLine();
        for (String course: RWCourse.names)
        {
            if (course.equals(courseName)) {
                //System.out.println(RWSelectedCourse.nowSelectionOfCourse(course));
                if (RWSelectedCourse.nowSelectionOfCourse(course) >= Double.parseDouble(RWCourse.capacities.get(RWCourse.names.indexOf(course))))
                {
                    System.out.println("This course is up to max capacity!");
                    return;
                }
                RWSelectedCourse.add(courseName, LogIn.account);
                return;
            }
        }
        System.out.println("This course is not existed!");
    }

    //重载方法，理由同前面重载
    public static void addCourse(String account)
    {

        if (RWSelectedCourse.nowSelectionOfStudent(account) >= 3)
        {
            System.out.println("Your cannot select more course");
            return;
        }


        Scanner input = new Scanner(System.in);
        System.out.print("Please input a course name:");
        String courseName = input.nextLine();
        for (String course: RWCourse.names)
        {
            if (course.equals(courseName)) {
                //System.out.println(RWSelectedCourse.nowSelectionOfCourse(course));
                if (RWSelectedCourse.nowSelectionOfCourse(course) >= Double.parseDouble(RWCourse.capacities.get(RWCourse.names.indexOf(course))))
                {
                    System.out.println("This course is up to max capacity!");
                    return;
                }
                RWSelectedCourse.add(courseName, account);
                return;
            }
        }
        System.out.println("This course is not existed!");
    }

    private static void deleteCourse()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Please input a course name:");
        String courseName = input.nextLine();
        for (int i = 0; i < RWSelectedCourse.courseNames.size(); i++)
        {
            if (RWSelectedCourse.courseNames.get(i).equals(courseName) && RWSelectedCourse.studentAccounts.get(i).equals(LogIn.account))
            {
                RWSelectedCourse.studentAccounts.remove(i);
                RWSelectedCourse.courseNames.remove(i);
                return;
            }
        }
        System.out.println("This course is not existed!");
    }

    //重载
    public static void deleteCourse(String account)
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Please input a course name:");
        String courseName = input.nextLine();
        for (int i = 0; i < RWSelectedCourse.courseNames.size(); i++)
        {
            if (RWSelectedCourse.courseNames.get(i).equals(courseName) && RWSelectedCourse.studentAccounts.get(i).equals(account))
            {
                RWSelectedCourse.studentAccounts.remove(i);
                RWSelectedCourse.courseNames.remove(i);
                return;
            }
        }
        System.out.println("This course is not existed!");
    }

    //学生改密码方法
    private static void changPassword()
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Please input new password:");
        String password = input.nextLine();
        if (password.contains(" ")) {
            System.out.println("Your input contains ' '");
            return;
        }
        RWAccount.passwords.set(RWAccount.accounts.indexOf(LogIn.account), password);
        System.out.println("Change Password Successfully");
    }
}
