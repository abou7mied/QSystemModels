package queueing.myapplication.models;

/**
 * Created by abou7mied on 18/12/17.
 */

public class MMCQueue extends Queue {

    int c;

    public MMCQueue(double lambda, double mue, int c) {
        super(lambda, mue);
        setC(c);
    }


    private void setC(int c) {
        this.c = c;
    }


    double getR() {
        return getLambda() / getMue();
    }

    double getP0() {
        double result = 0;
        double rPowerC = Math.pow(getR(), c);
        int factC = factorial(c);
        double v = rPowerC / factC;

        if (getRho() < 1) {
            for (int n = 0; n < c; n++) {
                result += Math.pow(getR(), n) / factorial(n);
            }
            result += (v * c) / (c - getR());
        } else {

            for (int n = 0; n < c; n++) {
                result += (Math.pow(getR(), n)) / factorial(n);
            }

            result += v * ((c * mue) / (c * mue - lambda));
        }

        return 1 / result;
    }

    double getRho() {
        return getR() / c;
    }

    double getPn(int n) {
        double v = Math.pow(lambda, n) * getP0() / Math.pow(mue, n);

        if (n < c) {
            return v / (factorial(n));
        }

        return v / (Math.pow(c, n - c) * factorial(c));


    }


    public double getQueueCustomersCount() {
        return (Math.pow(getR(), c + 1) / (c * factorial(c) * Math.pow(1 - getRho(), 2))) * getP0();
    }


    public double getSystemCustomersCount() {
        return getQueueCustomersCount() + getR();
    }


    public double getQueueWaitingTime() {
        return getQueueCustomersCount() / getLambda();
    }

    public double getSystemWaitingTime() {
        return getQueueWaitingTime() + (1 / getMue());
    }


    public int getIdleServers() {
        return (int) (c - getR());
    }

    static int factorial(int n) {
        if (n == 0)
            return 1;
        return n * factorial(n - 1);
    }

}
