package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final HashMap KEY_MAP = null;


    public static class Calc {
        double result;
        String inputNumStr;
        int operation; // 1 = +; 2 = -; 3 = *; 4 = /; 5 = SQR
        boolean nextOper = false;
        DecimalFormat df = new DecimalFormat("####################.####################");

        public Calc() {
            result = 0d;
            inputNumStr = "";
        }

        public void changeSign() {
            if (this.inputNumStr.substring(0, 1).equals("-")) {
                this.inputNumStr = this.inputNumStr.substring(1);
            } else {
                this.inputNumStr = "-" + this.inputNumStr;
            }
        }

        public void addSign(String sign) {
            if (nextOper) {
                nextOper = false;
                this.inputNumStr = "";
            }
            if (sign != null) {
                this.inputNumStr += sign;
            }
        }

        public void executeOperation() {
            double inputNum = Double.parseDouble(this.inputNumStr);
            if (this.operation == 1) {
                this.result = this.result + inputNum;
            } else if (this.operation == 2) {
                this.result = this.result - inputNum;
            } else if (this.operation == 3) {
                this.result = this.result * inputNum;
            } else if (this.operation == 4) {
                this.result = this.result / inputNum;
            }
            this.inputNumStr = "" + df.format(this.result);
            nextOper = true;
        }

        public void setOperation(int operation) {
            this.operation = operation;
            double inputNum = Double.parseDouble(this.inputNumStr);
            if (this.operation == 5) {
                this.result = Math.sqrt(inputNum);
                this.inputNumStr = "" + df.format(this.result);
            } else {
                this.result = Double.parseDouble(this.inputNumStr);
            }
            nextOper = true;
        }

        public void backspace() {
            if (inputNumStr.isEmpty()) {
                return;
            }
            inputNumStr = inputNumStr.substring(0, inputNumStr.length() - 1);
            if (inputNumStr.equals("-")) {
                inputNumStr = "";
                return;
            }
        }

        public void reset() {
            result = 0d;
            inputNumStr = "";
            operation = 0;
            nextOper = false;
        }

        public String getDisplay() {
            return inputNumStr.equals("") ? "0" : inputNumStr;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.calc_activity_main);
        View display_text = findViewById(R.id.display_text);

        Calc calc = new Calc();

        TextView resultTxt = findViewById(R.id.display_text);

        View.OnClickListener funcKeyClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.key_calc:
                        calc.executeOperation();
                        break;
                    case R.id.key_bckp:
                        calc.backspace();
                        break;
                    case R.id.key_c:
                        calc.reset();
                        break;
                    case R.id.key_pn:
                        calc.changeSign();
                        break;
                }
                resultTxt.setText(calc.getDisplay());
            }
        };

        findViewById(R.id.key_calc).setOnClickListener(funcKeyClickListener);
        findViewById(R.id.key_bckp).setOnClickListener(funcKeyClickListener);
        findViewById(R.id.key_c).setOnClickListener(funcKeyClickListener);
        findViewById(R.id.key_pn).setOnClickListener(funcKeyClickListener);

        Map<Integer, String> numKeyMap = new HashMap();
        numKeyMap.put(R.id.key_0, "0");
        numKeyMap.put(R.id.key_1, "1");
        numKeyMap.put(R.id.key_2, "2");
        numKeyMap.put(R.id.key_3, "3");
        numKeyMap.put(R.id.key_4, "4");
        numKeyMap.put(R.id.key_5, "5");
        numKeyMap.put(R.id.key_6, "6");
        numKeyMap.put(R.id.key_7, "7");
        numKeyMap.put(R.id.key_8, "8");
        numKeyMap.put(R.id.key_9, "9");
        numKeyMap.put(R.id.key_dot, ".");

        View.OnClickListener digitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc.addSign(numKeyMap.get(view.getId()));
                resultTxt.setText(calc.getDisplay());
            }
        };
        for (int key :
                numKeyMap.keySet()) {
            findViewById(key).setOnClickListener(digitClickListener);
        }

        Map<Integer, Integer> operKeyMap= new HashMap();
        operKeyMap.put(R.id.key_add, 1);
        operKeyMap.put(R.id.key_sub, 2);
        operKeyMap.put(R.id.key_mltp, 3);
        operKeyMap.put(R.id.key_div, 4);
        operKeyMap.put(R.id.key_sqr, 5);

        View.OnClickListener operClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc.setOperation(operKeyMap.get(view.getId()));
                resultTxt.setText(calc.getDisplay());
            }
        };
        for (int key :
                operKeyMap.keySet()) {
            findViewById(key).setOnClickListener(operClickListener);
        }
    }
}