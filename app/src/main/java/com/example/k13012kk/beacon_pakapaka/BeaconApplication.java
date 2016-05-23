package com.example.k13012kk.beacon_pakapaka;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import android.bluetooth.*;
import android.content.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Iterator;
import java.util.Map;
import java.util.Calendar;
import java.util.TreeMap;

/**
 * Created by k13012kk on 16/05/23.
 */
public class BeaconApplication extends Service{

    // iBeaconのデータを認識するためのParserフォーマット
    public static final String IBEACON_FORMAT = "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24";

    private int sum[] = new int [10];
    public static int identifier = 0;

    static Map<String,ArrayDeque<SensorValue>> map=new TreeMap<String,ArrayDeque<SensorValue>>();
    static Map<String,ArrayList<Integer>> lpmap=new TreeMap<String,ArrayList<Integer>>();
    static Map<String,BeaconIdentification> beaconmap = new TreeMap<String,BeaconIdentification>();

    private int time=1;
    Timer timer = new Timer();

    // iBeacon領域


    @Override
    public void onCreate() {
        super.onCreate();

        BluetoothManager bluetoothManager = (BluetoothManager)getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter mBluetoothAdapter = bluetoothManager.getAdapter();


        final BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
            @Override
            public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
                Calendar calendar = Calendar.getInstance();

                //ここに結果に対して行う処理を記述する
                if (scanRecord.length > 30) {
                    //iBeacon の場合 6 byte 目から、 9 byte 目はこの値に固定されている。
                    if ((scanRecord[5] == (byte) 0x4c) && (scanRecord[6] == (byte) 0x00) &&
                            (scanRecord[7] == (byte) 0x02) && (scanRecord[8] == (byte) 0x15)) {
                        String uuid = IntToHex2(scanRecord[9] & 0xff)
                                + IntToHex2(scanRecord[10] & 0xff)
                                + IntToHex2(scanRecord[11] & 0xff)
                                + IntToHex2(scanRecord[12] & 0xff)
                                + "-"
                                + IntToHex2(scanRecord[13] & 0xff)
                                + IntToHex2(scanRecord[14] & 0xff)
                                + "-"
                                + IntToHex2(scanRecord[15] & 0xff)
                                + IntToHex2(scanRecord[16] & 0xff)
                                + "-"
                                + IntToHex2(scanRecord[17] & 0xff)
                                + IntToHex2(scanRecord[18] & 0xff)
                                + "-"
                                + IntToHex2(scanRecord[19] & 0xff)
                                + IntToHex2(scanRecord[20] & 0xff)
                                + IntToHex2(scanRecord[21] & 0xff)
                                + IntToHex2(scanRecord[22] & 0xff)
                                + IntToHex2(scanRecord[23] & 0xff)
                                + IntToHex2(scanRecord[24] & 0xff);

                        String majo = IntToHex2(scanRecord[25] & 0xff) + IntToHex2(scanRecord[26] & 0xff);
                        String major =String.valueOf(Integer.parseInt(majo, 16));
                        String mino = IntToHex2(scanRecord[27] & 0xff) + IntToHex2(scanRecord[28] & 0xff);
                        String minor =String.valueOf(Integer.parseInt(mino, 16));

                        String key=IntToHex2(scanRecord[25] & 0xff) + IntToHex2(scanRecord[26] & 0xff)+IntToHex2(scanRecord[27] & 0xff) + IntToHex2(scanRecord[28] & 0xff);
                        String parameter = uuid+","+major+","+minor;

                        SensorValue sv = new SensorValue();
                        sv.rssi=rssi;
                        sv.time=calendar.getTimeInMillis();


                        if(beaconmap.containsKey(key)){
                            BeaconIdentification beacon = beaconmap.get(key);
                            DataHolder holder = null;

                            if(beacon.identifier==0) {
                                holder = DataHolderParameter.getInstance();
                            }else if(beacon.identifier==1) {
                                holder = DataHolderParameter1.getInstance();
                            }else if(beacon.identifier==2) {
                                holder = DataHolderParameter2.getInstance();
                            }else if(beacon.identifier==3){
                                holder = DataHolderParameter3.getInstance();
                            }else if(beacon.identifier==4){
                                holder = DataHolderParameter4.getInstance();
                            }else if(beacon.identifier==5){
                                holder = DataHolderParameter5.getInstance();
                            }else if(beacon.identifier==6){
                                holder = DataHolderParameter6.getInstance();
                            }else if(beacon.identifier==7){
                                holder = DataHolderParameter7.getInstance();
                            }else if(beacon.identifier==8){
                                holder = DataHolderParameter8.getInstance();
                            }else if(beacon.identifier==9){
                                holder = DataHolderParameter9.getInstance();
                            }

                            String beacon_rssi = String.valueOf(rssi);

                            holder.setRssi(beacon_rssi);
                        }else{
                            BeaconIdentification beacon = new BeaconIdentification();
                            beacon.uuid = uuid;
                            beacon.major = major;
                            beacon.minor = minor;
                            beacon.rssi = rssi;
                            beacon.parameter = parameter;
                            beacon.identifier = identifier;
                            beaconmap.put(key,beacon);
                            identifier++;

                            DataHolder holder = null;

                            if(beacon.identifier==0) {
                                holder = DataHolderParameter.getInstance();
                            }else if(beacon.identifier==1) {
                                holder = DataHolderParameter1.getInstance();
                            }else if(beacon.identifier==2) {
                                holder = DataHolderParameter2.getInstance();
                            }else if(beacon.identifier==3){
                                holder = DataHolderParameter3.getInstance();
                            }else if(beacon.identifier==4){
                                holder = DataHolderParameter4.getInstance();
                            }else if(beacon.identifier==5){
                                holder = DataHolderParameter5.getInstance();
                            }else if(beacon.identifier==6){
                                holder = DataHolderParameter6.getInstance();
                            }else if(beacon.identifier==7){
                                holder = DataHolderParameter7.getInstance();
                            }else if(beacon.identifier==8){
                                holder = DataHolderParameter8.getInstance();
                            }else if(beacon.identifier==9){
                                holder = DataHolderParameter9.getInstance();
                            }

                            String beacon_parameter = beacon.parameter;

                            String beacon_rssi = String.valueOf(beacon.rssi);

                            holder.setTestString(beacon_parameter);
                            holder.setRssi(beacon_rssi);
                        }


                        if(map.containsKey(key)){
                            ArrayDeque<SensorValue> queue = map.get(key);
                            queue.addLast(sv);
                        }else{
                            ArrayDeque<SensorValue> queue = new ArrayDeque<SensorValue>();
                            queue.addLast(sv);
                            map.put(key,queue);
                        }

                        ArrayDeque<SensorValue> queue = map.get(key);

                        if(queue.size() == 8){
                            int avg =0;
                            long time = 0;
                            long sec = 0;
                            long millis =0;
                            for(int i=0;i<8;i++) {
                                SensorValue lpsv = queue.pollFirst();
                                sum[i] = lpsv.rssi;
                                time = lpsv.time;

                                millis = time % 1000;
                                time = time / 1000;
                                sec = time % 60;

                                avg = avg+sum[i];
                                if(i>0){
                                    queue.addLast(lpsv);
                                }
                            }
                            avg = avg / 8;
                            Log.d("Beacon",",UUID,"+uuid+",key,"+key+",time,"+sec+"."+millis+",lp,"+avg + ",RSSI," + rssi);

                            //時刻情報があった方いい
                            if(lpmap.containsKey(key)){
                                ArrayList<Integer> list = lpmap.get(key);
                                list.add(avg);
                            }else{
                                ArrayList<Integer> list = new ArrayList<Integer>();
                                list.add(avg);
                                lpmap.put(key, list);
                            }
                        }
                        //Log.d("Beacon",", UUID:" + uuid + ", major:" + major + ", minor:" + minor + ",RSSI," + rssi );
                    }
                }
            }
            //intデータを 2桁16進数に変換するメソッド
            public String IntToHex2(int i) {
                char hex_2[] = {Character.forDigit((i>>4) & 0x0f,16),Character.forDigit(i&0x0f, 16)};
                String hex_2_str = new String(hex_2);
                return hex_2_str.toUpperCase();
            }
        };
        mBluetoothAdapter.startLeScan(mLeScanCallback);

        timer = new Timer();
        TimerTask timertask = new MyTimerTask(this);
        timer.scheduleAtFixedRate(timertask, 1000, 1000);
    }

    public void pakapakaCheck_All(){
        Iterator<String> it = lpmap.keySet().iterator();
        ArrayList list = null;
        while (it.hasNext()) {
            String key = it.next();
            list = lpmap.get(key);

            // Log.d("Beacon", "" + key);

            if (PakapakaCheck(list)) {
                BeaconIdentification beacon = beaconmap.get(key);
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

                list.clear();

                if(beacon.identifier==0) {
                    String beacon_parameter = beacon.parameter;
                    holder.setTestString(beacon_parameter);
                    holder.setColor(0xffff0000);
                    holder1.setColor(0xff000000);
                    holder2.setColor(0xff000000);
                    holder3.setColor(0xff000000);
                    holder4.setColor(0xff000000);
                    holder5.setColor(0xff000000);
                    holder6.setColor(0xff000000);
                    holder7.setColor(0xff000000);
                    holder8.setColor(0xff000000);
                    holder9.setColor(0xff000000);
                }else if(beacon.identifier==1) {
                    String beacon_parameter = beacon.parameter;
                    holder1.setTestString(beacon_parameter);
                    holder.setColor(0xff000000);
                    holder1.setColor(0xffff0000);
                    holder2.setColor(0xff000000);
                    holder3.setColor(0xff000000);
                    holder4.setColor(0xff000000);
                    holder5.setColor(0xff000000);
                    holder6.setColor(0xff000000);
                    holder7.setColor(0xff000000);
                    holder8.setColor(0xff000000);
                    holder9.setColor(0xff000000);
                }else if(beacon.identifier==2) {
                    String beacon_parameter = beacon.parameter;
                    holder2.setTestString(beacon_parameter);
                    holder.setColor(0xff000000);
                    holder1.setColor(0xff000000);
                    holder2.setColor(0xffff0000);
                    holder3.setColor(0xff000000);
                    holder4.setColor(0xff000000);
                    holder5.setColor(0xff000000);
                    holder6.setColor(0xff000000);
                    holder7.setColor(0xff000000);
                    holder8.setColor(0xff000000);
                    holder9.setColor(0xff000000);
                }else if(beacon.identifier==3){
                    String beacon_parameter = beacon.parameter;
                    holder3.setTestString(beacon_parameter);
                    holder.setColor(0xff000000);
                    holder1.setColor(0xff000000);
                    holder2.setColor(0xff000000);
                    holder3.setColor(0xffff0000);
                    holder4.setColor(0xff000000);
                    holder5.setColor(0xff000000);
                    holder6.setColor(0xff000000);
                    holder7.setColor(0xff000000);
                    holder8.setColor(0xff000000);
                    holder9.setColor(0xff000000);
                }else if(beacon.identifier==4){
                    String beacon_parameter = beacon.parameter;
                    holder4.setTestString(beacon_parameter);
                    holder.setColor(0xff000000);
                    holder1.setColor(0xff000000);
                    holder2.setColor(0xff000000);
                    holder3.setColor(0xff000000);
                    holder4.setColor(0xffff0000);
                    holder5.setColor(0xff000000);
                    holder6.setColor(0xff000000);
                    holder7.setColor(0xff000000);
                    holder8.setColor(0xff000000);
                    holder9.setColor(0xff000000);
                }else if(beacon.identifier==5){
                    String beacon_parameter = beacon.parameter;
                    holder5.setTestString(beacon_parameter);
                    holder.setColor(0xff000000);
                    holder1.setColor(0xff000000);
                    holder2.setColor(0xff000000);
                    holder3.setColor(0xff000000);
                    holder4.setColor(0xff000000);
                    holder5.setColor(0xffff0000);
                    holder6.setColor(0xff000000);
                    holder7.setColor(0xff000000);
                    holder8.setColor(0xff000000);
                    holder9.setColor(0xff000000);
                }else if(beacon.identifier==6){
                    String beacon_parameter = beacon.parameter;
                    holder6.setTestString(beacon_parameter);
                    holder.setColor(0xff000000);
                    holder1.setColor(0xff000000);
                    holder2.setColor(0xff000000);
                    holder3.setColor(0xff000000);
                    holder4.setColor(0xff000000);
                    holder5.setColor(0xff000000);
                    holder6.setColor(0xffff0000);
                    holder7.setColor(0xff000000);
                    holder8.setColor(0xff000000);
                    holder9.setColor(0xff000000);
                }else if(beacon.identifier==7){
                    String beacon_parameter = beacon.parameter;
                    holder7.setTestString(beacon_parameter);
                    holder.setColor(0xff000000);
                    holder1.setColor(0xff000000);
                    holder2.setColor(0xff000000);
                    holder3.setColor(0xff000000);
                    holder4.setColor(0xff000000);
                    holder5.setColor(0xff000000);
                    holder6.setColor(0xff000000);
                    holder7.setColor(0xffff0000);
                    holder8.setColor(0xff000000);
                    holder9.setColor(0xff000000);
                }else if(beacon.identifier==8){
                    String beacon_parameter = beacon.parameter;
                    holder8.setTestString(beacon_parameter);
                    holder.setColor(0xff000000);
                    holder1.setColor(0xff000000);
                    holder2.setColor(0xff000000);
                    holder3.setColor(0xff000000);
                    holder4.setColor(0xff000000);
                    holder5.setColor(0xff000000);
                    holder6.setColor(0xff000000);
                    holder7.setColor(0xff000000);
                    holder8.setColor(0xffff0000);
                    holder9.setColor(0xff000000);
                }else if(beacon.identifier==9){
                    String beacon_parameter = beacon.parameter;
                    holder9.setTestString(beacon_parameter);
                    holder.setColor(0xff000000);
                    holder1.setColor(0xff000000);
                    holder2.setColor(0xff000000);
                    holder3.setColor(0xff000000);
                    holder4.setColor(0xff000000);
                    holder5.setColor(0xff000000);
                    holder6.setColor(0xff000000);
                    holder7.setColor(0xff000000);
                    holder8.setColor(0xff000000);
                    holder9.setColor(0xffff0000);
                }

            }

        }

    }

    public boolean PakapakaCheck(ArrayList<Integer> list){
        boolean flag = false;
        int rssi = 0;
        int boundary = 0;
        int count = 0;


        for(int i=0;i<list.size();i++) {
            if (boundary == 0 && list.size() >= 10) {
                int sum = 0;
                for (int j = 0; j < 10; j++) {
                    sum = sum + list.get(j);
                }
                boundary = sum / 10;
            }else{
                rssi = list.get(i);
                if(boundary-rssi <= -5){
                    boundary = rssi;
                }
                if (rssi < boundary - 10) {
                    time = 0;
                } else {
                    if (time == 0) {
                        count++;
                    }
                    time = 1;
                }
            }
        }

        // Log.d("Beacon",""+boundary+","+rssi);
        if(count >= 2){
            flag = true;
            list.clear();
        }
        return flag;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

