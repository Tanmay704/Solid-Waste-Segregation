package org.pytorch.demo.objectdetection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class ReportPopUp extends AppCompatActivity{

    public String name;
    public int mobileNumber;

    EditText nameInput;

    EditText mobileNumberInput;

    Button submitButton,backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_popup_layout);

        nameInput = (EditText) findViewById(R.id.nameInput);
        mobileNumberInput = (EditText) findViewById(R.id.mobileNumberInput);

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  try{
                if (nameInput.getText().toString().trim().isEmpty() || mobileNumberInput.getText().toString().trim() == null)
                {
                    Toast.makeText(ReportPopUp.this,
                            "Empty field not allowed!",
                            Toast.LENGTH_SHORT).show();
                }else{
                name = nameInput.getText().toString();

                mobileNumber = Integer.parseInt(mobileNumberInput.getText().toString());

                openReportActivity(name, String.valueOf(mobileNumber));}
            } catch(Exception e){
                      Toast.makeText(ReportPopUp.this,
                              "Empty field not allowed!",
                              Toast.LENGTH_SHORT).show();
                  }
            }

        });

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDashboard();
            }
        });

    }

    private void openDashboard() {
        Intent intent = new Intent(ReportPopUp.this, FrontPageActivity.class);
        startActivity(intent);
    }

    private void openReportActivity(String name, String mobNO) {
        Intent intent = new Intent(ReportPopUp.this, MapsSelectLocation.class);
        intent.putExtra("Name", name);
        intent.putExtra("MOB_NUMBER",mobNO);
        startActivity(intent);

    }

}
