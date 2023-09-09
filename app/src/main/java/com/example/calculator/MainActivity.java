package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView output, output1;
    MaterialButton buttonC,buttonac,buttonpercentage,buttondivide,button7,button8,button9,button4,button5,button6,button1,button2,button3,buttonplus,buttonminus,buttonmultiply,button0,buttondot,buttonequal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output= findViewById(R.id.output);
        output1= findViewById(R.id.output1);


        assignid(buttonC,R.id.profile_image_c);
        assignid(buttonac,R.id.profile_image_ac);
        assignid(buttonpercentage,R.id.profile_image_percentage);
        assignid(buttondivide,R.id.profile_image_divide);
        assignid(button7,R.id.profile_image_seven);
        assignid(button8,R.id.profile_image_eight);
        assignid(button9,R.id.profile_image_nine);
        assignid(buttonmultiply,R.id.profile_image_into);
        assignid(button4,R.id.profile_image_four);
        assignid(button5,R.id.profile_image_five);
        assignid(button6,R.id.profile_image_six);
        assignid(buttonminus,R.id.profile_image_minus);
        assignid(button1,R.id.profile_image_one);
        assignid(button2,R.id.profile_image_two);
        assignid(button3,R.id.profile_image_three);
        assignid(buttonplus,R.id.profile_image_plus);
        assignid(button0,R.id.profile_image_zero);
        assignid(buttondot,R.id.profile_dot);
        assignid(buttonequal,R.id.profile_image_equals);


    }
    void assignid(MaterialButton btn , int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button=(MaterialButton) v;
        String buttonTxt = button.getText().toString();
        String data = output1.getText().toString();

        if (buttonTxt.equals("AC")){
          output1.setText(" ");
          output.setText("0");
          return;
        }
        if(buttonTxt.equals("=")){
            output1.setText(output.getText());
            return;
        }
        if(buttonTxt.equals("C")){
            data= data.substring(0,data.length()-1);
        }
        else{
            data+=buttonTxt;

        }
        output1.setText(data);

        String finalResult = getResult(data);

        if(!finalResult.equals("Error")){
            output.setText(finalResult);
        }
    }

    String getResult(String data ){
    try{
        Context context = Context.enter();
        context.setOptimizationLevel(-1);
        Scriptable scriptable = context.initSafeStandardObjects();
        String finalResult=   context.evaluateString(scriptable,data,"Javascript",1,null).toString();
        return finalResult;
    }catch (Exception e){
        return "Error";
    }
    }
}