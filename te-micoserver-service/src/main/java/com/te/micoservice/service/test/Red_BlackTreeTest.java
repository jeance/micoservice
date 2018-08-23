package com.te.micoservice.service.test;

/**
 * Created by cxj4842 on 2018/7/17.
 */
public class Red_BlackTreeTest {

    static RedBlackTree rbt=  new RedBlackTree();
    public static void main(String [] args)
    {
        float[] nodeValue = {11, 2, 14, 1, 7, 15, 5, 8, 4, 13};

        for(int i = 0; i < nodeValue.length; i++)
        {
            NodeStruct node = new NodeStruct();
            node.setNodeValue(nodeValue[i]);
            node.setNodeKey(nodeValue[i]);
            rbt.put(node);
        }

        if(rbt.root !=null)
        {
            //深度优先遍历或者前序遍历的递归展示二叉树
            show(rbt.root);
        }else
        {
            System.out.println("一颗空的红黑树！");
        }

    }

    public static void show(NodeStruct tree)
    {
        if(tree.getLeftNode() != null || tree.getRightNode() !=null)
        {
            if(tree.getLeftNode() != null)
            {
                float x = tree.getLeftNode().getNodeValue();
                boolean color = tree.getLeftNode().getColor();
                String colorStr = null;
                if(color)
                {
                    colorStr = "RED";
                }else
                {
                    colorStr = "BLACK";
                }
                String parentColor = tree.getColor() ? "RED" : "BLACK";
                System.out.println("left: " + "color: " + colorStr + "-->" + x + "  --父节点-->  " + parentColor + "  " + tree.getNodeValue());
                show(tree.getLeftNode());
            }
            if(tree.getRightNode() != null)
            {
                float y = tree.getRightNode().getNodeValue();
                boolean color = tree.getRightNode().getColor();
                String colorStr = null;
                if(color)
                {
                    colorStr = "RED";
                }else
                {
                    colorStr = "BLACK";
                }
                String parentColor = tree.getColor() ? "RED" : "BLACK";
                System.out.println("right: " + "color: " + colorStr + "-->" + y + "  --父节点-->  " + parentColor + "  " + tree.getNodeValue());
                show(tree.getRightNode());
            }
        }else
        {
            if(tree.getParentNode() == null)
            {
                System.out.println("该红黑树仅有根节点，且  “"+tree.getNodeValue()+ "” 为红黑树的根节点");
            }
        }
    }

}
