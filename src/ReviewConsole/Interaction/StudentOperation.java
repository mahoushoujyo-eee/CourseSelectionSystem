package ReviewConsole.Interaction;

import ReviewConsole.Business.CourseBusiness;
import ReviewConsole.Business.CourseCompatibilityBusiness;
import ReviewConsole.Business.CourseSelectionBusiness;
import ReviewConsole.Business.StudentBusiness;
import ReviewConsole.Data.Course;

import java.util.Scanner;

public class StudentOperation {
    private static Scanner input = new Scanner(System.in);

    public static void judgeLogInOrRegister()
    {
        boolean isContinue;
        do
        {
            System.out.println("Please choose log in or register:");
            System.out.println("A) log in   B) cancel");
            isContinue = true;
            switch (input.nextLine())
            {
                case "A":
                    studentLogIn();
                    break;
                case "B":
                    isContinue = false;
                    break;
                default:
                    System.out.println("Your input is out of bound");
            }
        }while (isContinue);
    }

    private static void studentLogIn()
    {
        System.out.println("There is the pane for student to log in");

        do
        {
            System.out.print("Please input your account:");
            String number = input.nextLine();
            System.out.print("Please input your password:");
            String password = input.nextLine();
            if (!StudentBusiness.judgeStudentNumberExist(number))
            {
                System.out.println("This student number is not exist");
            }
            else if (StudentBusiness.judgeStudentNumberMatchPassword(number, password))
            {
                System.out.println("Log in Successfully");
                studentOperate(number);
                break;
            }
            else
            {
                System.out.println("Your account don't match your password");
            }
        }while (true);
    }

    private static void studentOperate(String number)
    {
        System.out.println("Hello, student");


        boolean isContinue;

        do
        {
            showAllCourseSelection(number);
            showCourseSelectionWithStudent(number);
            System.out.println("Please choose one to continue:");
            System.out.println("A) select course");
            System.out.println("B) withdraw course");
            System.out.println("C) change password");
            System.out.println("D) cancel");

            isContinue = true;
            switch (input.nextLine())
            {
                case "A":
                    studentSelectCourse(number);
                    break;
                case "B":
                    studentWithdrawCourse(number);
                    break;
                case "C":
                    changePassword(number);
                    break;
                case "D":
                    isContinue = false;
                    break;
                default:
                    System.out.println("Your input is out of bound");
            }
        }while (isContinue);
    }

    public static void studentSelectCourse(String studentNumber)
    {
        System.out.print("Please input the course your want to select:");
        String courseName = input.nextLine();
        if (!CourseBusiness.judgeCourseNameExist(courseName))
        {
            System.out.println("This course is not existed");
            return;
        }
        if (!CourseSelectionBusiness.judgeCapacityEnough(courseName, CourseSelectionBusiness.getStudentCountsOfCourse(courseName) + ""))
        {
            System.out.println("The course is full");
            return;
        }
        CourseSelectionBusiness.addCourseSelection(courseName, studentNumber);
    }

    public static void studentWithdrawCourse(String studentNumber)
    {
        System.out.print("Please input the course you want to withdraw:");
        String courseName = input.nextLine();
        if (!CourseBusiness.judgeCourseNameExist(courseName))
        {
            System.out.println("This course is not existed");
            return;
        }
        else if (!CourseSelectionBusiness.judgeCourseSelectionExist(courseName, studentNumber))
        {
            System.out.println("You have not select this course");
            return;
        }
        CourseSelectionBusiness.removeCourseSelection(courseName, studentNumber);
    }

    private static void changePassword(String StudentNumber)
    {
        System.out.print("Please input new password:");
        String newPassword = input.nextLine();
        StudentBusiness.changePassword(StudentNumber, newPassword);
        System.out.println("Change Successfully");
    }

    public static void showAllCourseSelection(String studentNumber)
    {
        System.out.println("There are courses you can select");
        System.out.printf("%-15s%-15s%-15s%-15s\n", "name", "#selected", "capacity", "majors");
        for (Course course: CourseSelectionBusiness.sortBySelectedStudent(CourseCompatibilityBusiness.getCoursesOfMajor(StudentBusiness.inquireStudentByNumber(studentNumber).getMajor())))
        {
            System.out.printf("%-15s%-15d%-15s", course.getName(), CourseSelectionBusiness.getStudentCountsOfCourse(course.getName()), course.getCapacity());
            for (String major : CourseCompatibilityBusiness.getMajorsOfCourse(course.getName()))
                System.out.print(major + " ");
            System.out.println();
        }
    }

    public static void showCourseSelectionWithStudent(String studentNumber)
    {
        System.out.println("There are courses you have selected");
        System.out.printf("%-15s%-15s%-15s%-15s\n", "name", "#selected", "capacity", "majors");
        for (Course course: CourseSelectionBusiness.sortBySelectedStudent(CourseSelectionBusiness.getSelectedCourseOfStudent(studentNumber)))
        {
            System.out.printf("%-15s%-15d%-15s", course.getName(), CourseSelectionBusiness.getStudentCountsOfCourse(course.getName()), course.getCapacity());
            for (String major : CourseCompatibilityBusiness.getMajorsOfCourse(course.getName()))
                System.out.print(major + " ");
            System.out.println();
        }
    }

}
