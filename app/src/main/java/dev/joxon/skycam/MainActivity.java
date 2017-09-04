package dev.joxon.skycam;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.VideoView;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // 设置活动横屏
        setContentView(R.layout.activity_main);

//        VideoView videoView = (VideoView) findViewById(R.id.VideoView);
//        try {
//            videoView.setVideoURI(Uri.parse("http://10.206.12.207:8080"));
//        } catch (Exception e) {
//            Log.e("Error", e.getMessage());
//            e.printStackTrace();
//        }
//        videoView.requestFocus();

        final WebView webView = (WebView) findViewById(R.id.web_view);
        webView.setDrawingCacheEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        //webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://10.206.12.194:8080");


        FloatingActionButton settingButton = (FloatingActionButton) findViewById(R.id.FabMore);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 打开设置菜单
            }
        });

        FloatingActionButton screenshotButton = (FloatingActionButton) findViewById(R.id.FabShoot);
        screenshotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = webView.getDrawingCache();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US);
                String fname = Environment.getExternalStorageDirectory().getPath()+ "/" + sdf.format(new Date()) + ".png";
                if(bitmap != null)
                {
                    Log.d("screenshot:", "bitmap got!");
                    try{
                        FileOutputStream out = new FileOutputStream(fname);
                        bitmap.compress(Bitmap.CompressFormat.PNG,100, out);
                        Log.d("screenshot:", "file " + fname + "output done.");
                    }catch(Exception e) {
                        Log.i("Show", e.toString());
                    }
                }else{
                    Log.d("screenshot:", "bitmap is NULL!");
                }
            }}
        );
    }




}
