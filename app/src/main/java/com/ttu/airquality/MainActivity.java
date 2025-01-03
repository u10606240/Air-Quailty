package com.ttu.airquality;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button keelungbtn;
    private Button taipeibtn;
    private Button newtaipeibtn;
    private Button taoyuanbtn;
    private Button hsinchucitybtn;
    private Button hsinchucountybtn;
    private Button miaolicountybtn;
    private Button taichungbtn;
    private Button nantoubtn;
    private Button changhuabtn;
    private Button Yunlinbtn;
    private Button chiayicitybtn;
    private Button chiayicountybtn;
    private Button tainanbtn;
    private Button kaohsiungbtn;
    private Button pingtungbtn;
    private Button yilanbtn;
    private Button hualienbtn;
    private Button taitungbtn;
    private Button penghubtn;
    private Button kinmenbtn;
    private Button matsubtn;
    private Button nearbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        keelungbtn = (Button)findViewById(R.id.keelungbtn);
        taipeibtn = (Button)findViewById(R.id.taipeibtn);
        newtaipeibtn = (Button)findViewById(R.id.newtaipeibtn);
        taoyuanbtn = (Button)findViewById(R.id.taoyuanbtn);
        hsinchucitybtn = (Button)findViewById(R.id.hsinchucitybtn);
        hsinchucountybtn = (Button)findViewById(R.id.hsinchucountybtn);
        miaolicountybtn = (Button)findViewById(R.id.miaolicountybtn);
        taichungbtn = (Button)findViewById(R.id.taichungbtn);
        nantoubtn = (Button)findViewById(R.id.nantoubtn);
        changhuabtn = (Button)findViewById(R.id.changhuabtn);
        Yunlinbtn = (Button)findViewById(R.id.Yunlinbtn);
        chiayicitybtn = (Button)findViewById(R.id.chiayicitybtn);
        chiayicountybtn = (Button)findViewById(R.id.chiayicountybtn);
        tainanbtn = (Button)findViewById(R.id.tainanbtn);
        kaohsiungbtn = (Button)findViewById(R.id.kaohsiungbtn);
        pingtungbtn = (Button)findViewById(R.id.pingtungbtn);
        yilanbtn = (Button)findViewById(R.id.yilanbtn);
        hualienbtn = (Button)findViewById(R.id.hualienbtn);
        taitungbtn = (Button)findViewById(R.id.taitungbtn);
        penghubtn = (Button)findViewById(R.id.penghubtn);
        kinmenbtn = (Button)findViewById(R.id.kinmenbtn);
        matsubtn = (Button)findViewById(R.id.matsubtn);
        nearbtn = (Button)findViewById(R.id.nearbtn);

        keelungbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","1");
                startActivity(intent);
            }
        });
        taipeibtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","12");
                startActivity(intent);
            }
        });
        newtaipeibtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","70");
                startActivity(intent);
            }
        });
        taoyuanbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","21");
                startActivity(intent);
            }
        });
        hsinchucitybtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","24");
                startActivity(intent);
            }
        });
        hsinchucountybtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","23");
                startActivity(intent);
            }
        });
        miaolicountybtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","26");
                startActivity(intent);
            }
        });
        taichungbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","32");
                startActivity(intent);
            }
        });
        nantoubtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","36");
                startActivity(intent);
            }
        });
        changhuabtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","35");
                startActivity(intent);
            }
        });
        Yunlinbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","83");
                startActivity(intent);
            }
        });
        chiayicitybtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","42");
                startActivity(intent);
            }
        });
        chiayicountybtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","39");
                startActivity(intent);
            }
        });
        tainanbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","43");
                startActivity(intent);
            }
        });
        kaohsiungbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","54");
                startActivity(intent);
            }
        });
        pingtungbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","61");
                startActivity(intent);
            }
        });
        yilanbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","65");
                startActivity(intent);
            }
        });
        hualienbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","63");
                startActivity(intent);
            }
        });
        taitungbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","62");
                startActivity(intent);
            }
        });
        penghubtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","78");
                startActivity(intent);
            }
        });
        kinmenbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","77");
                startActivity(intent);
            }
        });
        matsubtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","75");
                startActivity(intent);
            }
        });
        nearbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CountyActivity.class);
                intent.putExtra("SiteId","99");
                startActivity(intent);
            }
        });
    }
}