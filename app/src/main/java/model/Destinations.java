package model;

import android.os.Parcel;
import android.os.Parcelable;

public class Destinations implements Parcelable {


    private Destination Destination;

    private String Rank;

    public Destination getDestination ()
    {
        return Destination;
    }

    public void setDestination (Destination Destination)
    {
        this.Destination = Destination;
    }

    public String getRank ()
    {
        return Rank;
    }

    public void setRank (String Rank)
    {
        this.Rank = Rank;
    }


    @Override
    public String toString() {
        return "Destinations{" +
                "Destination=" + Destination +
                ", Rank='" + Rank + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.Destination, flags);
        dest.writeString(this.Rank);
    }

    public Destinations() {
    }

    protected Destinations(Parcel in) {
        this.Destination = in.readParcelable(model.Destination.class.getClassLoader());
        this.Rank = in.readString();
    }

    public static final Parcelable.Creator<Destinations> CREATOR = new Parcelable.Creator<Destinations>() {
        @Override
        public Destinations createFromParcel(Parcel source) {
            return new Destinations(source);
        }

        @Override
        public Destinations[] newArray(int size) {
            return new Destinations[size];
        }
    };
}
