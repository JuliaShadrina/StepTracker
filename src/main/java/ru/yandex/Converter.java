package ru.yandex;

public class Converter {
    int stepToCm = 75;  // сантиметров в шаге
    int stepToKl = 50; // калорий в шаге

    int convertToKm(int steps){ // шаги → километры
        return (steps * stepToCm / 100000);
    }

    int convertStepsToKilocalories(int steps){ // шаги → ккал
        return (steps * stepToKl / 1000);
    }
}
