package com.example.anandaraju.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CommandChangingActivity extends AppCompatActivity {

    int changeint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command_changing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Spinner commandsspinner=(Spinner) findViewById(R.id.spinner);

        String[] commands=new String[]{"call to contact or to a number","Sending Whatsapp Message","Changing of silent and normal mode","Scheduler","Opening an app","turn on flashlight","turn off flashlight","turn on camera","play/resume music","play video","whatsapp voice call","whatsapp video call","play album,song,artist","pause music ","send SMS"};

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,commands);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        final Typeface typefaceoncommandchanging=Typeface.createFromAsset(CommandChangingActivity.this.getAssets(),"blackjack.otf");
        commandsspinner.setAdapter(adapter);
        final EditText existingcommmand=(EditText) findViewById(R.id.editText);
        final EditText changingcommmand=(EditText) findViewById(R.id.editText4);
        //changingcommmand.setTypeface(typefaceoncommandchanging,typefaceoncommandchanging.BOLD);
       final SharedPreferences sharedPreferencescommands=  getSharedPreferences("commands", Context.MODE_PRIVATE);

       commandsspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
              // existingcommmand.setTypeface(typefaceoncommandchanging);
                existingcommmand.setText(sharedPreferencescommands.getString(Integer.toString(position),""));
                changeint=position;
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        //existingcommmand.setEnabled(false);
        Button changecommand =(Button) findViewById(R.id.button10);
        changecommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              SharedPreferences.Editor changingeditor=sharedPreferencescommands.edit();
                changingeditor.putString(Integer.toString(changeint),changingcommmand.getText().toString());
                changingeditor.commit();
                Toast.makeText(getApplicationContext(),"Sucessfull",Toast.LENGTH_LONG).show();
                Intent i=new Intent(CommandChangingActivity.this,Home.class);
                startActivity(i);

            }
        });


    }

}
