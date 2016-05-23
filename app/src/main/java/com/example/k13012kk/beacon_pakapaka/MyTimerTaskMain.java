package com.example.k13012kk.beacon_pakapaka;

import android.content.Context;
import android.os.Handler;
import java.util.TimerTask;

/**
 * Created by k13012kk on 16/05/23.
 */
public class MyTimerTaskMain extends TimerTask{
    private Handler handler;
    private Context context;
    public MyTimerTaskMain(Context context) {
        handler = new Handler();
        this.context = context;
    }

    @Override
    public void run() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                ((MainActivity)context).beacon_print();
            }
        });
    }
}
