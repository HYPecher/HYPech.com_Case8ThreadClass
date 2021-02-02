package com.example.carson_ho.demoforthread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //主布局中定义了一个按钮用以启动线程
    Button button;

    //步骤1:创建线程类,继承自Thread类
    private class MyThread extends Thread{

        private int ticket = 100;//一个窗口有100张票
        private String name; //窗口名, 也即是线程的名字

        public MyThread(String name){
            this.name=name;
        }

        //在run方法里复写需要进行的操作
        @Override
        public void run(){
            while (ticket>0){
                ticket--;
                System.out.println(name + "卖掉了1张票，剩余票数为:"+ticket);

                try {
                    Thread.sleep(1000);//卖票速度是1s一张
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

            //Button按下时会开启一个新线程执行卖票
            button = (Button) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //步骤2:创建线程类的实例
                    //创建二个线程，模拟二个窗口卖票
                    MyThread mt1 = new MyThread("窗口1");
                    MyThread mt2 = new MyThread("窗口2");

                    //步骤3：调用start()方法开启线程
                    //启动二个线程，也即是窗口，开始卖票
                    mt1.start();
                    mt2.start();

                }
            });

        }
    }

