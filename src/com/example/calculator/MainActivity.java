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

	private String mathString;
	private TextView textView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                
        // Enable javascript for the view
        textView = (TextView) findViewById(R.id.textView);
        
        // Set the listener for all the buttons
        ButtonClickListener clickListener = new ButtonClickListener();
        int idList[] = { R.id.button0, R.id.button1, R.id.button2,
          R.id.button3, R.id.button4, R.id.button5, R.id.button6,
          R.id.button7, R.id.button8, R.id.button9, R.id.buttonLeftParen,
          R.id.buttonRightParen, R.id.buttonPlus, R.id.buttonPlus,
          R.id.buttonMinus, R.id.buttonDivide, R.id.buttonTimes,
          R.id.buttonDecimal, R.id.buttonBackspace, R.id.buttonClear };

        for(int id : idList) {
         View v = findViewById(id);
         v.setOnClickListener(clickListener);
        }
        
        setMathString("0");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void setMathString(String s) {
    	mathString = s;
    	textView.setText(s);
    }
    
    private class ButtonClickListener implements OnClickListener {
    	@Override
    	public void onClick(View v) 
    	{
    	 switch (v.getId()) {
    	 case R.id.buttonBackspace:
    	  if(mathString.length() > 0)
//    		  delete stuff here
    	  break;
    	 case R.id.buttonClear:
    	  if(mathString.length() > 0)
    		  setMathString("0");
    	  break;
    	 default:
    		 setMathString(mathString+(((Button)v).getText()));
    	 }
    	 
    	}
    }
    
}
