package com.cotiviti.movieticketbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@Scope("prototype")
public class GlobalResponse<T> implements Serializable {

    private T data;
    private boolean status;
    private String timestamp;
    private String message;

    public GlobalResponse successResponse(T data, String message) {
        this.data = data;
        this.message = message;
        this.status = true;
        timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
        return this;
    }

    public GlobalResponse failureResponse(String message, T data) {
        this.data = data;
        this.message = message;
        this.status = false;
        timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
        return this;
    }

}
