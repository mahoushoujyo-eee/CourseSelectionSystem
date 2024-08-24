package ReviewConsole.Business;


import ReviewConsole.Data.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentBusiness {
    public static ArrayList<Student> students = new ArrayList<>();

    private static String path = "src/ReviewConsole/TxtData/Student.txt";

    public static boolean judgeStudentNumberExist(String number)
    {
        for (Student student: students)
        {
            if (student.getNumber().equals(number))
                return true;
        }
        return false;
    }

    public static boolean judgeStudentNumberExistApproximately(String roughNumber)
    {
        for (Student student: students)
        {
            if (student.getNumber().contains(roughNumber))
                return true;
        }
        return false;
    }

    public static boolean judgeStudentNumberMatchPassword(String number, String password)
    {
        for (Student student: students)
        {
            if (student.getNumber().equals(number) && student.getPassword().equals(password))
                return true;
        }
        return false;
    }

    public static Student findStudentByNumber(String number)
    {
        for (Student student: students)
        {
            if (student.getNumber().equals(number))
                return student;
        }
        return null;
    }

    public static void changePassword(String number, String password)
    {
        for (Student student: students)
        {
            if (student.getNumber().equals(number))
                student.setPassword(password);
        }
    }

    public static void addStudent(String number, String name, String major)
    {
        students.add(new Student(number, name, major, "123456"));
    }

    public static void deleteStudent(String number)
    {
        // Find by object.
        int index = -1;
        for (Student student: students)
        {
            if (student.getNumber().equals(number))
            {
                index = students.indexOf(student);
            }
        }
        if (index != -1)
            students.remove(index);
    }

    public static void updateStudentNameByStudentNumber(String number, String name)
    {
        for (Student student: students)
        {
            if (student.getNumber().equals(number))
            {
                student.setName(name);
                break;
            }
        }
    }

    public static void updateStudentMajor(String number, String major)
    {
        for (Student student: students)
        {
            if (student.getNumber().equals(number))
            {
                student.setMajor(major);
                return;
            }
        }
    }

    public static ArrayList<Student> getStudentByName(String name)
    {
        ArrayList<Student> students = new ArrayList<>();
        for (Student student: StudentBusiness.students)
        {
            if (student.getName().equals(name))
                students.add(student);
        }
        return students;
    }

    public static ArrayList<Student> getStudentsByNameApproximately(String roughName)
    {
        ArrayList<Student> students = new ArrayList<>();
        for (Student student: StudentBusiness.students)
        {
            if (student.getName().contains(roughName))
                students.add(student);
        }
        return students;
    }

    public static Student getStudentByNumber(String number)
    {
        for (Student student: StudentBusiness.students)
            if (student.getNumber().equals(number))
                return student;
        return null;
    }

    public static ArrayList<Student> getStudentByNumberApproximately(String roughNumber)
    {
        ArrayList<Student> students = new ArrayList<>();
        for (Student student: StudentBusiness.students)
        {
            if (student.getNumber().contains(roughNumber))
                students.add(student);
        }
        return students;
    }

    public static boolean judgeStudentNameExist(String name)
    {
        for (Student student: students)
        {
            if (student.getName().equals(name))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean judgeStudentNameExistApproximately(String roughName)
    {
        for (Student student: students)
        {
            if (student.getName().contains(roughName))
            {
                return true;
            }
        }
        return false;
    }

    public static void initialize() throws FileNotFoundException
    {
        Scanner input = new Scanner(new File(path));
        while (input.hasNext())
        {
            String[] studentData = input.nextLine().split(" ");
            Student student = new Student(studentData[0], studentData[1], studentData[2], studentData[3]);
            students.add(student);
        }
    }

    public static void close() throws FileNotFoundException
    {
        PrintWriter printWriter = new PrintWriter(path);

        for (Student student:students)
        {
            printWriter.println(student.getNumber() + " " + student.getName() + " " + student.getMajor() + " " + student.getPassword());
        }

        printWriter.close();
    }
}
