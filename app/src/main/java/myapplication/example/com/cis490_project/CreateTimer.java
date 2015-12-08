package myapplication.example.com.cis490_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseObject;

public class CreateTimer extends AppCompatActivity {


    Button createButton;
    Button cancelButton;
    EditText timerNameField;
    EditText timerTimeField;
    String timerName;
    Integer time;
   // int timeint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_timer);

        createButton = (Button) findViewById(R.id.btCreate);
        cancelButton = (Button) findViewById(R.id.btCancel);
        timerNameField = (EditText) findViewById(R.id.txtName);
        timerTimeField = (EditText) findViewById(R.id.txtTime);



        createButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                timerName = timerNameField.getText().toString();
                //time = timerTimeField.getText().toString();
                time = Integer.parseInt(timerTimeField.getText().toString());

                int seconds = time;
                int hr = (seconds/3600);
                int rem = (seconds%3600);
                int mn = rem/60;
                int sec = rem%60;
                String hrStr = (hr<10 ? "0" : "")+hr;
                String mnStr = (mn<10 ? "0" : "")+mn;
                String secStr = (sec<10 ? "0" : "")+sec;

               String trueTime = (hrStr+ " : "+mnStr+ " : "+secStr+"");

               // int truetime = time
                // timeint = Integer.parseInt(time);


                ParseObject testObject = new ParseObject("Timers");
                testObject.put("Name", timerName);
                testObject.put("Time", trueTime);
                testObject.saveInBackground();


            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_timer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
