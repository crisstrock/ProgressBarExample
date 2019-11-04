package com.crisstrock.progressbarexampleasync;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressH, progressN;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addViews();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask_load().execute();
                btnStart.setClickable(false);
            }
        });
    }

    private void addViews() {
        btnStart = (Button)findViewById(R.id.btn_start);
        progressH = (ProgressBar) findViewById(R.id.progress_h);
        progressH.setProgress(0);
        progressN = (ProgressBar) findViewById(R.id.progress_n);
        progressN.setProgress(0);
    }

    public class AsyncTask_load extends AsyncTask<Void, Integer, Void> {

        int progreso;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Service Started...", Toast.LENGTH_SHORT).show();
            progreso = 0;
            progressN.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            while (progreso < 100){
                progreso++;
                publishProgress(progreso);
                SystemClock.sleep(30);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressH.setProgress(values[0]);
            progressN.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(MainActivity.this, "Service Stopped...", Toast.LENGTH_SHORT).show();
            btnStart.setClickable(true);
            progressN.setVisibility(View.INVISIBLE);
        }
    }
}
