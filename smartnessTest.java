import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

// Rules:
// Always apply the floor function to division. This is for calculation purposes
// When answering a question where you have to enter a previous question, exclude the [y/n] if it is in the question
// Using a calculator is CHEATING
// Looking at previous things is CHEATING
// NO CHEATING
// END OF RULES

public class smartnessTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String answer;

        System.out.println("WinparWinpar's Smartness Test");
        System.out.println("Rules:\nAlways apply the floor function to division. This is for calculation purposes\nWhen answering a question where you have to enter a previous question, exclude the [y/n] if it is in the question\nUsing a calculator is CHEATING\nLooking at previous questions or other things I say is CHEATING\nNO CHEATING\nEND OF RULES\n");
        System.out.println("Do you want to take my smartness test?[Y/n]");

        answer = scanner.next();

        if (answer == "" || answer == "Y" || answer == "y" || answer == "YES" || answer == "Yes" || answer == "yes") {
            SmartnessTest();
        } else if (answer == "N" || answer == "n" || answer == "NO" || answer == "No" || answer == "no") {}

        scanner.close();
    }

    public static void SmartnessTest() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> answers = new ArrayList<Integer>();
        List<String> answers2 = new ArrayList<String>();
        List<Integer> questions1 = new ArrayList<Integer>();
        List<Character> questions2 = new ArrayList<Character>();
        List<Integer> questions3 = new ArrayList<Integer>();

        System.out.println("What is 1 + 1?");
        answers.add(scanner.nextInt());
        for (int i = 0; i < 9; i++) {
            int randNum = randNum();
            char randChar = randChar();
            int randNum2 = randNum();
            System.out.println("What is " + randNum + " " + randChar + " " + randNum2 + "?");
            answers.add(scanner.nextInt());
            questions1.add(randNum);
            questions2.add(randChar);
            questions3.add(randNum2);
        }
        System.out.println("What is the first question I asked you?");
        answers2.add(scanner.next());
        System.out.println("What is the first thing I told you?");
        answers2.add(scanner.next());
        score(answers, answers2, questions1, questions2, questions3);

        scanner.close();
    }

    public static int randNum() {
        Random RNG = new Random();
        int randnum = RNG.nextInt(20 - 1);
        randnum = randnum + 1;
        return randnum;
    }

    public static char randChar() {
        Random RNG = new Random();
        char[] chars = {
            '+',
            '-',
            '*',
            '/'
        };
        int randnum = RNG.nextInt(4);
        return chars[randnum];
    }

    public static void score(List<Integer> answers, List<String> answers2, List<Integer> q1, List<Character> q2, List<Integer> q3) {
        int score = 0;
        int answer = 0;

        for (int i = 0; i < answers.size() - 1; i++) {
            if (i > q1.size()) {
                if (i == q1.size() + 1) {
                    if (answers2.get(i) == "Do you want to take my smartness test?") {
                        score++;
                    }
                } else if (i == q1.size() + 2) {
                    if (answers2.get(i) == "WinparWinpar's Smartness Test") {
                        score++;
                    }
                }
            } else if (i <= q1.size()) {
                if (q2.get(i) == '+') {
                    answer = Math.addExact(q1.get(i), q3.get(i));
                } else if (q2.get(i) == '-') {
                    answer = Math.subtractExact(q1.get(i), q3.get(i));
                } else if (q2.get(i) == '*') {
                    answer = Math.multiplyExact(q1.get(i), q3.get(i));
                } else if (q2.get(i) == '/') {
                    answer = Math.floorDiv(q1.get(i), q3.get(i));
                }

                if (answers.get(i) == answer) {
                    score++;
                }

                System.out.println(answer);
            }
        }

        System.out.println("Your score: " + score + " out of " + answers.size());
    }
}
