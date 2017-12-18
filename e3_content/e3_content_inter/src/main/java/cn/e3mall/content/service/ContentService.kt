package cn.e3mall.content.service

import cn.e3mall.common.pojo.EasyUIDataGridResult
import cn.e3mall.common.utils.E3Result
import cn.e3mall.pojo.TbContent

/**
 * Created by lucio on 11/12/2017.
 */
interface ContentService {

    fun saveContent(content: TbContent): E3Result

    fun getContentListByCid(cid: Long): List<TbContent>?

    fun getContentListByCid(cid: Long?, page: Int?, rows: Int?): EasyUIDataGridResult?
}
