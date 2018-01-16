package cn.e3mall.sso.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 16/01/2018 4:19 PM
 * @Version
 */
@Controller
class RegitsterController {

    @RequestMapping("/page/register")
    fun showRegister(): String {
        return "register"
    }
}