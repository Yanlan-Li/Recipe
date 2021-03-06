package com.example.recipe.user.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.recipe.R;
import com.example.recipe.databinding.ActivityRegisterBinding;

public class RegisterViewModel extends BaseObservable {

    ActivityRegisterBinding activityRegisterBinding;
    Application application;
    Context context;

    //RegisterModel registerModel=new RegisterModel();

    private String email;
    private String name;
    private String password;
    private String re_password;

    @Bindable
    public String getRe_password() {
        return re_password;
    }

    public void setRe_password(String re_password) {
        this.re_password = re_password;
        notifyPropertyChanged(R.id.register_re_password);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(R.id.register_name);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(R.id.register_password);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(R.id.register_email);
    }

    public void registerAct(View view){
        context=view.getContext();
        if (!password.equals(re_password)){
            Toast.makeText(view.getContext(),"两次密码不一致！",Toast.LENGTH_SHORT).show();
        }else{
            Log.e("TAG", "注册信息为:"+name+"\n"+email+"\n"+password+"\n"+re_password+"\n" );
            /*State state=registerModel.register(name,email,password);
            if (state.getCode()==200){
                Intent intent=new Intent(context, LoginActivity.class);
                intent.putExtra("loginUser",new LoginUser(email,name));
                context.startActivity(intent);
            }
            Toast.makeText(context,state.getMessage(),Toast.LENGTH_SHORT).show();

             */
        }
    }
}
