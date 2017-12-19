package queueing.myapplication.models;

/**
 * Created by abou7mied on 18/12/17.
 */

public class MM1KQueue extends Queue {

    private int K;

    public MM1KQueue(double lambda, double mue, int K) {
        super(lambda, mue);
        setK(K);
    }


    private void setK(int k) {
        this.K = k;
    }


    private double getRho() {
        return getLambda() / getMue();
    }

    public double getSystemCustomersCount() {

        double rho = getRho();

        if (rho == 1)
            return K / 2;


        return rho * ((1 - ((K + 1) * Math.pow(rho, K)) + (K * Math.pow(rho, K + 1))) / ((1 - rho) * (1 - Math.pow(rho, K + 1))));

    }


    public double getQueueCustomersCount() {
        return getSystemCustomersCount() - (getRho() * (1 - getPk()));
    }


    private double getPk() {
        return Math.pow(getRho(), K) * ((1 - getRho()) / (1 - Math.pow(getRho(), K + 1)));
    }


    public double getSystemWaitingTime() {
        return getSystemCustomersCount() / (getLambda() * (1 - getPk()));
    }

    public double getQueueWaitingTime() {
        return getSystemWaitingTime() - (1 / getMue());
    }


}
