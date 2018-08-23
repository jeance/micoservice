package com.te.micoservice.service.test;

/**
 * Created by cxj4842 on 2018/7/17.
 */
public class RedBlackTree {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    transient NodeStruct root;

    public NodeStruct put(NodeStruct newNode) {
        NodeStruct temp = root;
        NodeStruct parentNode = null;
        NodeStruct indexNode;
        boolean isLeft = true;
        if (temp == null) {
            if (newNode.getNodeKey() != 0) {
                root = new NodeStruct(null, null, null, BLACK,
                        newNode.getNodeKey(), newNode.getNodeValue());
            }
            return root;
        } else {
            indexNode = temp;
            while (indexNode != null) {
                parentNode = indexNode;
                if (newNode.getNodeValue() > indexNode.getNodeValue()) {
                    indexNode = indexNode.getRightNode();
                    isLeft = false;
                } else if (newNode.getNodeValue() < indexNode.getNodeValue()) {
                    indexNode = indexNode.getLeftNode();
                    isLeft = true;
                }
                else
                {
                    indexNode.setNodeValue(newNode.getNodeValue());
                    return null;
                }
            }
            newNode.setParentNode(parentNode);
            if (isLeft) {
                parentNode.setLeftNode(newNode);
            } else {
                parentNode.setRightNode(newNode);
            }
        }
        fixAfterInsertion(newNode);
        return null;
    }

    private void fixAfterInsertion(NodeStruct newNode) {
        newNode.setColor(RED);
        NodeStruct parentNode = null;
        NodeStruct uncleNode = null;
        while (newNode != null && newNode != root && newNode.getParentNode().getColor() == RED) {
            parentNode = newNode.getParentNode();
            if (parentNode.getParentNode().getLeftNode() == parentNode) {
                uncleNode = parentNode.getParentNode().getRightNode();
                if (uncleNode.getColor() == RED) {
                    //左边子树的父节点为红，且父节点的兄弟节点也为红色。
                    // 不管添加在父节点的左边还是父节点的右边都执行变色操作。并将当前节点设置为祖父节点。
                    // 视祖父节点为新增节点，再往上层修复，直到根节点。
                    parentNode.setColor(BLACK);
                    parentNode.getParentNode().setColor(RED);
                    uncleNode.setColor(BLACK);
                    newNode = parentNode.getParentNode();
                } else {
                    if (parentNode.getRightNode() == newNode) {
                        //正常的逻辑应该是这样，分添加左右子树，但是因为添加右节点的时候先左旋。
                        // 新节点会变成父节点的父节点为红色节点。父节点的父节点重新上色为红色没有影响。流程和第一个if一致
                        rotateLeft(parentNode);
//                    parentNode.setColor(BLACK);
//                    parentNode.getParentNode().setColor(RED);
//                    rotateRight(parentNode);
                    }
//                else{
//                    //添加在左边子树左节点父节点为红，和添加在左子树右节点的父节点为黑
//                    parentNode.setColor(BLACK);
//                    parentNode.getParentNode().setColor(RED);
//                    rotateRight(parentNode);
//                }
                    parentNode.getParentNode().setColor(BLACK);
                    parentNode.getParentNode().getParentNode().setColor(RED);
                    rotateRight(parentNode.getParentNode().getParentNode());
                }
            } else {
                uncleNode = parentNode.getParentNode().getLeftNode();
                if (uncleNode.getColor() == RED) {
                    parentNode.setColor(BLACK);
                    uncleNode.setColor(BLACK);
                    parentNode.getParentNode().setColor(RED);
                    newNode=parentNode.getParentNode();
                }
                else{
                    if(newNode==parentNode.getLeftNode())
                    {
                        rotateRight(parentNode);
                        parentNode.getParentNode().setColor(BLACK);
                        parentNode.getParentNode().getParentNode().setColor(RED);
                        rotateLeft(parentNode.getParentNode().getParentNode());
                    }
                    else
                    {
                        parentNode.getParentNode().setColor(BLACK);
                        parentNode.getParentNode().getParentNode().setColor(RED);
                        rotateLeft(parentNode.getParentNode().getParentNode());
                    }
                }
            }
        }
        root.setColor(BLACK);
    }


    private void rotateRight(NodeStruct node) {
        NodeStruct temp = node.getLeftNode();
        node.setLeftNode(temp.getRightNode());
        temp.getRightNode().setParentNode(node);
        temp.setParentNode(node.getParentNode());
        if (node.getParentNode() == null) {
            root = temp;
        } else {
            if (node.getParentNode().getRightNode() == node) {
                node.getParentNode().setRightNode(node);
            } else {
                node.getParentNode().setLeftNode(node);
            }
        }
        temp.setRightNode(node);
        node.setParentNode(temp);
    }

    private void rotateLeft(NodeStruct node) {
        NodeStruct temp = node.getRightNode();
        node.setRightNode(temp.getLeftNode());
        temp.getLeftNode().setParentNode(node);
        temp.setParentNode(node.getParentNode());
        if (node.getParentNode() == null) {
            root = temp;
        } else {

            if (node.getParentNode().getLeftNode() == node) {
                node.setLeftNode(temp);
            } else {
                node.setRightNode(temp);
            }
        }
        temp.setLeftNode(node);
        node.setParentNode(temp);


    }
}
