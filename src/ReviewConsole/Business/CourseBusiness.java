package ReviewConsole.Business;

import ReviewConsole.Data.Course;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseBusiness {
    public static ArrayList<Course> courses = new ArrayList<>();
    private static String path = "src/ReviewConsole/TxtData/Course.txt";

    public static boolean judgeCourseNameExist(String name)
    {
        for (Course course: courses)
        {
            if (course.getName().equals(name))
                return true;
        }
        return false;
    }

    public static boolean judgeCourseNameExistApproximately(String roughName)
    {
        for (Course course: courses)
        {
            if (course.getName().contains(roughName))
                return true;
        }
        return false;
    }

    public static boolean judgeCourseCapacityRight(String capacity)
    {
        try
        {
            if (Integer.parseInt(capacity) <= 0)
                return false;
            return true;
        }catch (NumberFormatException exception)
        {
            return false;
        }
    }

    public static void addCourse(Course course)
    {
        courses.add(course);
    }

    public static Course getCourseByCourseName(String courseName)
    {
        for (Course course: courses)
            if (course.getName().equals(courseName))
                return course;
        return null;
    }

    public static ArrayList<Course> getCourseByCourseNameApproximately(String roughCourseName)
    {
        ArrayList<Course> courses = new ArrayList<>();
        for (Course course: CourseBusiness.courses)
            if (course.getName().contains(roughCourseName))
                courses.add(course);
        return courses;
    }

    public static void deleteCourse(String courseName)
    {
        int index = -1;
        for (Course course: courses)
        {
            if (course.getName().equals(courseName))
            {
                index = courses.indexOf(course);
                break;
            }
        }
        if (index != -1)
            courses.remove(index);
    }

    public static void updateCourseCapacity(String courseName, String capacity)
    {
        for (Course course: courses)
            if (course.getName().equals(courseName))
            {
                course.setCapacity(capacity);
                break;
            }
    }

    public static void initialize() throws FileNotFoundException
    {
        Scanner input = new Scanner(new File(path));

        while (input.hasNext())
        {
            String[] courseData = input.nextLine().split(" ");
            courses.add(new Course(courseData[0], courseData[1]));
        }

        input.close();
    }

    public static void close() throws FileNotFoundException
    {
        PrintWriter printWriter = new PrintWriter(path);

        for (Course course: courses)
        {
            printWriter.println(course.getName() + " " + course.getCapacity());
        }

        printWriter.close();
    }
}
