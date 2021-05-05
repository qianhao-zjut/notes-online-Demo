package com.qianhao.notesonline;

import com.qianhao.notesonline.beans.Knowledge;
import com.qianhao.notesonline.service.KnowledgeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class NotesOnlineApplicationTests {
    @Autowired
    private KnowledgeService knowledgeService;
    @Test
    void contextLoads() {
        Map<String,Object> params=new HashMap<>();
        params.put("page",0);
        params.put("search","点1");
        List<Knowledge> knowledges = knowledgeService.limitQueue(params);
        for(Knowledge knowledge:knowledges){
            System.out.println(knowledge.getK_name()+"==");
        }
    }
    @Test
    void test1(){
        knowledgeService.k_countAdd(2);
    }

}
