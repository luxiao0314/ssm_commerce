package cn.e3mall.sso.service

import cn.e3mall.common.utils.E3Result



/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 16/01/2018 4:07 PM
 * @Version
 */
interface LoginService {

    //参数：用户名和密码
    //业务逻辑：
    /*
     * 1、判断用户和密码是否正确
     * 2、如果不正确，返回登录失败
     * 3、如果正确生成token。
     * 4、把用户信息写入redis，key：token value：用户信息
     * 5、设置Session的过期时间
     * 6、把token返回
     */
    //返回值：E3Result，其中包含token信息
    fun userLogin(username: String, password: String): E3Result
}