package com.example.k13012kk.beacon_pakapaka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(MainActivity.this, BeaconApplication.class));

        Timer timer = new Timer();
        TimerTask timertask = new MyTimerTaskMain(this);
        timer.scheduleAtFixedRate(timertask, 1000, 100);
    }

    public void OnClick(View view) {
        BeaconApplication.identifier=0;
        BeaconApplication.beaconmap.clear();
        BeaconApplication.lpmap.clear();
        BeaconApplication.map.clear();

        DataHolder holder = DataHolderParameter.getInstance();
        DataHolder holder1 = DataHolderParameter1.getInstance();
        DataHolder holder2 = DataHolderParameter2.getInstance();
        DataHolder holder3 = DataHolderParameter3.getInstance();
        DataHolder holder4 = DataHolderParameter4.getInstance();
        DataHolder holder5 = DataHolderParameter5.getInstance();
        DataHolder holder6 = DataHolderParameter6.getInstance();
        DataHolder holder7 = DataHolderParameter7.getInstance();
        DataHolder holder8 = DataHolderParameter8.getInstance();
        DataHolder holder9 = DataHolderParameter9.getInstance();
        holder.setTestString(" ");
        holder.setRssi(" ");
        holder.setColor(0xff000000);
        holder1.setTestString(" ");
        holder1.setRssi(" ");
        holder1.setColor(0xff000000);
        holder2.setTestString(" ");
        holder2.setRssi(" ");
        holder2.setColor(0xff000000);
        holder3.setTestString(" ");
        holder3.setRssi(" ");
        holder3.setColor(0xff000000);
        holder4.setTestString(" ");
        holder4.setRssi(" ");
        holder4.setColor(0xff000000);
        holder5.setTestString(" ");
        holder5.setRssi(" ");
        holder5.setColor(0xff000000);
        holder6.setTestString(" ");
        holder6.setRssi(" ");
        holder6.setColor(0xff000000);
        holder7.setTestString(" ");
        holder7.setRssi(" ");
        holder7.setColor(0xff000000);
        holder8.setTestString(" ");
        holder8.setRssi(" ");
        holder8.setColor(0xff000000);
        holder9.setTestString(" ");
        holder9.setRssi(" ");
        holder9.setColor(0xff000000);
    }

    public void beacon_print() {

        DataHolder holder_parameter = DataHolderParameter.getInstance();
        TextView tv = (TextView) findViewById(R.id.beaonparameter1);//テスト用
        tv.setText(holder_parameter.getTestString());//テスト用
        tv.setTextColor(holder_parameter.getColorString());
        TextView tv_1 = (TextView) findViewById(R.id.rssi);
        tv_1.setText(holder_parameter.getRssi());
        tv_1.setTextColor(holder_parameter.getColorString());

        DataHolder holder_parameter1 = DataHolderParameter1.getInstance();
        TextView tv1 = (TextView) findViewById(R.id.beaonparameter2);//テスト用
        tv1.setText(holder_parameter1.getTestString());//テスト用
        tv1.setTextColor(holder_parameter1.getColorString());
        TextView tv1_1 = (TextView) findViewById(R.id.rssi1);
        tv1_1.setText(holder_parameter1.getRssi());
        tv1_1.setTextColor(holder_parameter1.getColorString());

        DataHolder holder_parameter2 = DataHolderParameter2.getInstance();
        TextView tv2 = (TextView) findViewById(R.id.beaonparameter3);//テスト用
        tv2.setText(holder_parameter2.getTestString());//テスト用
        tv2.setTextColor(holder_parameter2.getColorString());
        TextView tv2_1 = (TextView) findViewById(R.id.rssi2);
        tv2_1.setText(holder_parameter2.getRssi());
        tv2_1.setTextColor(holder_parameter2.getColorString());

        DataHolder holder_parameter3 = DataHolderParameter3.getInstance();
        TextView tv3 = (TextView) findViewById(R.id.beaonparameter4);//テスト用
        tv3.setText(holder_parameter3.getTestString());//テスト用
        tv3.setTextColor(holder_parameter3.getColorString());
        TextView tv3_1 = (TextView) findViewById(R.id.rssi3);
        tv3_1.setText(holder_parameter3.getRssi());
        tv3_1.setTextColor(holder_parameter3.getColorString());

        DataHolder holder_parameter4 = DataHolderParameter4.getInstance();
        TextView tv4 = (TextView) findViewById(R.id.beaonparameter5);//テスト用
        tv4.setText(holder_parameter4.getTestString());//テスト用
        tv4.setTextColor(holder_parameter4.getColorString());
        TextView tv4_1 = (TextView) findViewById(R.id.rssi4);
        tv4_1.setText(holder_parameter4.getRssi());
        tv4_1.setTextColor(holder_parameter4.getColorString());

        DataHolder holder_parameter5 = DataHolderParameter5.getInstance();
        TextView tv5 = (TextView) findViewById(R.id.beaonparameter6);//テスト用
        tv5.setText(holder_parameter5.getTestString());//テスト用
        tv5.setTextColor(holder_parameter5.getColorString());
        TextView tv5_1 = (TextView) findViewById(R.id.rssi5);
        tv5_1.setText(holder_parameter5.getRssi());
        tv5_1.setTextColor(holder_parameter5.getColorString());

        DataHolder holder_parameter6 = DataHolderParameter6.getInstance();
        TextView tv6 = (TextView) findViewById(R.id.beaonparameter7);//テスト用
        tv6.setText(holder_parameter6.getTestString());//テスト用
        tv6.setTextColor(holder_parameter6.getColorString());
        TextView tv6_1 = (TextView) findViewById(R.id.rssi6);
        tv6_1.setText(holder_parameter6.getRssi());
        tv6_1.setTextColor(holder_parameter6.getColorString());

        DataHolder holder_parameter7 = DataHolderParameter7.getInstance();
        TextView tv7 = (TextView) findViewById(R.id.beaonparameter8);//テスト用
        tv7.setText(holder_parameter7.getTestString());//テスト用
        tv7.setTextColor(holder_parameter7.getColorString());
        TextView tv7_1 = (TextView) findViewById(R.id.rssi7);
        tv7_1.setText(holder_parameter7.getRssi());
        tv7_1.setTextColor(holder_parameter7.getColorString());

        DataHolder holder_parameter8 = DataHolderParameter8.getInstance();
        TextView tv8 = (TextView) findViewById(R.id.beaonparameter9);//テスト用
        tv8.setText(holder_parameter8.getTestString());//テスト用
        tv8.setTextColor(holder_parameter8.getColorString());
        TextView tv8_1 = (TextView) findViewById(R.id.rssi8);
        tv8_1.setText(holder_parameter8.getRssi());
        tv8_1.setTextColor(holder_parameter8.getColorString());

        DataHolder holder_parameter9 = DataHolderParameter9.getInstance();
        TextView tv9 = (TextView) findViewById(R.id.beaonparameter10);//テスト用
        tv9.setText(holder_parameter9.getTestString());//テスト用
        tv9.setTextColor(holder_parameter9.getColorString());
        TextView tv9_1 = (TextView) findViewById(R.id.rssi9);
        tv9_1.setText(holder_parameter9.getRssi());
        tv9_1.setTextColor(holder_parameter9.getColorString());

    }

}
