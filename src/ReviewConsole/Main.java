package ReviewConsole;

import ReviewConsole.Business.*;
import ReviewConsole.Data.Course;
import ReviewConsole.Data.CourseCompatibility;
import ReviewConsole.Data.CourseSelection;
import ReviewConsole.Interaction.Start;

import java.io.FileNotFoundException;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        initialize();

        Start.ReadChoose();

        close();
    }

    public static void initialize() throws FileNotFoundException
    {
        AdministratorBusiness.initialize();
        StudentBusiness.initialize();
        MajorBusiness.initialize();
        CourseBusiness.initialize();
        CourseSelectionBusiness.initialize();
        CourseCompatibilityBusiness.initialize();
    }

    public static void close() throws FileNotFoundException
    {
        AdministratorBusiness.close();
        StudentBusiness.close();
        MajorBusiness.close();
        CourseBusiness.close();
        CourseSelectionBusiness.close();
        CourseCompatibilityBusiness.close();
    }
}
