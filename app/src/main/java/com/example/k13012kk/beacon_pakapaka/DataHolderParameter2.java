package com.example.k13012kk.beacon_pakapaka;

/**
 * Created by k13012kk on 16/05/23.
 */
public class DataHolderParameter2 {
    static DataHolder _instance = null;

    /**
     * 常にこのメソッドから呼び出すようにする
     *
     *
     * @return
     */

    static public DataHolder getInstance(){
        if(_instance==null){
            _instance = new DataHolder();
        }
        return _instance;
    }

    String test ="";
    String rssi="";
    int color = 0xff000000;

    public String getTestString(){return test;}

    public int getColorString(){return color;}

    public String getRssi(){return rssi;}

    public void setTestString(String txt){
        this.test = txt;
    }

    public void  setColor(int color){this.color=color;}

    public void setRssi(String rssi){this.rssi=rssi;}
}
