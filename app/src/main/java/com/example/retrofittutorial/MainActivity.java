package com.example.retrofittutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView responseText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseText = (TextView) findViewById(R.id.responseText);

        /**
         GET List Resources
         **/
        Call<MultipleResource> call = AppManager.jsonPlaceHolder.doGetListResources();
        call.enqueue(new Callback<MultipleResource>() {
            @Override
            public void onResponse(Call<MultipleResource> call, Response<MultipleResource> response) {
                Log.d("noa",response.code()+"");

                String displayResponse = "";

                MultipleResource resource = response.body();
                Integer text = resource.page;
                Integer total = resource.total;
                Integer totalPages = resource.totalPages;
                List<MultipleResource.Datum> datumList = resource.data;

                displayResponse += text + " Page\n" + total + " Total\n" + totalPages + " Total Pages\n";

                for (MultipleResource.Datum datum : datumList) {
                    displayResponse += datum.id + " " + datum.name + " " + datum.pantoneValue + " " + datum.year + "\n";
                }

                responseText.setText(displayResponse);

            }

            @Override
            public void onFailure(Call<MultipleResource> call, Throwable t) {
                Log.d("noa",t.getMessage());
                call.cancel();

            }
        });

        /**
         Create new user
         **/
        User user = new User("noa", "leader");
        Call<User> call1 = AppManager.jsonPlaceHolder.createUser(user);
        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user1 = response.body();
                String displayResponse = "";
                displayResponse += user1.name + " "+ user1.job + " " + user1.id + " " + user1.createdAt;
                responseText.setText(displayResponse);
                Toast.makeText(getApplicationContext(), user1.name + " " + user1.job + " " + user1.id + " " + user1.createdAt, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("noa",t.getMessage());
                call.cancel();
            }
        });


        Call<UserList> userListCall = AppManager.jsonPlaceHolder.doGetUserList("2");
        userListCall.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                UserList userList = response.body();

                Integer page = userList.page;
                Integer per_page = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList = userList.data;

                Toast.makeText(getApplicationContext(), page + " page\n" + per_page + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

                for (UserList.Datum datum : datumList) {
                    Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                Log.d("noa",t.getMessage());
                call.cancel();

            }
        });


        /**
         POST name and job Url encoded.
         **/
        Call<UserList> call3 = AppManager.jsonPlaceHolder.doCreateUserWithField("noa","leader");
        call3.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                UserList userList = response.body();
                Integer page = userList.page;
                Integer per_page = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList = userList.data;
                Toast.makeText(getApplicationContext(), page + " page\n" + per_page + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

                for (UserList.Datum datum : datumList) {
                    Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                Log.d("noa",t.getMessage());
                call.cancel();
            }
        });

    }
}