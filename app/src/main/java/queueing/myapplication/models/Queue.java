package queueing.myapplication.models;

/**
 * Created by abou7mied on 18/12/17.
 */

public class Queue {

    double lambda;
    double mue;


    Queue() {
    }

    Queue(double lambda, double mue) {
        setLambda(lambda);
        setMue(mue);
    }


    void setLambda(double lambda) {
        this.lambda = lambda;
    }

    void setMue(double mue) {
        this.mue = mue;
    }

    double getLambda() {
        return lambda;
    }

    double getMue() {
        return mue;
    }

}
