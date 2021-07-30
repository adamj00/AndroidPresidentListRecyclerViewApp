package com.jarzabek.presidentlistrecycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class AddEditOne extends AppCompatActivity {

    Button btn_ok, btn_cancel;
    EditText et_name, et_date, et_url;
    TextView tv_id;
    List<President> presidentList;
    MyApplication myApplication = (MyApplication) this.getApplication();
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_one);

        // assign values to objects on the layout
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_ok = findViewById(R.id.btn_ok);
        et_date = findViewById(R.id.et_dateElection);
        et_name = findViewById(R.id.et_presidentName);
        et_url = findViewById(R.id.et_pictureURL);
        tv_id = findViewById(R.id.tv_presidentIdNumber);

        // global value
        presidentList = (List<President>) myApplication.getPresidentList();


        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        President president = null;

        if (id > -1) {
            // we are editing a existing president
            for (President p : presidentList) {
                if (p.getId() == id) {
                    president = p;
                    break;
                }
            }

            et_date.setText(String.valueOf(president.getDateOfElection()));
            et_name.setText(president.getName());
            et_url.setText(president.getImageURL());
            tv_id.setText(String.valueOf(president.getId()));

        }
        else {
            int nextId = myApplication.getNextId();
            tv_id.setText(String.valueOf(nextId));
        }



        // set button listeners

        // "ok" button listener

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id > -1) {
                    // editing president
                    President updatedPresident = new President(id, et_name.getText().toString(), Integer.parseInt(et_date.getText().toString()), et_url.getText().toString());
                    for(int i=0; i<MyApplication.getNextId(); i++) {
                        if (presidentList.get(i).getId() == id) {
                            presidentList.set(i, updatedPresident);
                            break;
                        }
                    }
                    // navigate to MainActivity
                    Intent intent = new Intent (AddEditOne.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    // adding new president
                    int nextId = myApplication.getNextId();
                    President newPresident = new President(nextId, et_name.getText().toString(), Integer.parseInt(et_date.getText().toString()), et_url.getText().toString());

                    // add it to the global list
                    presidentList.add(newPresident);
                    myApplication.setNextId(nextId + 1);

                    // navigate to MainActivity
                    Intent intent = new Intent (AddEditOne.this, MainActivity.class);
                    startActivity(intent);

                }
            }
        });

        // "cancel" button listener
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // navigate to MainActivity
                Intent intent = new Intent (AddEditOne.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}