package queueing.myapplication.results;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

import queueing.myapplication.SystemParams;
import queueing.myapplication.databinding.Mm1ResultsBinding;
import queueing.myapplication.models.MM1KQueue;
import queueing.myapplication.models.MMCQueue;

/**
 * Created by abou7mied on 18/12/17.
 */

public class MMCFragment extends Fragment {


    public static List<Entry> entries = new ArrayList<>();
    private Mm1ResultsBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = Mm1ResultsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MMCQueue mm1Queue = (MMCQueue) SystemParams.getQueue();
        binding.setL(mm1Queue.getSystemCustomersCount());
        binding.setLq(mm1Queue.getQueueCustomersCount());
        binding.setW(mm1Queue.getSystemWaitingTime());
        binding.setWq(mm1Queue.getQueueWaitingTime());



    }


}
