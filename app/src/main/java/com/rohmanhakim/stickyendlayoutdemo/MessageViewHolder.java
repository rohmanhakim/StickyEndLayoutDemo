package com.rohmanhakim.stickyendlayoutdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class MessageViewHolder extends RecyclerView.ViewHolder {

    TextView messageSender;
    TextView messageBody;
    TextView messageDate;

    MessageViewHolder(View itemView) {
        super(itemView);
        messageSender = (TextView) itemView.findViewById(R.id.message_sender);
        messageBody = (TextView) itemView.findViewById(R.id.message_body);
        messageDate = (TextView) itemView.findViewById(R.id.message_date);
    }
}
