package com.restingrobots.nm_1;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Andriy on 12.02.2016.
 */
public class Finder {

    private Finder(){}

    public static String bisection(double From, double To, double Eps) {
        if(getY(To)*getY(From) < 0) {
            double x;
            int i = 0;
            while(true) {
                x = (To + From)/2f;
                if(Math.abs(To - From) < Eps || getY(x) == 0) {
                    return ("Поділу: " + new BigDecimal(x).setScale(3, RoundingMode.HALF_UP).doubleValue() + "; " + i);
                }
                else {
                    if(getY(From)*getY(x) < 0) {
                        To = x;
                    }
                    else {
                        From = x;
                    }
                }
                i++;
            }
        }
        else {
            return "Поділу: Помилка";
        }
    }

    public static String chord(double From, double To, double Eps) {
        if(getY(To)*getY(From) < 0) {
            double x;
            int i = 0;
            if(Math.abs(To - From) < Eps) {
                x = (To + From)/2f;
                return ("Хорд: " + new BigDecimal(x).setScale(3, RoundingMode.HALF_UP).doubleValue() + "; " + i);
            }
            if(!(getYDD(To)*getY(To) > 0)) {
                double temp = From;
                From = To;
                To = temp;
            }
            while(true) {
                x = From - (getY(From)*(To-From)/(getY(To)-getY(From)));
                if(Math.abs(x - From) < Eps) {
                    return ("Хорд: " + new BigDecimal(x).setScale(3, RoundingMode.HALF_UP).doubleValue() + "; " + i);
                }
                else {
                    From = x;
                }
                i++;
            }
        }
        else {
            return "Хорд: Помилка";
        }
    }

    public static String neuton(double From, double To, double Eps) {
        if(getY(To)*getY(From) < 0) {
            double x;
            int i = 0;
            if(Math.abs(To - From) < Eps) {
                x = (To + From)/2f;
                return ("Ньютона: " + new BigDecimal(x).setScale(3, RoundingMode.HALF_UP).doubleValue() + "; " + i);
            }
            if(!(getYDD(To)*getY(To) > 0)) {
                double temp = From;
                From = To;
                To = temp;
            }
            while(true) {
                x = To - getY(To)/getYD(To);
                if(Math.abs(To - x) < Eps) {
                    return ("Ньютона: " + new BigDecimal(x).setScale(3, RoundingMode.HALF_UP).doubleValue() + "; " + i);
                }
                else {
                    To = x;
                }
                i++;
            }
        }
        else {
            return "Ньютона: Помилка";
        }
    }

    public static String iter(double From, double To, double Eps) {
        if(getY(To)*getY(From) < 0) {
            double x;
            double x0;
            int i = 0;
            if(Math.abs(To - From) < Eps) {
                x = (To + From)/2f;
                return ("Ітерацій: " + new BigDecimal(x).setScale(3, RoundingMode.HALF_UP).doubleValue() + "; " + i);
            }
            x0 = (To + From)/2f;
            while(true) {
                x = getFi(x0);
                if(Math.abs(x - x0) < Eps) {
                    return ("Ітерацій: " + new BigDecimal(x).setScale(3, RoundingMode.HALF_UP).doubleValue() + "; " + i);
                }
                else {
                    x0 = x;
                }
                i++;
            }
        }
        else {
            return "Ітерацій: Помилка";
        }
    }

    public static double getY(double x) {
        return (x*x*x - 2*x*x + 3);
    }

    public static double getYD(double x) {
        return (3*x*x - 4*x);
    }

    public static double getYDD(double x) {
        return (6*x - 4);
    }

    public static double getFi(double x) {
        return ((-0.025)*x*x*x + (0.05)*x*x + x - (0.075));
    }
}
