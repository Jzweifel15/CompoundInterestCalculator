package com.example.compoundinterest;

// A class of utilities for "separations of concerns" purposes
public class Utilities {

    public static double calculateCompoundInterest(double principal, double interest, int years, int compoundPeriod) {

        return Math.round(principal * Math.pow((1 + (interest / compoundPeriod)), (compoundPeriod * years)));

    }

}
