package com.gattuso42.BookStoreAPI.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class CustomExceptionResponse {
    private List<String>errorMessages;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timeStamp;

    public CustomExceptionResponse(List<String> errorMessages) {
        this.errorMessages = errorMessages;
        this.timeStamp = LocalDateTime.now();
    }
}
