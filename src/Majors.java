import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Majors {
    private static List<List> listOfMajors = new ArrayList<>();
    private static List<String> majorNames = List.of("NONE", "COMP");
    private static List<String> NONE = List.of("None");
    private static List<String> COMP = List.of("COMP 123", "COMP 127", "COMP 128", "COMP 221", "COMP 225", "COMP 240",
        "MATH 279", "Math Elective", "Math Elective", "Advanced Elective", "Advanced Elective", "Capstone");


    private static void setUpMajors() {
        listOfMajors.add(NONE);
        listOfMajors.add(COMP);

    }

    public static List<String> getMajor() {
        setUpMajors();
        Scanner question = new Scanner(System.in);
        System.out.println("What is your major? Enter the abbrieviation.");
        String answer = question.nextLine();
        question.close();
        int majorIndex = 0;
        for (int i = 0; i < majorNames.size(); i++) {
            if (majorNames.get(i).equals(answer.toUpperCase())) {
                majorIndex = i;

            }

        }
        return listOfMajors.get(majorIndex);

    }

}
