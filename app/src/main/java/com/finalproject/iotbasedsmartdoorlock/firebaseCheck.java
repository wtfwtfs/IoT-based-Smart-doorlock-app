package com.finalproject.iotbasedsmartdoorlock;

public class firebaseCheck {
    private boolean check;
    public firebaseCheck() {
        this.check=false;
    }
    public void  setCheck(boolean a){
        check=a;
    }

    public boolean getCheck() {
        return check;
    }

    public firebaseCheck(boolean check) {
        this.check = check;
    }
}
