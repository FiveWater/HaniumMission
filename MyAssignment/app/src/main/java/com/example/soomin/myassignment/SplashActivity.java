package com.example.soomin.myassignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

/**
 * Created by SOOMIN on 2017-03-23.
 */

public class SplashActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_splash);

        Handler handler = new Handler();
        handler.postDelayed(new SplashHandler(), 3000);
    }
    private class SplashHandler implements Runnable{
        public void run(){
            startActivity(new Intent(getApplication(), SelectActivity.class));
            SplashActivity.this.finish();
        }
    }
}
