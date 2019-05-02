package plainoldnotes.rk.bios.com.destinationfinder;

import android.app.Notification;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Destination;
import model.Destinations;
import model.FlightData;
import model.Token;
import util.DataGenerator;
import viewmodels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.dropdown_theme)
    Spinner dropdown_theme ;

    @BindView(R.id.btnget_flights)
    Button btnflights;


    @BindView(R.id.txt_origin)
    //EditText txt_origin ;
    AutoCompleteTextView txt_origin ;

    @BindView((R.id.txt_origin_country))
    AutoCompleteTextView txtcountry_code ;

    @BindView(R.id.txtinput_origin)
    TextInputLayout txt_origin_input ;

    @BindView(R.id.txtinput_origin_country)
    TextInputLayout txt_origin_country_input ;

    private MainActivityViewModel viewModel;
    private MutableLiveData<Token> mToken;

    private MutableLiveData<FlightData> flight_data ;

    private String[] City_Codes={"MIA","NYC","DFW","LAX","LAS","CHI","MVD","BUE"} ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Destination Finder");

        ButterKnife.bind(this);

        /*
        Populate the theme drop Down
         */
        initViewModel();
        List<String> list = new ArrayList<String>();
        list.add("");
        list.add("BEACH");
        list.add("CARIBBEAN");
        list.add("DISNEY");
        list.add("GAMBLING");
        list.add("HISTORIC");
        list.add("MOUNTAINS");
        list.add("NATIONAL-PARKS");
        list.add("OUTDOORS");
        list.add("ROMANTIC");
        list.add("SHOPPING");
        list.add("SKIING");
        list.add("THEME-PARK");


        ArrayAdapter<String> AutoComplete_Adapter_city_codes = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, City_Codes);

        // txt_origin.setAdapter(AutoComplete_Adapter);



        ArrayAdapter<String> AutoComplete_Adapter_country_codes = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, DataGenerator.getListOfCountryCodes());

        txtcountry_code.setAdapter(AutoComplete_Adapter_country_codes);


        ArrayAdapter<String> dataAdapter_themes = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);

        dropdown_theme.setAdapter(dataAdapter_themes);




          btnflights.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                //  viewModel.getAccesToken();

                  String origin =txt_origin.getText().toString().trim().toUpperCase();
                  String country_code = txtcountry_code.getText().toString().trim().toUpperCase();

                  if(validateOrigin(origin) && validateCountry(country_code)){

                   String theme = null ;

                   if(! dropdown_theme.getSelectedItem().toString().isEmpty()){

                       theme = dropdown_theme.getSelectedItem().toString();
                   }

                      viewModel.getFlightData(origin,country_code,theme);

                  }

              }
          });


          // on key down events for City and Country codes and update Text Boxes


    }



    private void initViewModel(){

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
     //   mToken = viewModel.getToken();
        flight_data  = viewModel.getFlight_data();

        final Observer<FlightData> observer = new Observer<FlightData>(){

            @Override
            public void onChanged(@Nullable FlightData flightData) {



             List<Destinations> list = Arrays.asList(flightData.getDestinations() );

                for(Destinations d :list){

                    Log.i("Api result",d.toString());
                }

                FlightData data = flight_data.getValue();
                Intent flight_details_activity = new Intent(MainActivity.this,FlightsActivity.class);

              //  flight_details_activity.putExtra("flight_data",data);

                startActivity(flight_details_activity);
            }

        };



flight_data.observe(this,observer);



    }


    private boolean validateOrigin(String origin){

        Pattern pattern = Pattern.compile("^[a-zA-Z]{3}+$");
        if( ! pattern.matcher(origin).matches() ){

            txt_origin_input.setError("Origin Country must be 3 letters");

            return false ;
        }else{

            txt_origin_input.setError(null);
        }


        return true  ;
    }


    private boolean validateCountry(String country){

        Pattern pattern = Pattern.compile("^[a-zA-Z]{2}+$");
        if( ! pattern.matcher(country).matches() ){
            txt_origin_country_input.setError("Country value should be 2 letters");
            return false ;
        }else{
            txt_origin_country_input.setError(null);
        }


        return true  ;
    }

}
