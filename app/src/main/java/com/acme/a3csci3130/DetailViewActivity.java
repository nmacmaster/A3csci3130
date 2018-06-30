package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class DetailViewActivity extends Activity {

    private Button submitButton;
    private EditText nameField, numField,addrField;
    private Spinner primaryDrop, provDrop;
    Business receivedPersonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Business)getIntent().getSerializableExtra("Business");

        submitButton = (Button) findViewById(R.id.submitButton);
        numField = (EditText) findViewById(R.id.Bnumber);
        nameField = (EditText) findViewById(R.id.name);
        addrField = (EditText) findViewById(R.id.address);

        primaryDrop = (Spinner)findViewById(R.id.primaryB);
        provDrop = (Spinner)findViewById(R.id.province);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            numField.setText(receivedPersonInfo.num);
            addrField.setText(receivedPersonInfo.address);

            String[] prime = new String[]{"Fisher", "Distributor", "Processor", "Fish Monger"};
            ArrayAdapter<String> primaryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, prime);
            primaryDrop.setAdapter(primaryAdapter);
            primaryDrop.setSelection(primaryAdapter.getPosition(receivedPersonInfo.primaryB));

            String[] prov = new String[]{"AB", "BC", "MB", "NB", "NL", "NS", "NT", "NU", "ON", "PE", "QC", "SK", "YT", " "};
            ArrayAdapter<String> provAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, prov);
            provDrop.setAdapter(provAdapter);
            provDrop.setSelection(provAdapter.getPosition(receivedPersonInfo.province));

        }
    }

    public void updateBusiness(View v){
        //TODO: Update contact funcionality
    }

    public void eraseBusiness(View v)
    {
        //TODO: Erase contact functionality
    }
}
