package com.example.demo.respository.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
@Builder
public class ResponseDTO<T> {
    
    private HttpStatus statusCode;
    private String resultMsg;
    private T resultData;

    public ResponseDTO(final HttpStatus statusCode, final String resultMsg) {
        this.statusCode = statusCode;
        this.resultMsg = resultMsg;
        this.resultData = null;
    }
    public static<T> ResponseDTO<T> res(final HttpStatus statusCode, final String resultMsg) {
        return res(statusCode, resultMsg, null);
    }

    public static<T> ResponseDTO<T> res(final HttpStatus statusCode, final String resultMsg, final T t) {
        return ResponseDTO.<T>builder()
                .resultData(t)
                .statusCode(statusCode)
                .resultMsg(resultMsg)
                .build();
    }
}
