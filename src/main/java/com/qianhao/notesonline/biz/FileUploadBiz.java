package com.qianhao.notesonline.biz;

import com.qianhao.notesonline.beans.Document;
import com.qianhao.notesonline.beans.Image;
import com.qianhao.notesonline.beans.Knowledge;
import com.qianhao.notesonline.result.Result;
import com.qianhao.notesonline.service.DocumentService;
import com.qianhao.notesonline.service.ImageService;
import com.qianhao.notesonline.service.KnowledgeService;
import com.qianhao.notesonline.utils.HtmlAnalysisUtil;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * 功能2：文件上传
 * 还未实现，文件的删除
 */
@Component
public class FileUploadBiz {
    //上传到的路径
    @Value("${filePath_Html}")
    private String filePath_Html;

    @Value("${filePath_Image}")
    private String filePath_Image;


    @Autowired
    private DocumentService documentService;
    @Autowired
    private KnowledgeService knowledgeService;
    @Autowired
    private ImageService imageService;
    /**
     *  笔记文档上传
     * @param file
     * @param request
     * @param uid
     * @param save      是否要保留源文档
     * @param split     知识点名称的标识符
     * @return
     */
    public Result uploadHtml(MultipartFile file,
                             HttpServletRequest request, String uid, boolean save,String split) {
        try {
            String filePath;
            if (file.getOriginalFilename().indexOf(".html") != -1) {
                filePath = filePath_Html;
                //先判断当前文件是否存在
                Map<String,Object>params=new HashMap<>();
                params.put("uid",Integer.parseInt(uid));
                params.put("d_name",file.getOriginalFilename());
                List<Document> documents = documentService.findByParams(params);
                if(documents.size()>0){
                    throw new Exception("当前文件已经存在，请重新上传!");
                }
                //文件不存在，上传文件
                uploadFile(file.getBytes(), filePath, file.getOriginalFilename(), uid);
                //对文件进行解析，并将数据存储到数据库中
                String html = HtmlAnalysisUtil.getHtml(uid + file.getOriginalFilename(),uid);
                String title = Jsoup.parse(html).title();
                Map<String, String> data = HtmlAnalysisUtil.getDataFromHtml(html, split);
                //获取需要上传的图片
                Set<String> requestImages = HtmlAnalysisUtil.requestImages(data,uid);



                insertIntoDB(file.getOriginalFilename(),data,uid,title);


                if (!save) {//不保存，上传完之后，删除源文档,

                }

                return Result.success("html文档上传成功", requestImages);
            } else {
                throw new Exception("文件格式不对");

            }

            /*if(file.getOriginalFilename().indexOf(".html")!=-1&&save==false){
                //删除已经上传的文件
            }*/
        } catch (Exception e) {
            e.printStackTrace();

            return Result.fail(e.getMessage());
        }

    }


    /**
     *
     * @param file          上传的文件
     * @param request
     * @param uid           用户id
     * @param right_name    需要上传的图片的名称
     * @return
     */
    public Result uploadImage(MultipartFile file,
                              HttpServletRequest request, String uid,String right_name) {
       try{
           if (file.getOriginalFilename().equals(right_name)) {
                String filePath=filePath_Image;
                //先判断当前图片是否存在
               Map<String,Object>params=new HashMap<>();
               params.put("uid",Integer.parseInt(uid));
               params.put("i_name",file.getOriginalFilename());
               List<Image> images = imageService.findByParams(params);
               if(images.size()>0);
               else{
                   uploadFile(file.getBytes(),filePath,file.getOriginalFilename(),uid);
                   Image image=new Image();
                   image.setUid(Integer.parseInt(uid));
                   image.setI_name(file.getOriginalFilename());
                    imageService.insert(image);
               }

               return  Result.success("图片上传成功！",file.getOriginalFilename());
           } else {
               throw new Exception("请上传正确的文件！");
           }
       }catch (Exception e){
            e.printStackTrace();
            return Result.fail("图片上传失败!",file.getOriginalFilename());
       }
    }


    /**
     * 上传文件
     * @param file          文件的字节数组
     * @param filePath      文件的路径
     * @param fileName      文件的名称
     * @param uid           用户的id
     * @throws Exception
     */
    private void  uploadFile(byte[] file,String filePath, String fileName,String uid) throws Exception {
        //将文件重新面名
        File targetFile = new File(filePath+uid+fileName);
        //创建目标文件
        targetFile.createNewFile();
        //向目标文件中写数据
        FileOutputStream out = new FileOutputStream(targetFile);
        out.write(file);
        out.flush();
        out.close();
    }

    /**
     *
     * @param fileName      文件的名字
     * @param data          知识点的数据
     * @param uid           用户id
     * @param title         文档标题
     * @throws Exception    sql异常
     */
    @Transactional
    public void insertIntoDB(String fileName,Map<String,String>data,String uid,String title) throws Exception {
        //存储到数据库中
        Document document=new Document();
        document.setD_name(fileName);
        document.setUid(Integer.parseInt(uid));
        document.setTitle(title);
        boolean uploaded = documentService.insert(document);

        List<Knowledge>knowledges=new LinkedList<>();
        Set<String> keySet = data.keySet();
        for(String k:keySet){
            Knowledge knowledge=new Knowledge();
            knowledge.setUid(Integer.parseInt(uid));
            knowledge.setK_name(k);
            knowledge.setK_content(data.get(k));
            knowledge.setBelong_title(title);
            knowledge.setU_name("qh");//先设置为qh
            knowledge.setK_describe("暂无描述...");//默认设置
            knowledges.add(knowledge);
        }

        int i = knowledgeService.insertBatch(knowledges);

        if(!uploaded){
            throw new Exception("数据库录入数据失败，请重新上传！");
        }else if(data.size()>0&&i<1){
            throw new Exception("数据库录入数据失败，请重新上传！");
        }
    }
}
