package com.example.zdm.simpletim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.zdm.simpletim.db.DatabaseHelper;

import java.util.Timer;
import java.util.TimerTask;

/**
 * User: ZDM
 * DateTime: 2019/10/14
 * Description: app欢迎页面
 * 设置启动模式为singleTask
 */
public class WelcomeActivity extends Activity {

    public static final String EXIST = "exist";   // 声明一个静态常量，用作退出WelcomeActivity的Tag

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // 创建数据库
        DatabaseHelper.setContext(this.getApplicationContext());   // getApplicationContext(): 返回应用的上下文，生命周期是整个应用，应用摧毁，它才摧毁

        // 跳转到LoginActivity
        final Intent intent=new Intent();   // final限制intent再指向别的对象
        intent.setClass(WelcomeActivity.this, LoginActivity.class);   // 模拟跳转到登陆注册界面

        // 开启一个简单的定时器
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);   // 要推迟执行的方法(模拟点开app时的缓冲界面)
            }
        };
        timer.schedule(timerTask, 2000);   // 两秒后执行
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {//判断其他Activity启动本Activity时传递来的intent是否为空
            //获取intent中对应Tag的布尔值
            boolean isExist = intent.getBooleanExtra(EXIST, false);
            //如果为真则退出本Activity
            if (isExist) {
                this.finish();
            }
        }
    }
}
/**
 * Timer是一种定时器工具，用来在一个后台线程计划执行指定任务，它可以计划执行一个任务一次或反复多次
 * TimerTask一个抽象类，它的子类代表一个可以被Timer计划的任务
 *
 * 用Timer线程实现和计划执行一个任务的基础步骤：
 * 1.实现自定义的TimerTask的子类，run方法包含要执行的任务代码。
 * 2.实例化Timer类，创建计时器后台线程。
 * 3.制定执行计划。这里用schedule方法，第一个参数是TimerTask对象，第二个参数表示开始执行前的延时时间
 *
 * schedule方法实际可有三个参数：
 * 第一个参数就是TimerTask类型的对象
 * 第二个参数有两种类型，第一种是long类型，表示多长时间后开始执行，另一种是Date类型，表示从那个时间后开始执行
 * 第三个参数就是执行的周期，为long类型，如果第三个参数不写的话，那么定时器就是1秒后执行，定时器只执行一次就不再执行了
 */
