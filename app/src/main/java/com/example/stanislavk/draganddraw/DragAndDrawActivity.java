package com.example.stanislavk.draganddraw;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class DragAndDrawActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return DragAndDrawFragment.newInstance();
    }
}
