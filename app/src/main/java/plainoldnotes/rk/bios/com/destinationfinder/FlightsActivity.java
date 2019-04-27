package plainoldnotes.rk.bios.com.destinationfinder;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import adaptor.FlightsAdaptor;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.Destinations;
import model.FlightData;
import viewmodels.FlightListViewModel;

public class FlightsActivity extends AppCompatActivity {

    private FlightData  flight_data ;

    private FlightListViewModel viewModel ;

    @BindView(R.id.rec_view)
    RecyclerView rec_view ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);

        initViewModel();

        ButterKnife.bind(this);
        /*
        Get Flight Data Object
         */

     //   Bundle extras = getIntent().getExtras();

        //flight_list =extras.getParcelableArrayList("flights");

    //    flight_data = extras.getParcelable("flight_data") ;

        flight_data=viewModel.getFlight_data().getValue() ;

        if(flight_data!=null){

            this.setTitle("Destinations for  "+flight_data.getOriginLocation());
            // set adaptor

           List<Destinations> destinations =Arrays.asList(flight_data.getDestinations());

            FlightsAdaptor adaptor = new FlightsAdaptor(destinations,FlightsActivity.this);
            rec_view.setAdapter(adaptor);

            DividerItemDecoration divider = new DividerItemDecoration(FlightsActivity.this,LinearLayoutManager.VERTICAL );
            rec_view.addItemDecoration(divider);
            rec_view.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        }


    }

private void initViewModel(){

        viewModel = ViewModelProviders.of(this).get(FlightListViewModel.class);



}

}
