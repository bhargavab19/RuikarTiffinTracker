package com.koaladev.ruikartiffintracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.koaladev.ruikartiffintracker.Database.DBHandler;
import com.koaladev.ruikartiffintracker.Model.Record;
import com.koaladev.ruikartiffintracker.Recycler.RecyclerViewAdapter;

import java.util.ArrayList;

public class ViewRecords extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Record> recordArrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewRecords.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.setNestedScrollingEnabled(true);

        DBHandler db=new DBHandler(this);

        recordArrayList=db.getAllRecords();

        recyclerViewAdapter=new RecyclerViewAdapter(ViewRecords.this,recordArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}