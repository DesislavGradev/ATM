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

        //Validating the login
        System.out.println("Въведете номер на карта или 0 за изход:");
        String cardNumber = scanner.nextLine();
        while (!cardNumber.equals("0")) {
            User currentUser = numberToUsers.get(cardNumber);
            if (currentUser != null) {
                System.out.println(separator());
                System.out.println("Въведете парола:");
                String currentPass = scanner.nextLine();
                boolean isBlocked = true;
                for (int i = 2; i > 0; i--) {
                    if (currentPass.equals(currentUser.getPassword())) {
                        isBlocked = false;
                        System.out.println(separator());
                        System.out.printf("Здравейте, %s%n", currentUser.getName());
                        cardNumber = operationsMenu(scanner, numberToUsers, currentUser);
                    } else {
                        String remaining = "";
                        System.out.println(separator());
                        System.out.println("Грешна парола! Опитайте отново.");
                        if (i == 2) {
                            remaining = "оставащи опита";
                        } else {
                            remaining = "оставащ опит";
                        }
                        System.out.printf("(%d %s.)%n", i, remaining);
                        currentPass = scanner.nextLine();
                    }
                }
                if (isBlocked) {
                    System.out.println("Грешна парола!");
                    System.out.println("Картата Ви е блокирана. Свържете се със своята банка.");
                    cardNumber = "0";
                    continue;
                } else {
                    continue;
                }

                //TODO Card blocking

            } else {
                System.out.println("Вашата карта е невалидна!");
                System.out.println("Моля, опитайте отново.");
            }
            cardNumber = scanner.nextLine();
        }
    }

    static String operationsMenu(Scanner scanner, Map<String, User> numberToUsers, User currentUser) {
        System.out.println("Изберете операция:");
        System.out.println("(1) Теглене");
        System.out.println("(2) Внасяне");
        System.out.println("(3) Справка");
        System.out.println("(4) Настройки");
        System.out.println("(0) Изход");
        String operation = scanner.nextLine();
        switch (operation) {
            case "1":
                withdraw(scanner, numberToUsers, currentUser);
                break;
            case "2":
                deposit(scanner, numberToUsers, currentUser);
                break;
            case "3":
                System.out.printf("Налична сума в сметката: %.2f", currentUser.getBalance());
                System.out.println("(0) Назад");
                if (scanner.nextLine().equals("0")) {
                    operationsMenu(scanner, numberToUsers, currentUser);
                }
                break;
            case "4":
                settings(scanner, numberToUsers, currentUser);
                break;
            case "0":
                return operation;
        }
        return "0";
    }

    private static void settings(Scanner scanner, Map<String, User> numberToUsers, User currentUser) {
        System.out.println("Смяна на парола.");
        System.out.println("Въведете нова парола:");
        System.out.println("(0) Назад");
        String newPassword = scanner.nextLine();

        //TODO password check (only digits)
        if (newPassword.equals("0")) {
            operationsMenu(scanner, numberToUsers, currentUser);
        } else {
            currentUser.setPassword(newPassword);
            numberToUsers.put(currentUser.getCardNumber(), currentUser);
        }
    }

    private static void deposit(Scanner scanner, Map<String, User> numberToUsers, User currentUser) {
        System.out.println("Въведете сума за внасяня:");
        System.out.println("(0) Назад");
        if (scanner.nextLine().equals("0")) {
            operationsMenu(scanner, numberToUsers, currentUser);
        } else {
            double sumToDeposit = Double.parseDouble(scanner.nextLine());
            //TODO Sum check (if decimal)
            currentUser.setBalance(currentUser.getBalance() + sumToDeposit);
            numberToUsers.put(currentUser.getCardNumber(), currentUser);
        }
    }

    private static void withdraw(Scanner scanner, Map<String, User> numberToUsers, User currentUser) {
        boolean isSuccessful = false;
        while (!isSuccessful) {
            System.out.println(separator());
            System.out.println("<<<<<< ТЕГЛЕНЕ >>>>>>");
            System.out.printf("Наличност в сметката: %.2fлв%n", currentUser.getBalance());
            System.out.println("Изберете сума за теглене:");
            System.out.println("(1) 50лв");
            System.out.println("(2) 100лв");
            System.out.println("(3) 150лв");
            System.out.println("(4) 200лв");
            System.out.println("(5) 300лв");
            System.out.println("(6) 400лв");
            System.out.println("(7) Друга сума");
            System.out.println("(8) Назад");
            String sumToWithdraw = scanner.nextLine();
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
                    System.out.println("(0) Назад");
                    if (scanner.nextLine().equals("0")) {
                        withdraw(scanner, numberToUsers, currentUser);
                    } else {
                        sum = Double.parseDouble(scanner.nextLine());
                    }
                    break;
                case "8":
                    System.out.println("Назад");
                    operationsMenu(scanner, numberToUsers, currentUser);
                    break;
            }
            if (currentUser.getBalance() >= sum) {
                currentUser.setBalance(currentUser.getBalance() - sum);
                numberToUsers.put(currentUser.getCardNumber(), currentUser);
                isSuccessful = true;
            } else {
                System.out.println("Тегленето не може да бъде извършено !");
                System.out.println("Желаната сума надхвърля наличната !");
            }
        }
    }

    static String separator() {
        return "-=-=-=-=-=-=-=-=-=-=-=-=-=-";
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

        void setPassword(String password) {
            this.password = password;
        }
    }
}
