package my.edu.taruc.lab34picker;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    //Variable Declaration
    Calendar today = Calendar.getInstance();
    TextView Ages = findViewById(R.id.textViewAge);
    TextView EligibleAmount = findViewById(R.id.textViewEligibleAmount);
    EditText Amount = findViewById(R.id.editTextAccountBalance);
    Button buttonDateOfBirth = findViewById(R.id.buttonDOB);
    Button calculate = findViewById(R.id.buttonCalculate);
    int minBasicSaving = 0;
    double eligibleAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }
    public void processDatePickerResult(int year, int month, int day)
    {
        //User Date Of Birth
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string +
                "/" + day_string + "/" + year_string);
        buttonDateOfBirth.setText(dateMessage);
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);

        //Calculate Ages
        //---------------------------------//
        int age = today.get(Calendar.YEAR) - year;

        if (today.get(Calendar.DAY_OF_YEAR) < day){
            age--;
        }
        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();
        //------------------------------//

        //Display Ages
        Ages.setText(ageS);

        //Calculate Eligible Amount
        int ages = Integer.parseInt(ageS);
        if(ages >= 16 && ages <= 20)
        {
            minBasicSaving = 5000;
        }
        else if(ages >= 21 && ages <= 25)
        {
            minBasicSaving = 14000;
        }
        else if(ages >= 26 && ages <= 30)
        {
            minBasicSaving = 29000;
        }
        else if(ages >= 31 && ages <= 35)
        {
            minBasicSaving = 50000;
        }
        else if(ages >= 36 && ages <= 40)
        {
            minBasicSaving = 78000;
        }
        else if(ages >= 41 && ages <= 45)
        {
            minBasicSaving = 116000;
        }
        else if(ages >= 46 && ages <= 50)
        {
            minBasicSaving = 165000;
        }
        else if(ages >= 51 && ages <= 55)
        {
            minBasicSaving = 228000;
        }
        else
        {
            minBasicSaving = 0;
        }


    }

    public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void CalculateEligibleAmount(View view)
    {
        String savingAccountAmount = Amount.getText().toString();
        int userInput = Integer.parseInt(savingAccountAmount);

        eligibleAmount = (userInput - minBasicSaving) * 0.3;
        String result = Double.toString(eligibleAmount);
        if(eligibleAmount > 0)
        {
            EligibleAmount.setText(result);
        }
        else
        {
            EligibleAmount.setText("The Amount is not eligible");
        }
    }


}
