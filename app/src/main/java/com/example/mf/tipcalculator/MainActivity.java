package com.example.mf.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    public EditText bill;
    SeekBar seekBar;
    TextView low;
    TextView high;
    TextView tipLow;
    TextView tipHigh;
    TextView totalLow;
    TextView totalHigh;
    Button minusBtn;
    Button plusBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bill = (EditText) findViewById(R.id.editText);
        tipLow = (TextView)findViewById(R.id.tipLow);
        tipHigh = (TextView)findViewById(R.id.tipHigh);
        totalLow = (TextView)findViewById(R.id.totalLow);
        totalHigh = (TextView)findViewById(R.id.totalHigh);

        seekBar = (SeekBar) findViewById(R.id.seekBar);

        minusBtn = (Button) findViewById(R.id.minus);
        plusBtn = (Button) findViewById(R.id.plus);

        low = (TextView)findViewById(R.id.low);
        high = (TextView)findViewById(R.id.high);

        seekBarr();

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBar.setProgress(seekBar.getProgress() - 1);
                Toast.makeText(seekBar.getContext(), "Value: " + seekBar.getProgress() / 5, Toast.LENGTH_SHORT).show();
            }
        });
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBar.setProgress(seekBar.getProgress()+1);
                Toast.makeText(seekBar.getContext(), "Value: " + seekBar.getProgress() / 5, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public float getNum(float percent, float total, float add){
        return ((percent+add)/100 *total);
    }

    public void seekBarr(){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float progress1;
            float total;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                progress1 = (float) (progress / 5.0f);
                DecimalFormat df = new DecimalFormat("###.##");
                low.setText(Float.toString(progress1) + " %");
                high.setText(Float.toString(progress1+5) + " %");

                if(bill.getText().toString().matches("") || bill.getText().toString() == null){
                    total = 0.0f;
                }else{
                    total = Float.valueOf(bill.getText().toString());
                }

                tipLow.setText("$ "  + df.format(getNum(progress1,total,0f)));
                tipHigh.setText("$ " + df.format(getNum(progress1,total,5f)));

                totalLow.setText("$ " + df.format(getNum(progress1, total, 0f) + total));
                totalHigh.setText("$ " + df.format(getNum(progress1, total, 5f) + total));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
}
