package com.myblog.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.Producer;
import com.myblog.demo.Dto.UserDto;
import com.myblog.demo.comment.JsonReturn;
import com.myblog.demo.entity.UserEntity;
import com.myblog.demo.service.UserService;
import com.myblog.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lululua
 * @since 2020-09-07
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping("/login")
    public JsonReturn login(@Validated @RequestBody UserDto userDto, HttpServletResponse response){
        JsonReturn jsonReturn = new JsonReturn();
        /**
         * 从数据库查出用户
         */
        UserEntity userEntity = userService.getOne(new QueryWrapper<UserEntity>().eq("username",userDto.getUsername()));
        if (userEntity==null){
            return jsonReturn.buildFailure("用户名不存在");
        }
        /**
         * 验证密码
         */
        if (userEntity.getPassword().equals(userDto.getPassword())){
            String token = jwtUtil.createToken(userDto.getUsername());
            response.setHeader("token",token);
            return jsonReturn.buildSuccess("登录成功");
        }
        return jsonReturn.buildFailure("密码错误");
    }

    /**
     * 验证码生成
     */
    @Autowired
    Producer producer;
    @GetMapping("/vc.jpg")
    public void getVerifyCode(HttpServletResponse resp, HttpSession session) throws IOException {
        resp.setContentType("image/jpeg");
        String text = producer.createText();
        session.setAttribute("verify_code", text);
        BufferedImage image = producer.createImage(text);
        try(ServletOutputStream out = resp.getOutputStream()) {
            ImageIO.write(image, "jpg", out);
        }
    }

    /**
     * 验证码验证
     * url {{host}}/checkCode?code=
     * @ResponseBody的作用其实是将java对象转为json格式的数据。
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/checkCode")
    @ResponseBody
    public boolean imgvrifyControllerDefaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String captchaId = (String) httpServletRequest.getSession().getAttribute("verify_code");
        String parameter = httpServletRequest.getParameter("code");
        if (!captchaId.equals(parameter)) {
            System.out.println(("错误的验证码"));
            return false;
        } else {
            return true;
        }
    }
}

