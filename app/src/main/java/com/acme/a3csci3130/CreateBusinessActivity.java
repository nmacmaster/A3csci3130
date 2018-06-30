package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateBusinessActivity extends Activity {

    private Button submitButton;
    private EditText nameField, numField,addrField;
    private Spinner primaryDrop, provDrop;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        numField = (EditText) findViewById(R.id.Bnumber);
        nameField = (EditText) findViewById(R.id.name);
        addrField = (EditText) findViewById(R.id.address);

        primaryDrop = (Spinner)findViewById(R.id.primaryB);
        provDrop = (Spinner)findViewById(R.id.province);

        //set primary business dropdown values
        String[] prime = new String[]{"Fisher", "Distributor", "Processor", "Fish Monger"};
        ArrayAdapter<String> primaryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, prime);
        primaryDrop.setAdapter(primaryAdapter);

        //set province dropdown values
        String[] prov = new String[]{"AB", "BC", "MB", "NB", "NL", "NS", "NT", "NU", "ON", "PE", "QC", "SK", "YT", " "};
        ArrayAdapter<String> provAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, prov);
        provDrop.setAdapter(provAdapter);
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String Bnum = numField.getText().toString();
        String name = nameField.getText().toString();
        String primary = primaryDrop.getSelectedItem().toString();
        String address = addrField.getText().toString();
        String province = provDrop.getSelectedItem().toString();

        Business business = new Business(Bnum,name,primary,address,province);

        appState.firebaseReference.child(Bnum).setValue(business);

        finish();

    }
}
