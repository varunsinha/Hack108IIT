package com.varunsinha.jam;

/**
 * Created by varun on 24-11-2016.
 */
public class addperson {

    //private String name;
   // private String address;
    private float lat,lon;
    String name;
    public addperson() {
      /*Blank default constructor essential for Firebase*/
    }
    //Getters and setters
    public float getLatitude() {
        return lat;
    }

    public String getName() {return name;}

    public void setName(String name ){ this.name=name;}
    public void setLatitude(float lat) {
        this.lat = lat;
    }

    public float getLongitude() {
        return lon;
    }

    public void setLongitude(float lon) {
        this.lon = lon;
    }

}
