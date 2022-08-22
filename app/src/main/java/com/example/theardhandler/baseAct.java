package com.example.theardhandler;

import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public abstract class baseAct <T extends ViewBinding> extends AppCompatActivity  implements View.OnClickListener {
             T binding ;  
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = initViewBinding();
        setContentView(binding.getRoot());
        initViews();
    }

    protected abstract void initViews();

    protected abstract T initViewBinding();

    @Override
    public void onClick(View view) {
        //do nothing 
    }
}
