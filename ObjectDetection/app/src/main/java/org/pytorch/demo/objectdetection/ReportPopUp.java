package org.pytorch.demo.objectdetection;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;


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
                name = nameInput.getText().toString();
                mobileNumber = Integer.parseInt(mobileNumberInput.getText().toString());

                openReportActivity();
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

    private void openReportActivity() {
        Intent intent = new Intent(ReportPopUp.this, MapsSelectLocation.class);
        intent.putExtra("Name", "name");
        startActivity(intent);
    }

}
