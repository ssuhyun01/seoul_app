package org.techtown.seoulapp_trial1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.PopupWindow;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;





public class Calender extends AppCompatActivity {


    CalendarView calendarView;
    TextView myDATE;
    Button b;
    EditText et;
    TextView tv;
    String sfName = "myfile";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);


        b = (Button) findViewById(R.id.Ok);
        et = (EditText) findViewById(R.id.dateInput);
        tv = (TextView) findViewById(R.id.dateInputreview);

        EditText dateInput = (EditText) findViewById(R.id.dateInput);
        dateInput.setHint("일정을 입력하세요");

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = et.getText().toString();
                tv.setText(str);
            }
        });
        SharedPreferences sf = getSharedPreferences(sfName, 0);
        String str = sf.getString("Date", "");
        et.setText(str);


        calendarView = (CalendarView) findViewById(R.id.calendarView);

        myDATE = (TextView) findViewById(R.id.myDate);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                                                 @Override
                                                 public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                                                     String date = (month + 1) + "/" + dayOfMonth + "/" + year;
                                                     myDATE.setText(date);
                                                 }
                                             }
        );






    }


    TextView txtResult;

    protected void onStop() {
        super.onStop();
        SharedPreferences sf = getSharedPreferences(sfName, 0);
        SharedPreferences.Editor editor = sf.edit();
        String str = et.getText().toString();
        editor.putString("Date", str);
        editor.putString("xx", "xx");
        editor.commit();


    }




}




//    //버튼
//    public void mOnPopupClick(View v){
//        //데이터 담아서 팝업(액티비티) 호출
//        Intent intent = new Intent(this, PopupActivity.class);
//        intent.putExtra("data", "Textpopup");
//        startActivityForResult(intent, 1);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(requestCode==1){
//            if(resultCode==RESULT_OK){
//                //데이터 받기
//                String result = data.getStringExtra("result");
//                txtResult.setText(result);
//            }
//        }
//    }
//}

