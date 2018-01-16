package cn.e3mall.controller;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.e3mall.common.utils.JsonUtils;

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 15/12/2017 11:51 AM
 * @Version
 */
@Controller
public class PictureController {

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    /**
     * 上传图片至七牛云,采用七牛云作为图片存储服务器
     * @param uploadFile
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/pic/upload", produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
    @ResponseBody
    public String upload(MultipartFile uploadFile) throws IOException {
        //创建上传对象
        UploadManager uploadManager = new UploadManager();
        try {
            //调用put方法上传
            Response res = uploadManager.put(uploadFile.getBytes(), uploadFile.getOriginalFilename(), getUpToken());
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(res.bodyString(), DefaultPutRet.class);
            String url = "http://p0zh7foee.bkt.clouddn.com/" + putRet.key;
            //封装到map中返回
            Map<String, Object> result = new HashMap<>();
            result.put("error", 0);
            result.put("url", url);
            return JsonUtils.objectToJson(result);
        } catch (QiniuException e) {
            e.printStackTrace();
            Map<String, Object> result = new HashMap<>();
            result.put("error", 1);
            result.put("message", "图片上传失败");
            return JsonUtils.objectToJson(result);
        }
    }

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    private String getUpToken() {
        //密钥配置
        String SECRET_KEY = "BOAl26EiEGE489x-tQAY7hTUIdrAiT0jnyr9p17V";
        String ACCESS_KEY = "sgUGpgbiqepgIllfuXDyCyCDmSWYLZa58wWZz_8M";
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String bucketname = "picture";
        return auth.uploadToken(bucketname);
    }

    //    @RequestMapping(value = "/pic/upload", produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
//    @ResponseBody
//    public String uploadFile(MultipartFile uploadFile) {
//        try {
//            //把图片上传的图片服务器
//            FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
//            //取文件扩展名
//            String originalFilename = uploadFile.getOriginalFilename();
//            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
//            //得到一个图片的地址和文件名
//            String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
//            //补充为完整的url
//            url = IMAGE_SERVER_URL + url;
//            //封装到map中返回
//            Map result = new HashMap<>();
//            result.put("error", 0);
//            result.put("url", url);
//            return JsonUtils.objectToJson(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Map result = new HashMap<>();
//            result.put("error", 1);
//            result.put("message", "图片上传失败");
//            return JsonUtils.objectToJson(result);
//        }
//    }
}
