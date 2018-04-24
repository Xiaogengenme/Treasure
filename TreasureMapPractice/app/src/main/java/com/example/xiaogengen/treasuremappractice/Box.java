package com.example.xiaogengen.treasuremappractice;
import java.util.ArrayList;

public class Box {
    private int idBox;
    private double latitudeBox;
    private double longitudeBox;

    public Box(int idBox,double latitudeBox, double longitudeBox){
        this.idBox=idBox;
        this.latitudeBox=latitudeBox;
        this.longitudeBox=longitudeBox;
    }
    public int getIdBox() {
        return idBox;
    }
    public void setIdBox(int idBox) {
        this.idBox = idBox;
    }
    public double getLatitudeBox() {
        return latitudeBox;
    }
    public void setLatitudeBox(double latitudeBox) {
        this.latitudeBox = latitudeBox;
    }
    public double getLongitudeBox() {
        return longitudeBox;
    }
    public void setLongitudeBox(double longitudeBox) {
        this.longitudeBox = longitudeBox;
    }
//        @Override
//        public String toString(){
//            return "idBox"+idBox+"latitudeBox"+latitudeBox+"longitudeBox"+longitudeBox;
//        }
}