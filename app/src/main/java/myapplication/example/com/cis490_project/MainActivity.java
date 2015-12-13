
package myapplication.example.com.cis490_project;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class MainActivity extends Activity {
    Button btnStart, btnStop;
    TextView textViewTime;
    String Timer;
    String Name;
    String Seconds;
    Integer ConvertSeconds;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();
        btnStart = (Button)findViewById(R.id.btnStart);
        btnStop = (Button)findViewById(R.id.btnStop);
        textViewTime = (TextView)findViewById(R.id.textViewTime);
        Timer = i.getStringExtra("Timer");
        textViewTime.setText(Timer);
        Name = i.getStringExtra("Name");
        Seconds = i.getStringExtra("Seconds");
        ConvertSeconds = Integer.valueOf(Seconds);


        final CounterClass timer = new CounterClass(ConvertSeconds,1000);
        btnStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
            }
        });
        btnStop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
            }
    });
}
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onFinish() {
            textViewTime.setText("Completed.");
        }
        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);
            textViewTime.setText(hms);
        }
    }
}