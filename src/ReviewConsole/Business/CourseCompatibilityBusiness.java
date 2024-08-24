package ReviewConsole.Business;

import ReviewConsole.Data.Course;
import ReviewConsole.Data.CourseCompatibility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseCompatibilityBusiness
{
    public static ArrayList<CourseCompatibility> courseCompatibilities = new ArrayList<>();

    private static final String path = "src/ReviewConsole/TxtData/CourseCompatibility.txt";


    public static void addCourseCompatibility(String courseName, String major)
    {
        courseCompatibilities.add(new CourseCompatibility(courseName, major));
    }

    public static void clearCourseCompatibility(String courseName)
    {
        ArrayList<CourseCompatibility> tempCourseCompatibilities = new ArrayList<>();

        for (CourseCompatibility courseCompatibility: courseCompatibilities)
        {
            if (courseCompatibility != null)
            {
                if (courseCompatibility.getCourseName().equals(courseName))
                    tempCourseCompatibilities.add(courseCompatibility);
            }
        }

        for (CourseCompatibility courseCompatibility: tempCourseCompatibilities)
        {
            courseCompatibilities.remove(courseCompatibility);
        }
    }

    public static void addCourseCompatibilities(String courseName, String[] majors)
    {
        for (String major: majors)
            addCourseCompatibility(courseName, major);
    }

    public static void addCourseCompatibilities(String courseName, ArrayList<String> majors)
    {
        for (String major: majors)
            addCourseCompatibility(courseName, major);
    }

    public static ArrayList<String> getMajorsOfCourse(String courseName)
    {
        ArrayList<String> majors = new ArrayList<>();
        for (CourseCompatibility courseCompatibility: courseCompatibilities)
        {
            if (courseCompatibility != null)
                if (courseCompatibility.getCourseName().equals(courseName))
                    majors.add(courseCompatibility.getMajor());
        }

        return majors;
    }

    public static boolean courseContainsMajor(String courseName, String major)
    {
        return getMajorsOfCourse(courseName).contains(major);
    }

    public static boolean courseContainsMajorApproximately(String courseName, String roughMajor)
    {
        for(String major:getMajorsOfCourse(courseName))
        {
            if (major.contains(roughMajor))
                return true;
        }
        return false;
    }



    public static ArrayList<Course> getCoursesOfMajor(String major)
    {
        ArrayList<Course> courses = new ArrayList<>();

        for (Course course: CourseBusiness.courses)
        {
            if (CourseCompatibilityBusiness.courseContainsMajor(course.getName(), major))
                courses.add(course);
        }

        return courses;
    }

    public static ArrayList<Course> getCoursesOfMajorApproximately(String roughMajor)
    {
        ArrayList<Course> courses = new ArrayList<>();

        for (Course course: CourseBusiness.courses)
        {
            if (CourseCompatibilityBusiness.courseContainsMajorApproximately(course.getName(), roughMajor))
                courses.add(course);
        }

        return courses;
    }


    public static void initialize() throws FileNotFoundException
    {
        Scanner input = new Scanner(new File(path));

        while (input.hasNext())
        {
            String[] courseCompatibilityData = input.nextLine().split(" ");
            courseCompatibilities.add(new CourseCompatibility(courseCompatibilityData[0], courseCompatibilityData[1]));
        }
        input.close();
    }

    public static void close() throws FileNotFoundException
    {
        PrintWriter printWriter = new PrintWriter(path);

        for (CourseCompatibility courseCompatibility: courseCompatibilities)
        {
            if (courseCompatibility != null)
                printWriter.println(courseCompatibility.getCourseName() + " " + courseCompatibility.getMajor());
        }
        printWriter.close();
    }
}
