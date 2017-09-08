package com.example.alongkun.c1_1_leddemo;

import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    private boolean ledSta = false;

    private Button button_1 = null;
    private CheckBox checkBox_1 = null;
    private CheckBox checkBox_2 = null;
    private CheckBox checkBox_3 = null;
    private CheckBox checkBox_4 = null;

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
                    }
                    else {
                        ledSta = true;
                        button_1.setText("ALL LED ON");
                        checkBox_1.setChecked(true);
                        checkBox_2.setChecked(true);
                        checkBox_3.setChecked(true);
                        checkBox_4.setChecked(true);
                    }
                    break;
            }
        }
    };

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox)view).isChecked();

        switch (view.getId()) {
            case R.id.checkbox_1:
                if (checked)
                    Toast.makeText(getApplicationContext(), "LED 1 ON", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "LED 1 OFF", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_2:
                break;
            case R.id.checkbox_3:
                break;
            case R.id.checkbox_4:
                break;
            default:
                break;
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
