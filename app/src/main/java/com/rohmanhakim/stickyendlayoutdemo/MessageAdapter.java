package com.rohmanhakim.stickyendlayoutdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private List<Message> messages;

    MessageAdapter(List<Message> messages) {
        this.setMessages(messages);
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.incoming_chat_bubble, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        holder.messageSender.setText(getMessages().get(position).getSender());
        holder.messageBody.setText(getMessages().get(position).getMessage());
        holder.messageDate.setText(getMessages().get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return getMessages().size();
    }

    private List<Message> getMessages() {
        return messages;
    }

    private void setMessages(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }
}
