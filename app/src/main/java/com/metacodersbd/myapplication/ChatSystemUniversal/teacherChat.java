package com.metacodersbd.myapplication.ChatSystemUniversal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.metacodersbd.myapplication.R;

public class teacherChat extends Fragment {
    View view;

    public teacherChat() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.teacher_chat_list, container, false);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
