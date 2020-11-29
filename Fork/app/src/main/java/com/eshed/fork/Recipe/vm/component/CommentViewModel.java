package com.eshed.fork.Recipe.vm.component;

import com.eshed.fork.Data.model.Comment;

public class CommentViewModel implements RecipeComponentViewModel, RecipeComponentIsEditable {

    public Comment comment;
    public Boolean isEditable;

    public CommentViewModel(Comment comment, Boolean isEditable){
        this.comment = comment;
        this.isEditable = isEditable;
    }

    public void changeComment(String changeComment){
        this.comment.setContent(changeComment);
    }

    @Override
    public Type getType() { return Type.Comment; }

    @Override
    public void setIsEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    @Override
    public boolean isEditable() {
        return isEditable;
    }

}
