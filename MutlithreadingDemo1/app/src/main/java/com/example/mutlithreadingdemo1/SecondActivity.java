package com.example.mutlithreadingdemo1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SecondActivity extends Activity {

    Button btnDownload;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        btnDownload = findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener(new BtnDownloadClickListener1());
    }

    class BtnDownloadClickListener1 implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String [] fileUrls = {
                    "file1",
                    "file2",
                    "file3",
                    "file4"
            };
            new Downloader(SecondActivity.this,new MyHandler()).execute(fileUrls);
        }
    }

    class MyHandler extends Handler{

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if(msg == null || msg.obj == null){
                return;
            }

            if(msg.what == 1){
                Float result = (Float) msg.obj;
                btnDownload.setText("Result" + result);
            }
            if(msg.what == 2){
                Integer [] progress = (Integer[]) msg.obj;
                btnDownload.setText("Progress " + progress[0] + "%");
            }
        }
    }
}