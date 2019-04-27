package model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.List;

public class Destination implements Parcelable {


    private String MetropolitanAreaName;

    private String Type;

    private String CountryName;

    private String RegionName;

    private Links[] Links;

    //private List<Links> Links ;

    private String CountryCode;

    private String DestinationLocation;

    public String getMetropolitanAreaName ()
    {
        return MetropolitanAreaName;
    }

    public void setMetropolitanAreaName (String MetropolitanAreaName)
    {
        this.MetropolitanAreaName = MetropolitanAreaName;
    }

    public String getType ()
    {
        return Type;
    }

    public void setType (String Type)
    {
        this.Type = Type;
    }

    public String getCountryName ()
    {
        return CountryName;
    }

    public void setCountryName (String CountryName)
    {
        this.CountryName = CountryName;
    }

    public String getRegionName ()
    {
        return RegionName;
    }

    public void setRegionName (String RegionName)
    {
        this.RegionName = RegionName;
    }


        public Links[] getLinks ()
    {
        return Links;
    }

    public void setLinks (Links[] Links)
    {
        this.Links = Links;
    }




    public String getCountryCode ()
    {
        return CountryCode;
    }

    public void setCountryCode (String CountryCode)
    {
        this.CountryCode = CountryCode;
    }

    public String getDestinationLocation ()
    {
        return DestinationLocation;
    }

    public void setDestinationLocation (String DestinationLocation)
    {
        this.DestinationLocation = DestinationLocation;
    }


    @Override
    public String toString() {
        return "Destination{" +
                "MetropolitanAreaName='" + MetropolitanAreaName + '\'' +
                ", Type='" + Type + '\'' +
                ", CountryName='" + CountryName + '\'' +
                ", RegionName='" + RegionName + '\'' +
                ", CountryCode='" + CountryCode + '\'' +
                ", DestinationLocation='" + DestinationLocation + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.MetropolitanAreaName);
        dest.writeString(this.Type);
        dest.writeString(this.CountryName);
        dest.writeString(this.RegionName);


        //dest.writeParcelableArray(this.Links,flags);
        dest.writeTypedList(Arrays.asList(this.Links));

        dest.writeString(this.CountryCode);
        dest.writeString(this.DestinationLocation);
    }

    public Destination() {
    }

    protected Destination(Parcel in) {
        this.MetropolitanAreaName = in.readString();
        this.Type = in.readString();
        this.CountryName = in.readString();
        this.RegionName = in.readString();

         in.readTypedList(Arrays.asList(this.Links), model.Links.CREATOR);

        this.CountryCode = in.readString();
        this.DestinationLocation = in.readString();
    }

    public static final Parcelable.Creator<Destination> CREATOR = new Parcelable.Creator<Destination>() {
        @Override
        public Destination createFromParcel(Parcel source) {
            return new Destination(source);
        }

        @Override
        public Destination[] newArray(int size) {
            return new Destination[size];
        }
    };
}
