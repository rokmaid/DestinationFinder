package model;

import android.os.Parcel;
import android.os.Parcelable;

public class Links implements Parcelable {

    private String rel;

    private String href;

    public String getRel ()
    {
        return rel;
    }

    public void setRel (String rel)
    {
        this.rel = rel;
    }

    public String getHref ()
    {
        return href;
    }

    public void setHref (String href)
    {
        this.href = href;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.rel);
        dest.writeString(this.href);
    }

    public Links() {
    }

    protected Links(Parcel in) {
        this.rel = in.readString();
        this.href = in.readString();
    }

    public static final Parcelable.Creator<Links> CREATOR = new Parcelable.Creator<Links>() {
        @Override
        public Links createFromParcel(Parcel source) {
            return new Links(source);
        }

        @Override
        public Links[] newArray(int size) {
            return new Links[size];
        }
    };
}
