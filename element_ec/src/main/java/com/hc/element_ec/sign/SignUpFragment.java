package com.hc.element_ec.sign;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.hc.element_ec.R;
import com.heyskill.element_core.fragments.BaseFragment;

/**
 * 注册页面
 * @author adai
 */
public class SignUpFragment extends BaseFragment {

    private TextInputEditText mEmail = null;
    private TextInputEditText mPassword = null;
    private AppCompatButton btn_sign_in;

    private ISignListener mISignListener = null;


    @Override
    public Object setLayout() {
        return R.layout.element_sign_up;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        initViewId(rootView);
    }

    private void initViewId(View rootView){
        mEmail = rootView.findViewById(R.id.edit_sign_in_email);
        mPassword = rootView.findViewById(R.id.edit_sign_in_password);
        btn_sign_in = rootView.findViewById(R.id.btn_sign_in);
        //注册
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSignIn();
            }
        });

    }

    //检查注册的格式
    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }
        return isPass;
    }

    private void onClickSignIn(){
        if(checkForm()){
            Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
           // SignHandler.onSignIn("123",mISignListener);
        }
    }
}
