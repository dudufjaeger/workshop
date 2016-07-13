package br.org.eldorado.workshop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get text from SharedPreferences
        SharedPreferences sp = getSharedPreferences(Constants.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
        String defaultName = "";
        String name = sp.getString(Constants.SHARED_PREFERENCES_NAME_KEY, defaultName);

        EditText eText = (EditText) findViewById(R.id.editText);
        eText.setText(name);
    }

    public void btnClicked(View v) {
        EditText eText = (EditText) findViewById(R.id.editText);
        String nameTyped = eText.getText().toString();

        Intent in = new Intent(this, SecondActivity.class);
        in.putExtra("name", nameTyped);

        startActivity(in);
    }

    public void startServiceTapped(View view) {
        serviceIntent = new Intent(getBaseContext(), ExampleService.class);
        startService(serviceIntent);
    }

    public void stopServiceTapped(View view) {
        if (serviceIntent != null) {
            stopService(serviceIntent);
            serviceIntent = null;
        } else {
            Log.d(TAG, "Service not started");
        }
    }
}
