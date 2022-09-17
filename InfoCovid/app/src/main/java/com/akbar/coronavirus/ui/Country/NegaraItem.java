package com.akbar.coronavirus.ui.Country;

import android.os.Parcel;
import android.os.Parcelable;

public class NegaraItem implements Parcelable {
    String OBJECTID, Country_Region, Confirmed, Deaths, Recovered;

    public NegaraItem() {
        this.OBJECTID = OBJECTID;
        Country_Region = Country_Region;
        Confirmed = Confirmed;
        Deaths = Deaths;
        Recovered = Recovered;
    }

    public String getOBJECTID() {
        return OBJECTID;
    }

    public void setOBJECTID(String OBJECTID) {
        this.OBJECTID = OBJECTID;
    }

    public String getCountry_Region() {
        return Country_Region;
    }

    public void setCountry_Region(String country_Region) {
        Country_Region = country_Region;
    }

    public String getConfirmed() {
        return Confirmed;
    }

    public void setConfirmed(String confirmed) {
        Confirmed = confirmed;
    }

    public String getDeaths() {
        return Deaths;
    }

    public void setDeaths(String deaths) {
        Deaths = deaths;
    }

    public String getRecovered() {
        return Recovered;
    }

    public void setRecovered(String recovered) {
        Recovered = recovered;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.OBJECTID);
        dest.writeString(this.Country_Region);
        dest.writeString(this.Confirmed);
        dest.writeString(this.Deaths);
        dest.writeString(this.Recovered);
    }

    protected NegaraItem(Parcel in) {
        this.OBJECTID = in.readString();
        this.Country_Region = in.readString();
        this.Confirmed = in.readString();
        this.Deaths = in.readString();
        this.Recovered = in.readString();
    }

    public static final Parcelable.Creator<NegaraItem> CREATOR = new Parcelable.Creator<NegaraItem>() {
        @Override
        public NegaraItem createFromParcel(Parcel source) {
            return new NegaraItem(source);
        }

        @Override
        public NegaraItem[] newArray(int size) {
            return new NegaraItem[size];
        }
    };
}
