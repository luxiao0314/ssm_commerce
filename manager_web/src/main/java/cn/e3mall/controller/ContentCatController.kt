package cn.e3mall.controller

import cn.e3mall.common.pojo.EasyUIDataGridResult
import cn.e3mall.common.pojo.EasyUITreeNode
import cn.e3mall.common.utils.E3Result
import cn.e3mall.content.service.ContentCategoryService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 * Created by lucio on 11/12/2017.
 */
@Controller
class ContentCatController {
    @Autowired
    private val contentCategoryService: ContentCategoryService? = null

    @RequestMapping("/content/category/list")
    @ResponseBody
    fun getContentCatList(
            @RequestParam(name = "id", defaultValue = "0") parentId: Long?): List<EasyUITreeNode> {
        return contentCategoryService!!.getContentCatList(parentId!!)
    }

    @RequestMapping(value = "/content/category/create", method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun createContentCategory(parentId: Long?, name: String): E3Result {
        return contentCategoryService!!.addContentCategory(parentId!!, name)
    }
}
