package cn.e3mall.portal.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

import cn.e3mall.content.service.ContentService
import org.springframework.beans.factory.annotation.Value
import org.springframework.ui.Model

/**
 * Created by lucio on 11/12/2017.
 */
@Controller
class IndexController {

    @Value("\${CONTENT_LUNBO_ID}")
    private val CONTENT_LUNBO_ID: Long? = null

    @Autowired
    private val contentService: ContentService? = null

    @RequestMapping("/index")
    fun showIndex(model: Model): String {
        val ad1List = contentService?.getContentListByCid(CONTENT_LUNBO_ID!!)
        // 把结果传递给页面
        model.addAttribute("ad1List", ad1List)
        return "index"
    }
}
