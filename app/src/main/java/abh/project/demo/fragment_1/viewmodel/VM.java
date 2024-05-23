package abh.project.demo.fragment_1.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VM extends ViewModel {
    int msg2_val = 0;

    MutableLiveData<Integer> value = new MutableLiveData<>(0);

    public int getValue() {
        //return 0;
        return value.getValue();
    }

    public void incremnt() {
        //msg2_val++;
        int s = value.getValue();
        value.postValue(++s);
    }
}
