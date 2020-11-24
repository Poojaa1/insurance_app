package com.example.licapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class plandetails extends AppCompatActivity {

    private Spinner select_plan;
    private String category;
    Button next;
    TextView details ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plandetails);
        select_plan=findViewById(R.id.select_plan);
        details = (TextView) findViewById(R.id.details);

        final Button next = (Button) findViewById(R.id.next);
        String[] planss=new String[]{"Select plans","Child plan","PensionPlan","EndowmentPlan","HelathPlan"};
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
        final int position = select_plan.getSelectedItemPosition();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (select_plan.equals("Select plans")) {
                    details.setText("Please select the plan for details");
                } else {
                    int position = select_plan.getSelectedItemPosition();
                    switch (position) {
                        case 0:
                            details.setText("Please select the plan for details");
                        case 1:
                            details.setText("Child plan is a mix of investment and insurance that usually aids in financial planning for kids' future needs and requirements at the right age. You can protect and secure the future of your child with child insurance plans encompassing child education plans");
                            ;
                            break;
                        case 2:
                            details.setText("A pension plan is a retirement plan that requires an employer to make contributions to a pool of funds set aside for a worker's future benefit. The pool of funds is invested on the employee's behalf, and the earnings on the investments generate income to the worker upon retirement.");
                            ;
                            break;
                        case 3:
                            details.setText("An endowment plan is a life insurance contract designed to pay a lump sum after a specific term (on its 'maturity') or on death. Typical maturities are ten, fifteen or twenty years up to a certain age limit. Endowment policy also pay out in the case of critical illness");
                            ;
                            break;
                        case 4:
                            details.setText("Health Insurance is a type of insurance that offers coverage to the policy holder for medical expenses in case of a health emergency. A health insurance plan chosen by the insured provides coverage for different expenses including surgical expenses, day-care expenses, and critical illness expenses .Discount on Premium: 5% for health workers");
                            ;
                            break;
                    }
                }
            }
        });

    }
}
