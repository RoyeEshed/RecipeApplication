package com.eshed.fork.Comment;

import com.eshed.fork.data.model.Comment;
import com.eshed.fork.R;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CommentingAdapter extends RecyclerView.Adapter<CommentingAdapter.CommentingViewHolder>{

    private Context context;
    private List<Comment> listComment;

    public CommentingAdapter(Context context, List<Comment> listComment){
        this.context = context;
        this.listComment = listComment;
    }

    @NonNull
    @Override
    public CommentingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View row = LayoutInflater.from(context).inflate(R.layout.row_comment,parent,false);
        return new CommentingViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentingViewHolder holder, int position){
        holder.tv_content.setText(listComment.get(position).getContent());
    }

    @Override
    public int getItemCount(){
        return listComment.size();
    }

    public class CommentingViewHolder extends RecyclerView.ViewHolder{

        TextView tv_content;

        public CommentingViewHolder(View itemView){
            super(itemView);
            tv_content = itemView.findViewById(R.id.comment_content);
        }
    }
}
