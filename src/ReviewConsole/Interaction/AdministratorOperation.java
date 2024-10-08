package ReviewConsole.Interaction;

import ReviewConsole.Business.*;
import ReviewConsole.Data.Administrator;
import ReviewConsole.Data.Course;
import ReviewConsole.Data.Student;

import java.util.ArrayList;
import java.util.Scanner;


public class AdministratorOperation {
    private static Scanner input;

    static
    {
        input = new Scanner(System.in);
    }


    public static void chooseLogInOrRegister()
    {
        boolean isContinue;
        do
        {
            System.out.println("Please choose log in or register:");
            System.out.println("A) log in   B) register   C) cancel");
            isContinue = true;
            switch (input.nextLine())
            {
                case "A":
                    administratorLogIn();
                    break;
                case "B":
                    administratorRegister();
                    break;
                case "C":
                    isContinue = false;
                    break;
                default:
                    System.out.println("Your input is out of bound");
            }
        }while (isContinue);
    }

    public static void administratorRegister()
    {
        System.out.println("There is the pane for administrator to register");

        do
        {
            System.out.print("Please input your account:");
            String account = input.nextLine();
            System.out.print("Please input your password:");
            String password = input.nextLine();
            Administrator administrator = new Administrator(account, password);
            if (AdministratorBusiness.accountExist(account))
            {
                System.out.println("This account already exist");
            }
            else if (!AdministratorBusiness.passwordLegal(password))
            {
                System.out.println("Your password is illegal");
            }
            else
            {
                System.out.println("Register Successfully");
                AdministratorBusiness.addAdministrator(administrator);
                break;
            }
        }while (true);

    }

    public static void administratorLogIn()
    {
        System.out.println("There is the pane for administrator to log in");

        do
        {
            System.out.print("Please input your account:");
            String account = input.nextLine();
            System.out.print("Please input your password:");
            String password = input.nextLine();
            Administrator administrator = new Administrator(account, password);
            if (!AdministratorBusiness.accountExist(account))
            {
                System.out.println("This account is not exist");
            }
            else if (AdministratorBusiness.accountAndPasswordMatch(administrator))
            {
                System.out.println("Log in Successfully");
                administratorOperate();
                break;
            }
            else
            {
                System.out.println("Your account doesn't match your password");
            }
        }while (true);
    }

    public static void administratorOperate()
    {
        System.out.println("Hello,Administrator!");
        boolean isContinue;
        do
        {
            isContinue = true;

            System.out.println("Please choose one to continue");
            System.out.println("A) operate student");
            System.out.println("B) operate course");
            System.out.println("C) inquire course selection");
            System.out.println("D) cancel");
            switch (input.nextLine())
            {
                case "A":
                    administratorOperateStudent();
                    break;
                case "B":
                    administratorOperateCourse();
                    break;
                case "C":
                    administratorInquireCourseSelection();
                    break;
                case "D":
                    isContinue = false;
                    break;
                default:
                    System.out.println("Your input is out of bound");
            }

        }while (isContinue);
    }

    public static void administratorOperateStudent()
    {
        System.out.println("Student Operation");

        boolean isContinue;
        do
        {
            showStudents(StudentBusiness.students);
            isContinue = true;
            System.out.println("Please choose to continue");
            System.out.println("A) add      B) delete");
            System.out.println("C) update   D) get");
            System.out.println("E) select for student");
            System.out.println("F) cancel");
            switch (input.nextLine())
            {
                case "A":
                    addStudent();
                    break;
                case "B":
                    deleteStudent();
                    break;
                case "C":
                    updateStudent();
                    break;
                case "D":
                    getStudent();
                    break;
                case "E":
                    selectionCourseForStudent();
                    break;
                case "F":
                    isContinue = false;
                    break;
                default:
                    System.out.println("Your input is out of bound");
            }

        }while (isContinue);
    }


    public static void administratorOperateCourse()
    {
        System.out.println("Course Operation");
        boolean isContinue;
        do
        {
            showCourseSelection(CourseBusiness.courses);
            isContinue = true;
            System.out.println("Please choose to continue");
            System.out.println("A) add      B) delete");
            System.out.println("C) update   D) get");
            System.out.println("E) cancel");
            switch (input.nextLine())
            {
                case "A":
                    addCourse();
                    break;
                case "B":
                    deleteCourse();
                    break;
                case "C":
                    updateCourse();
                    break;
                case "D":
                    getCourse();
                    break;
                case "E":
                    isContinue = false;
                    break;
                default:
                    System.out.println("Your input is out of bound");
            }

        }while (isContinue);
    }

    public static void administratorInquireCourseSelection()
    {
        System.out.println("Inquire Course Selection");
        boolean isContinue;
        do
        {
            isContinue = true;
            showCourseSelection(CourseSelectionBusiness.sortBySelectedCount(CourseBusiness.courses));
            System.out.println("Please choose to continue");
            System.out.println("A) major   B) name    C) cancel");
            switch (input.nextLine())
            {
                case "A":
                    findSelectionByMajor();
                    break;
                case "B":
                    findSelectionByName();
                    break;
                case "C":
                    isContinue =false;
                    break;
                default:
                    System.out.println("Your input is out of bound");
            }

        }while (isContinue);
    }


    private static void addStudent()
    {
        System.out.print("Please input student's number:");
        String number = input.nextLine();
        if (StudentBusiness.studentNumberExist(number))
        {
            System.out.println("This student number already exist");
            return;
        }
        System.out.print("Please input student's name:");
        String name = input.nextLine();
        System.out.print("Please input student's major:");
        String major = input.nextLine();
        if (!MajorBusiness.majorExist(major))
        {
            System.out.println("This major is not existed");
            return;
        }
        StudentBusiness.addStudent(number, name, major);
        System.out.println("Add Successfully");
    }

    private static void deleteStudent()
    {
        System.out.print("Please input student's number:");
        String number = input.nextLine();
        if (!StudentBusiness.studentNumberExist(number))
        {
            System.out.println("This number is not existed");
            return;
        }
        StudentBusiness.deleteStudent(number);
    }

    private static void updateStudent()
    {
        System.out.print("Please input student's number:");
        String number = input.nextLine();
        if (!StudentBusiness.studentNumberExist(number))
        {
            System.out.println("This number is not existed");
            return;
        }
        System.out.println("Please choose one to update");
        System.out.println("A) name   B) major  C) cancel");
        switch (input.nextLine())
        {
            case "A":
                System.out.print("Please input student's name:");
                String name = input.nextLine();
                StudentBusiness.updateStudentNameByNumber(number, name);
                break;
            case "B":
                System.out.print("Please input student's major:");
                String major = input.nextLine();
                if (MajorBusiness.majorExist(major))
                    StudentBusiness.updateStudentMajorByNumber(number, major);
                else
                    System.out.println("This major is not existed");
                break;
            case "C":
                break;
            default:
                System.out.println("Your input is out of bound");
        }
    }

    private static void getStudent()
    {
        System.out.println("Please choose one to inquire:");
        System.out.println("A) number    B) name   C) cancel");
        switch (input.nextLine())
        {
            case "A":
                System.out.print("Please input student's number:");
                String number = input.nextLine();
                if (StudentBusiness.studentNumberExistApproximately(number))//重写一个模糊搜索的监测方法
                    showStudents(StudentBusiness.getStudentsByNumberApproximately(number));
                else
                    System.out.println("This number is not existed");
                break;
            case "B":
                System.out.print("Please input student's name:");
                String name = input.nextLine();
                if (StudentBusiness.studentNameExistApproximately(name))
                    showStudents(StudentBusiness.getStudentsByNameApproximately(name));
                else
                    System.out.println("This name is not existed");
                break;
            case "C":
                break;
            default:
                System.out.println("Your input is out of bound");
        }
    }

    private static void selectionCourseForStudent()
    {
        System.out.print("Please input student's number:");
        String number = input.nextLine();
        if (!StudentBusiness.studentNumberExist(number))
        {
            System.out.println("This student number is not existed");
            return;
        }

        boolean isContinue;

        do
        {
            StudentOperation.showAllCourseSelection(number);
            StudentOperation.showCourseSelectionOfStudent(number);
            System.out.println("Please choose one to continue:");
            System.out.println("A) select course");
            System.out.println("B) withdraw course");
            System.out.println("C) cancel");

            isContinue = true;
            switch (input.nextLine())
            {
                case "A":
                    StudentOperation.studentSelectCourse(number);
                    break;
                case "B":
                    StudentOperation.studentWithdrawCourseSelection(number);
                    break;
                case "C":
                    isContinue = false;
                    break;
                default:
                    System.out.println("Your input is out of bound");
            }
        }while (isContinue);
    }

    private static void addCourse()
    {
        System.out.print("Please input course name:");
        String courseName = input.nextLine();
        if (CourseBusiness.courseNameExist(courseName))
        {
            System.out.println("This course already exist");
            return;
        }
        System.out.print("Please input course capacity:");
        String capacity = input.nextLine();
        if (!CourseBusiness.courseCapacityRight(capacity))
        {
            System.out.println("The capacity is not right");
            return;
        }
        System.out.print("Please input majors(use \" \" to split):");
        ArrayList<String> majors = MajorBusiness.splitToMajors(input.nextLine());
        if (!MajorBusiness.majorsExist(majors))
        {
            System.out.println("There is major don't exist");
            return;
        }
        CourseBusiness.addCourse(new Course(courseName, capacity));
        CourseCompatibilityBusiness.addCourseCompatibilities(courseName, majors);
        System.out.println("Add Course Successfully");
    }

    private static void deleteCourse()
    {
        System.out.print("Please input course name:");
        String courseName = input.nextLine();
        if (!CourseBusiness.courseNameExist(courseName))
        {
            System.out.println("This course is not existed");
            return;
        }
        CourseBusiness.deleteCourse(courseName);
    }

    private static void updateCourse()
    {
        System.out.print("Please input course name:");
        String courseName = input.nextLine();
        if (!CourseBusiness.courseNameExist(courseName))
        {
            System.out.println("This course is not existed");
            return;
        }
        System.out.println("Please choose one to continue:");
        System.out.println("A) capacity    B) applicable majors    C) cancel");
        switch (input.nextLine())
        {
            case "A":
                updateCourseCapacity(courseName);
                break;
            case "B":
                updateCourseMajors(courseName);
                break;
            case "C":
                break;
            default:
                System.out.println("Your input is out of bound");
        }
    }

    private static void getCourse()
    {
        System.out.println("Please choose one to continue:");
        System.out.println("A) name      B) major     C) cancel");
        switch (input.nextLine())
        {
            case "A":
                getCourseByName();
                break;
            case "B":
                getCourseByMajor();
                break;
            case "C":
                break;
            default:
                System.out.println("Your input is out of bound");
        }
    }

    private static void updateCourseCapacity(String courseName)
    {
        System.out.print("Please input new capacity:");
        String capacity = input.nextLine();
        if (!CourseBusiness.courseCapacityRight(capacity))
        {
            System.out.println("Your input is out of bound");
        }
        else if (!CourseSelectionBusiness.capacityEnough(courseName , capacity))
        {
            System.out.println("Update Failure");
            System.out.println("Your course capacity is not enough");
        }
        else
        {
            CourseBusiness.updateCourseCapacity(courseName, capacity);
            System.out.println("Update Successfully");
        }
    }

    private static void updateCourseMajors(String courseName)
    {
        System.out.print("Please input new majors(split by \" \"):");
        String[] majors = input.nextLine().split(" ");
        boolean isContinue = true;
        for (String major: majors)
        {
            if (!MajorBusiness.majorExist(major))
            {
                System.out.println("Your input major: " + major + " is not existed!");
                isContinue = false;
            }
        }
        if (isContinue)
        {
            CourseCompatibilityBusiness.clearCourseCompatibility(courseName);
            for (String major : majors)
                CourseCompatibilityBusiness.addCourseCompatibility(courseName, major);
            System.out.println("Update Successfully");
        }
    }

    private static void getCourseByName()
    {
        System.out.print("Please input course name:");
        String courseName = input.nextLine();
        if (!CourseBusiness.courseNameExistApproximately(courseName))
        {
            System.out.println("This course is not existed");
            return;
        }
        showCourses(CourseBusiness.getCourseByCourseNameApproximately(courseName));
    }

    private static void getCourseByMajor()
    {
        System.out.print("Please input the major:");
        String major = input.nextLine();
        if (!MajorBusiness.majorExistApproximately(major))
        {
            System.out.println("This major is not existed");
            return;
        }
        showCourses(CourseCompatibilityBusiness.getCoursesOfMajorApproximately(major));
    }

    private static void findSelectionByName()
    {
        System.out.print("Please input course name:");
        String courseName = input.nextLine();
        if (!CourseBusiness.courseNameExistApproximately(courseName))
        {
            System.out.println("This course is not existed");
            return;
        }
        showInquiredSelectedCourseData(CourseSelectionBusiness.sortBySelectedCount(CourseBusiness.getCourseByCourseNameApproximately(courseName)));
    }

    private static void findSelectionByMajor()
    {
        System.out.print("Please input major name:");
        String major = input.nextLine();
        if (!MajorBusiness.majorExistApproximately(major))
        {
            System.out.println("This major is not existed");
            return;
        }
        showInquiredSelectedCourseData(CourseSelectionBusiness.sortBySelectedCount(CourseCompatibilityBusiness.getCoursesOfMajorApproximately(major)));
    }

    private static void showCourses(ArrayList<Course> courses)
    {
        System.out.printf("%-15s%-15s%-15s\n", "name", "capacity", "majors");
        for (Course course: CourseSelectionBusiness.sortBySelectedCount(courses))
        {
            System.out.printf("%-15s%-15s", course.getName(), course.getCapacity());
            for (String major: CourseCompatibilityBusiness.getMajorsOfCourse(course.getName()))
                System.out.print(major + " ");
            System.out.println();
        }
    }

    private static void showCourse(String courseName)
    {
        System.out.printf("%-15s%-15s%-15s\n", "name", "capacity", "majors");
        for (Course course: CourseBusiness.courses)
        {
            if (course.getName().equals(courseName))
            {
                System.out.printf("%-15s%-15s", course.getName(), course.getCapacity());
                for (String major : CourseCompatibilityBusiness.getMajorsOfCourse(course.getName()))
                    System.out.print(major);
                System.out.println();
            }
        }
    }

    private static void showStudents(ArrayList<Student> students)
    {
        System.out.printf("%-15s%-15s%-15s\n", "number", "name", "major");
        for (Student student: students)
        {
            System.out.printf("%-15s%-15s%-15s\n", student.getNumber(), student.getName(), student.getMajor());
        }
    }

    private static void showCourseSelection(ArrayList<Course> courses)
    {
        System.out.printf("%-15s%-15s%-15s%-15s\n", "name", "#selected", "capacity", "majors");
        for (Course course: CourseSelectionBusiness.sortBySelectedCount(courses))
        {
            System.out.printf("%-15s%-15d%-15s", course.getName(), CourseSelectionBusiness.getStudentCountsOfCourse(course.getName()), course.getCapacity());
            for (String major : CourseCompatibilityBusiness.getMajorsOfCourse(course.getName()))
                    System.out.print(major + " ");
                System.out.println();
        }
    }

    private static void showCourseSelection(String courseName)
    {
        System.out.printf("%-15s%-15s%-15s%-15s\n", "name", "#selected", "capacity", "majors");
        for (Course course: CourseBusiness.courses)
        {
            if (course.getName().equals(courseName))
            {
                System.out.printf("%-15s%-15d%-15s", course.getName(), CourseSelectionBusiness.getStudentCountsOfCourse(course.getName()), course.getCapacity());
                for (String major : CourseCompatibilityBusiness.getMajorsOfCourse(course.getName()))
                    System.out.print(major + " ");
                System.out.println();
            }
        }
    }

    private static void showInquiredSelectedCourseData(ArrayList<Course> courses)
    {
        //System.out.printf("%-15s%-15s%-15s%-15s\n", "name", "selection", "capacity", "majors");

        for(Course course: courses)
        {
            showCourseSelection(course.getName());
            showStudentOfCourse(course.getName());
        }
        System.out.println();
    }

    private static void showStudentOfCourse(String courseName)
    {
        ArrayList<Student> students = CourseSelectionBusiness.getStudentsOfCourse(courseName);
        System.out.print("Student of Course:");
        for (Student student: students)
        {
            System.out.print(student.getNumber() + student.getName() + " ");
        }
        System.out.println();
    }
}