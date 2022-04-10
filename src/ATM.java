import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, User> numberToUsers = new HashMap<>();
        //Filling the map with data
        String[] initial = scanner.nextLine().split(":");
        for (String s : initial) {
            String[] userInfo = s.split("_");
            String name = userInfo[0];
            String cardNumber = userInfo[1];
            String password = userInfo[2];
            double balance = Double.parseDouble(userInfo[3]);
            User user = new User(name, cardNumber, password, balance);
            numberToUsers.put(cardNumber, user);
        }

        System.out.println("Въведете номер на карта или 0 за изход:");
        String input = scanner.nextLine();
        while (!input.equals("0")) {
            User currentUser = numberToUsers.get(input);
            if (currentUser != null) {
                String name = currentUser.getName();
                String password = currentUser.getPassword();
                double balance = currentUser.getBalance();
                System.out.println("Въведете парола:");
                String currentPass = scanner.nextLine();
                if (currentPass.equals(password)) {
                    System.out.printf("Здравейте, %s%n", name);
                    System.out.println("Изберете операция:");
                    System.out.println("(1) Теглене");
                    System.out.println("(2) Внасяне");
                    System.out.println("(3) Настройки");
                    String operation = scanner.nextLine();
                    switch (operation) {
                        case "1":

                            break;
                        case "2":
                            break;
                        case "3":
                            break;

                    }

                }
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
        double balance;

        User(String name, String cardNumber, String password, double balance) {
            this.name = name;
            this.cardNumber = cardNumber;
            this.password = password;
            this.balance = balance;
        }

        String getName() {
            return this.name;
        }

        String getCardNumber() {
            return this.cardNumber;
        }

        String getPassword() {
            return this.password;
        }

        double getBalance() {
            return this.balance;
        }
    }
}
