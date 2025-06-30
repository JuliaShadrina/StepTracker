package ru.yandex;

import java.util.Scanner;

    class StepTracker {
        Scanner scanner;
        MonthData[] monthToData = new MonthData[12]; // массив из 12-и объектов по 31 день
        int goalByStepsPerDay = 10000; // цель по шагам за день инициирована значением по умолчанию
        Converter converter = new Converter(); // конвертер для пересчёта статистики

        StepTracker(Scanner scan) {     // конструктор c параметром типа Scanner
            scanner = scan;

            for (int i = 0; i < monthToData.length; i++) { // инициация каждого элемента массива объектом MonthData
                monthToData[i] = new MonthData();
            }
        }

        void addNewNumberStepsPerDay() {  //
            System.out.println("Введите номер месяца");
            int month = scanner.nextInt();
            if (month < 1 || month > 12) { // валидация номера месяца
                System.out.println("Введённое значение " + month + " некорректно. Номер месяца может быть только от 1 "
                        + "до 12 включительно.");
                return;
            }

            System.out.println("Введите номер дня");
            int day = scanner.nextInt();

            if (day < 1 || day > 31) { // валидация и обработка некорректного ввода
                System.out.println("Введённое значение " + day + "некорректно. Номер дня может быть только от 1 "
                        + "до 31 включительно.");
                return;
            } else if (((month == 2) && (day == 30 || day == 31 || day == 29)) || ((month == 4 || month == 6
                    || month == 9 || month == 11) && (day == 31))) { // отсечение несуществующих дат
                System.out.println("В месяце " + month + " не может быть даты " + day);
                return;
            }

            System.out.println("Введите количество шагов");
            int steps = scanner.nextInt(); // ввод и валидация количества шагов
            if (steps < 0) {
                System.out.println("Введённое значение " + day + "некорректно. Количество шагов не может быть "
                        + "отрицательным.");
                return;
            }

            MonthData monthData = monthToData[month - 1]; // получение соответствующего объекта MonthToData из массива
            monthData.days[day - 1] = steps; // внесение значения в соответствующую дате ячейку
            System.out.println("Значение успешно сохранено: месяц " + month + " день " + day + " количество шагов "
            + steps);
        }

        void changeStepGoal() {
            System.out.println("Введите новую цель по количеству шагов в день");
            int newStepGoal = scanner.nextInt();
            if (newStepGoal <= 0) {       // цель не может быть отрицательной или нулевой
                System.out.println("Введённое значение " + newStepGoal + " некорректно. Целью может быть только число"
                        + " больше 0");
                return;
            }
            goalByStepsPerDay = newStepGoal;  // если значение валидно, сохраняем цель
            System.out.println("Значение успешно сохранено! Ваша новая цель: " + goalByStepsPerDay + " шагов в день");
        }

        void printStatistic() { // вывод статистики за месяц
            System.out.println("Статистика за какой месяц вам интересна? Введите номер месяца");
            int month = scanner.nextInt(); // ввод и валидация номера месяца
            if (month < 1 || month > 12) {
                System.out.println("Введённое значение: " + month + "некорректно. Номер месяца может быть только от 1 "
                        + "до 12 включительно.");
                return;
            }
            MonthData monthData = monthToData[month - 1]; // получение соответствующего объекта MonthToData из массива
            int daysInMonth;  // получение кол-ва дней в выбранном месяце для корректного подсчёта и вывода статистики
            if (month == 2){
                daysInMonth = 28;
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                daysInMonth = 30;
            } else {
                daysInMonth = 31;
            }

            monthData.printDaysAndStepsFromMonth(daysInMonth); // вывод общей статистики по дням
            int sumSteps = monthData.sumStepsFromMonth();  // получение суммы шагов за месяц
            System.out.println("Общее количество шагов за месяц: " + sumSteps);  // вывод суммы шагов за месяц
            System.out.println("Максимальное в месяце количество шагов за день: " + monthData.maxSteps());
            // вывод максимального пройденного количества шагов за день в выбранном месяце
            System.out.println("Среднее количество шагов за день: " + sumSteps/daysInMonth);  // вывод среднего
            // пройденного количества шагов за день в выбранном месяц
            System.out.println("Пройденная дистанция за месяц: " + converter.convertToKm(sumSteps) + " км."); // вывод
            // пройденной за месяц дистанции в километрах
            System.out.println("За месяц сожжено: " + converter.convertStepsToKilocalories(sumSteps) + " ккал.");
            // вывод количества сожжённых килокалорий за месяц
            System.out.println("Самая длинная серия дней с достигнутой целью: "
                    + monthData.bestSeries(goalByStepsPerDay));   // вывод лучшей серии
            System.out.println(); // дополнительный перенос строки
        }
    }
