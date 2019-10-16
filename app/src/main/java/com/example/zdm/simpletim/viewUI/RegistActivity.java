package com.example.zdm.simpletim.viewUI;

import android.content.Intent;
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

import com.example.zdm.simpletim.LoginActivity;
import com.example.zdm.simpletim.R;
import com.example.zdm.simpletim.dao.PersonDAO;
import com.example.zdm.simpletim.model.Person;

/**
 * User: ZDM
 * DateTime: 2019/10/14
 * Description: 注册界面
 */
public class RegistActivity extends AppCompatActivity {

    private AutoCompleteTextView mEmailView;  //用户名
    private EditText rename;                  //真实姓名
    private EditText mPasswordView;           //密码
    private EditText repassword;              //确认密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);//查找用户名控件
        rename=(EditText) findViewById(R.id.rename);                 //真实姓名控件
        mPasswordView = (EditText) findViewById(R.id.password);      //查找密码控件
        repassword=(EditText) findViewById(R.id.repassword);         //重复密码控件

        // 定义输入密码时软键盘的enter键功能(即activity_login.xml中设置的IME动作)
        // setOnEditorActionListener这个方法在我们编辑完之后点击软键盘上的回车键才会触发
        repassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE ) {
                    attemptLogin();   // 调用函数检查登陆信息是否合法
                    return true;
                }
                return false;
            }
        });

        // 注册按钮
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();   // 调用函数检查登陆信息是否合法
            }
        });

    }

    /**
     * 注册信息确认
     */
    private void attemptLogin() {
        // 初始化控件错误信息
        mEmailView.setError(null);
        mPasswordView.setError(null);
        repassword.setError(null);

        // 获取输入信息
        String email = mEmailView.getText().toString();
        String mname=rename.getText().toString();
        String password = mPasswordView.getText().toString();
        String mrepassword = repassword.getText().toString();

        boolean cancel = false;   // 是否是非法信息
        View focusView = null;

        // 检查密码是否有效
        if ( (!TextUtils.isEmpty(password) && !isPasswordValid(password)) || TextUtils.isEmpty(password) ) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // 检查两次密码是否相等
        if(!mrepassword.equals(password)){
            repassword.setError("两次密码不一致");
            focusView = repassword;
            cancel = true;
        }

        // 检查邮箱
        if ( TextUtils.isEmpty(email) ) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }

        //检查真实姓名
        if(mname.equals("")){
            rename.setError("真实姓名不能为空");
            focusView = rename;
            cancel = true;
        }

        // 注册逻辑实现
        if ( cancel ) {
            focusView.requestFocus();
        }
        else {
            String mrename=rename.getText().toString();
            String memail=mEmailView.getText().toString();
            String mpassword=repassword.getText().toString();
            Person person=new Person(mrename,memail,mpassword);

            PersonDAO personDAO=new PersonDAO();
            boolean isSuccess=false;

            if(personDAO.checkUsername(memail)) { // 用户已存在
                Toast.makeText(RegistActivity.this, "用户名已存在，请重新输入", Toast.LENGTH_SHORT).show();
            }
            else {
                isSuccess= personDAO.insert(person); // 添加到数据库
                if(isSuccess) { // 成功添加到数据库
                    Toast.makeText(RegistActivity.this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.setClass(RegistActivity.this,LoginActivity.class); // 转到登陆
                    RegistActivity.this.startActivity(intent);
                }
                else {
                    Toast.makeText(RegistActivity.this, "信息不合法，请确认输入", Toast.LENGTH_SHORT).show();
                }
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
