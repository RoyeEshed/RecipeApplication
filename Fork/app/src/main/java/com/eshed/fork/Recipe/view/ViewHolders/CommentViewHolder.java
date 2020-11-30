package com.eshed.fork.Recipe.view.ViewHolders;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.eshed.fork.Data.DbRecipeRepository;
import com.eshed.fork.Data.model.Comment;
import com.eshed.fork.Data.model.UserAccount;
import com.eshed.fork.R;
import com.eshed.fork.Recipe.vm.component.CommentViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;
import com.eshed.fork.Util.GlideApp;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CommentViewHolder extends RecipeViewHolder {

    TextView username;
    TextView comment;
    EditText content;
    ImageView userimage;
    Button addButton;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    Comment comments;
    static String COMMENT_KEY = "Comment";
    String PostKey;
    DbRecipeRepository repository;
    String uid;
    UserAccount user;

    private CommentViewModel commentViewModel;

    public CommentViewHolder(@NonNull View itemView){
        super(itemView);
        username = itemView.findViewById(R.id.comment_username);
        comment = itemView.findViewById(R.id.comment_content);
        content = itemView.findViewById(R.id.comment_comment);
        userimage = itemView.findViewById(R.id.comment_user_img);
        addButton = itemView.findViewById(R.id.comment_add_comment_btn);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        repository = DbRecipeRepository.getInstance();
        uid = firebaseUser.getUid();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addButton.setVisibility(View.INVISIBLE);
                String comment_content = content.getText().toString();
                //String uimg = firebaseUser.getPhotoUrl().toString();
                //DatabaseReference commentReference = firebaseDatabase.getReference(COMMENT_KEY).child(PostKey).push();
                comments.setContent(comment_content);
                //comments.setUid(uid);
               // comments.setUimg(uimg);

//                commentReference.setValue(comments).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        content.setText("");
//                        addButton.setVisibility(View.VISIBLE);
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                    }
//                });

            }
        });

    }

    @Override
    public void bind(RecipeComponentViewModel vm) {
        commentViewModel = (CommentViewModel) vm;
        repository.getUserWithUID(uid).subscribe(userAccount -> {
            user = userAccount;
        });
        GlideApp.with(itemView).load(user.getImageURL()).centerCrop().into(userimage);
        username.setText("" + user.getUsername());
        //comment.setText("" + comments.getContent());
        //userimage.setImageURI(firebaseUser.getPhotoUrl());
        content.setEnabled(commentViewModel.isEditable());
    }
}
