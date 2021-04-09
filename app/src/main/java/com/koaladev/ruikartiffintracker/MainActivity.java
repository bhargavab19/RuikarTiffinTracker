package com.koaladev.ruikartiffintracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.koaladev.ruikartiffintracker.Database.DBHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialise Database
        DBHandler db=new DBHandler(MainActivity.this);

        //Define page controls
        Button btnAdd=findViewById(R.id.btnAdd);
        Button btnView=findViewById(R.id.btnView);
        Button btnUpdate=findViewById(R.id.btnUpdate);
        Button btnDelete=findViewById(R.id.btnDelete);



        //Define Button Activity
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddNewRecord.class);
                startActivity(intent);
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ViewRecords.class);
                startActivity(intent);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,UpdateRecord.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DeleteRecord.class);
                startActivity(intent);
            }
        });

    }
}