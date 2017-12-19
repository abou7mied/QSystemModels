package queueing.myapplication.models;

/**
 * Created by abou7mied on 16/12/17.
 */

public class DD1KQueue extends Queue {

    private int K;
    private int M;


    public DD1KQueue(double lambda, double mue, int K, int M) {
        setLambda(lambda);
        setMue(mue);
        setK(K);
        setM(M);
    }


    private void setK(int k) {
        this.K = k;
    }

    private void setM(int m) {
        M = m;
    }


    private int getM() {
        return M;
    }

    public int getTi() {
        if (lambda == mue) {
            return 0;
        } else if (mue > lambda) {
            int ti = (int) ((M) / (mue - lambda));
            for (int newTi = ti - 1; newTi > 0; newTi--) {
                int newM = ((int) (mue * newTi)) - ((int) (lambda * newTi));
                if (newM != M) {
                    return newTi + 1;
                }
            }
            return ti;
        } else {
            int ti = (int) (((K - (mue / lambda)) / (lambda - mue)));
            for (int newTi = ti - 1; newTi > 0; newTi--) {
                int newK = ((int) (lambda * newTi)) - ((int) ((mue * newTi) - (mue / lambda)));
                if (newK != K) {
                    return newTi + 1;
                }
            }
            return ti;
        }
    }

    public int getNumOfCustomers(int t) {
        if (lambda == mue) {
            return getM();
        } else {
            double lambdaT = lambda * t;
            double mueT = mue * t;
            if (mue > lambda) {
                int ti = getTi();
                if (t >= ti) {
                    // TODO: alter between 0 and 1
                    return 0;
                } else {
                    return M + (int) lambdaT - (int) mueT;
                }

            } else {
                int ti = getTi();
                if (t >= ti) {
                    // TODO: alter between -1 and -2
                    return K - 1;
                } else {
                    return (int) lambdaT - (int) (mueT - (mue / lambda));
                }
            }
        }
    }

    public int getWaitingTime(int n) {
        int lambdaTi = getFirstBalk();
        double firstTerm = (1 / mue) - (1 / lambda);
        if (lambda == mue) {
            return (int) ((M - 1) * (1 / lambda));
        } else if (mue > lambda) {
            if (n >= lambdaTi)
                return 0;
            return (int) (((M - 1 + n) * (1 / mue)) - (n * (1 / lambda)));
        } else {
            if (n >= lambdaTi)
                // TODO: alter between -2 and -3
                return (int) (firstTerm * (lambdaTi - 2));

            return (int) (firstTerm * (n - 1));

        }
    }

    public int getFirstBalk() {
        return (int) (lambda * getTi());
    }

}
