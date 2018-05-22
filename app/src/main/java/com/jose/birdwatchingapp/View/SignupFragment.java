package com.jose.birdwatchingapp.View;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jose.birdwatchingapp.Presenter.SignupPresenter;
import com.jose.birdwatchingapp.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {

    private String TAG = SignupFragment.class.getSimpleName();
    private EditText userName,password,password2;
    private Button signupButton;
    private TextView loginLink;
    private Spinner spinner;
    private SignupPresenter presenter;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup,
                container, false);
        presenter = new SignupPresenter(this);
        userName = (EditText) view.findViewById(R.id.input_name);
        password = (EditText) view.findViewById(R.id.input_password);
        password2 = (EditText) view.findViewById(R.id.input_password2);
        signupButton = (Button) view.findViewById(R.id.btn_signup);
        loginLink = (TextView) view.findViewById(R.id.link_login);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        presenter.addItemsOnSpinner();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String name = userName.getText().toString();
                //signup();

                String name = userName.getText().toString();
                String password3 = password.getText().toString();
                String password4 = password2.getText().toString();

                String area=String.valueOf(spinner.getSelectedItem());
                if(!area.contains("Elige un área"))
                    presenter.signupButton(name,password3,password4,area);
                else
                    showMessage("Elige un área por favor");
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                //getActivity().finish();
                presenter.loginLink();
            }
        });
        return view;
    }

    public void showMessage(String message){
        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
    }
    public void setSignupButton(boolean vis){
        signupButton.setEnabled(vis);
    }
    public void addItemsOnSpinner(List<String> list){
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.refreshDrawableState();


    }
    //pasar a asynctask, o mirarlo
    public String signup() {
        Log.d(TAG, "Signup");
        String result="Gracias por registrarte";
        if (validate()!="ok") {
            onSignupFailed(validate());
            return validate();
        }
        Log.d(TAG,"no deberia llegar");
        signupButton.setEnabled(false);


        /*String name = userName.getText().toString();
        String password3 = password.getText().toString();
        String password4 = password2.getText().toString();
*/
        // TODO: Implement your own signup logic here.
        //TODO: progress dialog, algo parecido
        onSignupSuccess(result);
        return  result;
    }


    public void onSignupSuccess(String result) {
        signupButton.setEnabled(true);
        //setResult(RESULT_OK, null);
        Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
        getActivity().finish();
    }

    public void onSignupFailed(String result) {
        Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();

        signupButton.setEnabled(true);
    }

    public String validate() {
        String valid = "ok";


        String name = userName.getText().toString();
        String password3 = password.getText().toString();
        String password4 = password2.getText().toString();

        if (name.isEmpty() || name.length() < 4) {
            //_nameText.setError("at least 3 characters");
            valid = "El usuario tiene que tener entre 4 y 10 caracteres";
        } else if (password3.isEmpty() || password4.isEmpty() || password.length() < 4 || password.length() > 10) {
            //_emailText.setError("enter a valid password");
            valid = "La contraseña tiene que tener entre 4 y 10 caracteres";
        } else if (password3 != password4){
            valid = "La contraseña no coincide";
        }
        /* mas condiciones
        if (password3.isEmpty() || password.length() < 4 || password.length() > 10) {
            //_passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            //passwordText.setError(null);
        }*/

        return valid;
    }
}
