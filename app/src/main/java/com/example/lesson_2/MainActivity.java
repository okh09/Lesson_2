package com.example.lesson_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES = "mySettings";
    SharedPreferences mSettings;
    String oldNumber;
    String operator = "";
    Boolean isNew = true;
    EditText editText;
    SaveState saveState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        saveState = new SaveState(this);
        if (saveState.getState()) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        setContentView(R.layout.activity_main);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        boolean isSwitchChecked = mSettings.getBoolean("isChecked",false);

        if(isSwitchChecked) {
            saveState.setState(true);
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            saveState.setState(false);
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        editText = findViewById(R.id.editText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.info) {
            Toast.makeText(this, "Версия 1.0.2", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.settings) {
            Intent runSettings = new Intent(this, SettingsActivity.class);
            startActivity(runSettings);
        }
        return super.onOptionsItemSelected(item);
    }

    public void clickNumber(View view) {
        if(isNew)
            editText.setText("");
        isNew = false;

        String number = editText.getText().toString();
        switch (view.getId()) {
            case R.id.btn1 : number = number + "1"; break;
            case R.id.btn2 : number = number + "2"; break;
            case R.id.btn3 : number = number + "3"; break;
            case R.id.btn4 : number = number + "4"; break;
            case R.id.btn5 : number = number + "5"; break;
            case R.id.btn6 : number = number + "6"; break;
            case R.id.btn7 : number = number + "7"; break;
            case R.id.btn8 : number = number + "8"; break;
            case R.id.btn9 : number = number + "9"; break;
            case R.id.btn0 : number = number + "0"; break;
            case R.id.btnDot : if (dotIsPresent(number));
                 else number = number + "."; break;
            case R.id.btnPlusMinus : if (minusIsPresent(number)) {
                number = number.substring(1);
            } else {
                number = "-" + number;
            } break;
        }
        editText.setText(number);
    }

    public void operation(View view) {
        isNew = true;
        oldNumber = editText.getText().toString();
        switch (view.getId()) {
            case R.id.btnMinus: operator = "-"; break;
            case R.id.btnPlus: operator = "+"; break;
            case R.id.btnDivide: operator = "/"; break;
            case R.id.btnMultiply: operator = "*"; break;
        }
    }

    public void clickEqual(View view) {
        String newNumber = editText.getText().toString();
        double result = 0.0;
        switch (operator) {
            case "-" : result = Double.parseDouble(oldNumber) - Double.parseDouble(newNumber); break;
            case "+" : result = Double.parseDouble(oldNumber) + Double.parseDouble(newNumber); break;
            case "*" : result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber); break;
            case "/" : result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber); break;
        }
        editText.setText(result + "");
    }

    public void acClick(View view) {
        editText.setText("0");
        isNew = true;
    }

    public boolean dotIsPresent(String number) {
        return number.contains(".");
    }

    public boolean minusIsPresent (String number) {
        return number.charAt(0) == '-';
    }

    public void clickPercent(View view) {
        if(operator.equals("")) {
            String number = editText.getText().toString();
            double temp = Double.parseDouble(number) / 100;
            number = temp + "";
            editText.setText(number);
        } else {
            double result = 0.0;
            String newNumber = editText.getText().toString();
            switch (operator) {
                case "-" : result = Double.parseDouble(oldNumber) - Double.parseDouble(oldNumber) *
                        Double.parseDouble(newNumber) / 100; break;
                case "+" : result = Double.parseDouble(oldNumber) + Double.parseDouble(oldNumber) *
                        Double.parseDouble(newNumber) / 100; break;
                case "*" : result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber) / 100; break;
                case "/" : result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber) * 100; break;
            }
            editText.setText(result + "");
            operator = "";
        }
    }
}