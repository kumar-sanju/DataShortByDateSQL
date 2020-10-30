package com.sanju.descendingordersql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    Button save, refresh;
    EditText name, salary;
    private ListView listView;
    @Override
    protected void onCreate(Bundle readdInstanceState) {
        super.onCreate(readdInstanceState);
        setContentView(R.layout.activity_main2);
        final DatabaseHelper2 helper = new DatabaseHelper2(this);
        final ArrayList array_list = helper.getAllCotacts();
        name = findViewById(R.id.name);
        salary = findViewById(R.id.salary);
        listView = findViewById(R.id.listView);
        final ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity2.this,
                android.R.layout.simple_list_item_1, array_list);
        listView.setAdapter(arrayAdapter);
        findViewById(R.id.Delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (helper.delete()) {
                    Toast.makeText(MainActivity2.this, "Deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity2.this, "NOT Deleted", Toast.LENGTH_LONG).show();
                }
            }
        });
        findViewById(R.id.udate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty() && !salary.getText().toString().isEmpty()) {
                    if (helper.update(name.getText().toString(), salary.getText().toString())) {
                        Toast.makeText(MainActivity2.this, "Updated", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity2.this, "NOT Updated", Toast.LENGTH_LONG).show();
                    }
                } else {
                    name.setError("Enter NAME");
                    salary.setError("Enter Salary");
                }
            }
        });
        findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                array_list.clear();
                array_list.addAll(helper.getAllCotacts());
                arrayAdapter.notifyDataSetChanged();
                listView.invalidateViews();
                listView.refreshDrawableState();
            }
        });
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty() && !salary.getText().toString().isEmpty()) {
                    if (helper.insert(name.getText().toString(), salary.getText().toString())) {
                        Toast.makeText(MainActivity2.this, "Inserted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity2.this, "NOT Inserted", Toast.LENGTH_LONG).show();
                    }
                } else {
                    name.setError("Enter NAME");
                    salary.setError("Enter Salary");
                }
            }
        });
    }
}