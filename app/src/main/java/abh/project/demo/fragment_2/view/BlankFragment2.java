package abh.project.demo.fragment_2.view;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import abh.project.demo.R;


public class BlankFragment2 extends Fragment {

    EditText msg2;
    Button goToF1;
    int msg2_val = 0;
    Thread thread;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         msg2 = view.findViewById(R.id.msg2); //binding.msg2
        goToF1 = view.findViewById(R.id.goToF1); //binding.goToF1

        goToF1.setOnClickListener(this::navigate);

        thread = new Thread(){
            //UI threads  && Back Thread
            // UI : to refresh view || Toast msg
            // Back : Internet || DB connections (Block the UI : so Back thread is Obligation)
            @Override
            public void run() {
                while (true){
                    run10s();

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        };

        thread.start();
    }

    private void navigate(View view) {
        findNavController(view) // control ta3 navigation li rah ydirha view
                .navigate(R.id.action_blankFragment2_to_blankFragment1); // id ta3 nav mn navigation graph
    }

    public void run10s(){
        if (getActivity() == null) return;
        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (msg2.getText().toString().isEmpty())
                    Toast.makeText(requireContext(),""+ msg2_val++, Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(requireContext(), msg2.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onPause() {
        super.onPause();
        if (thread.isAlive()){
            try {
                thread.interrupt();
                thread.join();
            } catch (InterruptedException e) {
                Log.e("E",e.getMessage());
            }
        }
    }
}