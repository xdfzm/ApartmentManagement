package com.example.apartmentmanagement.utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   //自动生成get和set方法
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo<T> {
    private String msg;
    private int code;
    private T data;
    public ResultVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVo(Integer code, T data) {
        this.code = code;
        this.data = data;
    }
}
