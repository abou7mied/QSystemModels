package queueing.myapplication.models;

/**
 * Created by abou7mied on 18/12/17.
 */

public class MM1Queue extends Queue {


    public MM1Queue(double lambda, double mue) {
        super(lambda, mue);
    }

    public double getSystemWaitingTime() {
        return (1 / (getMue() - getLambda()));
    }


    public double getQueueWaitingTime() {
        return ((getLambda() / getMue()) * getSystemWaitingTime());
    }


    public double getSystemCustomersCount() {
        return (getLambda() * getSystemWaitingTime());
    }

    public double getQueueCustomersCount() {
        return (getLambda() * getQueueWaitingTime());
    }



}
