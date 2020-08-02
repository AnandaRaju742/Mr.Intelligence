package com.example.anandaraju.myapplication;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;

import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;

import android.util.Patterns;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SQLiteDatabase db;
    Account[] account;
    Pattern pattern;
    Cursor c;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.rgb(35, 33, 33));

        Context context=getApplicationContext();
        NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        pattern = Patterns.EMAIL_ADDRESS;

        db=openOrCreateDatabase("pass", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS pass (pass VARCHAR);");
        c=db.rawQuery("SELECT * FROM pass",null);
        if(c.getCount()==0)
        {
            SharedPreferences luckynuumber= getSharedPreferences("luckynumber", Context.MODE_PRIVATE);

            SharedPreferences.Editor edit=luckynuumber.edit();
            edit.putString("lucky","-2");
            edit.apply();


            Intent intent= new Intent(this,Main2Activity.class);
            startActivity(intent);
        }
        else {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        if (notificationManager != null && (!Settings.canDrawOverlays(this) || ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || !notificationManager.isNotificationPolicyAccessGranted() || ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)) {
            Intent i = new Intent(this, PermissionGettingActivity.class);
            startActivity(i);
        }


        //text.setText(Build.MODEL);

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Launching Intelligence", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i=new Intent(Home.this,assistant_ui_activity.class);
                startActivity(i);



            }
        });
      String accountname=GetAccountsName();

        accountname=accountname.split("@")[0];
        Toast.makeText(getApplicationContext(),"Welcome "+accountname,Toast.LENGTH_LONG).show();
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        TextView infos= findViewById(R.id.textView15);




        infos.setText("Brand: "+Build.BRAND.toUpperCase()+"\n\n"+"CPU ABI :"+Build.CPU_ABI.toUpperCase() +"\n\n"+"CPU ABI2 :"+Build.CPU_ABI2.toUpperCase()+"\n\n"+"DEVICE MODEL :"+Build.DEVICE.toUpperCase()+"\n\n"+"DISPLAY INFO. :"+Build.DISPLAY.toUpperCase()+"\n\n"+"FINGERPRINT SUPPORT NUMBER :"+Build.FINGERPRINT.toUpperCase()+"\n\n"+"HARDWARE NUMBER :"+Build.HARDWARE.toUpperCase()+"\n\n"+"HOST : "+ Build.HOST.toUpperCase() +"\n\n"+"BUILD ID: "+Build.ID.toUpperCase()+"\n\n"+"MANUFACTURER : "+Build.MANUFACTURER.toUpperCase()+"\n\n"+"PRODUCT : "+Build.PRODUCT.toUpperCase()+"\n\n"+"BUILD TYPE :"+Build.TYPE.toUpperCase()+"\n\n"+"USER :"+Build.USER.toUpperCase()+"\n\n" );



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent(this,RecognitionUI.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.about) {
            Intent intent=new Intent(this,Main6Activity.class);
            startActivity(intent);
        } else if (id == R.id.app_list) {
            Intent intent=new Intent(this,Main4Activity.class);
            startActivity(intent);

        } else if (id == R.id.Intelligence) {
            Intent intent=new Intent(this,assistant_ui_activity.class);
            startActivity(intent);

        }
        else if (id==R.id.textpasswordmenu) {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);

        }
        else if (id==R.id.speechpasswordmenu) {
            Intent intent=new Intent(this,Main3Activity.class);
            startActivity(intent);

        }
        else if(id==R.id.commands)
        {
            Intent intent=new Intent(this,CommandChangingActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.launcher)
        {
            Intent intent=new Intent(this,Log_Activity_Activity_Recognition.class);
            startActivity(intent);
        }


        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public String GetAccountsName(){

        try {
            account = AccountManager.get(Home.this).getAccounts();
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        String accounts="User";

        for (Account TempAccount : account) {

            if (pattern.matcher(TempAccount.name).matches()) {

               accounts=TempAccount.name ;
                break;
            }
        }
        return accounts;
    }
}
