package com.group59.studentCourseHelper.data.ui.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.group59.studentCourseHelper.R;
import com.group59.studentCourseHelper.data.Home;
import com.group59.studentCourseHelper.data.UserModel;


public class RegisterActivity extends AppCompatActivity {

    EditText email,name,password;
    ImageView profile;
    Button submit;
    Uri imageUri;
    ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        email=findViewById(R.id.et_email);
        password=findViewById(R.id.et_password);
        name=findViewById(R.id.et_name);
        profile=findViewById(R.id.iv_profile);
        submit=findViewById(R.id.b_register);
        loadingProgressBar = findViewById(R.id.loading);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signup();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload();
            }
        });

    }

    private void upload() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, 10);
    }

    private boolean validateForm() {
        final boolean[] valid = {true};

        String v_email = email.getText().toString();
        if (TextUtils.isEmpty(v_email)) {
            email.setError("The email shouldn't be empty");
            valid[0] = false;
        }
        else if (!v_email.contains("@")) {
            email.setError("invalid format");
            valid[0] = false;
        }
        else if (!v_email.contains(".com")) {
            email.setError("invalid format");
            valid[0] = false;
        }
        else if (!v_email.contains(".com")) {
            email.setError("invalid format");
            valid[0] = false;
        }
       // else if (isExistUserName(v_email)){
       //     email.setError("This name is exist");
        //    valid[0] = false;
        //}
        else
        {
            email.setError(null);

        }

        String v_password = password.getText().toString();
        if (TextUtils.isEmpty(v_password)) {
            password.setError("Required a password");
            valid[0] = false;
        }
        else if (password.length() < 6) {
            password.setError("password must be at least 6 characters long");
            valid[0] = false;
        }
        else {
            password.setError(null);
        }


        String v_name = name.getText().toString();
        if (TextUtils.isEmpty(v_name)) {
            name.setError("User name shouldn't be empty");
            valid[0] = false;
        } else {
            name.setError(null);
        }

        return valid[0];
    }

    private boolean isExistUserName(String userName){
        final boolean[] has_userName = {false};
//if(userName.equals("5@163.com")){has_userName[0]=true;}

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        //String uid = FirebaseAuthentication.getInstance().getCurrentUser().getUid();
        //Query queries=ref.child(uid).orderByChild("name").equalTo(userName);
        //queries
        ref.child(uid).orderByChild("email").equalTo(userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String name=dataSnapshot.child("name").getValue().toString();

                if (dataSnapshot.child("email").getValue(String.class) == null) {
                    //username doesn't exist
                    //do something
                    // Toast.makeText(RegisterActivity.this, "username Exists", Toast.LENGTH_LONG).show();
                    has_userName[0] = false;

                } else {
                    //username exist
                    //do something
                    has_userName[0] = true;
                }
            }


   /*
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(); //Getting root reference
        myRef.child("users").orderByChild("email").equalTo(userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue(String.class) == null) {
                    //username doesn't exist
                    //do something
                   // Toast.makeText(RegisterActivity.this, "username Exists", Toast.LENGTH_LONG).show();


                } else {
                    //username exist
                    //do something
                    has_userName[0] = true;
                }
            }
*/
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return has_userName[0];
    }

    private void signup() {

        if (!validateForm()) {
        return;
        }

        loadingProgressBar.setVisibility(View.VISIBLE);

        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email.getText().toString(),
                        password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    final String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
                    //final String uid=task.getResult().getUser().getUid();
                    final StorageReference storageReference= FirebaseStorage.getInstance().getReference().child("users").child(uid);

                    storageReference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                            if (task.isSuccessful()){

                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {

                                        String imageurl=uri.toString();

                                        UserModel userModel=new UserModel();
                                        userModel.name=name.getText().toString();
                                        userModel.email=email.getText().toString();
                                        userModel.password=password.getText().toString();
                                        userModel.uid=uid;
                                        userModel.imageurl=imageurl;

                                        FirebaseDatabase.getInstance().getReference().child("users").child(uid).setValue(userModel);


                                        Intent intent = new Intent(RegisterActivity.this,Home.class);
                                        intent.putExtra("key",userModel.name);   // String

                                        startActivity(intent);
                                        finish();

                                    }
                                });

                            }else{
                                Toast.makeText(RegisterActivity.this,"error",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else{

                    Toast.makeText(RegisterActivity.this,"error",Toast.LENGTH_SHORT).show();

                }
                loadingProgressBar.setVisibility(View.GONE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 10 && resultCode == RESULT_OK) {
            profile.setImageURI(data.getData());
            imageUri = data.getData();
        }
    }
}
