package com.akbar.coronavirus.ui.Provinsi;

import android.os.Parcel;
import android.os.Parcelable;

public class ProvinsiModel implements Parcelable {
    String FID, Kode_Provi, Provinsi, Kasus_Posi, Kasus_Semb, Kasus_Meni;

    public ProvinsiModel() {
        this.FID = FID;
        Kode_Provi = Kode_Provi;
        Provinsi = Provinsi;
        Kasus_Posi = Kasus_Posi;
        Kasus_Semb = Kasus_Semb;
        Kasus_Meni = Kasus_Meni;
    }

    public String getFID() {
        return FID;
    }

    public void setFID(String FID) {
        this.FID = FID;
    }

    public String getKode_Provi() {
        return Kode_Provi;
    }

    public void setKode_Provi(String kode_Provi) {
        Kode_Provi = kode_Provi;
    }

    public String getProvinsi() {
        return Provinsi;
    }

    public void setProvinsi(String provinsi) {
        Provinsi = provinsi;
    }

    public String getKasus_Posi() {
        return Kasus_Posi;
    }

    public void setKasus_Posi(String kasus_Posi) {
        Kasus_Posi = kasus_Posi;
    }

    public String getKasus_Semb() {
        return Kasus_Semb;
    }

    public void setKasus_Semb(String kasus_Semb) {
        Kasus_Semb = kasus_Semb;
    }

    public String getKasus_Meni() {
        return Kasus_Meni;
    }

    public void setKasus_Meni(String kasus_Meni) {
        Kasus_Meni = kasus_Meni;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.FID);
        dest.writeString(this.Kode_Provi);
        dest.writeString(this.Provinsi);
        dest.writeString(this.Kasus_Posi);
        dest.writeString(this.Kasus_Semb);
        dest.writeString(this.Kasus_Meni);
    }

    protected ProvinsiModel(Parcel in) {
        this.FID = in.readString();
        this.Kode_Provi = in.readString();
        this.Provinsi = in.readString();
        this.Kasus_Posi = in.readString();
        this.Kasus_Semb = in.readString();
        this.Kasus_Meni = in.readString();
    }

    public static final Parcelable.Creator<ProvinsiModel> CREATOR = new Parcelable.Creator<ProvinsiModel>() {
        @Override
        public ProvinsiModel createFromParcel(Parcel source) {
            return new ProvinsiModel(source);
        }

        @Override
        public ProvinsiModel[] newArray(int size) {
            return new ProvinsiModel[size];
        }
    };
}
