package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends Activity {


    private ListView businessListView;
    private FirebaseListAdapter<Business> firebaseAdapter;
    private Button createBusinessBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the app wide shared variables
        MyApplicationData appData = (MyApplicationData)getApplication();

        //Set-up Firebase
        appData.firebaseDBInstance = FirebaseDatabase.getInstance();
        appData.firebaseReference = appData.firebaseDBInstance.getReference();

        //Get the reference to the UI contents
        businessListView = findViewById(R.id.listView);
        createBusinessBtn=findViewById(R.id.submitButton);

        //Set up the List View
       firebaseAdapter = new FirebaseListAdapter<Business>(this, Business.class,
                android.R.layout.simple_list_item_1, appData.firebaseReference) {
            @Override
            protected void populateView(View v, Business model, int position) {
                TextView businessName = v.findViewById(android.R.id.text1);
                businessName.setText(model.name);
            }
        };
        businessListView.setAdapter(firebaseAdapter);

        businessListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // onItemClick method is called everytime a user clicks an item on the list
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Business business = (Business) firebaseAdapter.getItem(position);
                showDetailView(business);
            }
        });

        createBusinessBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                createBusinessButton(view);
            }
        });
    }

    public void createBusinessButton(View v)
    {
        Intent intent=new Intent(MainActivity.this, CreateBusinessActivity.class);
        startActivity(intent);
    }

    private void showDetailView(Business b)
    {
        Intent intent = new Intent(this, DetailViewActivity.class);
        intent.putExtra("Business",b);
        startActivity(intent);
    }



}
