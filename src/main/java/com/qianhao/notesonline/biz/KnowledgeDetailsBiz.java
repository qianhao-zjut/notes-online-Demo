package com.qianhao.notesonline.biz;

import com.qianhao.notesonline.beans.Knowledge;
import com.qianhao.notesonline.result.Result;
import com.qianhao.notesonline.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 根据知识点id,获取知识点详情
 */
@Component
public class KnowledgeDetailsBiz {
    @Autowired
    private KnowledgeService knowledgeService;

    /**
     *
     * @param k_id  知识点id
     * @return      查询结果
     */
    public Result getKDetails(int k_id){
        Knowledge knowledge = knowledgeService.findById(k_id);

        Result result = Result.success("获取知识点详情成功!", knowledge);
        return result;
    }
}
