package com.myblog.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myblog.demo.Dto.BannerEntityDto;
import com.myblog.demo.comment.JsonReturn;
import com.myblog.demo.entity.BannerEntity;
import com.myblog.demo.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lululua
 * @since 2020-09-02
 */
@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    /**
     * 添加
     * @param url
     * @param name
     * @param state
     * @return
     */
    @RequestMapping("/add")
    public JsonReturn addbannner(@RequestParam(value = "url") String url,
                                 @RequestParam(value = "name") String name,
                                 @RequestParam(value = "state") Integer state){
        JsonReturn jsonReturn = new JsonReturn();
        BannerEntity bannerEntity = new BannerEntity();
        bannerEntity.setUrl(url);
        bannerEntity.setName(name);
        bannerEntity.setState(state);
        return jsonReturn.buildSuccess(bannerService.save(bannerEntity));
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/del/{id}")
    public JsonReturn delbannner(@PathVariable String id){
        JsonReturn jsonReturn = new JsonReturn();
        return jsonReturn.buildSuccess(bannerService.removeById(id));
    }

    @RequestMapping("/update/{id}")
    public JsonReturn updatebannner(@PathVariable String id,@RequestParam(value = "url") String url,
                                 @RequestParam(value = "name") String name,
                                 @RequestParam(value = "state") Integer state){
        JsonReturn jsonReturn = new JsonReturn();
        QueryWrapper<BannerEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        BannerEntity bannerEntity = new BannerEntity();
        bannerEntity.setUrl(url);
        bannerEntity.setName(name);
        bannerEntity.setState(state);
        return jsonReturn.buildSuccess(bannerService.update(bannerEntity,queryWrapper));
    }

    /**
     * 用@validated来校验数据，如果数据异常则会统一抛出异常，方便异常中心统一处理。
     * @param bannerEntityDto
     * @return
     */
    @RequestMapping("/test")
    public JsonReturn testbannner(@Validated @RequestBody BannerEntityDto bannerEntityDto){
        JsonReturn jsonReturn = new JsonReturn();
//        System.out.println(bannerEntityDto.getName());
        return jsonReturn.buildSuccess(bannerEntityDto.getState());
//        return jsonReturn.buildSuccess("ok");
    }
}

