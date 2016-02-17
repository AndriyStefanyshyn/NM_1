package com.restingrobots.nm_1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener, ChoseDialog.ChoseDialogListener {

    private EditText etFromX;
    private EditText etToX;
    private EditText etFromY;
    private EditText etToY;

    private EditText etA;
    private EditText etB;
    private EditText etEps;

    TextView[] v;
    public static final String PREFS_NAME = "Solutions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFromX = (EditText) findViewById(R.id.etFromX);
        etToX = (EditText) findViewById(R.id.etToX);
        etFromY = (EditText) findViewById(R.id.etFromY);
        etToY = (EditText) findViewById(R.id.etToY);

        etA = (EditText) findViewById(R.id.etA);
        etB = (EditText) findViewById(R.id.etB);
        etEps = (EditText) findViewById(R.id.etEps);

        v = new TextView[4];
        v[0] = (TextView) findViewById(R.id.textView);
        v[1] = (TextView) findViewById(R.id.textView2);
        v[2] = (TextView) findViewById(R.id.textView3);
        v[3] = (TextView) findViewById(R.id.textView4);
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        ChoseDialog editNameDialog = new ChoseDialog();
        editNameDialog.show(fm, "fragment_chose");
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
            case R.id.btnGetResult: {
                if(etA.getText().toString().isEmpty() || etB.getText().toString().isEmpty() ||
                        etEps.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Заповніть всі поля", Toast.LENGTH_SHORT).show();
                }
                else {
                    showEditDialog();
                }
                break;
            }
        }
    }

    @Override
    public void onChose(int id) {
        String result = "Помилка";
        double A = Double.parseDouble(etA.getText().toString());
        double B = Double.parseDouble(etB.getText().toString());
        double Eps = Double.parseDouble(etEps.getText().toString());
        switch (id) {
            case 0: {
                result = Finder.bisection(A, B, Eps);
                break;
            }
            case 1: {
                result = Finder.chord(A, B, Eps);
                break;
            }
            case 2: {
                result = Finder.neuton(A, B, Eps);
                break;
            }
            case 3: {
                result = Finder.iter(A, B, Eps);
                break;
            }
        }
        v[id].setText((result));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
