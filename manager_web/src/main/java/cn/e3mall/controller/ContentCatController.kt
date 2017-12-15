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

    @RequestMapping("/content/query/list")
    @ResponseBody
    fun queryContentList(@RequestParam(name = "categoryId", defaultValue = "0") categoryId: Int?, @RequestParam page: Int?, @RequestParam rows: Int?): EasyUIDataGridResult {
        return contentCategoryService!!.queryContentList(categoryId!!.toLong(), page, rows)
    }

    @RequestMapping(value = "/content/delete", method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun deleteContent(@RequestParam(name = "ids", defaultValue = "0") id: Long): E3Result {
        return contentCategoryService!!.deleteContent(id)
    }
}
