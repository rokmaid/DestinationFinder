package viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import model.FlightData;
import repository.AppRepository;

public class FlightListViewModel extends AndroidViewModel {


    private AppRepository repo ;

    // private MutableLiveData<Token> token ;

    private MutableLiveData<FlightData> flight_data ;


    public FlightListViewModel(@NonNull Application application) {
        super(application);

        repo= AppRepository.getInstance(application.getApplicationContext());

        flight_data  =repo.getFlight_data();
    }


    public MutableLiveData<FlightData> getFlight_data() {
        return flight_data;
    }


}
