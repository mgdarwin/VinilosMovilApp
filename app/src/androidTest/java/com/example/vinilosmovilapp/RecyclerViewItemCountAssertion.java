package com.example.vinilosmovilapp;

import static androidx.test.espresso.matcher.ViewMatchers.assertThat;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
<<<<<<< HEAD
import static org.hamcrest.Matchers.is;

import android.view.View;
=======

import android.util.Log;
import android.view.View;
import android.widget.TextView;
>>>>>>> develop

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

public class RecyclerViewItemCountAssertion implements ViewAssertion {
    private final int expectedCount;

    public RecyclerViewItemCountAssertion(int expectedCount) {
        this.expectedCount = expectedCount;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }
<<<<<<< HEAD

=======
>>>>>>> develop
        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        assertThat(adapter.getItemCount(), greaterThanOrEqualTo(expectedCount));
    }
}
