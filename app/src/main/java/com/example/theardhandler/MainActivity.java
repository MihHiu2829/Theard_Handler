package com.example.theardhandler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.theardhandler.databinding.ActivityMainBinding;

public class MainActivity extends baseAct<ActivityMainBinding> {
    private Thread th ;
    private static final int MSG_UPDATE_COUNTING = 113 ;
    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if(message.what == MSG_UPDATE_COUNTING)
            {
                int i = message.arg1 ;
                binding.tvCouting.setText(String.format("%s",i));
            }
            return false;
        }
    });

    @Override
    protected void initViews() {
        binding.btStart.setOnClickListener(v -> startCounting());
        binding.btStop.setOnClickListener(v -> stopCounting());
    }

    Runnable rb1 = new Runnable() {
        @Override
        public void run() {
            for (int i= 1 ;  i<= 30 ; i++)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
//                    return ;
                    break ;
                }
                Message msg = new Message() ;
                msg.what = MSG_UPDATE_COUNTING ;
                msg.arg1 =  i ;
                msg.setTarget(mHandler);
                msg.sendToTarget();

            }

        }
    };
    private void stopCounting() {
        if(th != null && th.isAlive())
        {
            th.interrupt();
        }

    }

    private void startCounting() {
        if(th == null || !th.isAlive())
        {
            th = new Thread(rb1);
            th.start();

        }

    }

    @Override
    protected ActivityMainBinding initViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }
}