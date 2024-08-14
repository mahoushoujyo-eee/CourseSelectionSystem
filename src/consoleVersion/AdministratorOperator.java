package consoleVersion;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


//处理管理员事务
public class AdministratorOperator {
    public static void display() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello, Administrator!");

        //循环处理管理员的基本操作
        boolean isContinue = true;
        do {

            System.out.println("Please choose one to operate:");
            System.out.println("A) Operate student");
            System.out.println("B) Operate course");
            System.out.println("C) Inquire selected courses");
            System.out.println("D) Cancel");

            switch (input.nextLine())
            {
                case "A":operateStudent();break;
                case "B":operateCourse();break;
                case "C":inquireCourseSelection();break;
                case "D":isContinue = false;break;
                default:
                System.out.println("Your input has a error!");
            }
        }while (isContinue);
    }


    //对学生执行的操作
    private static void operateStudent() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);


        //循环处理
        boolean isContinue = true;
        do {
            System.out.println("There are all students:");
            RWAccount.showStudents();
            System.out.print("Operate Student\nPlease choose to continue: \n");
            System.out.println("A) Create    B) Delete");
            System.out.println("C) Update D) Retrieve");
            System.out.println("E) Select for student");
            System.out.println("F) Cancel");

            switch (input.nextLine())
            {
                case "A":
                    createStudent();break;
                case "B":
                    deleteStudent();break;
                case "C":
                    updateStudent();break;
                case "D":
                    inquireStudent();break;
                case "E":
                    selectForStudent();break;
                case "F":isContinue = false;break;
                default:
                System.out.println("Your input has a error!");;
            }
        }while (isContinue);
    }

    //对课程进行的操作
    private static void operateCourse()
    {
        Scanner input = new Scanner(System.in);

        //循环处理
        boolean isContinue = true;
        do {
            System.out.print("Operate Course\nPlease choose to continue: \n");
            System.out.println("A) Create    B) Delete");
            System.out.println("C) Update    D) Retrieve");
            System.out.println("E) Cancel");

            switch (input.nextLine())
            {
                case "A":createCourse();break;
                case "B":deleteCourse();break;
                case "C":updateCourse();break;
                case "D":inquireCourse();break;
                case "E":isContinue = false;break;
                default:
                    System.out.println("Your input is out of bound");break;
            }
        }while (isContinue);
    }

    //查询选课内容的方法
    private static void inquireCourseSelection()
    {
        Scanner input = new Scanner(System.in);

        boolean isContinue;
        do {
            isContinue = true;
            System.out.println("Please choose one to continue:");
            System.out.println("A) show all   B) inquire course of major  C) cancel");
            switch (input.nextLine())
            {
                case "A":showAll();break;
                case "B":showDesignatedMajor();break;
                case "C":isContinue = false;break;
                default:
                    System.out.println("Your input is out of bound");
            }

        }while (isContinue);
    }

    //查询选课的子方法：展示所有课程的选课情况
    private static void showAll()
    {
        System.out.printf("%-10s%-10s%-10s  %s\n", "Course", "Selected", "Capacity", "Majors");
        for (Integer index: RWCourse.sort())
            inquireSelectedCourse(RWCourse.names.get(index));
    }

    //查询选课的子方法：展示指定专业的课程选课情况
    private static void showDesignatedMajor()
    {
        Scanner input = new Scanner(System.in);

        System.out.println("Please input the major:");
        String major = input.nextLine();

        int count = 0;
        System.out.printf("%-10s%-10s%-10s  %s\n", "Course", "Selected", "Capacity", "Majors");
        for (Integer index: RWCourse.sort())
        {
            ArrayList<String> majors = new ArrayList<>(Arrays.asList(RWCourse.majors.get(index)));
            if (majors.contains(major))
            {
                count++;
                inquireSelectedCourse(RWCourse.names.get(index));
            }
        }

        if (count == 0)
        {
            System.out.println("Not Found!");
        }
    }


    //原来写的展示选课内容的方法，后来经过展示格式的修改把他的所有展示都合并到RWCourse.showCourse()方法中了，这个就是一个展示课程被选情况以及其他信息的方法
    private static void inquireSelectedCourse(String courseName)
    {
        //Scanner input = new Scanner(System.in);

        //System.out.print("Please input course name you want to inquire:");
        //String courseName = input.nextLine();


        if (!RWCourse.names.contains(courseName))
        {
            System.out.println("This course is not existed");
            return;
        }
        //System.out.printf("%-10s%-10s%-10s  %s\n", "Course", "Selected", "Capacity", "Majors");
        RWCourse.showCourse(courseName);
        /*System.out.println("Student:");
        for (int i = 0; i < RWSelectedCourse.courseNames.size(); i++)
        {
            if (RWSelectedCourse.courseNames.get(i).equals(courseName))
            {
                System.out.print(RWSelectedCourse.studentAccounts.get(i) + " ");
            }
        }
        System.out.println();
        System.out.println("Now " + RWSelectedCourse.nowSelectionOfCourse(courseName) + " students have chosen this course");*/
    }

    private static void createCourse()
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Please input course's name: ");
        String name = input.nextLine();
        if (RWCourse.names.contains(name))
        {
            System.out.println("This course is existed");
            return;
        }
        System.out.print("Please input course's capacity: ");
        String capacity = input.nextLine();
        System.out.print("Please input applicable majors(use \",\" to split):");
        String[] major = input.nextLine().split(",");

        RWCourse.add(name, capacity, major);
    }

    private static void deleteCourse()
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Please input course's name: ");
        String name = input.nextLine();
        if (!RWCourse.names.contains(name))
        {
            System.out.println("This course is not existed");
            return;
        }
        RWCourse.majors.remove(RWCourse.names.indexOf(name));
        RWCourse.capacities.remove(RWCourse.names.indexOf(name));
        RWCourse.names.remove(name);
        RWSelectedCourse.removeSelectedData(name);
    }

    private static void updateCourse()
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Please input course's name: ");
        String name = input.nextLine();
        if (!RWCourse.names.contains(name))
        {
            System.out.println("This course is not existed");
            return;
        }
        System.out.println("Please choose one to update:");
        System.out.println("A) capacity   B) applicable majors");
        switch (input.nextLine())
        {
            case "A":System.out.print("Please input new capacity: ");
            String capacity = input.nextLine();
            if (Double.parseDouble(capacity) < 0)
            {
                System.out.println("Your input has error!");
                return;
            }
            if (isOutOfSelection(name, capacity))
                System.out.println("The capacity now is too small for student!");
            RWCourse.capacities.set(RWCourse.names.indexOf(name), capacity);
            break;
            case "B":System.out.print("Please input new majors(use \",\" to split): ");
            RWCourse.majors.set(RWCourse.names.indexOf(name), input.nextLine().split(","));
            break;
            default:
                System.out.println("Your input is out of bound");return;
        }
        System.out.println("Update Successfully");
    }

    private static void inquireCourse()
    {
        Scanner input = new Scanner(System.in);

        boolean isContinue;
        do {
            isContinue = true;
            System.out.println("Which one do you want to use to inquire:");
            System.out.println("A) name    B) major  C) cancel");

            switch (input.nextLine()) {
                case "A":inquireCourseName();break;
                case "B":inquireCourseMajor();break;
                case "C":isContinue = false;break;
                default:
                    System.out.println("Your input is out of bound");
            }
        }while (isContinue);
    }

    private static void createStudent() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        System.out.print("Please input student's name: ");
        String name = input.nextLine();
        System.out.print("Please input student's no: ");
        String no = input.nextLine();
        if (isRepeated(no))
        {
            System.out.println("The no you input is repeated!");
            return;
        }
        System.out.print("Please input student's major: ");
        String major = input.nextLine();
        RWAccount.add(name + no, "123456", major + "Student");

    }

    private static void deleteStudent() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        System.out.print("Please input student's no: ");
        String no = input.nextLine();

        boolean isDeleted = false;
        for (String account: RWAccount.accounts)
        {
            if (account.matches(".+" + no + "$"))
            {
                RWAccount.passwords.remove(RWAccount.accounts.indexOf(account));
                RWAccount.identities.remove(RWAccount.accounts.indexOf(account));
                RWAccount.accounts.remove(account);
                isDeleted = true;
                break;
            }
        }
        if (!isDeleted)
            System.out.println("The no you delete not exist");
    }

    private static void updateStudent()
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Please input student's no: ");
        String no = input.nextLine();
        boolean isFound = false;
        for (String account: RWAccount.accounts)
        {
            if (account.matches(".+" + no + "$"))
            {
                System.out.print("Please input new major:");
                String newMajor = input.nextLine();
                RWAccount.identities.set(RWAccount.accounts.indexOf(account), newMajor + "Student");
                isFound = true;
                break;
            }
        }

        if (!isFound)
        {
            System.out.println("The no you input is not found!");
        }
    }

    private static void inquireStudent()
    {
        Scanner input = new Scanner(System.in);

        boolean isContinue;
        do {
            isContinue = true;
            System.out.println("Which one do you want to use to inquire:");
            System.out.println("A) name    B) no   C) cancel");

            switch (input.nextLine()) {
                case "A":
                    inquireName();break;
                case "B":
                    inquireNo();break;
                case "C":isContinue = false;break;
                default:
                    System.out.println("Your input is out of bound");
            }
        }while (isContinue);
    }

    private static void selectForStudent() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        System.out.print("Please input student no: ");
        String no = input.nextLine();
        if (!isRepeated(no))
        {
            System.out.println("This no is not found");
            return;
        }

        String account = seekAccount(no);
        String identity = RWAccount.identities.get(RWAccount.accounts.indexOf(account));


        boolean isContinue;
        do {
            isContinue = true;

            StudentOperator.showAllCourse(identity);
            StudentOperator.showSelectedCourse(account);
            System.out.println("Please choose to add or delete course: ");
            System.out.println("A) add    B) delete   C) cancel");
            switch (input.nextLine())
            {
                case "A":StudentOperator.addCourse(account);break;
                case "B":StudentOperator.deleteCourse(account);break;
                case "C":isContinue = false;break;
                default:
                    System.out.println("Your input is out of bound!");
            }

        }while (isContinue);
    }

    //判断一个学号是否已被使用的方法看，是在创建学生方法中调用的
    private static boolean isRepeated(String no) throws FileNotFoundException {
        for (String account: RWAccount.accounts)
        {
            if (account.matches(".+" + no + "$"))
                return true;
        }
        return false;
    }

    //根据学号返回学生账户名，也就是学生姓名 + 学号
    private static String seekAccount(String no) throws FileNotFoundException {
        for (String account: RWAccount.accounts)
        {
            if (account.matches(".+" + no + "$")) {
                return account;
            }
        }
        return null;
    }

    //查询这个学生姓名是否存在并展示
    private static void inquireName()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Please input name to inquire: ");
        String name = input.nextLine();
        boolean isFound = false;
        for (String nameData: RWAccount.accounts)
        {
            if (nameData.startsWith(name))
            {
                RWAccount.showStudent(nameData);
                isFound = true;
            }
        }
        if (!isFound)
        {
            System.out.println("The name you input is not found");
        }
    }

    //查询这个学号是否存在并展示
    private static void inquireNo()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Please input no to inquire: ");
        String no = input.nextLine();
        boolean isFound = false;
        for (String noData: RWAccount.accounts)
        {
            if (noData.endsWith(no))
            {
                RWAccount.showStudent(noData);
                isFound = true;
            }
        }
        if (!isFound)
            System.out.println("The no you input is not found");
    }

    //查询课程名字并展示
    private static void inquireCourseName()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Please input name to inquire: ");
        String name = input.nextLine();
        boolean isFound = false;
        for (String nameData: RWCourse.names)
        {
            if (nameData.contains(name))
            {
                System.out.printf("%-10s%-10s%-10s  %s\n", "Course", "Selected", "Capacity", "Majors");
                RWCourse.showCourse(nameData);
                isFound = true;
            }
        }
        if (!isFound)
        {
            System.out.println("The name you input is not found");
        }
    }

    //根据专业查询选课情况
    private static void inquireCourseMajor()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Please input major to inquire: ");
        String majorName = input.nextLine();
        boolean isFound = false;
        for (String nameData: RWCourse.names)
        {
            ArrayList<String> newMajors = new ArrayList<>(Arrays.asList(RWCourse.majors.get(RWCourse.names.indexOf(nameData))));
            if (newMajors.contains(majorName))
            {
                //RWCourse.showCourse(nameData);
                isFound = true;
            }
        }
        if (!isFound)
        {
            System.out.println("The name you input is not found");
            return;
        }
        System.out.printf("%-10s%-10s%-10s  %s\n", "Course", "Selected", "Capacity", "Majors");
        for (String nameData: RWCourse.names)
        {
            ArrayList<String> newMajors = new ArrayList<>(Arrays.asList(RWCourse.majors.get(RWCourse.names.indexOf(nameData))));
            if (newMajors.contains(majorName))
            {
                RWCourse.showCourse(nameData);

            }
        }
    }

    //判断输入的新capacity是否足够容纳现在选课的人数
    private static boolean isOutOfSelection(String courseName, String capacity)
    {
        if (RWSelectedCourse.nowSelectionOfCourse(courseName) > Double.parseDouble(capacity))
            return true;
        else
            return false;
    }


}
