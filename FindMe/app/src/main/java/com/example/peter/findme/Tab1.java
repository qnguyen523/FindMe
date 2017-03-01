package com.example.peter.findme;

/**
 * Created by Peter on 11/27/16.
 */
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class Tab1 extends Fragment
{
    private String name;
    public View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);

        TextView welcomeMessage = (TextView) rootView.findViewById(R.id.section_label);
        String message = "Hello "+name + "\nWelcome to FindMe\nYou are at tab 1";
        welcomeMessage.setText(message);
        setView(rootView);
        return rootView;
    }
    // set view
    public void setView(View view)
    {
        this.view = view;
    }
    // set name
    public void setName(String name)
    {
        this.name = name;
    }
    // get view
    public View getView(){ return this.view; }
}
