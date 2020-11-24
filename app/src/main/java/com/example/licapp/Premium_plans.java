package com.example.licapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class Premium_plans extends AppCompatActivity {
    private Spinner select_plan;
    private String category;
    EditText name, bday,age ,sumas,term;
    TextView result ;
    SeekBar ageseekbar,termseekbar;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_plans);
        select_plan=findViewById(R.id.select_plan);
        ageseekbar=findViewById(R.id.ageseekbar);
        termseekbar=findViewById(R.id.termseekbar);
        age=findViewById(R.id.Age);
        name = (EditText) findViewById(R.id.ed1);
        age = (EditText) findViewById(R.id.ed3);
        bday = (EditText) findViewById(R.id.ed2);
        term = (EditText) findViewById(R.id.ed4);
        sumas = (EditText) findViewById(R.id.ed5);
        result = (TextView) findViewById(R.id.result);
        final EditText e1 = (EditText) findViewById(R.id.ed3);
        final EditText e2 = (EditText) findViewById(R.id.ed4);
        Button button = (Button) findViewById(R.id.button);
        ageseekbar.setProgress(0);
ageseekbar.setMax(100);
        termseekbar.setProgress(0);
        termseekbar.setMax(20);
        String[] planss=new String[]{"Select plans","Child plan","PensionPlan","EndowmentPlan","HelathPlan"};
        ageseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                e1.setText(String.valueOf(progress));            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        termseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                e2.setText(String.valueOf(progress));            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        select_plan.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,planss));
        select_plan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category=select_plan.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (term.getText().toString().equals("") || sumas.getText().toString().equals("")) {
                    result.setText("Term & Sum Assured are must for Premium Calculation:");
                } else {
                    int position = select_plan.getSelectedItemPosition();
                    float q = Float.parseFloat(term.getText().toString());
                    float w = Float.parseFloat(sumas.getText().toString());
                    float cal1 = 0, cal2 = 0, cal3 = 0;
                    switch (position) {
                        case 1:
                            cal1 = (int) ((w * (1.11)) / q);
                            cal2 = (int) ((w * (1.14)) / (4 * q));
                            cal3 = (int) ((w * (1.17)) / (12 * q));
                            break;
                        case 2:
                            cal1 = (int) ((w * (1.11)) / q);
                            cal2 = (int) ((w * (1.12)) / (4 * q));
                            cal3 = (int) ((w * (1.15)) / (12 * q));
                            break;
                        case 3:
                            cal1 = (int) ((w * (1.12)) / q);
                            cal2 = (int) ((w * (1.13)) / (4 * q));
                            cal3 = (int) ((w * (1.17)) / (12 * q));
                            break;
                        case 4:
                            cal1 = (int) ((w * (0.9)) / q);
                            cal2 = (int) ((w * (1.2)) / (4 * q));
                            cal3 = (int) ((w * (1.4)) / (12 * q));
                            break;
                    }
                    result.setText("\nTerm:" + term.getText().toString() + "\nSum Assured:" +
                                    sumas.getText().toString() + "\nMonthly Premium:" + cal3 + "\nQuaterly Premium;" + cal2 + "\nYearly Premium:" + cal1);
                }
            }
        });

    }
}
