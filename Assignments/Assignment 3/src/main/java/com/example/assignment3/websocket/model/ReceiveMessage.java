package com.example.assignment3.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReceiveMessage {

    private String content;

    public String getContent() {
        return content;
    }

}
