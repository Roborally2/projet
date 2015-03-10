package com.example.isen.testgit2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by isen on 19/02/2015.
 */
public class Joueur implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.username);
        dest.writeInt(this.vie);
        dest.writeInt(this.degats);
    }

    public Joueur(Parcel in){
        this.id = in.readInt();
        this.username = in.readString();
        this.vie = in.readInt();
        this.degats = in.readInt();

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


}
