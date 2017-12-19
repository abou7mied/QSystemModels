package queueing.myapplication.models;

/**
 * Created by abou7mied on 18/12/17.
 */

public class MMCKQueue extends MMCQueue {

    private int K;

    public MMCKQueue(double lambda, double mue, int c, int K) {
        super(lambda, mue, c);
        setK(K);
    }

    private void setK(int k) {
        K = k;
    }

    private int getK() {
        return K;
    }

    public double getP0() {
        double result = 0;
        for (int n = 0; n < c; n++) {
            result += Math.pow(getR(), n) / factorial(n);
        }

        double v = Math.pow(getR(), c) / factorial(c);

        if (getRho() != 1) {
            result += v * ((1 - Math.pow(getRho(), getK() - c + 1)) / (1 - getRho()));
        } else {
            result += v * (getK() - c + 1);
        }

        return 1 / result;
    }

    public double getQueueCustomersCount() {
        double x = (getRho() * getP0() * Math.pow(getR(), c)) / ((factorial(c) * Math.pow(1 - getRho(), 2)));
        return x * (1 - Math.pow(getRho(), getK() - c + 1) - ((1 - getRho()) * (getK() - c + 1) * (Math.pow(getRho(), getK() - c))));
    }

    public double getSystemCustomersCount() {
        double v = 0;
        for (int n = 0; n < c; n++) {
            v += (c - n) * Math.pow(getR(), n) / factorial(n);
        }
        v *= getP0();
        return getQueueCustomersCount() + c - v;
    }

    private double getLambdaDash() {
        return lambda * (1 - getPn(K));
    }


    public double getSystemWaitingTime() {
        return getSystemCustomersCount() / getLambdaDash();
    }

    public double getQueueWaitingTime() {
        return getQueueCustomersCount() / getLambdaDash();
    }


}
