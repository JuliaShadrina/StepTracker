package ru.yandex;

public class MonthData {
    int[] days = new int[31];  // поле массив с данными за месяц

    void printDaysAndStepsFromMonth(int daysInMonth) { // вывод кол-ва шагов за месяц по дням
        for (int i = 0; i < daysInMonth; i++) {
            System.out.println((i + 1) + " день: " + days[i]);
        }
    }

    int sumStepsFromMonth() { // сумма шагов за месяц = сумма элементов массива days[]
        int sumSteps = 0;
        for (int i = 0; i < days.length; i++) {
            sumSteps += days[i];
        }
        return sumSteps;
    }

    int maxSteps() { // максимальное количество шагов за месяц
        int maxSteps = 0;
        for (int i = 0; i < days.length; i++) {  // поиск максимального элемента
            if (days[i] > maxSteps) {
                maxSteps = days[i];
            }
        }
        return maxSteps;
    }

    int bestSeries(int goalByStepsPerDay) {  // поиск максимальной серии с достигнутой целью по шагам
        int currentSeries = 0;
        int finalSeries = 0;
        for (int i = 0; i < days.length; i++) {    // перебираем дни
            if (days[i] >= goalByStepsPerDay) {    // проверяем достигнута ли цель в этот день
                currentSeries++;                   // если да, то накапливаем этот день в счётчик серии
            } else {                               // если нет, значит, серия прервалась
                if (currentSeries > finalSeries) { // сравниваем с максимальной серией
                    finalSeries = currentSeries;
                }
                currentSeries = 0;                 // сбрасываем счётчик
            }
        }
        if (currentSeries > finalSeries) { // после ревью добавлена проверка длины последней серии после выхода из цикла
            finalSeries = currentSeries;
        }
        return finalSeries;
    }
}