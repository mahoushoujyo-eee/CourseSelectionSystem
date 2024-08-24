package ReviewConsole.Interaction;

import java.util.Scanner;

public class Start
{
    static Scanner input = new Scanner(System.in);
    public static void ReadChoose()
    {
        System.out.println("CourseSelectionSystem");

        boolean isContinue;
        do {
            System.out.println("Please choose your identity");
            System.out.println("A) Administrator   B) Student  C) cancel");
            isContinue = true;
            switch (input.nextLine())
            {
                case "A":
                    AdministratorOperation.chooseLogInOrRegister();
                    break;
                case "B":
                    StudentOperation.chooseLogInOrRegister();
                    break;
                case "C":
                    isContinue = false;
                    break;
                default:
                    System.out.println("Your input is out of bound");
            }
        }while (isContinue);
    }
}
