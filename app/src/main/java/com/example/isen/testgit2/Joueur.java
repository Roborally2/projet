package com.example.isen.testgit2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by isen on 19/02/2015.
 */
public class Joueur {
    protected int id;
    protected String username;
    protected int vie;
    protected int degats;

    public Joueur(String id, String name, String vie, String degats){
        this.id = Integer.parseInt(id);
        this.username = name;
        this.vie = Integer.parseInt(vie);
        this.degats = Integer.parseInt(degats);
    }

    /*public Joueur(Parcel in){
        String[] strData = new String[4];
        in.readStringArray(strData);
        this.id = strData[0];
        this.username = strData[1];
        this.vie = strData[2];
        this.degats = strData[3];

    }


    public static final Parcelable.Creator<Joueur> CREATOR= new Parcelable.Creator<Joueur>() {

        @Override
        public Joueur createFromParcel(Parcel source) {
            return new Joueur(source);  //using parcelable constructor
        }

        @Override
        public Joueur[] newArray(int size) {
            return new Joueur[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.id, this.username,this.vie,this.degats});
    }*/
}
