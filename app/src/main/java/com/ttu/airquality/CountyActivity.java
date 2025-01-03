package com.ttu.airquality;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class CountyActivity extends Activity {
    private static final UUID MY_UUID_SECURE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private final Timer timer100ms = new Timer();
    public byte[] messages = new byte[1];
    BluetoothDevice bluetoothDevice;
    BluetoothSocket bluetoothSocket = null;
    OutputStream outputStream = null;
    int foo;
    int test;
    String post;
    String test2;
    TextView test3;
    String host = "https://data.moenv.gov.tw/api/v2/aqx_p_432?language=zh&offset=0&limit=10000&api_key=8a1cc973-da8e-41f8-a240-1d62b6159503";
    Location location;
    double[] dtmp1;
    double[] dtmp2;
    private int ENABLE_BLUETOOTH = 2;
    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private String btAddress = "FC:A8:9A:00:28:81";//藍芽模塊的MAC地址
    private ArrayList<Air> lists = new ArrayList<>();
    private JsonAdapter jsonAdapter;
    private String countyId = "1";
    private boolean searchDone = true, inputList = true;
    private double longtitudeTmp;
    //String host="https://data.epa.gov.tw/api/v1/aqx_p_432?limit=1000&api_key=9be7b239-557b-4c10-9775-78cadfc555e9&format=json";
    LocationListener mListener = new LocationListener() {
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
            if (dtmp1 != null && dtmp2 != null) {
                longtitudeTmp = getApproximatePlace(location.getLongitude(), location.getLatitude(), dtmp1, dtmp2);
            }
        }
    };
    private ListView listview;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {//定时器周期处理,时间100ms
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            if ((!searchDone) && (!inputList)) {
                jsonAdapter = new JsonAdapter(CountyActivity.this, lists);
                jsonAdapter.notifyDataSetChanged();
                listview.setAdapter(jsonAdapter);
                inputList = true;
            }
        }
    };
    private TimerTask taskScreen;

    public static String readParse(String urlPath) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];

        int len = 0;

        URL url = new URL(urlPath);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        InputStream inStream = conn.getInputStream();
        while ((len = inStream.read(data)) != -1) {
            outStream.write(data, 0, len);
        }
        inStream.close();
        return new String(outStream.toByteArray());//通過outStream.toByteArray獲取到寫的數據
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_county);
        countyId = getIntent().getStringExtra("SiteId");
        if (countyId.equals("99")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(this, new String[]{
                        android.Manifest.permission.ACCESS_COARSE_LOCATION,
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                }, 100);
            }
        }

        initView();
        listview = (ListView) findViewById(R.id.listview);
        Log.d("check", "initView()");

        if (bluetoothAdapter == null) {
//            Toast.makeText(this, "不支持藍芽", Toast.LENGTH_LONG).show();
        } else if (!bluetoothAdapter.isEnabled()) {
            Log.d("true", "開始連接");
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, ENABLE_BLUETOOTH);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (searchDone) {
                    try {
                        parseJsonData(readParse(host));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //這裡休眠是為了讓子線程结束 lists才有值
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        taskScreen = new TimerTask() { //初始化定时器
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };
        timer100ms.schedule(taskScreen, 500, 500);  //启动定时器 500ms
    }

    private void initView() {
        Button start = (Button) findViewById(R.id.button00);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messages[0] = (byte) foo;//設置要發送的數值
                bluesend(messages);//發送數值
                Log.d("value", "" + messages[0]);
            }
        });
        Button button = (Button) findViewById(R.id.button02);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (lists.size() > 0) {
                    Intent intent = new Intent();
                    intent.setClass(CountyActivity.this, MainActivity.class);
                    startActivity(intent);
                    CountyActivity.this.finish();
                }
            }
        });

    }

    //藍牙發送數據
    public void bluesend(byte[] messages) {
        if (messages != null) {
            try {
                outputStream = bluetoothSocket.getOutputStream();
            } catch (IOException e) {
                Log.e("Fatal Error", "in sendData() input and output stream creation failed:" + e.getMessage() + ".");
            }
            try {
                outputStream.write(messages);
            } catch (IOException e) {
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (bluetoothAdapter != null) {
                bluetoothSocket.close();
            }
        } catch (IOException e) {
            System.out.println("onDestroy錯誤!!!");
            e.printStackTrace();
        }
    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        if (Build.VERSION.SDK_INT >= 10) {
            try {
                final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", new Class[]{UUID.class});
                return (BluetoothSocket) m.invoke(device, MY_UUID_SECURE);
            } catch (Exception e) {
                Log.e("true", "Could not create Insecure RFComm Connection", e);
            }
        }
        return device.createRfcommSocketToServiceRecord(MY_UUID_SECURE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("true", "...onResume - try connect...");
        if (bluetoothAdapter != null) {
            bluetoothDevice = bluetoothAdapter.getRemoteDevice(btAddress);

            try {
                bluetoothSocket = createBluetoothSocket(bluetoothDevice);
            } catch (IOException e) {
                Log.d("Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
            }
            Log.d("true", "...Connecting...");
            try {
                bluetoothSocket.connect();
                Log.d("true", "....Connection ok...");
            } catch (IOException e) {
                try {
                    bluetoothSocket.close();
                    Log.d("true", "....Close...");
                } catch (IOException e2) {
                    Log.d("Fatal Error", "In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
                }
            }
        }
    }

    private void parseJsonData(String string) throws JSONException {
//        try {
        //JSONArray array = new JSONArray(airStr);
        JSONObject jsonObject = new JSONObject(string);
        JSONArray array = jsonObject.getJSONArray("records");
        dtmp1 = new double[array.length()];
        dtmp2 = new double[array.length()];
        String pro = "監測站未提供資料";
        Air air = new Air();
        int mode = 0;
        int tmp = 1;
        if (countyId.equals("99")) {
            for (int i = 0; i < array.length(); i++) {
                dtmp1[i] = Double.parseDouble(array.getJSONObject(i).getString("longitude"));
                dtmp2[i] = Double.parseDouble(array.getJSONObject(i).getString("latitude"));
                if (array.getJSONObject(i).getString("longitude").equals(Double.toString(longtitudeTmp))) {
                    countyId = array.getJSONObject(i).getString("siteid");
                }
            }
        } else {
            for (int i = 0; i < array.length(); i++) {
                if (array.getJSONObject(i).getString("siteid").equals(countyId)) {
                    tmp = i;
                }
            }
            if (array.getJSONObject(tmp).getString("aqi").equals("")) {
                air.setAQI("空氣品質指標 : " + pro);
                mode = 1;
            }
            if (array.getJSONObject(tmp).getString("pm2.5").equals("")) {
                air.setPM2_5("PM2.5指數 : " + pro);
                mode = 1;
            }
            if (array.getJSONObject(tmp).getString("status").equals("")) {
                air.setStatus("空氣狀態 : " + pro);
                mode = 1;
            }
            if (array.getJSONObject(tmp).getString("publishtime").equals("")) {
                air.setPublishTime("發布時間 : " + pro);
                mode = 1;
            }

            if (mode == 0) {
                air.setCounty("縣市 : " + array.getJSONObject(tmp).getString("county"));
                air.setSiteName("地區 : " + array.getJSONObject(tmp).getString("sitename"));
                air.setLongitude("經度 : " + array.getJSONObject(tmp).getString("longitude"));
                air.setLatitude("緯度 : " + array.getJSONObject(tmp).getString("latitude"));
                air.setAQI("空氣品質指標 : " + array.getJSONObject(tmp).getString("aqi"));
                air.setPM2_5("PM2.5指數 : " + array.getJSONObject(tmp).getString("pm2.5"));
                air.setStatus("空氣狀態 : " + array.getJSONObject(tmp).getString("status"));
                air.setPublishTime("發布時間 : " + array.getJSONObject(tmp).getString("publishtime"));
            } else if (mode == 1) {
                air.setCounty("縣市 : " + array.getJSONObject(tmp).getString("county"));
                air.setSiteName("地區 : " + array.getJSONObject(tmp).getString("sitename"));
                air.setLongitude("經度 : " + array.getJSONObject(tmp).getString("longitude"));
                air.setLatitude("緯度 : " + array.getJSONObject(tmp).getString("latitude"));
                if (!array.getJSONObject(tmp).getString("aqi").equals(""))
                    air.setAQI("空氣品質指標 : " + array.getJSONObject(tmp).getString("aqi"));
                if (!array.getJSONObject(tmp).getString("pm2.5").equals(""))
                    air.setPM2_5("PM2.5指數 : " + array.getJSONObject(tmp).getString("pm2.5"));
                if (!array.getJSONObject(tmp).getString("status").equals(""))
                    air.setStatus("空氣狀態 : " + array.getJSONObject(tmp).getString("status"));
                if (!array.getJSONObject(tmp).getString("publishtime").equals(""))
                    air.setPublishTime("發布時間 : " + array.getJSONObject(tmp).getString("publishtime"));
            }
            test = Integer.parseInt(array.getJSONObject(tmp).getString("aqi"));
            if (test >= 0 && test < 50) air.setTips("小提醒 : 能正常戶外活動");
            else if (test >= 51 && test < 100) air.setTips("小提醒 : 能正常戶外活動");
            else if (test >= 101 && test < 150)
                air.setTips("小提醒 : 一般民眾如果有不適，如眼痛，咳嗽或喉嚨痛等，應該考慮減少戶外活動");
            else if (test >= 151 && test < 200)
                air.setTips("小提醒 : 一般民眾如果有不適，如眼痛，咳嗽或喉嚨痛等，應減少體力消耗，特別是減少戶外活動");
            else if (test >= 201 && test < 300) air.setTips("小提醒 : 一般民眾應減少戶外活動");
            else if (test >= 301 && test < 500)
                air.setTips("小提醒 : 一般民眾應避免戶外活動，室內應緊閉門窗，必要外出應配戴口罩等防護用具");
            lists.add(air);

            Log.i("Test", "OK,數據儲存完成");
            Log.i("Test", "List長度為：" + lists.size());
            searchDone = false;
            inputList = false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        getLocal();
    }

    private void getLocal() {
        String localProvider = "";
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        /**沒有權限則返回*/
        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "未開啟定位權限", Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.setClass(CountyActivity.this, MainActivity.class);
            startActivity(intent);
            CountyActivity.this.finish();
            return;
        }
        /**知道位置後..*/
        location = manager.getLastKnownLocation(localProvider);
        if (location == null) {
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, mListener);
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, mListener);
        }
    }

    private void showLocation(Location location) {
        String address = "緯度：" + location.getLatitude() + "\n經度：" + location.getLongitude();
//        Toast.makeText(this, address, Toast.LENGTH_LONG).show();
        Log.d("Winnie", "adrress:" + address);
    }

    private double getApproximatePlace(double longi, double lati, double[] source1, double[] source2) {
        double minDiffLongi = Math.abs(source1[0] + longi);
        double minDiffLati = Math.abs(source2[0] - lati);
        double minDiffDistance = Math.pow(minDiffLongi, minDiffLati);
        int minIndex = 0;

        for (int i = 1; i < source1.length; i++) {
            double temp1 = Math.abs(source1[i] + longi);
            double temp2 = Math.abs(source2[i] - lati);
            double temp3 = Math.pow(temp1, temp2);
            if (temp3 < minDiffDistance) {
                minIndex = i;
                minDiffDistance = temp3;
            }
        }
        return source1[minIndex];
    }
}