package com.example.mytodoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText editText;
    private Button button;
    private ListView listView;

    private ArrayList <String> item;
    private ArrayAdapter <String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText= findViewById(R.id.item_edit_text);
        button=findViewById(R.id.add_btn);
        listView=findViewById(R.id.item_list);
        item = FileHelper.readData(this);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, item);
        listView.setAdapter(adapter);


        button.setOnClickListener(this);
        listView.setOnItemClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_btn:
                String itemEntered= editText.getText().toString();
                if (!itemEntered.isEmpty())
                {
                    Log.d("Add", "onClick: " + itemEntered);
                    adapter.add(itemEntered);
                    editText.setText("");
                    FileHelper.writeData(item, this);

                    Toast.makeText(this,"Item Added",Toast.LENGTH_SHORT).show();
                    break;
                }
                else
                    Toast.makeText(this,"FIELD CANNOT BE EMPTY",Toast.LENGTH_SHORT).show();



        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        item.remove(position);
        adapter.notifyDataSetChanged();
        FileHelper.writeData(item, this);
        Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();

    }

    public void closexit(View view) {
     moveTaskToBack(true);
      android.os.Process.killProcess(android.os.Process.myPid());
     System.exit(1);
  //      this.finish();
    }
}
