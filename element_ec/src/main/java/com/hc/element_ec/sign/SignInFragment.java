package com.hc.element_ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hc.element_ec.R;
import com.heyskill.element_core.fragments.BaseFragment;

public class SignInFragment extends BaseFragment {

    private TextInputEditText mEmail = null;
    private TextInputEditText mPassword = null;
    private AppCompatButton btn_sign_in;
    private AppCompatTextView tv_link_sign_up;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof  ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.element_sign_in;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        initViewID(rootView);
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSignIn();
            }
        });
        tv_link_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickToSignUp();
            }
        });
    }

    private void initViewID(View rootView){
        mEmail = rootView.findViewById(R.id.edit_sign_in_email);
        mPassword = rootView.findViewById(R.id.edit_sign_in_password);
        btn_sign_in = rootView.findViewById(R.id.btn_sign_in);
        tv_link_sign_up = rootView.findViewById(R.id.tv_link_sign_up);
    }

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
            //SignHandler.onSignIn("123",mISignListener);
        }
    }

    private void onclickToSignUp(){
       // getSupportDelegate().start(new SignUpFragment());
    }
}
