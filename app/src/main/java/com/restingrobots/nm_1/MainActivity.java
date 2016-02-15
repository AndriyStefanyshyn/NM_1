package com.restingrobots.nm_1;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etFromX;
    EditText etToX;
    EditText etFromY;
    EditText etToY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etFromX = (EditText) findViewById(R.id.etFromX);
        etToX = (EditText) findViewById(R.id.etToX);
        etFromY = (EditText) findViewById(R.id.etFromY);
        etToY = (EditText) findViewById(R.id.etToY);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGetGraph: {
                if(etFromX.getText().toString().isEmpty() || etToX.getText().toString().isEmpty() ||
                        etFromY.getText().toString().isEmpty() || etToY.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Заповніть всі поля", Toast.LENGTH_SHORT).show();
                }
                else {
                    int fromX = Integer.parseInt(etFromX.getText().toString());
                    int toX = Integer.parseInt(etToX.getText().toString());
                    int fromY = Integer.parseInt(etFromY.getText().toString());
                    int toY = Integer.parseInt(etToY.getText().toString());
                    if(toX-fromX <= 0 || toY-fromY <= 0) {
                        Toast.makeText(this, "Перевірте введені дані", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(this, GraphActivity.class);
                        intent.putExtra("fromX", fromX);
                        intent.putExtra("fromY", fromY);
                        intent.putExtra("toX", toX);
                        intent.putExtra("toY", toY);
                        startActivity(intent);
                    }
                }
                break;
            }
        }
    }
}
