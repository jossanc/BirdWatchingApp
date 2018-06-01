package com.jose.birdwatchingapp.View;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jose.birdwatchingapp.Presenter.LoginPresenter;
import com.jose.birdwatchingapp.R;

public class LoginFragment extends Fragment {

    private static final String TAG = LoginFragment.class.getSimpleName();
    private EditText editUserName, editPassword;
    private Button loginButton;
    private TextView linkSignUp;
    private LoginPresenter presenter;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login,
                container, false);
        // Enlazar views  
        editUserName = (EditText) view.findViewById(R.id.input_user);
        editPassword = (EditText) view.findViewById(R.id.input_password);
        loginButton = (Button) view.findViewById(R.id.btn_login);
        linkSignUp = (TextView) view.findViewById(R.id.link_signup);
        presenter = new LoginPresenter(this);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String name = editUserName.getText().toString();
                String pass = editPassword.getText().toString();
                presenter.loginButton(name,pass);
            }
        });
        linkSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                presenter.linkSignup();
            }
        });

        return view;
    }
    public void showMessage(String message){
        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
    }
    public void setLoginButton(boolean vis){
        loginButton.setEnabled(vis);
    }

}
