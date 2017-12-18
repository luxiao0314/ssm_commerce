package cn.e3mall.controller

import cn.e3mall.common.pojo.EasyUIDataGridResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

import cn.e3mall.common.utils.E3Result
import cn.e3mall.content.service.ContentService
import cn.e3mall.pojo.TbContent
import org.springframework.web.bind.annotation.RequestParam

/**
 * @Description
 * *
 * @Author luxiao418
 * *
 * @Email luxiao418@pingan.com.cn
 * *
 * @Date 12/12/2017 4:16 PM
 * *
 * @Version
 */
@Controller
internal class ContentController {

    @Autowired
    private val contentService: ContentService? = null

    @RequestMapping(value = "/content/save", method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun addContent(content: TbContent): E3Result {
        return contentService!!.saveContent(content)
    }

    @RequestMapping("/content/query/list")
    @ResponseBody
    fun queryContentList(@RequestParam(name = "categoryId", defaultValue = "0") categoryId: Long?, @RequestParam page: Int?, @RequestParam rows: Int?): EasyUIDataGridResult? {
        return contentService?.getContentListByCid(categoryId, page, rows)
    }

    @RequestMapping(value = "/content/delete", method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun deleteContent(@RequestParam(name = "ids", defaultValue = "0") id: Long): E3Result? {
        return contentService?.deleteContent(id)
    }
}
