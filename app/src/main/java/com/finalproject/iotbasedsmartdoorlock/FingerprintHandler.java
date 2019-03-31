package com.finalproject.iotbasedsmartdoorlock;


import android.app.Activity;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {
    private Context context;

    private int second=0;
    private boolean running=true;
    private boolean wasRunning= false;


    public FingerprintHandler(Context context){

        this.context = context;

    }


    public void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject){

        CancellationSignal cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this, null);

    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {

        this.update("There was an Auth Error. " + errString, false);


    }

    @Override
    public void onAuthenticationFailed() {

        this.update("Auth Failed. ", false);

    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {

        this.update("Error: " + helpString, false);

    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {

        this.update("You can now access .", true);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myref=database.getReference();
        firebaseCheck checkIt= new firebaseCheck(true);

        myref.child("status").push().setValue(checkIt);



    }

    private void update(String s, boolean b) {

        TextView paraLabel = (TextView) ((Activity)context).findViewById(R.id.paraLabel);
        ImageView imageView = (ImageView) ((Activity)context).findViewById(R.id.fingerprintImage);



        // checkIt.setCheck(true);

        //myref.setValue(checkIt);
        paraLabel.setText(s);

        if(b == false){

            paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        } else {

            paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            imageView.setImageResource(R.mipmap.action_done);

        }

    }
}
