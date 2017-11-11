package com.github.angads25.roomexample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.github.angads25.roomexample.model.Todo;

/**
 * <p>
 * Created by Angad Singh on 5/11/17.
 * </p>
 */

class ViewTodoDialog extends AppCompatDialog implements View.OnClickListener {
    private Todo todo;

    ViewTodoDialog(Context context, Todo todo) {
        super(context, R.style.DialogTheme);
        this.todo = todo;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_view_todo);

        AppCompatTextView tvTitle = findViewById(R.id.tv_title);
        AppCompatTextView tvText = findViewById(R.id.tv_text);

        tvTitle.setText(todo.getTodoTitle());
        tvText.setText(todo.getTodoText());
        findViewById(R.id.action_close).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action_close: {
                dismiss();
                break;
            }
        }
    }
}
