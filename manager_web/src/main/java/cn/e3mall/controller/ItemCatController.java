package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.pojo.Msg;
import cn.e3mall.service.ItemCatService;

/**
 * 商品分类管理Controller
 * <p>Title: ItemCatController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p>
 * @version 1.0
 */
@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCatList(@RequestParam(name="id", defaultValue="0")Long parentId) {
        //调用服务查询节点列表
        return itemCatService.getItemCatlist(parentId);
    }

    @RequestMapping("/item/cat/list/{pages}/{rows}")
    @ResponseBody
    public Msg getItemCatList(@PathVariable Integer pages, @PathVariable Integer rows) {
        return Msg.success().add("carlist", itemCatService.getCatPageList(pages, rows));
    }
}
