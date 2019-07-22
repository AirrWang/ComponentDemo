package com.muheda.componenttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mhd.basekit.viewkit.util.route.RouteConstant;
import com.mhd.basekit.viewkit.util.route.RouteUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouteUtil.routeSkip(RouteConstant.TEST, new Object[][]{});
            }
        });
    }
}
