package com.example.mutlithreadingdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDownload = findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener(new BtnDownloadClickListener());
    }

    class BtnDownloadClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String [] fileUrls = {
                    "file1",
                    "file2",
                    "file3",
                    "file4"
            };
            new DownloadThread().execute(fileUrls);
        }
    }

    class DownloadThread extends AsyncTask<String,Integer, Float>{
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

        @Override
        protected Float doInBackground(String... fileUrls) {

            for(String fileUrl :  fileUrls){
                for(int i = 0; i<100; i++){
                Log.e("tag","downloading" + fileUrl + i + "%");

                    try{
                        Thread.sleep(50);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    Integer [] progress = new Integer[1];
                    progress[0] = i;
                    publishProgress(i);
                    progressDialog.setProgress(i);
                }
            }
            return 100.00F;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Download");
            progressDialog.setMessage("This is downloader dialog");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            btnDownload.setText("Progress " + values[0] + "%");
        }

        @Override
        protected void onPostExecute(Float result) {
            super.onPostExecute(result);
            btnDownload.setText("Float" + result);
        }
    }
}