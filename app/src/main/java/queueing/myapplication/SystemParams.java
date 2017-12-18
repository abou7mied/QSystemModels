package queueing.myapplication;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import queueing.myapplication.databinding.ActivitySystemParamsBinding;
import queueing.myapplication.models.DD1KQueue;
import queueing.myapplication.models.MM1KQueue;
import queueing.myapplication.models.MM1Queue;
import queueing.myapplication.models.Queue;

public class SystemParams extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    ActivitySystemParamsBinding binding;
    VisibilityBinding visibilityBinding = new VisibilityBinding();
    ValuesBinding valuesBinding = new ValuesBinding();
    int selectedSystem;


    public static Queue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_system_params);
        binding.setVisible(visibilityBinding);
        binding.setValues(valuesBinding);

        binding.systemType.setOnItemSelectedListener(this);

        binding.calculate.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (view == binding.calculate) {
            calculate();
        }
    }


    public void calculate() {


        if (!validInputs()) {
            Toast.makeText(this, "Please Enter a valid numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, SystemResultsActivity.class);
        startActivity(intent);

    }

    private boolean validInputs() {
        double lambda = 0, mue = 0;
        int systemCapacity = 0, initialCustomers = 0;

        try {
            if (isVisible(binding.lambda))
                lambda = Double.parseDouble(binding.lambda.getText().toString());

            if (isVisible(binding.mue))
                mue = Double.parseDouble(binding.mue.getText().toString());

            if (isVisible(binding.systemCapacity))
                systemCapacity = Integer.parseInt(binding.systemCapacity.getText().toString());

            if (!isValid(binding.lambda, lambda) || !isValid(binding.mue, mue) || !isValid(binding.systemCapacity, systemCapacity)) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

        try {
            initialCustomers = Integer.parseInt(binding.initialCustomers.getText().toString());
        } catch (Exception e) {

        }

        switch (selectedSystem) {
            case 0:
                queue = new DD1KQueue(lambda, mue, systemCapacity + 1, initialCustomers);
                break;

            case 1:
                queue = new MM1Queue(lambda, mue);
                break;

            case 2:
                queue = new MM1KQueue(lambda, mue, systemCapacity + 1);
                break;

        }


        return true;
    }


    boolean isVisible(View v) {
        return v.getVisibility() == View.VISIBLE;
    }

    boolean isValid(View v, double i) {
        return v.getVisibility() != View.VISIBLE || i > 0;
    }


    public static Queue getQueue() {
        return queue;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

        selectedSystem = pos;
        visibilityBinding.reset();

        switch (pos) {
            case 0:
                visibilityBinding.setSystemCapacity(true);
                break;

            case 2:
                visibilityBinding.setSystemCapacity(true);
                break;


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public class ValuesBinding extends BaseObservable {

        String lambda = "";
        String mue = "";
//        String systemCapacity = "";
//        String numberOfServers = "";


        public void setLambda(String lambda) {
            this.lambda = lambda;
            notifyPropertyChanged(BR.lambda);
            initInitial();

        }

        public void setMue(String mue) {
            this.mue = mue;
            notifyPropertyChanged(BR.mue);
            initInitial();
        }

        public void initInitial() {
            if (selectedSystem == 0)
                try {
                    double mue = Double.parseDouble(this.mue);
                    double lambda = Double.parseDouble(this.lambda);
                    visibilityBinding.setInitialCustomers(mue > lambda);
                } catch (Exception e) {

                }
        }

//        public void setSystemCapacity(String systemCapacity) {
//            this.systemCapacity = systemCapacity;
//        }
//
//        public void setNumberOfServers(String numberOfServers) {
//            this.numberOfServers = numberOfServers;
//        }


        @Bindable
        public String getLambda() {
            return lambda;
        }

        @Bindable
        public String getMue() {
            return mue;
        }

//        @Bindable
//        public String getSystemCapacity() {
//            return systemCapacity;
//        }
//
//        @Bindable
//        public String getNumberOfServers() {
//            return numberOfServers;
//        }
    }

    public class VisibilityBinding extends BaseObservable {

        boolean initialCustomers = false;
        boolean systemCapacity = false;
        boolean numberOfServers = false;

        public void reset() {
            initialCustomers = systemCapacity = numberOfServers = false;
            notifyPropertyChanged(BR._all);
        }


        public void setInitialCustomers(boolean initialCustomers) {
            this.initialCustomers = initialCustomers;
            notifyPropertyChanged(BR.initialCustomers);
        }


        public void setSystemCapacity(boolean systemCapacity) {
            this.systemCapacity = systemCapacity;
            notifyPropertyChanged(BR.systemCapacity);
        }

        public void setNumberOfServers(boolean numberOfServers) {
            this.numberOfServers = numberOfServers;
            notifyPropertyChanged(BR.numberOfServers);

        }

        @Bindable
        public boolean isInitialCustomers() {
            return initialCustomers;
        }

        @Bindable
        public boolean isSystemCapacity() {
            return systemCapacity;
        }

        @Bindable
        public boolean isNumberOfServers() {
            return numberOfServers;
        }

    }

}
