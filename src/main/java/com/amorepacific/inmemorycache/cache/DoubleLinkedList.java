package com.amorepacific.inmemorycache.cache;

public class DoubleLinkedList {
    private Node headNode;  // 첫번째 노드
    private Node tailNode;  // 마지막 노드

    public Node getHeadNode() {
        return headNode;
    }

    public void setHeadNode(Node headNode) {
        this.headNode = headNode;
    }

    public Node getTailNode() {
        return tailNode;
    }

    public void setTailNode(Node tailNode) {
        this.tailNode = tailNode;
    }
}
