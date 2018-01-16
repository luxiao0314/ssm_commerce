package cn.e3mall.search.service.impl

import cn.e3mall.common.utils.E3Result
import cn.e3mall.search.mapper.ItemMapper
import cn.e3mall.search.service.SearchItemService
import org.apache.solr.client.solrj.SolrServer
import org.apache.solr.common.SolrInputDocument
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Description
 * *
 * @Author luxiao418
 * *
 * @Email luxiao418@pingan.com.cn
 * *
 * @Date 10/01/2018 11:35 AM
 * *
 * @Version
 */
@Service
public class SearchItemServiceImpl : SearchItemService {

    @Autowired
    private val itemMapper: ItemMapper? = null
    @Autowired
    private val solrServer: SolrServer? = null

    override fun importAllItems(): E3Result? {
        try {
            for (searchItem in itemMapper?.itemList!!) {
                val document = SolrInputDocument()
                document.addField("id", searchItem.id)
                document.addField("item_title", searchItem.title)
                document.addField("item_sell_point", searchItem.sell_point)
                document.addField("item_price", searchItem.price)
                document.addField("item_image", searchItem.image)
                document.addField("item_category_name", searchItem.category_name)
                solrServer?.add(document)
            }
            solrServer?.commit()
            return E3Result.ok()
        } catch(e: Exception) {
            e.printStackTrace()
            return E3Result.build(500, "数据导入时发生异常")
        }
    }
}
