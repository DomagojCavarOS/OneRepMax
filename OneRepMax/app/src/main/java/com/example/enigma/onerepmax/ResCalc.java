package com.example.enigma.onerepmax;

/**
 * Created by Enigma on 2.11.2015..
 */
public class ResCalc {
    float weight;
    float reps;

    public ResCalc(float weight,float reps){
        super();
        this.weight=weight;
        this.reps=reps;
    }

    public float calculate(float we,float re){
        //= $B15*(($C$15/30)+1)
        weight=we;
        reps=re;
        return (weight*((reps/30)+1));
    }
}
