package com.metacodersbd.myapplication.ChatSystemUniversal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.metacodersbd.myapplication.R;

public class allDeptChat extends Fragment {

    View view;

    public allDeptChat() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_chat_page, container, false);



    return  null ;

    }
}