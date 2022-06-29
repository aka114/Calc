package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final HashMap KEY_MAP = null;


    public static class Calc {
        double result;
        String inputNumStr;
        int operation; // 1 = +; 2 = -; 3 = *; 4 = /; 5 = SQR

        public Calc() {
            result = 0d;
            inputNumStr = "";
        }

        public void ChangeSign() {
            if (this.inputNumStr.substring(0, 0) == "-") {
                this.inputNumStr = this.inputNumStr.substring(1);
            } else {
                this.inputNumStr = "-" + this.inputNumStr;
            }
        }

        public void addSign(String sign) {
            this.inputNumStr += sign;
        }

        public void executeOperation() {
            double inputNum = Double.parseDouble(this.inputNumStr);
            if (this.operation == 5) {
                this.result = Math.sqrt(inputNum);
            } else if (this.operation == 1) {
                this.result = this.result + inputNum;
            } else if (this.operation == 2) {
                this.result = this.result - inputNum;
            } else if (this.operation == 3) {
                this.result = this.result * inputNum;
            } else if (this.operation == 4) {
                this.result = this.result / inputNum;
            }
        }

        public void setOperation(int operation) {
            this.operation = operation;
        }

        public void reset() {
            result = 0d;
            inputNumStr = "";
            operation = 0;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.calc_activity_main);
        View display_text = findViewById(R.id.display_text);

        Calc calc = new Calc();
        View layout = getLayoutInflater().inflate(R.layout.calc_activity_main, null);

        Button key_calc = findViewById(R.id.key_calc);
        Button key_c = findViewById(R.id.key_c);
        Button key_sqr = findViewById(R.id.key_sqr);
        Button key_pn = findViewById(R.id.key_pn);
        Button key_bckp = findViewById(R.id.key_bckp);

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
            }
        };

        findViewById(R.id.key_0).setOnClickListener(digitClickListener);
        findViewById(R.id.key_1).setOnClickListener(digitClickListener);
        findViewById(R.id.key_2).setOnClickListener(digitClickListener);
        findViewById(R.id.key_3).setOnClickListener(digitClickListener);
        findViewById(R.id.key_4).setOnClickListener(digitClickListener);
        findViewById(R.id.key_5).setOnClickListener(digitClickListener);
        findViewById(R.id.key_6).setOnClickListener(digitClickListener);
        findViewById(R.id.key_7).setOnClickListener(digitClickListener);
        findViewById(R.id.key_8).setOnClickListener(digitClickListener);
        findViewById(R.id.key_9).setOnClickListener(digitClickListener);
        findViewById(R.id.key_dot).setOnClickListener(digitClickListener);


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
            }
        };

        findViewById(R.id.key_add).setOnClickListener(operClickListener);
        findViewById(R.id.key_sub).setOnClickListener(operClickListener);
        findViewById(R.id.key_mltp).setOnClickListener(operClickListener);
        findViewById(R.id.key_div).setOnClickListener(operClickListener);
        findViewById(R.id.key_sqr).setOnClickListener(operClickListener);

    }
}