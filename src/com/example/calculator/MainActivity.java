package com.example.calculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private String currentString = "0";
	private String previousString = null;
	private boolean isTempStringShown;
	private TextView textView;
	private int currentOpperand = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                
        // Enable javascript for the view
        textView = (TextView) findViewById(R.id.textView);
        
        // Set the listener for all the buttons
        int numberButtons[] = { R.id.button0, R.id.button1, R.id.button2,
          R.id.button3, R.id.button4, R.id.button5, R.id.button6,
          R.id.button7, R.id.button8, R.id.button9
        };
        int opperandButtons[] = {
        		R.id.buttonPlus, R.id.buttonMinus, R.id.buttonDivide, R.id.buttonTimes,
                R.id.buttonDecimal, R.id.buttonClear, R.id.buttonEquals
        };

        NumberButtonClickListener numberClickListener = new NumberButtonClickListener();
        for(int id : numberButtons) {
         View v = findViewById(id);
         v.setOnClickListener(numberClickListener);
        }
        
        OpperandButtonClickListener oppClickListener = new OpperandButtonClickListener();
        for(int id : opperandButtons) {
         View v = findViewById(id);
         v.setOnClickListener(oppClickListener);
        }
        
        setCurrentString("0");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void setCurrentString(String s) {
    	currentString = s;
    	textView.setText(s);
    }
    public String getCurrentString() {
    	return currentString;
    }
    
    public void clear() {
		isTempStringShown = false;
		setCurrentString("0");
		previousString = null;
    }
    public void calculate(int id) {
		double curr = Double.parseDouble(getCurrentString());
		double result = curr;
		if (previousString != null) {
			double prev = Double.parseDouble(previousString);
			switch(currentOpperand) {
				case R.id.buttonPlus: result = prev + curr; break;
				case R.id.buttonMinus: result = prev - curr; break;
				case R.id.buttonTimes: result = prev * curr; break;
				case R.id.buttonDivide: result = prev / curr; break;
				default: break;
			}
		}
		currentOpperand = id;
		previousString = ""+result;
		setCurrentString(previousString);
		isTempStringShown = true;
    }
    
    private class NumberButtonClickListener implements OnClickListener {
    	@Override
    	public void onClick(View v)  {
    		String text = (String) ((Button)v).getText();    		
    		if (currentString.equals("0") || isTempStringShown) {
    			isTempStringShown = false;
    			setCurrentString(text);
    		}else {
    			setCurrentString(getCurrentString()+text);
    		}
    	}
    }
    
    private class OpperandButtonClickListener implements OnClickListener {
    	@Override
    	public void onClick(View v)  {
    		int id = v.getId();
    		if (id == R.id.buttonClear) {
    			clear();
    		}else if (id == R.id.buttonEquals) {
    			calculate(currentOpperand);
    		}else if (id == R.id.buttonDecimal) {
    			if (!getCurrentString().contains(".")) {
    				if (isTempStringShown) {
    					isTempStringShown = false;
    					setCurrentString("0");
    				}
    				setCurrentString(getCurrentString()+".");
    			}
    		}else {
    			calculate(id);
    		}
    			
    	}
    }
    
}
