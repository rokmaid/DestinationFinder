package model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.List;

public class FlightData implements Parcelable {


    private String OriginLocation;

   private Destinations[] Destinations;

  //  private List<Destinations> Destinations ;
    public String getOriginLocation ()
    {
        return OriginLocation;
    }

    public void setOriginLocation (String OriginLocation)
    {
        this.OriginLocation = OriginLocation;
    }


    public Destinations[] getDestinations ()
    {
       return Destinations;
    }


   public void setDestinations (Destinations[] Destinations)

    {
        this.Destinations = Destinations;
    }




    @Override
    public String toString() {
        return "FlightData{" +
                "OriginLocation='" + OriginLocation + '\'' +
                ", Destinations=" + Destinations.toString() +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.OriginLocation);

      //  dest.writeParcelableArray(this.Destinations,flags);

        dest.writeTypedList(Arrays.asList(this.Destinations));
    }

    public FlightData() {
    }

    protected FlightData(Parcel in) {
        this.OriginLocation = in.readString();

     //   this.Destinations =(Destinations[])  in.readArray(model.Destinations.class.getClassLoader());

        in.readTypedList(Arrays.asList(this.Destinations), model.Destinations.CREATOR);



    }

    public static final Parcelable.Creator<FlightData> CREATOR = new Parcelable.Creator<FlightData>() {
        @Override
        public FlightData createFromParcel(Parcel source) {
            return new FlightData(source);
        }

        @Override
        public FlightData[] newArray(int size) {
            return new FlightData[size];
        }
    };
}
