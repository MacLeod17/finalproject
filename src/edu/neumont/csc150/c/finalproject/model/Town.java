package edu.neumont.csc150.c.finalproject.model;

public class Town {

    public static final int TOWNSQUARE_ROWS = 10;
    public static final int TOWNSQAURE_ROWS = 10;

    public char[][] townSquares = new char[][]{
            {'-','-','-','-','-','-','-','-','-','-'},
            {'-','-','-','-','-','-','-','-','-','-'},
            {'-','-','-','-','-','-','-','-','-','-'},
            {'-','-','-','-','-','-','-','-','-','-'},
            {'-','-','-','-','-','-','-','-','-','-'},
            {'-','-','-','-','-','-','-','-','-','-'},
            {'-','-','-','-','-','-','-','-','-','-'},
            {'-','-','-','-','-','-','-','-','-','-'},
            {'-','-','-','-','-','-','-','-','-','-'},
            {'-','-','-','-','-','-','-','-','-','-'}
    };


    public boolean checkForMovementRight() {
        return false;
    }

    public boolean checkForMovementLeft() {
        return false;
    }

    public boolean checkForBackwardsMovement() {
        return false;
    }

    public boolean checkForForwardMovement() {
        return false;
    }

}