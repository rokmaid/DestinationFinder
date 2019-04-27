package viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;

import java.util.List;

import model.FlightData;
import model.Token;
import plainoldnotes.rk.bios.com.destinationfinder.MainActivity;
import repository.AppRepository;

public class MainActivityViewModel extends AndroidViewModel {

    private AppRepository repo ;

   // private MutableLiveData<Token> token ;

    private MutableLiveData<FlightData> flight_data ;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        repo= AppRepository.getInstance(application.getApplicationContext());

        //token = repo.getToken() ;
        flight_data  =repo.getFlight_data();

    }




    public void getFlightData(String origin){

        repo.getFlightData(origin,null,null);
    }


    public void getFlightData(String origin,String country_code , String theme ){

        repo.getFlightData(origin,country_code,theme);
    }


    public MutableLiveData<FlightData> getFlight_data() {
        return flight_data;
    }





}
