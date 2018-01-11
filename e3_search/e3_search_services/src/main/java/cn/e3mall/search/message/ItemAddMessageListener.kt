package cn.e3mall.search.message

import org.springframework.beans.factory.annotation.Autowired
import javax.jms.Message
import javax.jms.MessageListener
import javax.jms.TextMessage
import cn.e3mall.search.mapper.ItemMapper
import org.apache.solr.client.solrj.SolrServer
import org.apache.solr.common.SolrInputDocument


/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 11/01/2018 11:45 AM
 * @Version
 */
class ItemAddMessageListener : MessageListener {

    @Autowired
    private val itemMapper: ItemMapper? = null
    @Autowired
    private val solrServer: SolrServer? = null

    /**
     * 接收addItem的时候发送的消息 itemId
     */
    override fun onMessage(message: Message?) {
        try {
            val textMessage = message as TextMessage
            val itemId = textMessage.text.toLong()
            //等待事务提交
            Thread.sleep(1000)
            val searchItem = itemMapper?.getItemById(itemId)
            //addItem的时候,往solr搜索库中添加数据同步索引库
            val document = SolrInputDocument()
            document.addField("id", searchItem?.id)
            document.addField("item_title", searchItem?.title)
            document.addField("item_sell_point", searchItem?.sell_point)
            document.addField("item_price", searchItem?.price)
            document.addField("item_image", searchItem?.image)
            document.addField("item_category_name", searchItem?.category_name)
            solrServer?.add(document)
            solrServer?.commit()
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }
}