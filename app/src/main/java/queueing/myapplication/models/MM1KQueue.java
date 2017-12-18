package queueing.myapplication.models;

/**
 * Created by abou7mied on 18/12/17.
 */

public class MM1KQueue extends Queue {

    int K;

    public MM1KQueue(double lambda, double mue, int K) {
        setLambda(lambda);
        setMue(mue);
        setK(K);
    }


    public void setK(int k) {
        this.K = k;
    }

    public int getK() {
        return K;
    }


    double getRho() {
        return getLambda() / getMue();
    }

    public double getSystemCustomersCount() {

        double rho = getRho();

        if (rho == 1)
            return K / 2;

        double result = rho * ((1 - ((K + 1) * Math.pow(rho, K)) + (K * Math.pow(rho, K + 1))) / ((1 - rho) * (1 - Math.pow(rho, K + 1))));


        return result;

    }


    public double getQueueCustomersCount() {
        return getSystemCustomersCount() - (getRho() * (1 - getPk()));
    }


    public double getPk() {
        return Math.pow(getRho(), K) * ((1 - getRho()) / (1 - Math.pow(getRho(), K + 1)));
    }


    public double getSystemWaitingTime() {
        return getSystemCustomersCount() / (getLambda() * (1 - getPk()));
    }

    public double getQueueWaitingTime() {
        return getSystemWaitingTime() - (1 / getMue());
    }


}
