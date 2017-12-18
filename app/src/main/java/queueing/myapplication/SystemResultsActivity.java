package queueing.myapplication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import queueing.myapplication.databinding.SystemResultsBinding;
import queueing.myapplication.models.DD1KQueue;
import queueing.myapplication.models.MM1KQueue;
import queueing.myapplication.models.MM1Queue;
import queueing.myapplication.models.Queue;
import queueing.myapplication.results.DD1kFragment;
import queueing.myapplication.results.MM1Fragment;
import queueing.myapplication.results.MM1KFragment;

public class SystemResultsActivity extends AppCompatActivity {

    SystemResultsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.system_results);


        Queue queue = SystemParams.getQueue();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;

        if (queue instanceof DD1KQueue) {
            fragment = new DD1kFragment();
        } else if (queue instanceof MM1Queue) {
            fragment = new MM1Fragment();
        } else if (queue instanceof MM1KQueue) {
            fragment = new MM1KFragment();
        }

        ft.replace(R.id.results, fragment);
        ft.commit();


    }


}
