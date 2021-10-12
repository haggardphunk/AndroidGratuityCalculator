package com.example.csci3800_hw4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText totalAmountEditText;
    private EditText taxAmountEditText;
    private TextView tipAmountTextView;
    private TextView grandTotalEditText;
    private RadioButton zeroRadioButton;
    private RadioButton fiveRadioButton;
    private RadioButton tenRadioButton;
    private RadioButton twentyRadioButton;
    private static final boolean DEBUG = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //opted to not use binding because there are so few variables for this app.
        totalAmountEditText = findViewById(R.id.totalAmountEditText);
        taxAmountEditText = findViewById(R.id.taxAmountEditText);
        tipAmountTextView = findViewById(R.id.tipAmountTextView);
        grandTotalEditText = findViewById(R.id.grandTotalEditText);
        zeroRadioButton = findViewById(R.id.zeroRadioButton);
        fiveRadioButton = findViewById(R.id.fiveRadioButton);
        tenRadioButton = findViewById(R.id.tenRadioButton);
        twentyRadioButton = findViewById(R.id.twentyRadioButton);
    }

    public void clearForm(View view) {
        clearForm();
    }
    public void clearForm(){
        //clears totalAmountEditText, taxAmountEditText, and resets radio selection to 0%
        totalAmountEditText.setText("");
        taxAmountEditText.setText("");
        tipAmountTextView.setText("");
        grandTotalEditText.setText("");
        zeroRadioButton.setChecked(true);
    }

    public void calculate(View view) {
        calculate();
    }
    public void calculate(){
        double total=0;
        double tax = 0;
        double tipPercent = 0;
        double grandTotal;
        double tipTotal;
        char op;

        DecimalFormat precision = new DecimalFormat("#,###.00");
        NumberFormat cf = NumberFormat.getCurrencyInstance(Locale.US);

        try {
            total = Double.parseDouble(totalAmountEditText.getText().toString());
        }
        catch(NumberFormatException ex){
            if(DEBUG){
                Log.i("debug", "Value of total is "+total);
            }
            total = 0.0;
        }
        try {
            tax = Double.parseDouble((taxAmountEditText.getText().toString()));
        }
        catch(NumberFormatException ex){
            if(DEBUG){
                Log.i("debug", "Value of tax is "+tax);
            }
            tax = 0.0;
        }

        //if statements to set the percentage of the tip.
        if(zeroRadioButton.isChecked()){
            tipPercent = .0;
        }
        if(fiveRadioButton.isChecked()){
            tipPercent = .05;
        }
        if(tenRadioButton.isChecked()){
            tipPercent = .10;
        }
        if(twentyRadioButton.isChecked()){
            tipPercent = .20;
        }
        //now calculate the tip amount and set the text view
        tipTotal = total * tipPercent;
        grandTotal = total+tipTotal+tax;
        tipAmountTextView.setText(cf.format(tipTotal));
        grandTotalEditText.setText(cf.format(grandTotal));

    }
}