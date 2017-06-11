package com.rohmanhakim.stickyendlayoutdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Message> messages = new ArrayList<>();
        messages.add(new Message("a","b","22:00 AM"));
        messages.add(new Message("a","qwertyuiop asd sad","22:00 AM"));
        messages.add(new Message("a","ajsdjakljd sdajdlkas dsaldjkas dsalkjdas dlsakjdsa dlkajsd asdjasdas dlkasjdas dalskjdas dlajsd", "22:00 AM"));
        messages.add(new Message("Sample User Name", "a", "22:00 AM"));
        messages.add(new Message("Sample User", "Sample Text Longer Than", "22:00 AM"));
        messages.add(new Message("a","ajsdjakljd sdajdlkas dsaldjkas dsalkjd", "22:00 AM"));

        RecyclerView listChat = (RecyclerView) findViewById(R.id.listChat);
        listChat.setLayoutManager(new LinearLayoutManager(this));
        listChat.setAdapter(new MessageAdapter(messages));
    }
}
