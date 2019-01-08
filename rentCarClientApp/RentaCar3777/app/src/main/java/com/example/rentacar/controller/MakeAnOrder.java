package com.example.rentacar.controller;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.net.ParseException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentacar.R;
import com.example.rentacar.model.backend.Static;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MakeAnOrder extends Fragment implements View.OnClickListener{
    private TextView textView2;
    private LinearLayout linearLayout2;
    private TextView beginDate;
    private TextView textView7;
    private LinearLayout Date;
    private CalendarView calendarView4;
    private CalendarView calendarView3;
    private Button button6;
    java.sql.Date date1=null;
    java.sql.Date date2=null;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-06-03 18:46:21 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(LayoutInflater inflater, ViewGroup container,View view) {
        textView2 = (TextView)view.findViewById( R.id.textView2 );
        linearLayout2 = (LinearLayout)view.findViewById( R.id.linearLayout2 );
        beginDate = (TextView)view.findViewById( R.id.beginDate );
        textView7 = (TextView)view.findViewById( R.id.textView7 );
        Date = (LinearLayout)view.findViewById( R.id.Date );
        calendarView4 = (CalendarView)view.findViewById( R.id.calendarView4 );
        calendarView3 = (CalendarView)view.findViewById( R.id.calendarView3 );
        button6 = (Button)view.findViewById( R.id.button6 );
        button6.setOnClickListener( this );

        calendarView4.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String curDate1 = (String.valueOf(year)+"-"+String.valueOf(month+1)+"-"+String.valueOf(dayOfMonth));//seting the selected date into a format
                date1= java.sql.Date.valueOf((curDate1));

            }
        });
        calendarView3.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
               String  curDate2 = (String.valueOf(year)+"-"+String.valueOf(month+1)+"-"+String.valueOf(dayOfMonth));//seting the selected date into a format
                date2= java.sql.Date.valueOf((curDate2));
            }
        });


    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2018-06-03 18:46:21 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {

        Fragment myfragment=null;
        if ( v == button6 )
        {
                if(date1==null||date2==null) {//if dates was not selected
                Toast.makeText(getContext(), "Please choose rental dates", Toast.LENGTH_SHORT).show();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(this).attach(this).commit();
            }
            else {

                Date d = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = df.format(d);
                d=java.sql.Date.valueOf((formattedDate));
                long difference=date2.getTime()-date1.getTime();
                float daysBetween = (difference / (1000*60*60*24))+1;
                long difference1=date1.getTime()-d.getTime();
                float daysBetween1 = (difference1 / (1000*60*60*24))+1;
                long difference2=date2.getTime()-d.getTime();
                float daysBetween2 = (difference2 / (1000*60*60*24))+1;
                if (daysBetween <= 0||daysBetween1<=0||daysBetween2<=0) {//chack if the dates are correct
                    Toast.makeText(getContext(), "enter valid rental dates", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Static.d1=date1;
                    Static.d2=date2;
                    myfragment = new BranchList();
                    if (myfragment != null) {
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_container, myfragment, "findThisFragment")
                                .addToBackStack(null)
                                .commit();

                    }
                }
            }



        }

    }



    public MakeAnOrder() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_make_an_order, container, false);
        findViews(inflater, container,rootView);
        return rootView;
    }

}
