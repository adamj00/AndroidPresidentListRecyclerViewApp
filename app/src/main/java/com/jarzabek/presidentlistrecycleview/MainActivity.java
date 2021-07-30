package com.jarzabek.presidentlistrecycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_addOne;
    List<President> presidentList;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    MyApplication myApplication = (MyApplication) this.getApplication();

    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // global value
        presidentList = myApplication.getPresidentList();

        // assign values to objects in layout
        btn_addOne = findViewById(R.id.btn_addOne);
        recyclerView = (RecyclerView) findViewById(R.id.rv_presidentList);

        // recycler view
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerViewAdapter(presidentList, MainActivity.this);
        recyclerView.setAdapter(mAdapter);


        // set click listener for the button
        btn_addOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // navigate to AddEditOne
                Intent intent = new Intent(MainActivity.this, AddEditOne.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_aToz:
                // sort A to Z
                Collections.sort(presidentList, President.PresidentNameAZComparator);
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.menu_zToa:
                // sort Z to A
                Collections.sort(presidentList, President.PresidentNameZAComparator);
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.menu_dateAsc:
                // sort ascending
                Collections.sort(presidentList, President.PresidentDateAscComparator);
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.menu_dateDesc:
                // sort descending
                Collections.sort(presidentList, President.PresidentDateDescComparator);
                mAdapter.notifyDataSetChanged();
                return true;

        }


        return super.onOptionsItemSelected(item);
    }


}