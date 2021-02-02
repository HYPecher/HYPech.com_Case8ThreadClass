package com.hypech.case8thread.threadclass1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Define a button to start the thread
    Button button;

    // Step1: Create class MyThread extended from Android's Thread
    private class MyThread extends Thread{

        private int ticketQty = 100;   // initial 100 tickets for each window
        private String winName;        // window's name i.e. thread's name

        public MyThread(String pName){
            this.winName = pName;
        }

        // Step2: rewrite run() method to do something
        @Override
        public void run(){
            while (ticketQty>0){
                ticketQty--;
                System.out.println(winName + "Sold 1 ticket, left: "+ticketQty);

                try {
                    Thread.sleep(1000);     //selling ticket speed is 1 piece/second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            button = (Button) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Step3: instance of the thread class
                    // create 2 threads here for 2 windows.
                    MyThread mt1 = new MyThread("Win 1");
                    MyThread mt2 = new MyThread("Win 2");

                    //Step 4: start the thread with start()
                    //start 2 threads for win1 and win1. Two windows selling tickets same time.
                    mt1.start();
                    mt2.start();
                }
            });
        }
    }

