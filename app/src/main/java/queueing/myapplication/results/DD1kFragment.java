package queueing.myapplication.results;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import queueing.myapplication.BR;
import queueing.myapplication.SystemParams;
import queueing.myapplication.databinding.Dd1kResultsBinding;
import queueing.myapplication.models.DD1KQueue;

/**
 * Created by abou7mied on 18/12/17.
 */

public class DD1kFragment extends Fragment {


    public static List<Entry> entries = new ArrayList<>();
    Dd1kResultsBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = Dd1kResultsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        CalculationResults data = new CalculationResults();
        binding.setData(data);

        DD1KQueue DD1KQueue = (queueing.myapplication.models.DD1KQueue) SystemParams.getQueue();

        binding.setFirstBalk(DD1KQueue.getFirstBalk() + "");
        binding.setTi(DD1KQueue.getTi() + "");


        initEntries();

        LineDataSet dataSet = new LineDataSet(entries, "");
        dataSet.setDrawFilled(true);
        dataSet.setFillAlpha(65);


        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        LineData lineData = new LineData(dataSet);

        XAxis xAxis = binding.chart.getXAxis();
        YAxis yAxis = binding.chart.getAxis(YAxis.AxisDependency.LEFT);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0);

        xAxis.setGranularity(1);
        yAxis.setGranularity(1);

        binding.chart.getAxisRight().setEnabled(false);


        binding.chart.setData(lineData);
        binding.chart.getLegend().setEnabled(false);


    }


    private void initEntries() {
        entries.clear();
        DD1KQueue DD1KQueue = (queueing.myapplication.models.DD1KQueue) SystemParams.getQueue();
        for (int i = 0; i < DD1KQueue.getTi() + 5; i++) {
            entries.add(new Entry(i, DD1KQueue.getNumOfCustomers(i)));
        }
    }





    public static class CalculationResults extends BaseObservable {

        private String t = "";
        private String n = "";


        public void setT(String t) {
            this.t = t;
            notifyPropertyChanged(BR._all);
        }

        public void setN(String n) {
            this.n = n;
            notifyPropertyChanged(BR._all);
        }

        @Bindable
        public String getT() {
            return t;
        }

        @Bindable
        public String getN() {
            return n;
        }

        @Bindable
        public String getCustomersNumAtT() {
            try {
                return ((DD1KQueue) (SystemParams.getQueue())).getNumOfCustomers(Integer.parseInt(t)) + " customer";
            } catch (Exception e) {
                return "Invalid time";
            }
        }

        @Bindable
        public String getWaitingForCustomerN() {

            try {
                int n = Integer.parseInt(this.n);
                if (n <= 0)
                    throw new Exception();
                return ((DD1KQueue) (SystemParams.getQueue())).getWaitingTime(n) + " second";
            } catch (Exception e) {
                return "Invalid number";
            }
        }
    }


}
