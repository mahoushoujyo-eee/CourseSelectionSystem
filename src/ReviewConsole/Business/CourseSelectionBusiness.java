package ReviewConsole.Business;

import ReviewConsole.Data.Course;
import ReviewConsole.Data.CourseSelection;
import ReviewConsole.Data.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseSelectionBusiness
{
    public static ArrayList<CourseSelection> courseSelections = new ArrayList<>();
    private static final String path = "src/ReviewConsole/TxtData/CourseSelection.txt";


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

    public static boolean capacityEnough(String courseName, String capacity)
    {
        return Integer.parseInt(capacity) >= getStudentCountsOfCourse(courseName);
    }

    public static void addCourseSelection(String courseName, String studentNumber)
    {
        courseSelections.add(new CourseSelection(courseName, studentNumber));
    }

    public static boolean courseSelectionExist(String courseName, String studentNumber)
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
        CourseSelection tempCourseSelection = null;

        for (CourseSelection courseSelection: courseSelections)
        {
            if (courseSelection.getCourseName().equals(courseName) && courseSelection.getStudentNumber().equals(studentNumber))
            {
                tempCourseSelection = courseSelection;
                break;
            }
        }
        if (tempCourseSelection != null)
            courseSelections.remove(tempCourseSelection);
    }

    public static ArrayList<Course> sortBySelectedCount(ArrayList<Course> courses)
    {
        for (int i = 0; i < courses.size() - 1; i++)
        {
            int maxIndex = i;
            for (int j = i + 1; j < courses.size(); j++)
            {
                if (getStudentCountsOfCourse(courses.get(maxIndex).getName()) < getStudentCountsOfCourse(courses.get(j).getName()))
                    maxIndex = j;
            }
            Course temp = courses.get(i);
            courses.set(i, courses.get(maxIndex));
            courses.set(maxIndex, temp);
        }
        return courses;
    }

    public static ArrayList<Student> getStudentsOfCourse(String courseName)
    {
        ArrayList<Student> students = new ArrayList<>();

        for (CourseSelection courseSelection: courseSelections)
        {
            if (courseSelection.getCourseName().equals(courseName))
                students.add(StudentBusiness.getStudentByNumber(courseSelection.getStudentNumber()));
        }
        return students;
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
