package pl.edu.pg.eti.mos.lab.drawingapp;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Kuba on 3/18/2018.
 */

public class Coordinates {
    @Getter @Setter private double x;
    @Getter @Setter private double y;

    public Coordinates(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Coordinates(){
        this.x = 0;
        this.y = 0;
    }
}
