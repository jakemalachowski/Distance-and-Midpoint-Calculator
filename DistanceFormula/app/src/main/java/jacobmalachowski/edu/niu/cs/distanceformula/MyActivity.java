package jacobmalachowski.edu.niu.cs.distanceformula;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


public class MyActivity extends Activity implements View.OnClickListener {

    EditText x1, x2, y1, y2;
    Button calcBtn, clearBtn;
    TextView distanceResultTV, midpointResultTV;
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        x1 = (EditText)findViewById(R.id.x1);
        x2 = (EditText)findViewById(R.id.x2);
        y1 = (EditText)findViewById(R.id.y1);
        y2 = (EditText)findViewById(R.id.y2);
        distanceResultTV = (TextView)findViewById(R.id.distanceResultTV);
        midpointResultTV = (TextView)findViewById(R.id.midpointResultTV);

        calcBtn = (Button)findViewById(R.id.calcButton);
        calcBtn.setOnClickListener(this);

        clearBtn = (Button)findViewById(R.id.clearButton);
        clearBtn.setOnClickListener((android.view.View.OnClickListener) this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        double x1result = 0, x2result = 0, y1result = 0, y2result = 0, xSquared, ySquared, xMid, yMid;
        DecimalFormat df = new DecimalFormat("#.00");
        switch (v.getId())
        {
            case R.id.calcButton:
            {

                try {

                    //convert the edittext fields to text and then to actual numbers
                    x1result = Double.parseDouble(x1.getText().toString());
                    y1result = Double.parseDouble(y1.getText().toString());
                    x2result = Double.parseDouble(x2.getText().toString());
                    y2result = Double.parseDouble(y2.getText().toString());
                } catch(NumberFormatException n)
                {
                    //Handles the exception of trying to calculate without entering values
                    Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_LONG).show();
                }

                //calculate the distance formula
                //square both numbers for distance formula
                xSquared = pow(x1result - x2result, 2);
                ySquared = pow(y1result - y2result, 2);

                //get the distance in a format that can be read by format()
                double distance = sqrt(xSquared + ySquared);
                distanceResultTV.setText(df.format(distance));


                //calculate midpoint
                xMid = (x1result + x2result) / 2;
                yMid = (y1result + y2result) / 2;

                midpointResultTV.setText("(" + df.format(xMid) + ", " + df.format(yMid) + ")");

                break;
            }
            case R.id.clearButton: {
                //set all textViews back to their original state
                distanceResultTV.setText("");
                midpointResultTV.setText("");

                x1.setText("");
                x2.setText("");
                y1.setText("");
                y2.setText("");

                x1.requestFocus();

                break;
            }
        }
    }
}
