package com.github.angads25.roomexample.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.angads25.roomexample.R;
import com.github.angads25.roomexample.model.Todo;

import java.util.List;

/**
 * <p>
 * Created by Angad Singh on 5/11/17.
 * </p>
 */

public class TodoListAdapter extends RecyclerView.Adapter <TodoListAdapter.TodoViewHolder> {
    private Context context;
    private List<Todo> todoArrayList;

    public TodoListAdapter(Context context, List<Todo> todoArrayList) {
        this.context = context;
        this.todoArrayList = todoArrayList;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_todo, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        holder.title.setText(todoArrayList.get(position).getTodoTitle());
        holder.text.setText(todoArrayList.get(position).getTodoText());
    }

    @Override
    public int getItemCount() {
        return todoArrayList.size();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView title;
        private AppCompatTextView text;

        TodoViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            text = itemView.findViewById(R.id.text);
        }
    }
}
