import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, User> numberToUsers = new HashMap<>();
        String[] initial = scanner.nextLine().split(":");
        for (String s : initial) {
            String[] userInfo = s.split("_");
            String name = userInfo[0];
            String cardNumber = userInfo[1];
            String password = userInfo[2];
            double ballance = Double.parseDouble(userInfo[3]);
            User user = new User(name, cardNumber, password, ballance);
            numberToUsers.put(cardNumber, user);
        }

        String[] userInfo = initial[0].split("_");
        System.out.println("Въведете номер на карта или 0 за изход:");
        String input = scanner.nextLine();
        while (!input.equals("0")) {
            if (numberToUsers.containsKey(input)) {
                //TODO
            } else {
                System.out.println("Вашата карта е невалидна!");
                System.out.println("Моля, опитайте отново.");
            }
            input = scanner.nextLine();
        }
    }

    static class User {
        String name;
        String cardNumber;
        String password;
        double ballance;

        User(String name, String cardNumber, String password, double ballance) {
            this.name = name;
            this.cardNumber = cardNumber;
            this.password = password;
            this.ballance = ballance;
        }
    }
}
