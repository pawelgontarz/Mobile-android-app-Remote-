package com.mechatronika.magazyn;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //get actionbar
    ActionBar actionBar;

    //buttons
   public  Button btn_set_products;
   public  Button btn_get_products;
   public  Button btn_manual_control;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        //set new tittle
        actionBar.setTitle(R.string.main_menu);
        //get buttons
        btn_get_products = (Button) findViewById(R.id.get_products);
        btn_manual_control = (Button) findViewById(R.id.manual_control);


        //manual_button listener
        btn_manual_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_manual = new Intent(MainActivity.this, ManualControl.class);
                startActivity(intent_manual);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();

            }
        });

        //get_products_button listener
        btn_get_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_manual = new Intent(MainActivity.this, GetProductsActivity.class);
                startActivity(intent_manual);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();

            }
        });



    }


    //override hardware back button to exit from app
    public void onBackPressed() {

        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

}



