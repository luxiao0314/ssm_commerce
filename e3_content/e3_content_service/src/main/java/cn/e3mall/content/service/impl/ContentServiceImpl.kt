package cn.e3mall.content.service.impl

import cn.e3mall.common.pojo.EasyUIDataGridResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.Date

import cn.e3mall.common.utils.E3Result
import cn.e3mall.content.service.ContentService
import cn.e3mall.mapper.TbContentMapper
import cn.e3mall.pojo.TbContent
import cn.e3mall.pojo.TbContentCategoryExample
import cn.e3mall.pojo.TbContentExample
import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo

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

    override fun getContentListByCid(cid: Long): MutableList<TbContent>? {
        val example = TbContentExample()
        example.createCriteria().andCategoryIdEqualTo(cid)
        return contentMapper?.selectByExampleWithBLOBs(example)
    }

    override fun getContentListByCid(cid: Long?, page: Int?, rows: Int?): EasyUIDataGridResult? {
        PageHelper.startPage(page!!, rows!!)
        val example = TbContentExample()
        example.createCriteria().andCategoryIdEqualTo(cid)
        val tbItems = contentMapper?.selectByExampleWithBLOBs(example)
        val easyUIDataGridResult = EasyUIDataGridResult()
        easyUIDataGridResult.rows = tbItems
        val pageInfo = PageInfo(tbItems)
        easyUIDataGridResult.size = pageInfo.pageSize
        easyUIDataGridResult.total = pageInfo.total
        return easyUIDataGridResult
    }

    override fun deleteContent(id: Long): E3Result {
        val example = TbContentExample()
        example.createCriteria().andIdEqualTo(id)
        contentMapper?.deleteByExample(example)
        return E3Result.ok()
    }
}
