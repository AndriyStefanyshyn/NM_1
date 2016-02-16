package com.restingrobots.nm_1;

/**
 * Created by Andriy on 12.02.2016.
 */
public class Finder {

    private Finder(){}

    public static double bisection(double From, double To, double Eps) {
        if(getY(To)*getY(From) < 0) {
            double x;
            while(true) {
                x = (To + From)/2f;
                if(Math.abs(To - From) < Eps || getY(x) == 0) {
                    return x;
                }
                else {
                    if(getY(From)*getY(x) < 0) {
                        To = x;
                    }
                    else {
                        From = x;
                    }
                }
            }
        }
        else {
            return -1;
        }
    }

    public static double chord(double From, double To, double Eps) {
        if(getY(To)*getY(From) < 0) {
            double x;
            if(Math.abs(To - From) < Eps) {
                x = (To + From)/2f;
                return x;
            }
            if(!(getYDD(To)*getY(To) > 0)) {
                double temp = From;
                From = To;
                To = temp;
            }
            while(true) {
                x = From - (getY(From)*(To-From)/(getY(To)-getY(From)));
                if(Math.abs(x - From) < Eps) {
                    return x;
                }
                else {
                    From = x;
                }
            }
        }
        else {
            return -1;
        }
    }

    public static double neuton(double From, double To, double Eps) {
        if(getY(To)*getY(From) < 0) {
            double x;
            if(Math.abs(To - From) < Eps) {
                x = (To + From)/2f;
                return x;
            }
            if(!(getYDD(To)*getY(To) > 0)) {
                double temp = From;
                From = To;
                To = temp;
            }
            while(true) {
                x = To - getY(To)/getYD(To);
                if(Math.abs(To - x) < Eps) {
                    return x;
                }
                else {
                    To = x;
                }
            }
        }
        else {
            return -1;
        }
    }

    public static double iter(double From, double To, double Eps) {
        if(getY(To)*getY(From) < 0) {
            double x;
            double x0;
            if(Math.abs(To - From) < Eps) {
                x = (To + From)/2f;
                return x;
            }
            x0 = (To + From)/2f;
            while(true) {
                x = getFi(x0);
                if(Math.abs(x - x0) < Eps) {
                    return x;
                }
                else {
                    x0 = x;
                }
            }
        }
        else {
            return -1;
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
