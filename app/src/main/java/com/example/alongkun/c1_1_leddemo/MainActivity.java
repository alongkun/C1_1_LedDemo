package com.example.alongkun.c1_1_leddemo;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import android.os.ILedService;
import android.os.ServiceManager;

public class MainActivity extends AppCompatActivity {

    private boolean ledSta = false;

    private Button button_1 = null;
    private CheckBox checkBox_1 = null;
    private CheckBox checkBox_2 = null;
    private CheckBox checkBox_3 = null;
    private CheckBox checkBox_4 = null;

    private ILedService iLedService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        button_1 = (Button) findViewById(R.id.button_1);
        checkBox_1 = (CheckBox) findViewById(R.id.checkbox_1);
        checkBox_2 = (CheckBox) findViewById(R.id.checkbox_2);
        checkBox_3 = (CheckBox) findViewById(R.id.checkbox_3);
        checkBox_4 = (CheckBox) findViewById(R.id.checkbox_4);

        button_1.setOnClickListener(buttonClickListener);

        iLedService = ILedService.Stub.asInterface(ServiceManager.getService("led"));

    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case  R.id.button_1:
                    if (ledSta) {
                        ledSta = false;
                        button_1.setText("ALL LED OFF");
                        checkBox_1.setChecked(false);
                        checkBox_2.setChecked(false);
                        checkBox_3.setChecked(false);
                        checkBox_4.setChecked(false);

                        for(int i = 0; i < 4; i++) {
                            try {
                                iLedService.ledCtrl(i, 0);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    else {
                        ledSta = true;
                        button_1.setText("ALL LED ON");
                        checkBox_1.setChecked(true);
                        checkBox_2.setChecked(true);
                        checkBox_3.setChecked(true);
                        checkBox_4.setChecked(true);

                        for(int i = 0; i < 4; i++) {
                            try {
                                iLedService.ledCtrl(i, 1);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
            }
        }
    };

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox)view).isChecked();

        try {
            switch (view.getId()) {
                case R.id.checkbox_1:
                    if (checked)
                        iLedService.ledCtrl(0, 1);
                    else
                        iLedService.ledCtrl(0, 0);
                    break;
                case R.id.checkbox_2:
                    if (checked)
                        iLedService.ledCtrl(1, 1);
                    else
                        iLedService.ledCtrl(1, 0);
                    break;
                case R.id.checkbox_3:
                    if (checked)
                        iLedService.ledCtrl(2, 1);
                    else
                        iLedService.ledCtrl(2, 0);
                    break;
                case R.id.checkbox_4:
                    if (checked)
                        iLedService.ledCtrl(3, 1);
                    else
                        iLedService.ledCtrl(3, 0);
                    break;
                default:
                    break;
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
