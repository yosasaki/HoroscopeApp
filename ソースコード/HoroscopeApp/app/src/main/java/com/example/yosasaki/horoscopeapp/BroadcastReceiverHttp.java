package com.example.yosasaki.horoscopeapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class BroadcastReceiverHttp extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String str = "HTTP_ACTION";
            Bundle status = intent.getExtras();
            String action = intent.getAction();
            if(status.getInt("status") == 0){
                if(str.equals(action)) {
                    Toast.makeText(context, "占い情報取得完了", Toast.LENGTH_LONG).show();
                    intent.setClass(context, MainActivity2Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else {
                    Toast.makeText(context, "占い情報取得失敗", Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(context, "占い情報取得失敗", Toast.LENGTH_LONG).show();
            }
        }
    }
