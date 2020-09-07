package com.myblog.demo.handle;

import com.myblog.demo.comment.JsonReturn;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常捕获
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * MethodArgumentNotValidException 异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public JsonReturn handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        JsonReturn jsonReturn = new JsonReturn();
        return jsonReturn.buildFailure(objectError.getDefaultMessage());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public JsonReturn handleMethodArgumentNotValidException(HttpMessageNotReadableException e){
        String message = e.getLocalizedMessage();
//        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        JsonReturn jsonReturn = new JsonReturn();
        return jsonReturn.buildFailure("not a valid Integer value");
    }
}