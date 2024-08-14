package consoleVersion;

import javafx.application.Application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


//This is a java file I don't use. I tried to use it to add a test for administrator.
public class ReadAdId {
    private static String AdIdPath = "data/AdministratorId";
    public static ArrayList<String> readId() throws FileNotFoundException {
        ArrayList<String> ids = new ArrayList<>();

        Scanner input = new Scanner(new FileInputStream(AdIdPath));
        while (input.hasNext())
            ids.add(input.nextLine());

        return ids;
    }
}
