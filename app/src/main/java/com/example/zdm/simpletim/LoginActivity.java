package com.example.zdm.simpletim;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zdm.simpletim.dao.PersonDAO;
import com.example.zdm.simpletim.utils.APPglobal;
import com.example.zdm.simpletim.viewUI.RegistActivity;

/**
 * User: ZDM
 * DateTime: 2019/10/14
 * Description: app登录页面
 */
public class LoginActivity extends AppCompatActivity {

    private static boolean isExit=false;       // 判断是否直接退出程序
    private AutoCompleteTextView mEmailView;   // 用户名
    private EditText mPasswordView;            // 密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);   // 获得用户名控件
        mPasswordView = (EditText) findViewById(R.id.password);         // 获得密码控件

        // 定义输入密码时软键盘的enter键功能(即activity_login.xml中设置的IME动作)
        // setOnEditorActionListener这个方法在我们编辑完之后点击软键盘上的回车键才会触发
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE ) {
                    attemptLogin();   // 调用函数检查登陆信息是否合法
                    return true;
                }
                return false;
            }
        });

        // 登录按钮
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();   // 调用函数检查登陆信息是否合法
            }
        });

        // 注册按钮
        Button mRegisterButton = (Button) findViewById(R.id.regist);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(LoginActivity.this,RegistActivity.class);   // 跳转到注册页面
                LoginActivity.this.startActivity(intent);
            }
        });

        // 无法登陆按钮
        Button mLoginHelpButton = (Button) findViewById(R.id.login_help);
        mLoginHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "未完成", Toast.LENGTH_SHORT).show();
            }
        });

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit=false;
        }
    };

    /**
     * 登录信息确认
     */
    private void attemptLogin() {
        // 初始化错误信息为null
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // 获取输入信息.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;   // 是否是非法信息
        View focusView = null;

        // 检查密码是否有效
        if ( (!TextUtils.isEmpty(password) && !isPasswordValid(password)) || TextUtils.isEmpty(password) ) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // 检查邮箱
        if ( TextUtils.isEmpty(email) ) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }

        if ( cancel ) {//非法信息
            focusView.requestFocus();//标签用于指定屏幕内的焦点View。
        }
        else { //合法信息
            // 登录跳转逻辑
            PersonDAO personDAO = new PersonDAO();
            boolean success = personDAO.chechLogin(email,password);
            if(success) { // 信息合法
                APPglobal.NAME = PersonDAO.findNameByUsername(email);   // 根据账号找到当前账号真实名字
                APPglobal.USERNAME = email;
                Intent intent=new Intent();
                intent.setClass(LoginActivity.this,MainActivity.class);   // 跳转到app主页面
                LoginActivity.this.startActivity(intent);
            }
            else {
                Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 密码是否和非法，至少需要4位
     * @param password
     * @return
     */
    private boolean isPasswordValid(String password) {
        return password.length() >= 4;
    }
}
