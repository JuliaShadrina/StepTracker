package ru.yandex;

import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker(scanner);

        while (true) {  // консольный интерфейс с бесконечным циклом
            printMenu();

            int command = scanner.nextInt();
            if (command == 1) {
                stepTracker.addNewNumberStepsPerDay(); // введение кол-ва шагов
            } else if (command == 2) {
                stepTracker.changeStepGoal();  // изменение цели по шагам за день
            } else if (command == 3) {
                stepTracker.printStatistic();  // вывод статистики
            } else if (command == 0) {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }

        }
    }
    static void printMenu() { // печать меню
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Ввести количество шагов за день");
        System.out.println("2 - Изменить цель по количеству шагов в день");
        System.out.println("3 - Напечатать статистику за месяц");
        System.out.println("0 - Выход");

    }
}