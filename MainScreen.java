package com.example.blah.anagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.Random;
import android.widget.TextView;

public class MainScreen extends AppCompatActivity {
    EditText input;
    Button submit;
    Button reset;
    Random r = new Random();
    CardView cardview;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        input = (EditText)findViewById(R.id.input);
        submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                String inputStr = input.getText().toString().toUpperCase();
                if (!inputStr.equals("")) {
                    createResult(inputStr, makeAnagram(inputStr));
                } else {
                    input.requestFocus();
                }
            };
        });
        reset = (Button)findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                input.setText("");
            }
        });
        cardview = (CardView) findViewById(R.id.resultCard);
        resultText = (TextView)findViewById(R.id.resultText);
    }

    String makeAnagram(String input){
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> numbers = new ArrayList<>(input.length());
        for (int i = 0; i < input.length(); i++){
            numbers.add(i);
        }
        while (numbers.size() > 0){
            int index = r.nextInt(numbers.size());
            sb.append(input.charAt(numbers.get(index)));
            numbers.remove(index);
        }
        return sb.toString();
    }
    void createResult (String originalStr, String resultStr){
        if (originalStr.length() > 15){
            resultText.setPadding(15, 100, 15, 100);
        } else {
            resultText.setPadding(15, 175, 15, 25);
        }
        resultText.setText("Anagram of " + originalStr + "\n\n"+ resultStr);
    }
}
