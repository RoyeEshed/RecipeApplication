package com.eshed.fork.Comment;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.eshed.fork.Comment.CommentingAdapter;
import com.eshed.fork.R;
import com.eshed.fork.data.model.Comment;

import java.util.List;

public class Commenting extends AppCompatActivity{

    EditText editTextComment;
    Button btnAddComment;
    RecyclerView RvComment;
    CommentingAdapter commentingAdapter;
    List<Comment> listComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();

        RvComment = findViewById(R.id.rv_comment);
        editTextComment = findViewById(R.id.post_detail_comment);
        btnAddComment = findViewById(R.id.post_detail_add_comment_btn);


        btnAddComment.setOnClickListener(view -> {


            btnAddComment.setVisibility(View.INVISIBLE);
            String comment_content = editTextComment.getText().toString();
            Comment comment = new Comment(comment_content);
            listComment.add(comment);

            commentingAdapter = new CommentingAdapter(comment, listComment);
            RvComment.setAdapter(commentingAdapter);
        });
    }
}
