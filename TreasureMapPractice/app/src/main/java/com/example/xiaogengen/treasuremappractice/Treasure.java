package com.example.xiaogengen.treasuremappractice;

public class Treasure {

    private Integer treasureId;
    private String treasureName;
    private int valueTreasure;
    private int imageId;

    public Treasure() {

    }

    public Treasure(Integer treasureId) {
        this.treasureId = treasureId;
    }

    public Treasure(Integer treasureId, String treasureName, int valueTreasure) {
        this.treasureId = treasureId;
        this.treasureName = treasureName;
        this.valueTreasure = valueTreasure;
       // imageId = treasureId;
    }

    public Integer getTreasureId() {
        return treasureId;
    }

    public void setTreasureId(Integer treasureId) {
        this.treasureId = treasureId;
    }

    public String getTreasureName() {
        return treasureName;
    }

    public void setTreasureName(String treasureName) {
        this.treasureName = treasureName;
    }

    public int getValueTreasure() {
        return valueTreasure;
    }

    public void setValueTreasure(int valueTreasure) {
        this.valueTreasure = valueTreasure;
    }

    public int getImageId(){
        return imageId;
    }

}
