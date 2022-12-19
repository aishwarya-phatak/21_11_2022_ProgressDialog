package com.example.mutlithreadingdemo1;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class Downloader extends AsyncTask<String, Integer, Float>{

    ProgressDialog progressDialog;
    Context context;
    Handler handler;

    public Downloader(Context context, Handler handler){
        this.context = context;
        this.handler = handler;
    }

    @Override
    protected Float doInBackground(String... fileUrls) {

        for(String fileUrl : fileUrls){
            for(int i = 0; i<100; i++){
                Log.e("tag","downloading" + fileUrl + i + "%");

                try{
                    Thread.sleep(50);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                Integer [] progress = new Integer[1];
                progress[0] = i;
                Log.e("tag","progress"+progress[0]);
                publishProgress(i);
            }
        }
        return 100.23F;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Float result) {
        super.onPostExecute(result);
        Message message = new Message();
        message.what = 1;
        message.obj = result;
        handler.sendMessage(message);
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);
        Message message = new Message();
        message.what = 2;
        message.obj = progress;
        handler.sendMessage(message);
    }
}
