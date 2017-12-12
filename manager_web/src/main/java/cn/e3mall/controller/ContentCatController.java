package cn.e3mall.controller;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lucio on 11/12/2017.
 */
@Controller
public class ContentCatController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(
            @RequestParam(name = "id", defaultValue = "0") Long parentId) {
        return contentCategoryService.getContentCatList(parentId);
    }

    @RequestMapping(value = "/content/category/create", method = RequestMethod.POST)
    @ResponseBody
    public E3Result createContentCategory(Long parentId, String name) {
        return contentCategoryService.addContentCategory(parentId, name);
    }

    @RequestMapping("/content/query/list")
    @ResponseBody
    public EasyUIDataGridResult queryContentList(@RequestParam(name = "categoryId", defaultValue = "0") Integer categoryId, @RequestParam Integer page, @RequestParam Integer rows) {
        return contentCategoryService.queryContentList(categoryId, page, rows);
    }

    @RequestMapping(value = "/content/delete", method = RequestMethod.POST)
    @ResponseBody
    public E3Result deleteContent(@RequestParam(name = "ids", defaultValue = "0") long id) {
        return contentCategoryService.deleteContent(id);
    }
}
