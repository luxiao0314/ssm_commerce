package cn.e3mall.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

import cn.e3mall.common.pojo.EasyUIDataGridResult
import cn.e3mall.common.pojo.EasyUITreeNode
import cn.e3mall.common.pojo.Msg
import cn.e3mall.common.utils.E3Result
import cn.e3mall.content.service.ContentService
import cn.e3mall.pojo.TbItem
import cn.e3mall.service.ItemService

/**
 * 商品管理Controller
 *
 * Title: ItemController
 *
 * Description:
 *
 * Company: www.itcast.cn

 * @version 1.0
 */
@Controller
class ItemController {

    @Autowired
    private val itemService: ItemService? = null

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    fun getItemById(@PathVariable itemId: Long?): Msg {
        return Msg.success().add("item", itemService!!.getItemById(itemId!!))
    }

    /**
     * 分页查找 http://localhost:8081/item/tblist/1/20

     * @param page
     * *
     * @param rows
     * *
     * @return
     */
    @RequestMapping("/item/itemlist/{page}/{rows}")
    @ResponseBody
    fun getItemList(@PathVariable page: Int?, @PathVariable rows: Int?): Msg {
        return Msg.success().add("tblist", itemService!!.getItemList(page!!, rows!!))
    }

    /**
     * 分页查找 http://localhost:8081/item/tblist?page=3&rows=20

     * @param page
     * *
     * @param rows
     * *
     * @return
     */
    @RequestMapping("/item/itemlist")
    @ResponseBody
    fun getItemLists(@RequestParam page: Int?, @RequestParam rows: Int?): Msg {
        return Msg.success().add("tblist", itemService!!.getItemList(page!!, rows!!))
    }

    @RequestMapping("/item/list")
    @ResponseBody
    fun getResultList(page: Int?, rows: Int?): EasyUIDataGridResult {
        return itemService!!.getItemList(page!!, rows!!)
    }

    @RequestMapping("/rest/item/delete")
    @ResponseBody
    fun deleteItem(@RequestParam(name = "ids", defaultValue = "0") id: Long): E3Result {
        return itemService!!.deleteItem(id)
    }

    /**
     * 商品添加功能
     */
    @RequestMapping(value = "/item/save", method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun addItem(item: TbItem, desc: String): E3Result {
        return itemService!!.addItem(item, desc)
    }
}
