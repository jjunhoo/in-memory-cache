package com.amorepacific.inmemorycache.cache;

import com.amorepacific.inmemorycache.domain.CacheDomain;
import com.amorepacific.inmemorycache.domain.CacheProduct;

import java.util.List;

public class Node {
    private String id;                      // Key
    private CacheDomain data;               // Data
    private List<CacheProduct> dataList;    // DataList
    private Node prevNode;                  // 이전 노드
    private Node nextNode;                  // 다음 노드

    public Node(String id, CacheDomain data) {
        this.id = id;
        this.data = data;
    }

    public Node(String id, List<CacheProduct> dataList) {
        this.id = id;
        this.dataList = dataList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CacheDomain getData() {
        return data;
    }

    public void setData(CacheDomain data) {
        this.data = data;
    }

    public Node getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(Node prevNode) {
        this.prevNode = prevNode;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public List<CacheProduct> getDataList() {
        return dataList;
    }

    public void setDataList(List<CacheProduct> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "id='" + id + '\'' + ", data=" + data.toString() + ", dataList=" + dataList.toString();
    }
}
