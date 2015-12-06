package myapplication.example.com.cis490_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class loginactivity extends AppCompatActivity {

    Button loginButton;
   Button signUpButton;
   EditText usernameField;
    EditText passwordField;

    String username;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        loginButton = (Button) findViewById(R.id.btlogin);
        signUpButton = (Button) findViewById(R.id.btsignup);
        usernameField = (EditText) findViewById(R.id.tName);
        passwordField = (EditText) findViewById(R.id.tpassword);


        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "5JJQ74Au3x8kcf5GZySPXDNwVcwalXAH1VJ69unT", "TV7vSHWB0o4bXoZwWvgRNrNxoJtgCLsJtsvFfYrj");

        ParseObject testObject = new ParseObject("Testing");
        testObject.put("foo", "badr");
        testObject.saveInBackground();


        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                username = usernameField.getText().toString();
                password = passwordField.getText().toString();
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    public void done(ParseUser user, com.parse.ParseException e) {
                        if (user != null) {
                            //start next activity
                            //start sinch service

                            final Intent intent = new Intent(getApplicationContext(), Welcome.class);
                            startActivity(intent);
                        } else {
                        Toast.makeText(getApplicationContext(),
                                "You have entered an incorrect password or an invalid username.",
                                Toast.LENGTH_LONG).show();
                    }
                }
                });
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               username = usernameField.getText().toString();
                  password = passwordField.getText().toString();
                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);
                user.signUpInBackground(new SignUpCallback() {
                    public void done(com.parse.ParseException e) {
                        if (e == null) {
                            //start next activity
                            //start sinch service
                            final Intent intent = new Intent(getApplicationContext(), Welcome.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "There was an error signing up."
                                    , Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_loginactivity, menu);
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
