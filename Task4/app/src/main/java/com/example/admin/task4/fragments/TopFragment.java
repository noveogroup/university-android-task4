package com.example.admin.task4.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.task4.R;

public class TopFragment extends Fragment {
    public TopFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top, null);
        TextView textView = (TextView)v.findViewById(R.id.top_fragment_text);
        textView.setText("Top fragment");
        return v;
    }
}
