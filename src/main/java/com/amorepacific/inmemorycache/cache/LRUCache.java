package com.amorepacific.inmemorycache.cache;

import com.amorepacific.inmemorycache.domain.CacheDomain;
import com.amorepacific.inmemorycache.domain.CacheProduct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LRUCache {
    private int actualSize;
    private Map<String, Node> map;
    private DoubleLinkedList linkedList;

    public LRUCache() {
        this.map = new HashMap<>();
        this.linkedList = new DoubleLinkedList();
    }

    // 데이터 삽입 (List) - Method
    public void putList(String id, List<CacheProduct> dataList) {
        // 수정인 경우, 기존 노드 업데이트
        if (map.containsKey(id)) {
            Node node = this.map.get(id);
            node.setDataList(dataList);
            update(node);
            return;
        }
        // 새 노드 생성
        Node newNode = new Node(id, dataList);

        // 지정된 CAPACITY 보다 실제 사이즈가 작은 경우, 삽입
        if (this.actualSize < Constants.CAPACITY) {
            this.actualSize++;
            add(newNode);
        }
        // Cache Data Eviction Policy - 지정된 Capacity 를 초과한 경우, LRU 알고리즘을 통해 최근 가장 사용되지 않은 데이터를 우선적으로 제거
        // 이유 : 가장 오랫동안 사용하지 않았던 데이터는 앞으로도 사용할 확률이 적기 때문에 LRU 정책 사용
        // 지정된 CAPACITY 보다 실제 사이즈가 크거나 같으면 마지막 노드를 제거하고 삽입
        else {
            System.out.println("Cache Memory is Full - Remove Tail Node");
            removeTail();
            add(newNode);
        }
    }

    // 데이터 삽입 (Object) - Method
    public void put(String id, CacheDomain data) {
        // 수정인 경우, 기존 노드 업데이트
        if (map.containsKey(id)) {
            Node node = this.map.get(id);
            node.setData(data);
            update(node);
            return;
        }
        // 새 노드 생성
        Node newNode = new Node(id, data);

        // 지정된 CAPACITY 보다 실제 사이즈가 작은 경우, 삽입
        if (this.actualSize < Constants.CAPACITY) {
            this.actualSize++;
            add(newNode);
        }
        // Cache Data Eviction Policy - 지정된 Capacity 를 초과한 경우, LRU 알고리즘을 통해 최근 가장 사용되지 않은 데이터를 우선적으로 제거
        // 이유 : 가장 오랫동안 사용하지 않았던 데이터는 앞으로도 사용할 확률이 적기 때문에 LRU 정책 사용
        // 지정된 CAPACITY 보다 실제 사이즈가 크거나 같으면 마지막 노드를 제거하고 삽입
        else {
            System.out.println("Cache Memory is Full - Remove Tail Node");
            removeTail();
            add(newNode);
        }
    }

    // 특정 노드 반환 및 반환된 노드를 head 노드로 변경
    public Node get(String id) {
        // id 에 매핑된 key값이 map에 존재하지 않으면 null return
        if (!this.map.containsKey(id)) {
            return null;
        }

        // id 값에 해당하는 노드 추출
        Node node = this.map.get(id);

        // 추출한 노드를 head 노드로 변경
        update(node);
        return node;
    }

    // Cache size
    public int size() {
        int size = 0;
        Node actualNode = this.linkedList.getHeadNode();
        while (actualNode != null) {
            actualNode = actualNode.getNextNode();
            size++;
        }
        return size;
    }

    // 노드 삽입
    private void add(Node newNode) {
        // 새 노드의 다음 노드를 기존의 head 노드로 셋팅 (새노드 -> head 노드)
        newNode.setNextNode(this.linkedList.getHeadNode());
        newNode.setPrevNode(null);

        // 기존의 head 노드의 이전 노드를 새 노드로 셋팅 (새노드 <- head 노드)
        if (linkedList.getHeadNode() != null) {
            linkedList.getHeadNode().setPrevNode(newNode);
        }

        // 새 노드를 head 노드로 셋팅 (head 노드 = 새 노드)
        linkedList.setHeadNode(newNode);

        // tail 노드가 없는 경우 (첫번째로 삽입되는 노드 인 경우) tail 노드를 새 노드로 셋팅
        if (linkedList.getTailNode() == null) {
            linkedList.setTailNode(newNode);
        }

        this.map.put(newNode.getId(), newNode);
    }

    // 연결리스트 변경
    private void update(Node node) {
        Node prevNode = node.getPrevNode();
        Node nextNode = node.getNextNode();

        // head 노드가 아니면 (middle 노드인 경우)
        if (prevNode != null) {
            prevNode.setNextNode(nextNode);
        }
        // head 노드인 경우
        else {
            this.linkedList.setHeadNode(nextNode);
        }

        // tail 노드가 아닌 경우 (middle 노드인 경우)
        if (nextNode != null) {
            nextNode.setPrevNode(prevNode);
        }
        // tail 노드인 경우
        else {
            this.linkedList.setTailNode(prevNode);
        }

        add(node);
    }

    // tail 노드 삭제
    private void removeTail() {
        Node lastNode = this.map.get(this.linkedList.getTailNode().getId());
        this.linkedList.setTailNode(linkedList.getTailNode().getPrevNode());

        if (this.linkedList.getTailNode() != null) {
            this.linkedList.getTailNode().setNextNode(null);
        }

        this.map.remove(this.linkedList.getTailNode().getId());

        lastNode = null;
    }
}

