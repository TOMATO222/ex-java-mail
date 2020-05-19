package com.lpq.mail.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wei yuyaung
 * @date 2020.05.19 12:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult<T> {
    private Integer code;
    private String message;
    private T data;

    private BaseResult(T data){
        this.code = CodeMessage.CODE_SUCCESS;
        this.data = data;
    }

    private BaseResult(CodeMessage codeMessage){
        this.code = codeMessage.getCode();
        this.message = codeMessage.getMessage();
    }

    public static <T> BaseResult<T> success(T data){
        return new BaseResult<>(data);
    }

    public static <T> BaseResult<T> fail(CodeMessage codeMessage){
        return new BaseResult<>(codeMessage);
    }
}
