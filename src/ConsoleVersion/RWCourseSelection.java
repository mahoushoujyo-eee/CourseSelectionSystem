package ConsoleVersion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class RWCourseSelection
{
    public static ArrayList<String> courseNames = new ArrayList<>();
    public static ArrayList<String> studentAccounts = new ArrayList<>();
    private static String path = "data/selectedCourse.txt";

    //初始化
    public static void readAll() throws FileNotFoundException {
        Scanner input = new Scanner(new File(path));

        while (input.hasNext())
        {
            String[] readLine = input.nextLine().split(" ");
            courseNames.add(readLine[0]);
            studentAccounts.add(readLine[1]);
        }
        input.close();
    }


    //添加选课信息
    public static void add(String courseName, String studentAccount)
    {
        courseNames.add(courseName);
        studentAccounts.add(studentAccount);
    }

    //写入文件
    public static void close() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new File(path));

        for (int i = 0; i < courseNames.size(); i++)
        {
            printWriter.println(courseNames.get(i) + " " + studentAccounts.get(i));
        }
        printWriter.close();
    }

    //返回现在指定课程的被选数量
    public static int selectedCountOfCourse(String course)
    {
        if (course.equals("min"))
            return -1;
        int count = 0;
        for (String courseName: courseNames)
            if (courseName.equals(course))
                count++;
        return count;
    }

    //返回学生的已选课数量
    public static int nowSelectionOfStudent(String nowAccount)
    {
        int count = 0;
        for (String account: studentAccounts)
            if (account.equals(nowAccount))
                count++;
        return count;
    }

    //删除某一条选课信息
    public static void removeSelectedData(String courseName)
    {
        // 第一步：找到要删除的元素，或者它的下标，循环里面
        // 第二步：循环结束后再删掉它
        ArrayList<String> tempCourse = new ArrayList<>();
        for (int i = 0; i < RWCourseSelection.courseNames.size(); i++) {
            if (RWCourseSelection.courseNames.get(i).equals(courseName))
            {
                RWCourseSelection.studentAccounts.remove(i);
            }
            else
            {
                tempCourse.add(RWCourseSelection.courseNames.get(i));
            }
        }
        RWCourseSelection.courseNames = tempCourse;
    }
}
