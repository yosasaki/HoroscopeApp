package com.example.yosasaki.horoscopeapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private Button mButton1, mButton2, mButton3, mButton4, mButton5, mButton6, mButton7, mButton8, mButton9, mButton10, mButton11, mButton12;
    static int selectHoro;
    static Date dateHoro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("星座占い");
        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(mOnClickListener);

        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(mOnClickListener);

        mButton3 = (Button) findViewById(R.id.button3);
        mButton3.setOnClickListener(mOnClickListener);

        mButton4 = (Button) findViewById(R.id.button4);
        mButton4.setOnClickListener(mOnClickListener);

        mButton5 = (Button) findViewById(R.id.button5);
        mButton5.setOnClickListener(mOnClickListener);

        mButton6 = (Button) findViewById(R.id.button6);
        mButton6.setOnClickListener(mOnClickListener);

        mButton7 = (Button) findViewById(R.id.button7);
        mButton7.setOnClickListener(mOnClickListener);

        mButton8 = (Button) findViewById(R.id.button8);
        mButton8.setOnClickListener(mOnClickListener);

        mButton9 = (Button) findViewById(R.id.button9);
        mButton9.setOnClickListener(mOnClickListener);

        mButton10 = (Button) findViewById(R.id.button10);
        mButton10.setOnClickListener(mOnClickListener);

        mButton11 = (Button) findViewById(R.id.button11);
        mButton11.setOnClickListener(mOnClickListener);

        mButton12 = (Button) findViewById(R.id.button12);
        mButton12.setOnClickListener(mOnClickListener);

        TextView dateText = (TextView)findViewById(R.id.date);
        // 現在の時刻を取得
        dateHoro = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy'年'MM'月'dd'日'");

        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
        dateText.setText(sdf.format(dateHoro));
    }

    @Override
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

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mButton1) {
                selectHoro = 0;
            } else if(v == mButton2) {
                selectHoro = 1;
            } else if(v == mButton3) {
                selectHoro = 2;
            } else if(v == mButton4) {
                selectHoro = 3;
            } else if(v == mButton5) {
                selectHoro = 4;
            } else if(v == mButton6) {
                selectHoro = 5;
            } else if(v == mButton7) {
                selectHoro = 6;
            } else if(v == mButton8) {
                selectHoro = 7;
            } else if(v == mButton9) {
                selectHoro = 8;
            } else if(v == mButton10) {
                selectHoro = 9;
            } else if(v == mButton11) {
                selectHoro = 10;
            } else if(v == mButton12) {
                selectHoro = 11;
            }
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), IntentServiceHttp.class);
            startService(intent);
        }

    };
}