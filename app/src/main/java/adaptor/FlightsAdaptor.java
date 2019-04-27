package adaptor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Destinations;
import plainoldnotes.rk.bios.com.destinationfinder.R;

public class FlightsAdaptor extends RecyclerView.Adapter<FlightsAdaptor.ViewHolder> {


    private List<Destinations> destinations ;

    private Context mContext = null ;

    public FlightsAdaptor(List<Destinations> destinations, Context context){

        this.destinations = destinations ;
        this.mContext = context ;

    }

public  class ViewHolder extends  RecyclerView.ViewHolder{

        @BindView(R.id.txt_rank)
        TextView txt_rank ;

        @BindView(R.id.txt_country_code)
        TextView txt_country_code ;

        @BindView(R.id.txt_country_name)
        TextView txt_country_name ;

        @BindView(R.id.txt_destination_location)
        TextView txt_destination_location ;
        @BindView(R.id.txt_type)
        TextView txt_type ;

        // add extra controls here


    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.flight_item,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Destinations dest = destinations.get(position);

        holder.txt_rank.setText(dest.getRank());
        holder.txt_country_code.setText(dest.getDestination().getCountryCode());
        holder.txt_country_name.setText(dest.getDestination().getCountryName());
        holder.txt_destination_location.setText(dest.getDestination().getDestinationLocation());
        holder.txt_type.setText(dest.getDestination().getType());

        // map extra controls here

    }

    @Override
    public int getItemCount() {
        return this.destinations.size();
    }
}
