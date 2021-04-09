package com.koaladev.ruikartiffintracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.koaladev.ruikartiffintracker.Database.DBHandler;
import com.koaladev.ruikartiffintracker.Model.Record;
import com.koaladev.ruikartiffintracker.Recycler.RecyclerViewAdapter;

import java.util.ArrayList;

public class AddNewRecord extends AppCompatActivity {
    int quantity,rate;

    EditText txtDate;
    CheckBox cbLunch;
    CheckBox cbDinner;
    Spinner ddQuantity;
    Spinner ddRate;
    Button submit;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Record> recordArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_record);

        //Create DB object
        DBHandler db=new DBHandler(this);

        //Define screen controls
        txtDate=findViewById(R.id.editTextDate);
        cbLunch=findViewById(R.id.cbLunch);
        cbDinner=findViewById(R.id.cbDinner);
        ddQuantity=findViewById(R.id.ddQuantity);
        ddRate=findViewById(R.id.ddRate);
        submit=findViewById(R.id.btnSubmit);
        recyclerView=findViewById(R.id.addNewRecycler);

        //Customize RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(AddNewRecord.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(AddNewRecord.this,LinearLayoutManager.VERTICAL));

        //Set Listener on Dropdown controls
        ddQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                quantity=Integer.parseInt(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                quantity=Integer.parseInt(parent.getItemAtPosition(0).toString());
            }
        });
        ddRate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rate=Integer.parseInt(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                rate=Integer.parseInt(parent.getItemAtPosition(0).toString());
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Record record=new Record();
                record.setDate(String.valueOf(txtDate.getText()));
                record.setLunch(cbLunch.isChecked()?"Yes":"No");
                record.setDinner(cbDinner.isChecked()?"Yes":"No");
                record.setQuantity(quantity);
                record.setRate(rate);
                //Check if Record exists
                if((db.getSpecificRecord(record.getDate()).isEmpty())) {
                    //Check if Record is inserted successfully
                    if (db.addRecord(record) == -1)
                        Toast.makeText(AddNewRecord.this, "Problem Occurred", Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(AddNewRecord.this, "Record successfully inserted", Toast.LENGTH_SHORT).show();
                        recordArrayList = db.getSpecificRecord(record.getDate());
                        recyclerViewAdapter = new RecyclerViewAdapter(AddNewRecord.this, recordArrayList);
                        recyclerView.setAdapter(recyclerViewAdapter);
                    }
                }
                else
                    Toast.makeText(AddNewRecord.this, "Record already exists", Toast.LENGTH_SHORT).show();
                clearContents();
            }
        });
    }

    public void clearContents(){
        txtDate.setText("");
        if(cbLunch.isChecked())
            cbLunch.setChecked(false);
        if(cbDinner.isChecked())
            cbDinner.setChecked(false);
        ddQuantity.setSelection(0);
        ddRate.setSelection(0);
    }
}