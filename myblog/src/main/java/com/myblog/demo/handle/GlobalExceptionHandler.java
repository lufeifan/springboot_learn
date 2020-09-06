package com.myblog.demo.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myblog.demo.comment.JsonReturn;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

//全局异常捕获
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public void handleMethodArgumentNotValidException(MethodArgumentNotValidException e,HttpServletResponse response) throws IOException {
        System.out.println(e.getMessage());
        Map<String, String> map = new HashMap<>();
        map.put("msg",e.getLocalizedMessage());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(map));
        out.flush();
        out.close();
    }
}