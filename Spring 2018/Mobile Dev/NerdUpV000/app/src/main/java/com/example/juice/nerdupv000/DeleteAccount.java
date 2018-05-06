package com.example.juice.nerdupv000;

import android.content.Intent;
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


public class DeleteAccount extends android.app.Fragment {
    private Button delete;
    private EditText currentEmail, currentPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete_account, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        delete = view.findViewById(R.id.deleteButton);
        currentEmail = view.findViewById(R.id.currentEmail);
        currentPassword = view.findViewById(R.id.currentPassword);

        getListeners();
    }

    public void getListeners(){
        delete.setOnTouchListener(new View.OnTouchListener() {
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
                                        user.delete()
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(getView().getContext(), "Account Deleted", Toast.LENGTH_SHORT).show();
                                                            getFragmentManager().popBackStack();
                                                            FirebaseAuth.getInstance().signOut();
                                                            Intent intent = new Intent(getView().getContext(), Login.class);
                                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                            startActivity(intent);
                                                        } else {
                                                            Toast.makeText(getView().getContext(), "Error: unable to delete account", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                    } else {
                                        Toast.makeText(getView().getContext(), "Incorrect Credentials.  Unable to delete account", Toast.LENGTH_SHORT).show();
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
