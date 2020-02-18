package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText loginEditText = null;
    EditText passwordEditText = null;
    Button connectButton = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passwordEditText = findViewById(R.id.loginEditText);
        loginEditText = findViewById(R.id.passwordEditText);

        connectButton = findViewById(R.id.connectButton);

        MyDatabase mydb = new MyDatabase(this);

        connectButton.setOnClickListener(connectClicked);


    }

    private View.OnClickListener connectClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String result = "";
            boolean tryConnect = mydb.connection(loginEditText.getText().toString(), passwordEditText.getText().toString());
            if(tryConnect)
                result = "true";
            else
                result = "false";

            Log.i("Result", result);
        }
    };

}
