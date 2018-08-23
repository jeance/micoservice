package com.te.micoservice.service.test;

/**
 * Created by cxj4842 on 2018/7/17.
 */
public class NodeStruct {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private NodeStruct leftNode;
    private NodeStruct rightNode;
    private NodeStruct parentNode;

    private boolean color = BLACK;
    private float nodeValue;
    private float nodeKey;

    public float getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(float nodeKey) {
        this.nodeKey = nodeKey;
    }

    public NodeStruct() {

    }

    public NodeStruct(NodeStruct left, NodeStruct right, NodeStruct parent,
                      boolean color, float nodeKey, float nodeValue) {
        this.leftNode = left;
        this.rightNode = right;
        this.parentNode = parent;
        this.color = color ? BLACK : RED;
        this.nodeKey = nodeKey;
        this.nodeValue = nodeValue;

    }

    public NodeStruct getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(NodeStruct leftNode) {
        this.leftNode = leftNode;
    }

    public NodeStruct getRightNode() {
        return rightNode;
    }

    public void setRightNode(NodeStruct rightNode) {
        this.rightNode = rightNode;
    }

    public NodeStruct getParentNode() {
        return parentNode;
    }

    public void setParentNode(NodeStruct parentNode) {
        this.parentNode = parentNode;
    }


    public void setColor(boolean color) {
        this.color = color;
    }

    public boolean getColor() {
        return this.color;
    }

    public float getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(float nodeValue) {
        this.nodeValue = nodeValue;
    }
}
