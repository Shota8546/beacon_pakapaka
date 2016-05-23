package com.example.k13012kk.beacon_pakapaka;

import java.util.TimerTask;
import android.os.Handler;
import android.content.Context;

/**
 * Created by k13012kk on 16/05/23.
 */
public class MyTimerTask extends TimerTask{
    private Handler handler;
    private Context context;
    public MyTimerTask(Context context) {
        handler = new Handler();
        this.context = context;
    }

    @Override
    public void run() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                ((BeaconApplication)context).pakapakaCheck_All();
            }
        });
    }
}
