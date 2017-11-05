package com.github.angads25.roomexample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.github.angads25.roomexample.dao.AppDBHandler;
import com.github.angads25.roomexample.dao.AppDao;
import com.github.angads25.roomexample.interfaces.DataCallbackListener;
import com.github.angads25.roomexample.model.Todo;

/**
 * <p>
 * Created by Angad Singh on 5/11/17.
 * </p>
 */

public class CreateTodoDialog extends AppCompatDialog implements View.OnClickListener {
    private Todo todo;

    private AppCompatEditText edtTitle;
    private AppCompatEditText edtText;

    private DataCallbackListener<Todo> dataCallbackListener;

    CreateTodoDialog(Context context) {
        super(context, R.style.DialogTheme);
    }

    CreateTodoDialog(Context context, Todo todo) {
        super(context, R.style.DialogTheme);
        this.todo = todo;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_create_todo);

        edtTitle = findViewById(R.id.edt_title);
        edtText = findViewById(R.id.edt_text);

        if(todo != null) {
            edtTitle.setText(todo.todoTitle);
            edtText.setText(todo.todoText);
        }
        findViewById(R.id.action_cancel).setOnClickListener(this);
        findViewById(R.id.action_save).setOnClickListener(this);
    }

    void setDataCallbackListener(DataCallbackListener<Todo> dataCallbackListener) {
        this.dataCallbackListener = dataCallbackListener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action_save: {
                if(edtTitle.getText().toString().isEmpty()) {
                    edtTitle.setError("Pss! Tell us what you are doing!");
                    edtTitle.requestFocus();
                    return;
                } else if(edtText.getText().toString().isEmpty()) {
                    edtText.setError("Fill this, or you will forget.");
                    edtText.requestFocus();
                    return;
                }

                if(this.todo == null) {
                    todo = new Todo();
                    todo._id = System.currentTimeMillis();
                    todo.timeStamp = System.currentTimeMillis();
                    todo.todoTitle = edtTitle.getText().toString();
                    todo.todoText = edtText.getText().toString();
                    final AppDao dao = AppDBHandler.getInstance(getContext()).getAppDao();
                    Thread T1 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            dao.insertTodo(todo);
                            dataCallbackListener.onDataReceived(todo);
                        }
                    });
                    T1.start();
                } else {
                    todo.timeStamp = System.currentTimeMillis();
                    todo.todoTitle = edtTitle.getText().toString();
                    todo.todoText = edtText.getText().toString();
                    final AppDao dao = AppDBHandler.getInstance(getContext()).getAppDao();
                    Thread T1 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            dao.updateTodo(todo);
                            dataCallbackListener.onDataReceived(todo);
                        }
                    });
                    T1.start();
                }
            }

            case R.id.action_cancel: {
                dismiss();
                break;
            }
        }
    }
}
