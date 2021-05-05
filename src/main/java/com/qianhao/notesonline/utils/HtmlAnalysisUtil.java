package com.qianhao.notesonline.utils;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * html解析工具类
 *
 */
public class HtmlAnalysisUtil {

    //设置图片文件在源电脑上的目录

    private static String image_Resources="C:\\Users\\QH\\AppData\\Roaming\\Typora\\typora-user-images\\";
    private static String new_Image_Resources="/uploadfile/images/";

    /**
     * 从String的html文档中，提取数据，并保持在map中
     * @param html  源html
     * @param split 知识点名称的区分：例如，h5表示的是知识点名称
     * @return      知识点名称与知识点对应的内容
     */
    public static Map<String,String>getDataFromHtml(String html,String split){
        Map<String,String>resultMap;

        switch (split){
            case "h5":{
                resultMap=new HashMap<>();
                String[] strings = html.split("<h5>");
                for(int i=1;i<strings.length;i++){
                    int index = strings[i].indexOf("</h5>");
                    String key=strings[i].substring(0,index);
                    String value="";
                    int v_begin=index+5;
                    if(i!=strings.length-1){
                        value=strings[i].substring(v_begin);
                    }else{
                        int v_end=strings[i].indexOf("</body>");
                        value=strings[i].substring(v_begin,v_end);
                    }
                    resultMap.put(key,value);
                }
                return resultMap;
            }
            case "h4":{
                resultMap=new HashMap<>();
                String[] strings = html.split("<h4>");
                for(int i=1;i<strings.length;i++){
                    int index = strings[i].indexOf("</h4>");
                    String key=strings[i].substring(0,index);
                    String value="";
                    int v_begin=index+5;
                    if(i!=strings.length-1){
                        value=strings[i].substring(v_begin);
                    }else{
                        int v_end=strings[i].indexOf("</body>");
                        value=strings[i].substring(v_begin,v_end);
                    }
                    resultMap.put(key,value);
                }
                return resultMap;
            }
            case "h3":{
                resultMap=new HashMap<>();
                String[] strings = html.split("<h3>");
                for(int i=1;i<strings.length;i++){
                    int index = strings[i].indexOf("</h3>");
                    String key=strings[i].substring(0,index);
                    String value="";
                    int v_begin=index+5;
                    if(i!=strings.length-1){
                        value=strings[i].substring(v_begin);
                    }else{
                        int v_end=strings[i].indexOf("</body>");
                        value=strings[i].substring(v_begin,v_end);
                    }
                    resultMap.put(key,value);
                }
                return resultMap;
            }
            case "h2":{
                resultMap=new HashMap<>();
                String[] strings = html.split("<h2>");
                for(int i=1;i<strings.length;i++){
                    int index = strings[i].indexOf("</h2>");
                    String key=strings[i].substring(0,index);
                    String value="";
                    int v_begin=index+5;
                    if(i!=strings.length-1){
                        value=strings[i].substring(v_begin);
                    }else{
                        int v_end=strings[i].indexOf("</body>");
                        value=strings[i].substring(v_begin,v_end);
                    }
                    resultMap.put(key,value);
                }
                return resultMap;
            }
            default:return null;
        }


    }

    /**
     * 从html中获取String类型的html
     * @param name  需要获取的html名称，例如,a.html对应 a
     *              html文件默认存储在resources中
     * @return      String类型的html
     */
    public static String getHtml(String name,String uid){
        String path="src\\main\\resources\\static\\uploadfile\\html\\"+name;
        File file=new File(path);
        StringBuffer html=new StringBuffer();
        BufferedReader br=null;
        try{
            br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                html.append( System.lineSeparator() + s);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(br!=null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String result=html.toString();
        //将图片的文件目录改变

        result=result.replace(jsonString(image_Resources),jsonString(new_Image_Resources+uid));

        return result;
    }

    /**
     * 获取需要上传的图片，并将图片名称改为uid+xxx.png
     * @param dataMap   知识点名称与知识点对应的内容
     * @return          当前html中需要上传的图片
     */
    public static Set<String> requestImages(Map<String,String>dataMap,String uid){
        Set<String>images=new HashSet<>();
        Set<String> keySet = dataMap.keySet();
        for(String k:keySet){
            String v=dataMap.get(k);
            int begin_index=v.indexOf(uid+"image-");
            if(begin_index!=-1){
                int end_index=v.indexOf(".png");
                String str=v.substring(begin_index+1,end_index+4);
                images.add(str);

            }
        }
        return images;
    }

    /**
     * 防止字符串转义的方法
     * @param s
     * @return
     */
    public static String jsonString(String s) {
        char[] temp = s.toCharArray();
        int n = temp.length;
        for (int i =0; i<n; i++) {
            if (temp[i] == ':' && temp[i+1] == '"') {
                for (int j = i+2; j<n; j++) {
                    if (temp[j] == '"') {
                        if (temp[j+1] != ',' && temp[j+1] != '}') {
                            temp[j] = '”';
                        } else if(temp[j+1] ==',' || temp[j+1] == '}') {
                            break;
                        }
                    }
                }
            }
        }
        return new String(temp);
    }
}
