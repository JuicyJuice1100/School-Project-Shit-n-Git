package com.example.juice.nerdupv000;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.support.constraint.Constraints.TAG;

//please note that you can change the password but doesn't update that in the database.  Didn't have enough time to implement.

public class ChangeEmail extends android.app.Fragment {
    public Button sendEmailButton;
    public EditText newEmail, currentEmail, currentPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_email, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        newEmail = view.findViewById(R.id.newEmail);
        sendEmailButton= view.findViewById(R.id.sendEmail);
        currentEmail = view.findViewById(R.id.currentEmail);
        currentPassword = view.findViewById(R.id.currentPassword);

        getListeners();
    }

    public void getListeners(){
        sendEmailButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                   final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    // Get auth credentials from the user for re-authentication. The example below shows
                    // email and password credentials but there are multiple possible providers,
                    // such as GoogleAuthProvider or FacebookAuthProvider.
                    AuthCredential credential = EmailAuthProvider
                            .getCredential(currentEmail.getText().toString(), currentPassword.getText().toString());

                    // Prompt the user to re-provide their sign-in credentials
                    user.reauthenticate(credential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "User re-authenticated.");
                                        user.updateEmail(newEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(getView().getContext(), "Email updated", Toast.LENGTH_SHORT).show();
                                                    Log.d("email", "User email address updated.");
                                                    getFragmentManager().popBackStack();
                                                } else {
                                                    Log.d("email", "Unable to update email.");
                                                    Toast.makeText(getView().getContext(), "Error: unable to update", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    } else {
                                        Toast.makeText(getView().getContext(), "Incorrect Credentials.  Unable to update email.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}
