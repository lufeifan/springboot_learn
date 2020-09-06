package com.myblog.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myblog.demo.comment.JsonReturn;
import com.myblog.demo.entity.MBlogEntity;
import com.myblog.demo.service.MBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lululua
 * @since 2020-09-02
 */
@RestController
public class MBlogController {
    @Autowired
    private MBlogService blogService;

    /**
     * 时间线
     * @param pages
     * @return
     */
    @RequestMapping("/list/time")
    public JsonReturn time(@RequestParam(value = "pages") Integer pages){
        JsonReturn jsonReturn = new JsonReturn();
        IPage<MBlogEntity> page = new Page<>(pages,5);
        IPage<MBlogEntity> page1 = blogService.page(page);
        return jsonReturn.buildSuccess(page1.getRecords());
    }
    /**
     * 某个分类的全部文章
     * @param types
     * @return
     */
    @RequestMapping("/list")
    public JsonReturn getfenlei(@RequestParam(value = "type") String types, @RequestParam(value = "pages") Integer pages){
        JsonReturn jsonReturn = new JsonReturn();
        QueryWrapper<MBlogEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fenlei",types);
        IPage<MBlogEntity> page = new Page<>(pages,5);
        IPage<MBlogEntity> page1 = blogService.page(page,queryWrapper);
        List<Map<String, Object>> maps = blogService.listMaps(queryWrapper);
        return  jsonReturn.buildSuccess(page1);
    }

    /**
     * 文章详情
     * @param types
     * @param id
     * @return
     */
    @RequestMapping("/list/fenlei")
    public JsonReturn getdetail(@RequestParam(value = "type") String types,@RequestParam(value = "id") Integer id){
        JsonReturn jsonReturn = new JsonReturn();
        QueryWrapper<MBlogEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fenlei",types).eq("id",id);
        return jsonReturn.buildSuccess(blogService.getOne(queryWrapper));
    }
}

