package com.example.recipe.user.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.recipe.R;
import com.example.recipe.user.view.RegisterActivity;

public class LoginViewModel extends BaseObservable {

    private String email;
    private String password;
  //  State state;

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(R.id.login_email);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(R.id.login_password);
    }

    //跳转到注册页面
    public void goToRegister(View view){
        Context context=view.getContext();
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    //    登录
    public void loginAct(View view){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                //state= HttpUtils.login("http://47.106.76.106:8080/hotNewsSys/login?email="+email+"&password="+password);
            }
        });
        thread.start();
        try{
            thread.join();
            Thread.sleep(20);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
       /* if (state.getCode()==200){
            Intent intent=new Intent(view.getContext(), HomeActivity.class);
            LoginUser loginUser=new LoginUser();
            loginUser.setEmail(email);
            intent.putExtra("loginUser",loginUser);
            view.getContext().startActivity(intent);
        }
        Toast.makeText(view.getContext(), state.getMessage(), Toast.LENGTH_SHORT).show();
        */
    }
}
