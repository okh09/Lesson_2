package com.example.lesson_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    String oldNumber;
    String operator = "";
    Boolean isNew = true;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
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