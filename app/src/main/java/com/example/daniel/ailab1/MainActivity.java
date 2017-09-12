package com.example.daniel.ailab1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int x;
    private int smoothing = 2;
    private int sensebility = 6;
    Girl Blonde, Redhead, Brunete;
    private double[] valuesArray = {0.0, 0.0, 0.0};
    TextView xValue;
    TextView sensetiveValue;
    TextView blondePercent;
    TextView redPercent;
    TextView brunetePercent;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xValue = (TextView)findViewById(R.id.xValue);
        sensetiveValue = (TextView)findViewById(R.id.sensetiveValue);
        blondePercent = (TextView)findViewById(R.id.blondePercent);
        redPercent = (TextView)findViewById(R.id.redPercent);
        brunetePercent = (TextView)findViewById(R.id.brunetePercent);
        textResult = (TextView)findViewById(R.id.textResult);

        Blonde = new Girl(0, "Blonde", 100.0);
        Redhead = new Girl(50, "Redhead", 0);
        Brunete = new Girl(100, "Brunete", 0);

        onChanges();

        final SeekBar xChanger = (SeekBar)findViewById(R.id.xChanger);
        xChanger.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                x = xChanger.getProgress();
                onChanges();
            }
        });

        final SeekBar sensetiveChanger = (SeekBar)findViewById(R.id.sensetiveChanger);
        sensetiveChanger.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                sensebility = 2 + sensetiveChanger.getProgress() * 2;
                smoothing = 4 + sensebility;
                onChanges();
            }
        });
    }

    private void onChanges(){
        /*Update here canvas (graphics) and values*/
        valuesArray[0] = function(Blonde.getShift(), sensebility, smoothing, x);
        valuesArray[1] = function(Redhead.getShift(), sensebility, smoothing, x);
        valuesArray[2] = function(Brunete.getShift(), sensebility, smoothing, x);

        Blonde.setPercentage(valuesArray[0] * 100 /
                (valuesArray[0] + valuesArray[1] + valuesArray[2]));
        Redhead.setPercentage(valuesArray[1] * 100 /
                (valuesArray[0] + valuesArray[1] + valuesArray[2]));
        Brunete.setPercentage(valuesArray[2] * 100 /
                (valuesArray[0] + valuesArray[1] + valuesArray[2]));

        xValue.setText(Integer.toString(x));
        sensetiveValue.setText(Integer.toString(sensebility));
        blondePercent.setText(Double.toString(Blonde.getPercentage()));
        redPercent.setText(Double.toString(Redhead.getPercentage()));
        brunetePercent.setText(Double.toString(Brunete.getPercentage()));

        String output = "";
        output += toneSwitch(Blonde.getPercentage(), Blonde.getColorValue());
        output += toneSwitch(Redhead.getPercentage(), Redhead.getColorValue());
        output += toneSwitch(Brunete.getPercentage(), Brunete.getColorValue());

        textResult.setText(output);
    }

    private String toneSwitch(double perc, String hairColor){
        String res = "";
        if (perc < 1)
            return res;
        else if (perc < 20)
            res = "not";
        else if (perc < 40)
            res = "already not";
        else if (perc < 60)
            res = "either";
        else if (perc < 80)
            res = "still";
        else
            res = "already";
        return res + " " + hairColor + " ";
    }

    private double function(int a, int b, int c, int x){
        /*a - shift of func
        * b -
        * с -
        * x - value of seekBar
        * */
        double res;
        if (b != 0)
            res = 1/(1+Math.pow(((x-a)/b), c));
        else
            res = 0;
        return res;
    }
}
