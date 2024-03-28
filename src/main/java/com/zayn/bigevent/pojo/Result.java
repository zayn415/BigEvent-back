package com.zayn.bigevent.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zayn
 * * @date 2024/3/25/21:56
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;
    
    public static <E> Result<E> success(E data) {
        return new Result<>(0, "success", data);
    }
    
    public static Result<Void> success() {
        return new Result<>(0, "success", null);
    }
    
    public static Result<Void> error(String msg) {
        return new Result<>(1, msg, null);
    }
}
