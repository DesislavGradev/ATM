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
        String cardNumber = scanner.nextLine();
        while (!cardNumber.equals("0")) {
            User currentUser = numberToUsers.get(cardNumber);
            if (currentUser != null) {
                System.out.println("Въведете парола:");
                String currentPass = scanner.nextLine();
                if (currentPass.equals(currentUser.getPassword())) {
                    cardNumber =  operationsMenu(scanner, numberToUsers, currentUser);
                    continue;
                }
            } else {
                System.out.println("Вашата карта е невалидна!");
                System.out.println("Моля, опитайте отново.");
            }
            cardNumber = scanner.nextLine();
        }
    }

    static String operationsMenu(Scanner scanner, Map<String, User> numberToUsers, User currentUser) {
        System.out.printf("Здравейте, %s%n", currentUser.getName());
        System.out.println("Изберете операция:");
        System.out.println("(1) Теглене");
        System.out.println("(2) Внасяне");
        System.out.println("(3) Справка");
        System.out.println("(4) Настройки");
        System.out.println("(0) Изход");
        String operation = scanner.nextLine();
        switch (operation) {
            case "1":
                String sumToWithdraw = withdrawMenu(scanner, currentUser.getBalance());
                while sumToWithdraw
                double sum = 0;
                switch (sumToWithdraw) {
                    case "1":
                        sum = 50;
                        break;
                    case "2":
                        sum = 100;
                        break;
                    case "3":
                        sum = 150;
                        break;
                    case "4":
                        sum = 200;
                        break;
                    case "5":
                        sum = 300;
                        break;
                    case "6":
                        sum = 400;
                        break;
                    case "7":
                        System.out.println("Въведете сума:");
                        sum = Double.parseDouble(scanner.nextLine());
                        break;
                    case "8":
                        System.out.println("Назад");
                        //TODO
                        break;
                }
                if (currentUser.getBalance() >= sum) {
                    currentUser.setBalance(currentUser.getBalance() - sum);
                    numberToUsers.put(currentUser.getCardNumber(), currentUser);
                } else {
                    System.out.println("Тегленето не може да бъде извършено !%nЖеланата сума надхвърля наличната !");
                    withdrawMenu(scanner, currentUser.getBalance());
                }
                break;
            case "2":
                break;
            case "3":
                break;
            case "0":
                return operation;
        }
        return "0";
    }

    static String withdrawMenu(Scanner scanner, double balance) {
        System.out.printf("Наличност в сметката: %.2fлв%n", balance);
        System.out.println("Изберете сума за теглене:");
        System.out.println("(1) 50лв");
        System.out.println("(2) 100лв");
        System.out.println("(3) 150лв");
        System.out.println("(4) 200лв");
        System.out.println("(5) 300лв");
        System.out.println("(6) 400лв");
        System.out.println("(7) Друга сума");
        return scanner.nextLine();

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

        String getPassword() {
            return this.password;
        }

        String getCardNumber() {
            return this.cardNumber;
        }

        double getBalance() {
            return this.balance;
        }

        void setBalance(double balance) {
            this.balance = balance;
        }
    }
}
