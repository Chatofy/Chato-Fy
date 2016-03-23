package com.example.shubham.chato_fy;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;
import java.lang.Object;




public class ContactListActivity extends Activity {

    private WifiManager wifiManager;
    private WiFiScanReceiver wifiReciever;
    private ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        ListView listView = (ListView)findViewById(R.id.listView);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        wifiReciever = new WiFiScanReceiver();


        adapter.clear();

       // ToggleButton toggleButton = (ToggleButton) view;

        if (wifiManager == null) {
            // Device does not support Wi-Fi
            Toast.makeText(getApplicationContext(), "Oop! Your device does not support Wi-Fi",
                    Toast.LENGTH_SHORT).show();
            //toggleButton.setChecked(false);

        }
        //else {
            //if (toggleButton.isChecked()) { // To turn on Wi-Fi
              //  if (!wifiManager.isWifiEnabled()) {

                    //Toast.makeText(getApplicationContext(), "Wi-Fi is turned on." +
                      //              "\n" + "Scanning for access points...",
                        //    Toast.LENGTH_SHORT).show();

                  //  wifiManager.setWifiEnabled(true);

                 else {
            //Toast.makeText(getApplicationContext(), "Wi-Fi is already turned on." +
            //              "\n" + "Scanning for access points...",
            //    Toast.LENGTH_SHORT).show();

            wifiManager.startScan();
        }

         //   } else { // To turn off Wi-Fi
           //     Toast.makeText(getApplicationContext(), "Wi-Fi is turned off.",
             //           Toast.LENGTH_SHORT).show();
            }
        }



class WiFiScanReceiver extends Activity {
    private BroadcastReceiver mReceiver;


    private WifiManager wifiManager;
    private ArrayAdapter adapter;
    private Object wifiReciever;
    //private Object wifiReciever;

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)) {

            List<ScanResult> wifiScanResultList;
            wifiScanResultList = wifiManager.getScanResults();
            for(int i = 0; i < wifiScanResultList.size(); i++){
                ScanResult accessPoint = wifiScanResultList.get(i);
                String listItem = accessPoint.SSID+", "+accessPoint.BSSID;
                adapter.add(listItem);
            }
        }
    }

    protected void onResume() {
        super.onResume();
        // Register the BroadcastReceiver for SCAN_RESULTS_AVAILABLE_ACTION
        IntentFilter filter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        mReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {}
        };
        //registering our receiver
        this.registerReceiver(mReceiver,filter);

    }


    //@Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


}





