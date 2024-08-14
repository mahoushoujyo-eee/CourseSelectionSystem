package consoleVersion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//这是一个读写课程文件的类
public class RWCourse {
    private static String path = "data/courses.txt";
    public static ArrayList<String> names = new ArrayList<>();
    public static ArrayList<String> capacities = new ArrayList<>();
    public static ArrayList<String[]> majors = new ArrayList<>();


    //初始化
    public static void readAll() throws FileNotFoundException {
        Scanner input = new Scanner(new File(path));

        while (input.hasNext())
        {
            String[] content = input.nextLine().split(" ");
            names.add(content[0]);
            capacities.add(content[1]);
            String splitMajor = content[2].substring(1, content[2].length() - 1);
            //System.out.println(splitMajor);
            majors.add(content[2].split(","));
        }
    }

    //展示指定名称的课程信息
    public static void showCourse(String name)
    {
        System.out.printf("%-10s%-10s%-10s  ", name, RWSelectedCourse.nowSelectionOfCourse(name), capacities.get(names.indexOf(name)));
        String courseMajors = "";
        for (String major: majors.get(names.indexOf(name)))
        {
            courseMajors = courseMajors + major + ",";
        }
        System.out.println(courseMajors.substring(0, courseMajors.length() - 1));
    }

    //添加课程信息
    public static void add(String name, String capacity, String[] major)
    {
        names.add(name);
        capacities.add(capacity);
        majors.add(major);
    }

    //这是我写的一个排序方法，我也不知道怎么说这个思路，反正写的有点抽象
    public static ArrayList<Integer> sort()
    {
        ArrayList<String> temp = new ArrayList<>();
        for (String course: names)
        {
            temp.add(course);
        }

        ArrayList<Integer> maxIndexes = new ArrayList<>();

        for (int i = 0; i < names.size(); i++)
        {
            int max = RWSelectedCourse.nowSelectionOfCourse(temp.get(0));
            int maxIndex = 0;
            for (int j = 0; j < temp.size();j++)
            {
                if (RWSelectedCourse.nowSelectionOfCourse(temp.get(j)) >= max) {
                    max = RWSelectedCourse.nowSelectionOfCourse(temp.get(j));
                    maxIndex = j;
                }
            }
            temp.set(maxIndex, "min");
            maxIndexes.add(maxIndex);
            //System.out.println(maxIndexes);
        }

        return maxIndexes;
    }


    //重新写入
    public static void close() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new File(path));

        for (int i = 0;i < names.size(); i++)
        {
            printWriter.print(names.get(i) + " " + capacities.get(i) + " ");
            String courseMajors = "";
            for (String major: majors.get(i))
            {
                courseMajors = courseMajors + major + ",";
            }
            printWriter.println(courseMajors.substring(0, courseMajors.length() - 1));
        }

        printWriter.close();
    }
}
