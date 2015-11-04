package com.example.enigma.onerepmax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    Spinner Excer;
    TextView Weight,Reps,Result;
    EditText EWeight,EReps;
    Button Calc;
    String GymExc="Bench Press";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ResCalc GymBaja = new ResCalc(0, 0);

        Excer = (Spinner) findViewById(R.id.spinner);
        EWeight = (EditText) findViewById(R.id.editText);
        EReps = (EditText) findViewById(R.id.editText2);
        Calc = (Button) findViewById(R.id.button);
        Result = (TextView) findViewById(R.id.textView3);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.gymExercises, R.layout.support_simple_spinner_dropdown_item);
        Excer.setAdapter(adapter);


        Calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((EWeight.getText().toString().trim().length() ==0)||EReps.getText().toString().trim().length()==0 ) {
                    Toast.makeText(getApplicationContext(),"Please check entries",Toast.LENGTH_LONG).show();
                }
               else {
                    float result = GymBaja.calculate(Float.parseFloat(EWeight.getText().toString()), Float.parseFloat(EReps.getText().toString()));
                    Result.setText("One rep Max of " + GymExc + " is: " + result + " kg");
                }
            }
        });

        Excer.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        GymExc=Excer.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
