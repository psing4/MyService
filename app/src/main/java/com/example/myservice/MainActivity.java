package com.example.myservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.editText);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = text.getText().toString();

                Intent intent = new Intent(getApplicationContext(),MyService.class);
                intent.putExtra("command","showw");
                intent.putExtra("name",a);
                startService(intent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processCommand(intent);
        super.onNewIntent(intent);
    }
    private void processCommand(Intent intent)
    {
        if(intent != null)
        {
            String com = intent.getStringExtra("com");
            String name = intent.getStringExtra("name");

            Toast.makeText(this,"서비스로 전달받은 데이터 : " + com + ", "+ name,Toast.LENGTH_LONG).show();
        }
    }
}
