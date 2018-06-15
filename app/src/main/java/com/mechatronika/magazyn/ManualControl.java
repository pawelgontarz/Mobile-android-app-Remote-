package com.mechatronika.magazyn;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

/*

    a - ruch prowadnicy poziomej w lewo
    b - ruch prowadnicy poziomej w prawo

    c - ruch prowadnicy pionowej w górę
    d - ruch prowadnicy poziomej w dół

    e - ruch pobieraka do przodu
    f - ruch pobieraka do tyłu

    x - zatrzymanie silników

    g - moc silnika 200
    h - moc silnika 400
    i - moc silnika 600
    j - moc silnika 800
 */

public class ManualControl extends AppCompatActivity {

    //get actionbar
    ActionBar actionBar;
    //buttons
    ImageButton btn_left_X;
    ImageButton btn_right_X;
    ImageButton btn_up_Y;
    ImageButton btn_down_Y;
    ImageButton btn_up_Z;
    ImageButton btn_down_Z;
    Button power1;
    Button power2;
    Button power3;
    Button power4;



    String address = null , name=null;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    Set<BluetoothDevice> pairedDevices;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_control);

        actionBar = getSupportActionBar();
        //set new tittle
        actionBar.setTitle(R.string.manual_control);

        //get buttons
        btn_left_X = (ImageButton) findViewById(R.id.button_left_X);
        btn_right_X = (ImageButton) findViewById(R.id.button_right_X);
        btn_up_Y = (ImageButton) findViewById(R.id.button_up_Y);
        btn_down_Y = (ImageButton) findViewById(R.id.button_down_Y);
        btn_up_Z = (ImageButton) findViewById(R.id.button_up_Z);
        btn_down_Z = (ImageButton) findViewById(R.id.button_down_Z);
        power1 = (Button) findViewById(R.id.button1);
        power2 = (Button) findViewById(R.id.button2);
        power3 = (Button) findViewById(R.id.button3);
        power4 = (Button) findViewById(R.id.button4);




        try {

            bluetoothAction();
        }
        catch (Exception e) {

        }

    }



    @SuppressLint("ClickableViewAccessibility")
    private void bluetoothAction() throws IOException
    {

        bluetooth_connect_device();

    //LISTENERS

        power1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage("g");
                power1.setTextSize(22);
                power1.setTextColor(Color.WHITE);
                power2.setTextSize(18);
                power2.setTextColor(getResources().getColor(R.color.lightBlue));
                power3.setTextSize(18);
                power3.setTextColor(getResources().getColor(R.color.lightBlue));
                power4.setTextSize(18);
                power4.setTextColor(getResources().getColor(R.color.lightBlue));


            }
        });

        power2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage("h");
                power2.setTextSize(22);
                power2.setTextColor(Color.WHITE);
                power1.setTextSize(18);
                power1.setTextColor(getResources().getColor(R.color.lightBlue));
                power3.setTextSize(18);
                power3.setTextColor(getResources().getColor(R.color.lightBlue));
                power4.setTextSize(18);
                power4.setTextColor(getResources().getColor(R.color.lightBlue));


            }
        });

        power3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage("i");
                power3.setTextSize(22);
                power3.setTextColor(Color.WHITE);
                power1.setTextSize(18);
                power1.setTextColor(getResources().getColor(R.color.lightBlue));
                power2.setTextSize(18);
                power2.setTextColor(getResources().getColor(R.color.lightBlue));
                power4.setTextSize(18);
                power4.setTextColor(getResources().getColor(R.color.lightBlue));

            }
        });

        power4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage("j");
                power4.setTextSize(22);
                power4.setTextColor(Color.WHITE);
                power1.setTextSize(18);
                power1.setTextColor(getResources().getColor(R.color.lightBlue));
                power2.setTextSize(18);
                power2.setTextColor(getResources().getColor(R.color.lightBlue));
                power3.setTextSize(18);
                power3.setTextColor(getResources().getColor(R.color.lightBlue));

            }
        });


        btn_left_X.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        sendMessage("a");

                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        sendMessage("x");
                        break;
                }
                return false;
            }
        });



        btn_right_X.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        sendMessage("b");

                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        sendMessage("x");
                        break;
                }
                return false;
            }
        });



        btn_up_Y.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        sendMessage("c");

                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        sendMessage("x");
                        break;
                }
                return false;
            }
        });



        btn_down_Y.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        sendMessage("d");

                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        sendMessage("x");
                        break;
                }
                return false;
            }
        });



        btn_up_Z.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        sendMessage("e");



                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        sendMessage("x");
                        break;
                }
                return false;
            }
        });



        btn_down_Z.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        sendMessage("f");

                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        sendMessage("x");
                        break;
                }
                return false;
            }
        });


    }


    //connect with BT device
    private void bluetooth_connect_device() throws IOException
    {
        try
        {
            myBluetooth = BluetoothAdapter.getDefaultAdapter();
            address = myBluetooth.getAddress();
            pairedDevices = myBluetooth.getBondedDevices();
            if (pairedDevices.size()>0)
            {
                for(BluetoothDevice bt : pairedDevices)
                {

                    address=bt.getAddress().toString();
                    name = bt.getName().toString();
                    //Toast.makeText(getApplicationContext(),"Searched BT device: " + name+", Address: "+address, Toast.LENGTH_LONG).show();

                }
            }

            else
            {

            }

        }
        catch(Exception e){}
        myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
        BluetoothDevice bluetoothDevice = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
        btSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection



        final AlertDialog.Builder builder = new AlertDialog.Builder(ManualControl.this);
        builder.setMessage("Nazwa: "+name +"\n" + "Adres: " + address+ "\n" + "Nawiązać połączenie?");
        builder.setCancelable(true);
        builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Nie wybrano połączenia z: "+name, Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {




                try {

                        btSocket.connect();
                        Toast.makeText(getApplicationContext(), "Połączono z: " + name, Toast.LENGTH_SHORT).show();

                } catch (Exception e) {

                }

                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Znaleziono urządzenie Bluetooth!");
        alertDialog.show();


    }

    //sending message to BT device
    private void sendMessage(String msg)
    {
        try
          {
            if (btSocket!=null)
               {

                   btSocket.getOutputStream().write(msg.toString().getBytes());
               }

          }
         catch (Exception e)
          {
             Toast.makeText(getApplicationContext(),"Nie połączono z urządzeniem BT ", Toast.LENGTH_SHORT).show();

          }

}


    //Override hardware back button to go to main menu app
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            Intent nextFlow = new Intent(this, MainActivity.class);
            startActivity(nextFlow);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

            try {

                if(btSocket.isConnected() == true)
                {
                    Toast.makeText(getApplicationContext(),"Rozłączono od: " + name, Toast.LENGTH_LONG).show();
                }
                else
                {

                }

                btSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

}
