package com.qianhao.notesonline.service;

import com.qianhao.notesonline.beans.Document;

import java.util.List;
import java.util.Map;

public interface DocumentService {
    Document findById(int id);
    List<Document>findByParams(Map<String,Object>params);
    boolean insert(Document document);
}
