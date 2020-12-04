package com.eshed.fork.Recipe.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.R;
import com.eshed.fork.Recipe.view.ViewHolders.DirectionViewHolder;
import com.eshed.fork.Recipe.view.ViewHolders.IngredientViewHolder;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
    private RecipeRecyclerViewAdapter adapter;
    private Drawable icon;
    private final ColorDrawable background;

    public interface SwipeListener {
        void onItemDeleted(int position, RecipeComponentViewModel componentViewModel);
    }

    public SwipeListener listener;

    public SwipeToDeleteCallback(RecipeRecyclerViewAdapter adapter) {
        super(0, ItemTouchHelper.LEFT);
        this.adapter = adapter;

        icon = ContextCompat.getDrawable(adapter.getContext(),
                R.drawable.ic_delete_white_36);
        background = new ColorDrawable(Color.RED);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        if (viewHolder instanceof DirectionViewHolder) {
            listener.onItemDeleted(position, ((DirectionViewHolder) viewHolder).directionViewModel);
        } else if (viewHolder instanceof IngredientViewHolder) {
            listener.onItemDeleted(position, ((IngredientViewHolder) viewHolder).ingredientViewModel);
        }
    }

    @Override
    public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof DirectionViewHolder) {
            if (((DirectionViewHolder) viewHolder).inEditMode) {
                return super.getSwipeDirs(recyclerView, viewHolder);
            }
            return 0;
        } else if (viewHolder instanceof IngredientViewHolder) {
            if (((IngredientViewHolder) viewHolder).inEditMode) {
                return super.getSwipeDirs(recyclerView, viewHolder);
            }
            return 0;
        } else {
            return 0;
        }
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        View itemView = viewHolder.itemView;
        int backgroundCornerOffset = 20;

        int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
        int iconTop = itemView.getTop() + (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
        int iconBottom = iconTop + icon.getIntrinsicHeight();

        if (dX < 0) { // Swiping left
            int iconLeft = itemView.getRight() - iconMargin - icon.getIntrinsicWidth();
            int iconRight = itemView.getRight() - iconMargin;
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

            background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                    itemView.getTop(), itemView.getRight(), itemView.getBottom());
        } else {
            background.setBounds(0, 0, 0, 0);
            icon.setBounds(0, 0, 0, 0);
        }

        background.draw(c);
        icon.draw(c);
    }
}