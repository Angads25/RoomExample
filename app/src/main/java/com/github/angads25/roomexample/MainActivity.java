package com.github.angads25.roomexample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.github.angads25.roomexample.adapter.TodoListAdapter;
import com.github.angads25.roomexample.interfaces.DataCallbackListener;
import com.github.angads25.roomexample.interfaces.OnItemClickListener;
import com.github.angads25.roomexample.interfaces.OnItemLongClickListener;
import com.github.angads25.roomexample.model.Todo;
import com.github.angads25.roomexample.utils.RecyclerViewTouchHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        OnItemClickListener,
        OnItemLongClickListener,
        DataCallbackListener<Todo> {

    private TodoListAdapter todoListAdapter;
    private CoordinatorLayout parentLayout;
    private int updatePosition = -1;
    private List<Todo> todos;
    private StaggeredGridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parentLayout = findViewById(R.id.root_layout);

        todos = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.todo_list);

        int spanCount = 2;
        layoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        todoListAdapter = new TodoListAdapter(MainActivity.this, todos);
        recyclerView.setAdapter(todoListAdapter);

        findViewById(R.id.action_create).setOnClickListener(this);

        RecyclerViewTouchHandler handler = new RecyclerViewTouchHandler(this, recyclerView);
        handler.setOnClickListener(this);
        handler.setOnlongClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action_create: {
                CreateTodoDialog createTodoDialog = new CreateTodoDialog(MainActivity.this);
                createTodoDialog.setDataCallbackListener(MainActivity.this);
                createTodoDialog.show();
            }
        }
    }

    @Override
    public void onClick(RecyclerView parent, View v, int position) {
        ViewTodoDialog dialog = new ViewTodoDialog(MainActivity.this, todos.get(position));
        dialog.show();
    }

    @Override
    public void onClick(View v, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose an Option");

        String[] animals = {
            "Edit",
            "Delete"
        };
        builder.setItems(animals, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: {
                        CreateTodoDialog createTodoDialog = new CreateTodoDialog(MainActivity.this, todos.get(position));
                        createTodoDialog.setDataCallbackListener(MainActivity.this);
                        createTodoDialog.show();
                        updatePosition = position;
                        break;
                    }
                    case 1: {
                        Thread T1 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                todos.remove(position);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Snackbar.make(parentLayout, "Todo Deleted", Snackbar.LENGTH_SHORT).show();
                                        todoListAdapter.notifyItemRemoved(position);
                                    }
                                });
                            }
                        });
                        T1.start();
                        break;
                    }
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onDataReceived(final Todo data) {
        if(updatePosition == -1) {
            todos.add(0, data);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Snackbar.make(parentLayout, "Todo Created", Snackbar.LENGTH_SHORT).show();
                    todoListAdapter.notifyItemInserted(0);
                    layoutManager.scrollToPosition(0);
                    updatePosition = -1;
                }
            });
        }
        else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Snackbar.make(parentLayout, "Todo Updated", Snackbar.LENGTH_SHORT).show();
                    todos.remove(updatePosition);
                    todoListAdapter.notifyItemRemoved(updatePosition);
                    todos.add(0, data);
                    todoListAdapter.notifyItemInserted(0);
                    layoutManager.scrollToPosition(0);
                    updatePosition = -1;
                }
            });
        }
    }
}
