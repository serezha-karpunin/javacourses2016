package com.epam.javacourses2016.task5;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Интерфейс для юнит-тестирования задания №5.
 *
 * Список содержит результаты измерений тока и напряжения на неизвестном сопротивлении R.
 * Найти приближенное число R методом наименьших квадратов.
 * Для повышения точности вычислений использовать класс {@link java.math.BigDecimal}
 */
public class SolverTask5 {

    /**
     * Вычисляет сопротивление методом наименьших квадратов.
     *
     * @param measurements Измерения в ходе эксперимента.
     * @return Вычисленное по исходным данным сопротивление.
     */
    double calcResistance(List<Measurement> measurements) {
        BigDecimal R;
        double sumI = 0;
        double sumU = 0;
        double  sumUI = 0;
        double  sum_sqrtI = 0;
        for(Measurement m: measurements) {
            sumI += m.getCurrent();
            sumU += m.getVoltage();
            sumUI += m.getCurrent() * m.getVoltage();
            sum_sqrtI += Math.pow(m.getCurrent(),2);
        }
        double numerator = measurements.size() * sumUI  - sumI * sumU;
        double denomantor = measurements.size() * sum_sqrtI - Math.pow(sumI,2);
        if(denomantor != 0)
            R = new BigDecimal( numerator / denomantor );
        else R = new BigDecimal(0.0);
        return R.setScale(3,BigDecimal.ROUND_DOWN).doubleValue();
    }
}
