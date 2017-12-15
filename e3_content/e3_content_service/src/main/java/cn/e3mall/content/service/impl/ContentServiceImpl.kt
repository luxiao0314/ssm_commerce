package cn.e3mall.content.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.Date

import cn.e3mall.common.utils.E3Result
import cn.e3mall.content.service.ContentService
import cn.e3mall.mapper.TbContentMapper
import cn.e3mall.pojo.TbContent

/**
 * Created by lucio on 11/12/2017.
 */
@Service
class ContentServiceImpl : ContentService {

    @Autowired
    private val contentMapper: TbContentMapper? = null

    override fun saveContent(content: TbContent): E3Result {
        content.created = Date()
        content.updated = Date()
        contentMapper!!.insert(content)
        return E3Result.ok()
    }
}
