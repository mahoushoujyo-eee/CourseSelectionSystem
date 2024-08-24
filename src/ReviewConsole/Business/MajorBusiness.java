package ReviewConsole.Business;

import ReviewConsole.Data.Major;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MajorBusiness
{
    public static ArrayList<Major> majors = new ArrayList<Major>();
    private static final String path = "src/ReviewConsole/TxtData/Major.txt";

    public static boolean majorExist(String majorData)
    {
        for (Major major: majors)
            if (major.getMajor().equals(majorData))
                return true;

        return false;
    }

    public static boolean majorExistApproximately(String majorData)
    {
        for (Major major: majors)
            if (major.getMajor().contains(majorData))
                return true;

        return false;
    }


    public static boolean majorsExist(String[] majors)
    {
        for (String major: majors)
            if (!majorExist(major))
                return false;
        return true;
    }

    public static boolean majorsExist(ArrayList<String> majors)
    {
        for (String major: majors)
            if (!majorExist(major))
                return false;
        return true;
    }



    public static ArrayList<String> splitToMajors(String majorData)
    {
        ArrayList<String> majors = new ArrayList<>();
        String[] tempMajors = majorData.split(" ");
        for (String major: tempMajors)
        {
            if (!majors.contains(major))
                majors.add(major);
        }
        return majors;
    }

    public static void initialize() throws FileNotFoundException
    {
        Scanner input = new Scanner(new File(path));
        while (input.hasNext())
        {
            String[] majorData = input.nextLine().split(" ");
            majors.add(new Major(majorData[0], majorData[1]));
        }
    }

    public static void close() throws FileNotFoundException
    {
        PrintWriter printWriter = new PrintWriter(path);

        for (Major major: majors)
        {
            printWriter.println(major.getMajor() + " " + major.getMajorId());
        }

        printWriter.close();
    }
}
