package queueing.myapplication.models;

/**
 * Created by abou7mied on 18/12/17.
 */

public class Queue {

    double lambda;
    double mue;


    public Queue() {
    }

    public Queue(double lambda, double mue) {
        setLambda(lambda);
        setMue(mue);
    }


    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public void setMue(double mue) {
        this.mue = mue;
    }

    public double getLambda() {
        return lambda;
    }

    public double getMue() {
        return mue;
    }

}
