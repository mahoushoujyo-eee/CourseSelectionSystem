package ReviewConsole.Business;

import ReviewConsole.Data.Course;
import ReviewConsole.Data.CourseSelection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseSelectionBusiness
{
    public static ArrayList<CourseSelection> courseSelections = new ArrayList<>();
    private static String path = "src/ReviewConsole/TxtData/CourseSelection.txt";


    public static int getStudentCountsOfCourse(String courseName)
    {
        int count = 0;
        for (CourseSelection courseSelection: courseSelections)
        {
            if (courseSelection.getCourseName().equals(courseName))
                count++;
        }

        return count;
    }

    public static int getCourseCountsOfStudent(String studentNumber)
    {
        int count = 0;
        for (CourseSelection courseSelection: courseSelections)
        {
            if (courseSelection.getStudentNumber().equals(studentNumber))
                count++;
        }

        return count;
    }

    public static ArrayList<Course> getSelectedCourseOfStudent(String studentNumber)
    {
        ArrayList<Course> courses = new ArrayList<>();

        for (CourseSelection courseSelection: courseSelections)
        {
            if (courseSelection.getStudentNumber().equals(studentNumber))
                courses.add(CourseBusiness.getCourseByCourseName(courseSelection.getCourseName()));
        }

        return courses;
    }

    public static boolean judgeCapacityEnough(String courseName, String capacity)
    {
        return Integer.parseInt(capacity) >= getStudentCountsOfCourse(courseName);
    }

    public static void addCourseSelection(String courseName, String studentNumber)
    {
        courseSelections.add(new CourseSelection(studentNumber, courseName));
    }

    public static boolean judgeCourseSelectionExist(String courseName, String studentNumber)
    {
        for (CourseSelection courseSelection: courseSelections)
        {
            if (courseSelection.getCourseName().equals(courseName) && courseSelection.getStudentNumber().equals(studentNumber))
                return true;
        }
        return false;
    }

    public static void removeCourseSelection(String courseName, String studentNumber)
    {
        int index = -1;

        for (CourseSelection courseSelection: courseSelections)
        {
            if (courseSelection.getCourseName().equals(courseName) && courseSelection.getStudentNumber().equals(studentNumber))
            {
                index = courseSelections.indexOf(courseSelection);
                break;
            }
        }
        if (index != -1)
            courseSelections.remove(index);
    }

    public static void initialize() throws FileNotFoundException
    {
        Scanner input = new Scanner(new File(path));
        while (input.hasNext())
        {
            String[] courseSelectionData = input.nextLine().split(" ");
            courseSelections.add(new CourseSelection(courseSelectionData[0], courseSelectionData[1]));
        }
        input.close();
    }

    public static void close() throws FileNotFoundException
    {
        PrintWriter printWriter = new PrintWriter(path);
        for (CourseSelection courseSelection: courseSelections)
        {
            printWriter.println(courseSelection.getCourseName() + " " + courseSelection.getStudentNumber());
        }
        printWriter.close();
    }
}
