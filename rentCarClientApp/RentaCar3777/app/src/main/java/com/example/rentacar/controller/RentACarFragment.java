package com.example.rentacar.controller;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rentacar.R;

public class RentACarFragment extends Fragment implements View.OnClickListener
{

    private ImageView imageView2;
    private TextView call;
    private ImageView imageView3;
    private TextView textView3;
    private ImageView imageView4;
    private TextView email;
    private ImageView imageView6;
    private TextView link;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-05-31 09:45:00 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(View rootView) {
        imageView2 = (ImageView) rootView.findViewById( R.id.imageView2 );
        call = (TextView)  rootView.findViewById( R.id.call );
        imageView3 = (ImageView) rootView.findViewById( R.id.imageView3 );
        textView3 = (TextView) rootView.findViewById( R.id.textView3 );
        imageView4 = (ImageView) rootView.findViewById( R.id.imageView4 );
        email = (TextView) rootView.findViewById( R.id.emailRentCar);
        imageView6 = (ImageView) rootView.findViewById( R.id.imageView6 );
        link = (TextView) rootView.findViewById( R.id.link );
        link.setOnClickListener(this);
        call.setOnClickListener(this);
        email.setOnClickListener(this);


    }


    public RentACarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);


        View rootView = inflater.inflate(R.layout.fragment_take_go, container, false);
        findViews(rootView);
        link.setClickable(true);
        link.setMovementMethod(LinkMovementMethod.getInstance());

        String text = "<a href='http://www.rentacar.fr/en/'> www.rentacar.fr </a>";
        link.setText(Html.fromHtml(text));
        link.setMovementMethod(LinkMovementMethod.getInstance());
        return rootView;
    }

    @Override
    public void onClick(View v)
    {
        if(v==call)
            call("0527193890");
        if(v==email)
            email();
        if(v==link)
            link();
    }

    private void link() {
        Intent intent = new Intent(getContext(), WebActivity.class);
        startActivity(intent);
    }

    private void email() {
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

/* Fill it with Data */
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"chanismile0340@gmail.com"});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Contacting Rent a Car management");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");

/* Send it off to the Activity-Chooser */
        getActivity().startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }

    private void call(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }


}