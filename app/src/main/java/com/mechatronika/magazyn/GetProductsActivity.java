package com.mechatronika.magazyn;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;





public class GetProductsActivity extends AppCompatActivity {


    /*

    A - stanowisko 1x1
    B - stanowisko 1x2
    C - stanowisko 1x3

    D - stanowisko 1x2
    E - stanowisko 2x2
    F - stanowisko 2x3

    G - stanowisko 3x1
    H - stanowisko 3x2
    I - stanowisko 3x3

    J - stanowisko 4x1
    K - stanowisko 4x2
    L - stanowisko 4x3


    k - kalibrowanie


     */



    String address = null , name=null;

    public static final String TAG = "BLUETOOTH CONECTION: ";

    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    Set<BluetoothDevice> pairedDevices;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    //max value of products
    public final int MAX_PRODUCTS = 2;

    //check if can change products amount
    public boolean canChange;

    //get actionbar
    ActionBar actionBar;

    //create products with value 0
    public Product[][] product = new Product[4][3];



    //BUTTONS
    Button btn_1x1_minus;
    Button btn_1x1_plus;
    Button btn_1x2_minus;
    Button btn_1x2_plus;
    Button btn_1x3_minus;
    Button btn_1x3_plus;

    Button btn_2x1_minus;
    Button btn_2x1_plus;
    Button btn_2x2_minus;
    Button btn_2x2_plus;
    Button btn_2x3_minus;
    Button btn_2x3_plus;

    Button btn_3x1_minus;
    Button btn_3x1_plus;
    Button btn_3x2_minus;
    Button btn_3x2_plus;
    Button btn_3x3_minus;
    Button btn_3x3_plus;

    Button btn_4x1_minus;
    Button btn_4x1_plus;
    Button btn_4x2_minus;
    Button btn_4x2_plus;
    Button btn_4x3_minus;
    Button btn_4x3_plus;

    //Button start

    Button btn_start_getting_products;

    //TEXTVIEWS

    TextView textView_1x1;
    TextView textView_1x2;
    TextView textView_1x3;

    TextView textView_2x1;
    TextView textView_2x2;
    TextView textView_2x3;

    TextView textView_3x1;
    TextView textView_3x2;
    TextView textView_3x3;

    TextView textView_4x1;
    TextView textView_4x2;
    TextView textView_4x3;


    //TEXTVIEWS(Positions)

    TextView textViewPos_1x1;
    TextView textViewPos_1x2;
    TextView textViewPos_1x3;

    TextView textViewPos_2x1;
    TextView textViewPos_2x2;
    TextView textViewPos_2x3;

    TextView textViewPos_3x1;
    TextView textViewPos_3x2;
    TextView textViewPos_3x3;

    TextView textViewPos_4x1;
    TextView textViewPos_4x2;
    TextView textViewPos_4x3;


    //poprawione
    public TextView[][] textViews = new TextView[4][3];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_products);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        actionBar = getSupportActionBar();
        //set new tittle
        actionBar.setTitle(R.string.title_activity_get_products);

        //canChange= true (default)
        canChange = true;

        //create products with value 0
        for(int i = 0; i<4;i++)
        {
            for(int j = 0; j<3; j++)
            {
                product[i][j] = new Product();
            }


        }




        //TEXTVIEWS
        textView_1x1 = (TextView) findViewById(R.id.amount_1x1);
        textView_1x2 = (TextView) findViewById(R.id.amount_1x2);
        textView_1x3 = (TextView) findViewById(R.id.amount_1x3);

        textView_2x1 = (TextView) findViewById(R.id.amount_2x1);
        textView_2x2 = (TextView) findViewById(R.id.amount_2x2);
        textView_2x3 = (TextView) findViewById(R.id.amount_2x3);

        textView_3x1 = (TextView) findViewById(R.id.amount_3x1);
        textView_3x2 = (TextView) findViewById(R.id.amount_3x2);
        textView_3x3 = (TextView) findViewById(R.id.amount_3x3);

        textView_4x1 = (TextView) findViewById(R.id.amount_4x1);
        textView_4x2 = (TextView) findViewById(R.id.amount_4x2);
        textView_4x3 = (TextView) findViewById(R.id.amount_4x3);

        //macierz textView


        textViews[0][0] = textView_1x1;
        textViews[0][1] = textView_1x2;
        textViews[0][2] = textView_1x3;

        textViews[1][0] = textView_2x1;
        textViews[1][1] = textView_2x2;
        textViews[1][2] = textView_2x3;

        textViews[2][0] = textView_3x1;
        textViews[2][1] = textView_3x2;
        textViews[2][2] = textView_3x3;

        textViews[3][0] = textView_4x1;
        textViews[3][1] = textView_4x2;
        textViews[3][2] = textView_4x3;


        //TEXTVIEWS(Positions)

         textViewPos_1x1 = (TextView) findViewById(R.id.textView_product_1x1);
         textViewPos_1x2 = (TextView) findViewById(R.id.textView_product_1x2);
        textViewPos_1x3 = (TextView) findViewById(R.id.textView_product_1x3);

        textViewPos_2x1 = (TextView) findViewById(R.id.textView_product_2x1);
        textViewPos_2x2 = (TextView) findViewById(R.id.textView_product_2x2);
        textViewPos_2x3 = (TextView) findViewById(R.id.textView_product_2x3);

        textViewPos_3x1 = (TextView) findViewById(R.id.textView_product_3x1);
        textViewPos_3x2 = (TextView) findViewById(R.id.textView_product_3x2);
        textViewPos_3x3 = (TextView) findViewById(R.id.textView_product_3x3);

        textViewPos_4x1 = (TextView) findViewById(R.id.textView_product_4x1);
        textViewPos_4x2 = (TextView) findViewById(R.id.textView_product_4x2);
        textViewPos_4x3 = (TextView) findViewById(R.id.textView_product_4x3);


        //BUTTONS
        btn_1x1_minus = (Button) findViewById(R.id.btn_minus_1x1);
        btn_1x1_plus = (Button) findViewById(R.id.btn_plus_1x1);
        btn_1x2_minus = (Button) findViewById(R.id.btn_minus_1x2);
        btn_1x2_plus = (Button) findViewById(R.id.btn_plus_1x2);
        btn_1x3_minus = (Button) findViewById(R.id.btn_minus_1x3);
        btn_1x3_plus = (Button) findViewById(R.id.btn_plus_1x3);

        btn_2x1_minus = (Button) findViewById(R.id.btn_minus_2x1);
        btn_2x1_plus = (Button) findViewById(R.id.btn_plus_2x1);
        btn_2x2_minus = (Button) findViewById(R.id.btn_minus_2x2);
        btn_2x2_plus = (Button) findViewById(R.id.btn_plus_2x2);
        btn_2x3_minus = (Button) findViewById(R.id.btn_minus_2x3);
        btn_2x3_plus = (Button) findViewById(R.id.btn_plus_2x3);

        btn_3x1_minus = (Button) findViewById(R.id.btn_minus_3x1);
        btn_3x1_plus = (Button) findViewById(R.id.btn_plus_3x1);
        btn_3x2_minus = (Button) findViewById(R.id.btn_minus_3x2);
        btn_3x2_plus = (Button) findViewById(R.id.btn_plus_3x2);
        btn_3x3_minus = (Button) findViewById(R.id.btn_minus_3x3);
        btn_3x3_plus = (Button) findViewById(R.id.btn_plus_3x3);

        btn_4x1_minus = (Button) findViewById(R.id.btn_minus_4x1);
        btn_4x1_plus = (Button) findViewById(R.id.btn_plus_4x1);
        btn_4x2_minus = (Button) findViewById(R.id.btn_minus_4x2);
        btn_4x2_plus = (Button) findViewById(R.id.btn_plus_4x2);
        btn_4x3_minus = (Button) findViewById(R.id.btn_minus_4x3);
        btn_4x3_plus = (Button) findViewById(R.id.btn_plus_4x3);


        //POPRAWIONE
        //[][][] = [pierwsza_wspolrzedna][druga_wspolrzedna][znak]
        Button[][][] buttons = new Button[4][3][2];

        buttons[0][0][0] = (Button) findViewById(R.id.btn_minus_1x1);
        buttons[0][0][1] = (Button) findViewById(R.id.btn_plus_1x1);
        buttons[0][1][0] = (Button) findViewById(R.id.btn_minus_1x2);
        buttons[0][1][1] = (Button) findViewById(R.id.btn_plus_1x2);
        buttons[0][2][0] = (Button) findViewById(R.id.btn_minus_1x3);
        buttons[0][2][1] = (Button) findViewById(R.id.btn_plus_1x3);

        buttons[1][0][0] = (Button) findViewById(R.id.btn_minus_2x1);
        buttons[1][0][1] = (Button) findViewById(R.id.btn_plus_2x1);
        buttons[1][1][0] = (Button) findViewById(R.id.btn_minus_2x2);
        buttons[1][1][1] = (Button) findViewById(R.id.btn_plus_2x2);
        buttons[1][2][0] = (Button) findViewById(R.id.btn_minus_2x3);
        buttons[1][2][1] = (Button) findViewById(R.id.btn_plus_2x3);

        buttons[2][0][0] = (Button) findViewById(R.id.btn_minus_3x1);
        buttons[2][0][1] = (Button) findViewById(R.id.btn_plus_3x1);
        buttons[2][1][0] = (Button) findViewById(R.id.btn_minus_3x2);
        buttons[2][1][1] = (Button) findViewById(R.id.btn_plus_3x2);
        buttons[2][2][0] = (Button) findViewById(R.id.btn_minus_3x3);
        buttons[2][2][1] = (Button) findViewById(R.id.btn_plus_3x3);

        buttons[3][0][0] = (Button) findViewById(R.id.btn_minus_4x1);
        buttons[3][0][1] = (Button) findViewById(R.id.btn_plus_4x1);
        buttons[3][1][0] = (Button) findViewById(R.id.btn_minus_4x2);
        buttons[3][1][1] = (Button) findViewById(R.id.btn_plus_4x2);
        buttons[3][2][0] = (Button) findViewById(R.id.btn_minus_4x3);
        buttons[3][2][1] = (Button) findViewById(R.id.btn_plus_4x3);



        //button start
        btn_start_getting_products = (Button) findViewById(R.id.btn_start);




        showNumberOfProducts();

        //Button start

        btn_start_getting_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(canChange) {
                        Toast.makeText(getApplicationContext(), "Rozpoczynanie pobierania. Brak możliwości edycji produktów.", Toast.LENGTH_SHORT).show();
                        sendMessage("k" + CreateDataString());
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Brak możliwości edycji. Wróć do Menu", Toast.LENGTH_SHORT).show();
                    }
                    canChange = false;

            }
        });



        for(int i=0; i<4;i++)
        {
            for(int j = 0; j<3;j++)
            {
                for(int k = 0; k<2;k++)
                {
                    setupListener(buttons[i][j][k],i,j,k, canChange);
                }
            }
        }




        bluetooth();

    }



    // 0 - minus
    // 1 - plus

    private void setupListener(Button button, final int i, final int j, final int k, final boolean canChange) {
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(canChange)
                {

                    if(k==0)
                    {
                        if(product[i][j].getProduct() > 0)
                        {

                            product[i][j].setProduct(product[i][j].getProduct() - 1);
                            textViews[i][j].setText(Integer.toString(product[i][j].getProduct()));
                            Toast.makeText(getApplicationContext(), "Odjeto element od: " + "Stanowisko: "+(i+1)+"X"+(j+1), Toast.LENGTH_SHORT).show();

                        }
                        else
                        {

                            Toast.makeText(getApplicationContext(), "Stanowisko: "+(i+1)+"X"+(j+1) + " nie moze byc mniejsze od 0!", Toast.LENGTH_SHORT).show();

                        }

                    }

                    if(k==1)
                    {

                        if(product[i][j].getProduct() < MAX_PRODUCTS)
                        {
                            product[i][j].setProduct(product[i][j].getProduct() + 1);
                            textViews[i][j].setText(Integer.toString(product[i][j].getProduct()));
                            Toast.makeText(getApplicationContext(), "Dodano element do: " + "Stanowisko: "+(i+1)+"X"+(j+1), Toast.LENGTH_SHORT).show();

                        }
                        else
                        {

                            Toast.makeText(getApplicationContext(), "Stanowisko: "+(i+1)+"X"+(j+1) + " nie może byc większe od "+MAX_PRODUCTS, Toast.LENGTH_SHORT).show();

                        }
                    }


                }


                else
                {
                    Toast.makeText(getApplicationContext(), "Brak mozliwosci edycji. Cofnij do menu i sprobuj ponownie.", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }



    //create a string with informations about products which will be send to the RoboCore
    private String CreateDataString()
    {
        String temporary = "";

        // 1x1
        if(product[0][0].getProduct() == 0)
        {
            temporary = temporary + "0A";
        }
        if (product[0][0].getProduct() == 1)
        {
            temporary = temporary + "1A";
        }
        if (product[0][0].getProduct() == 2)
        {
            temporary = temporary + "2A";
        }

        // 1x2
        if(product[0][1].getProduct() == 0)
        {
            temporary = temporary + "0B";
        }
        if (product[0][1].getProduct() == 1)
        {
            temporary = temporary + "1B";
        }
        if (product[0][1].getProduct() == 2)
        {
            temporary = temporary + "2B";
        }

        // 1x3
        if(product[0][2].getProduct() == 0)
        {
            temporary = temporary + "0C";
        }
        if (product[0][2].getProduct() == 1)
        {
            temporary = temporary + "1C";
        }
        if (product[0][2].getProduct() == 2)
        {
            temporary = temporary + "2C";
        }


        // 2x1
        if(product[1][0].getProduct() == 0)
        {
            temporary = temporary + "0D";
        }
        if (product[1][0].getProduct() == 1)
        {
            temporary = temporary + "1D";
        }
        if (product[1][0].getProduct() == 2)
        {
            temporary = temporary + "2D";
        }

        // 2x2
        if(product[1][1].getProduct() == 0)
        {
            temporary = temporary + "0E";
        }
        if (product[1][1].getProduct() == 1)
        {
            temporary = temporary + "1E";
        }
        if (product[1][1].getProduct() == 2)
        {
            temporary = temporary + "2E";
        }

        // 2x3
        if(product[1][2].getProduct() == 0)
        {
            temporary = temporary + "0F";
        }
        if (product[1][2].getProduct() == 1)
        {
            temporary = temporary + "1F";
        }
        if (product[1][2].getProduct() == 2)
        {
            temporary = temporary + "2F";
        }


        // 3x1
        if(product[2][0].getProduct() == 0)
        {
            temporary = temporary + "0G";
        }
        if (product[2][0].getProduct() == 1)
        {
            temporary = temporary + "1G";
        }
        if (product[2][0].getProduct() == 2)
        {
            temporary = temporary + "2G";
        }

        // 3x2
        if(product[2][1].getProduct() == 0)
        {
            temporary = temporary + "0H";
        }
        if (product[2][1].getProduct() == 1)
        {
            temporary = temporary + "1H";
        }
        if (product[2][1].getProduct() == 2)
        {
            temporary = temporary + "2H";
        }

        // 3x3
        if(product[2][2].getProduct() == 0)
        {
            temporary = temporary + "0I";
        }
        if (product[2][2].getProduct() == 1)
        {
            temporary = temporary + "1I";
        }
        if (product[2][2].getProduct() == 2)
        {
            temporary = temporary + "2I";
        }

        // 4x1
        if(product[3][0].getProduct() == 0)
        {
            temporary = temporary + "0J";
        }
        if (product[3][0].getProduct() == 1)
        {
            temporary = temporary + "1J";
        }
        if (product[3][0].getProduct() == 2)
        {
            temporary = temporary + "2J";
        }


        // 4x2
        if(product[3][1].getProduct() == 0)
        {
            temporary = temporary + "0K";
        }
        if (product[3][1].getProduct() == 1)
        {
            temporary = temporary + "1K";
        }
        if (product[3][1].getProduct() == 2)
        {
            temporary = temporary + "2K";
        }


        // 4x3
        if(product[3][2].getProduct() == 0)
        {
            temporary = temporary + "0L";
        }
        if (product[3][2].getProduct() == 1)
        {
            temporary = temporary + "1L";
        }
        if (product[3][2].getProduct() == 2)
        {
            temporary = temporary + "2L";
        }




        return temporary;
    }


    private void showNumberOfProducts()
    {

        textView_1x1.setText(Integer.toString(product[0][0].getProduct()));
        textView_1x2.setText(Integer.toString(product[0][1].getProduct()));
        textView_1x3.setText(Integer.toString(product[0][2].getProduct()));

        textView_2x1.setText(Integer.toString(product[1][0].getProduct()));
        textView_2x2.setText(Integer.toString(product[1][1].getProduct()));
        textView_2x3.setText(Integer.toString(product[1][2].getProduct()));

        textView_3x1.setText(Integer.toString(product[2][0].getProduct()));
        textView_3x2.setText(Integer.toString(product[2][1].getProduct()));
        textView_3x3.setText(Integer.toString(product[2][2].getProduct()));

        textView_4x1.setText(Integer.toString(product[3][0].getProduct()));
        textView_4x2.setText(Integer.toString(product[3][1].getProduct()));
        textView_4x3.setText(Integer.toString(product[3][2].getProduct()));

    }

    //communication via bluetooth
    private void bluetooth()
    {

        //search bluetooth devices button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Szukaj urządzeń bluetooth", Snackbar.LENGTH_LONG)
                        .setAction("SZUKAJ", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                try {
                                    bluetooth_connect_device();
                                }
                                catch (Exception e)
                                {

                                }
                            }
                        }).show();
            }
        });





        //set bt memory
        try {
            bluetooth_connect_device_start();
        } catch (Exception e) {

        }

    }

    //set bt memory
    private void bluetooth_connect_device_start() throws IOException
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
        btSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(myUUID);//create  connection

                try {


                }

                    catch (Exception e)
                {

                }

    }

    //connect with BT device
    private void bluetooth_connect_device() throws IOException
    {
        try
        {

            myBluetooth = BluetoothAdapter.getDefaultAdapter();
            //get bt device addres
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
        BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
        btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection



        final AlertDialog.Builder builder = new AlertDialog.Builder(GetProductsActivity.this);
        builder.setMessage("Nazwa: "+name +"\n" + "Adres: " + address+ "\n" + "Nawiązać połączenie?");
        builder.setCancelable(true);
        builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                try {
                    btSocket.connect();
                    Toast.makeText(getApplicationContext(),"Połączono z: "+name, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
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


            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

}