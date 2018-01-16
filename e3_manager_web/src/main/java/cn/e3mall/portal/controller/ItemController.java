package cn.e3mall.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.pojo.Msg;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.service.ItemService;

/**
 * 商品管理Controller
 * <p>Title: ItemController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p>
 *
 * @version 1.0
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public Msg getItemById(@PathVariable Long itemId) {
        return Msg.success().add("item", itemService.getItemById(itemId));
    }

//    @RequestMapping("/item/cat/list")
//    @ResponseBody
//    public List<TbItemCat> getItemCatList() {
////        return Msg.success().add("carlist", itemService.getItemCatList());
//        return itemService.getItemCatList();
//    }

//    @RequestMapping("/item/cat/list/{pages}/{rows}")
//    @ResponseBody
//    public Msg getItemCatList(@PathVariable Integer pages, @PathVariable Integer rows) {
//        return Msg.success().add("carlist", itemService.getCatPageList(pages, rows));
//    }
//
//    /**
//     * 根据parentId查询item_cat
//     *
//     * @return
//     */
//    @RequestMapping("/item/cat/list")
//    @ResponseBody
//    public List<EasyUITreeNode> getItemCarList(@RequestParam(name = "id", defaultValue = "0") long parentId) {
////        return Msg.success().add("carlist", itemService.getItemCatListByParentId(parentId));
//        return itemService.getItemCatListByParentId(parentId);
//    }

    /**
     * 分页查找 http://localhost:8081/item/tblist/1/20
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/item/itemlist/{page}/{rows}")
    @ResponseBody
    public Msg getItemList(@PathVariable Integer page, @PathVariable Integer rows) {
        return Msg.success().add("tblist", itemService.getItemList(page, rows));
    }

    /**
     * 分页查找 http://localhost:8081/item/tblist?page=3&rows=20
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/item/itemlist")
    @ResponseBody
    public Msg getItemLists(@RequestParam Integer page, @RequestParam Integer rows) {
        return Msg.success().add("tblist", itemService.getItemList(page, rows));
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getResultList(Integer page, Integer rows) {
        return itemService.getItemList(page, rows);
    }

    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public E3Result deleteItem(@RequestParam(name = "ids", defaultValue = "0") long id) {
        return itemService.deleteItem(id);
    }

    /**
     * 商品添加功能
     */
//    @RequestMapping(value="/item/save", method= RequestMethod.POST)
//    @ResponseBody
//    public E3Result addItem(TbItem item, String desc) {
//        return itemService.addItem(item, desc);
//    }
}
