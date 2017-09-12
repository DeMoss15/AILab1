package com.example.daniel.ailab1;

/**
 * Created by Daniel on 12.09.2017.
 */

class Girl {

    private int shift;
    private double percentage;
    private String hairColor;

    Girl(int shiftP, String hairColorP, double percentageP){
        shift = shiftP;
        percentage = percentageP;
        hairColor = hairColorP;
    }

    String getColorValue(){
        return hairColor;
    }

    int getShift(){
        return shift;
    }

    double getPercentage(){
        return percentage;
    }

    void setPercentage(double newPercentage){
        percentage = newPercentage;
    }
}
