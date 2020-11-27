package com.eshed.fork.Settings.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.eshed.fork.Data.DbRepository;
import com.eshed.fork.Fork;
import com.eshed.fork.R;
import com.eshed.fork.Settings.vm.UserSettingsViewModel;
import com.eshed.fork.Util.GlideApp;
import com.eshed.fork.Util.Util;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class SettingsActivity extends AppCompatActivity {
    UserSettingsViewModel vm;
    ImageView userImage;
    TextView starredRecipes;
    TextView submittedRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (this.getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        TextView title = toolbar.findViewById(R.id.toolbar_title);
        title.setText("Settings");

        userImage = findViewById(R.id.user_image);
        userImage.setOnClickListener(view -> {
            changeUserImage();
        });

        starredRecipes = findViewById(R.id.recipes_starred);
        submittedRecipes = findViewById(R.id.recipes_submitted);

        Util.setupTabBar(this);

        Fork app = (Fork) getApplication();
        vm = new UserSettingsViewModel(app.uid, DbRepository.getInstance());
        setupUserInfo();
    }

    public void changeUserImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    public void setupUserInfo() {
        TextView emailTextview = findViewById(R.id.email_textview);
        TextView usernameTextview = findViewById(R.id.username_textview);

        String email = vm.user.getEmail();
        emailTextview.setText(email);

        String username = vm.user.getUsername();
        usernameTextview.setText(username);

        String size = "" + vm.user.getSubmittedRecipes().size();
        submittedRecipes.setText(size);

        String imageURL = vm.user.getImageURL();
        FirebaseStorage ref = FirebaseStorage.getInstance();
        StorageReference gsReference = ref.getReferenceFromUrl(imageURL);
        GlideApp.with(this).load(gsReference).centerCrop().circleCrop().into(userImage);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedPhotoUri = data.getData();
            // TODO: update image on screen
            uploadImageToStorage(selectedPhotoUri);

        }
    }

    private void uploadImageToStorage(Uri uri) {
        String filename = "/images/users/" + UUID.randomUUID().toString();
        StorageReference ref = FirebaseStorage.getInstance().getReference(filename);
        ref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                ref.getDownloadUrl().addOnSuccessListener(uri1 -> {
                    Log.d("TAG", "onSuccess: image url" + uri1.toString());
                    updateUserImage(uri1.toString());
                });
            }
        });
    }

    private void updateUserImage(String imageURL) {
        vm.saveUserImage(imageURL);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference gsReference = storage.getReferenceFromUrl(vm.user.getImageURL());
        GlideApp.with(this).load(gsReference).centerCrop().circleCrop().into(userImage);
    }
}
