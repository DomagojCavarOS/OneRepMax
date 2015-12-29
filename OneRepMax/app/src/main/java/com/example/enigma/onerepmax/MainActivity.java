package com.example.enigma.onerepmax;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    Spinner Excer;
    TextView Result;
    EditText EWeight, EReps;
    ImageButton Calc;
    TextInputLayout TWeight, TReps;
    String GymExc = "Bench Press";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ResCalc GymBaja = new ResCalc(0, 0);

        Excer = (Spinner) findViewById(R.id.spinner);
        EWeight = (EditText) findViewById(R.id.editText);
        EReps = (EditText) findViewById(R.id.editText2);
        Calc = (ImageButton) findViewById(R.id.imageButton);
        Result = (TextView) findViewById(R.id.textView3);
        TWeight = (TextInputLayout) findViewById(R.id.input_weight);
        TReps = (TextInputLayout) findViewById(R.id.input_reps);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.gymExercises, R.layout.support_simple_spinner_dropdown_item);
        Excer.setAdapter(adapter);

        EWeight.addTextChangedListener(new MyTextWatcher(EWeight));
        EReps.addTextChangedListener(new MyTextWatcher(EReps));
        Calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((EWeight.getText().toString().trim().length() == 0)) {
                    TWeight.setError("Please enter weight");
                    EWeight.requestFocus();

                } else if (EReps.getText().toString().trim().length() == 0) {
                    TReps.setError("Please enter reps");
                    TReps.requestFocus();

                } else {
                    float result = GymBaja.calculate(Float.parseFloat(EWeight.getText().toString()), Float.parseFloat(EReps.getText().toString()));
                    Result.setText("One rep Max of " + GymExc + " is: " + Math.round(result) + " kg");
                    EReps.clearFocus();
                    EWeight.clearFocus();
                }
            }
        });

        Excer.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        GymExc = Excer.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public class MyTextWatcher implements TextWatcher {

        public View view;

        public MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()) {
                case R.id.editText:
                    validateWeight();
                    break;
                case R.id.editText2:
                    validateReps();
                    break;
            }
        }
    }

    private boolean validateReps() {
        if (EReps.getText().toString().trim().length()==0) {
            TReps.setError("Please enter weight");
            EReps.requestFocus();
            // requestFocus(inputName);
            return false;
        } else {
            TReps.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateWeight() {
        if (EWeight.getText().toString().trim().length()==0) {
            TWeight.setError("Please enter weight");
            EWeight.requestFocus();
            return false;
        } else {
            TWeight.setErrorEnabled(false);
        }

        return true;
    }

}
