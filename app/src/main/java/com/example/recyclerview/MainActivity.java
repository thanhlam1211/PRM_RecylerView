package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class  MainActivity extends AppCompatActivity implements IClickUserItemListener{

    private RecyclerView rcvUser;
    private UserAdapter sUserAdapter;
    private SearchView searchView;
    List<User> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        //initiation
        rcvUser = findViewById(R.id.rcv_user);
        sUserAdapter = new UserAdapter(this,this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        rcvUser.setLayoutManager(gridLayoutManager);

        //set data for adapter
        sUserAdapter.setData(getListUser());
        //set adapter for recycler view
        rcvUser.setAdapter(sUserAdapter);
    }

    private void filterList(String text) {
        List<User> filterdList = new ArrayList<>();
        for(User user: list){
            if(user.getName().toLowerCase().contains(text.toLowerCase())){
                filterdList.add(user);
            }
        }
        if(filterdList.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }else{
            sUserAdapter.setFilteredList(filterdList);
        }
    }

    private List<User> getListUser() {
        list.add(new User(R.drawable.images, "User name 1"));
        list.add(new User(R.drawable.images, "User name 2"));
        list.add(new User(R.drawable.images, "User name 3"));
        list.add(new User(R.drawable.images, "User name 4"));
        list.add(new User(R.drawable.images, "User name 5"));
        list.add(new User(R.drawable.images, "User name 6"));
        list.add(new User(R.drawable.images, "User name 7"));
        list.add(new User(R.drawable.images, "User name 8"));

        return list;
    }

    @Override
    public void onClickUserItem(int position) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("Name", list.get(position).getName());
        intent.putExtra("Image", list.get(position).getResource());

        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.item_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case
//        }
//            return super.onOptionsItemSelected(item);
//    }
}