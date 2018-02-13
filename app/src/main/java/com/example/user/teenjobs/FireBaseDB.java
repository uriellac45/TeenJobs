package com.example.user.teenjobs;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


/**
 * Created by USER on 13/12/2017.
 */

public class FireBaseDB
{
    private StorageReference storageReference;
    private FirebaseAuth auth;
    private FirebaseUser userAuth;
    private DatabaseReference ref;
    private String userAuthUid;
    private Context context;

    public FireBaseDB(Context context) {

        Firebase.setAndroidContext(context);
        this.context=context;
        auth = FirebaseAuth.getInstance();
        userAuth=auth.getCurrentUser();
        storageReference= FirebaseStorage.getInstance().getReference();

        if (userAuth!=null)
            userAuthUid=userAuth.getUid();
        ref = FirebaseDatabase.getInstance().getReference();
    }

    public void signInWithEmailAndPassword(String mail, String password, final VariableListener returnMessageVariableListener) {
        if (checkInternetConnection()) {
            auth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        userAuth = auth.getCurrentUser();
                        if (userAuth.isEmailVerified()) {
                            ref.child("users").child(userAuth.getUid()).child("active").setValue(true);
                            returnMessageVariableListener.setVar(null);
                        } else {
                            auth.signOut();
                            returnMessageVariableListener.setVar("verify email");
                        }
                    } else
                        returnMessageVariableListener.setVar("email or Password wrong");
                }
            });
        }else
            returnMessageVariableListener.setVar("There is no Internet connection");



    }
    public void CreateNewEmployer(final Employer userInfo, final VariableListener returnMessageVariableListener){
        if (checkInternetConnection()) {
            auth.createUserWithEmailAndPassword(userInfo.getEmail(), userInfo.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        final FirebaseUser firebaseUser=task.getResult().getUser();

                        try {
                            final DatabaseReference user = ref.child("employers").child(firebaseUser.getUid());
                            user.setValue(userInfo);
                            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        returnMessageVariableListener.setVar(null);
                                    } else {
                                        user.removeValue();
                                        firebaseUser.delete();
                                        returnMessageVariableListener.setVar(task.getException().getMessage());
                                    }
                                }
                            });
                        } catch (Exception e) {
                            ref.child("users").child(firebaseUser.getUid()).removeValue();
                            firebaseUser.delete();
                            returnMessageVariableListener.setVar(e.getMessage());
                        }

                    } else {
                        returnMessageVariableListener.setVar(task.getException().getMessage());
                    }
                }
            });
        }else
            returnMessageVariableListener.setVar("There is no Internet connection");

    }

    public void CreateNewEmployee(final Employee userInfo, final VariableListener returnMessageVariableListener){
        if (checkInternetConnection()) {
            auth.createUserWithEmailAndPassword(userInfo.getEmail(), userInfo.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        final FirebaseUser firebaseUser=task.getResult().getUser();

                        try {
                            final DatabaseReference user = ref.child("employees").child(firebaseUser.getUid());
                            user.setValue(userInfo);
                            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        returnMessageVariableListener.setVar(null);
                                    } else {
                                        user.removeValue();
                                        firebaseUser.delete();
                                        returnMessageVariableListener.setVar(task.getException().getMessage());
                                    }
                                }
                            });
                        } catch (Exception e) {
                            ref.child("users").child(firebaseUser.getUid()).removeValue();
                            firebaseUser.delete();
                            returnMessageVariableListener.setVar(e.getMessage());
                        }

                    } else {
                        returnMessageVariableListener.setVar(task.getException().getMessage());
                    }
                }
            });
        }else
            returnMessageVariableListener.setVar("There is no Internet connection");

    }


    private boolean checkInternetConnection() {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = manager.getActiveNetworkInfo();
        return !(ni == null || ni.getState() != NetworkInfo.State.CONNECTED);}

    public void getAllJobs(final OnNewJobListener listener) {

        ref.child("jobTitles").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                //cast snapshot to job:
                Job job = dataSnapshot.getValue(Job.class);

                listener.onNewJob(job);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
