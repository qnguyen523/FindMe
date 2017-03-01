package com.example.peter.findme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Peter on 11/27/16.
 */
public class Tab3 extends Fragment
{
    private String name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3, container, false);

        TextView welcomeMessage = (TextView) rootView.findViewById(R.id.section_label);
        String message = "Hello "+name + "\nWelcome to FindMe\nYou are at tab 3";
        welcomeMessage.setText(message);
        return rootView;
    }
    // set name
    public void setName(String name)
    {
        this.name = name;
    }
}