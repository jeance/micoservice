package com.te.micoservice.common.definition;

/**
 * Created by yc12904 on 2017/7/12.
 */
public class PublicEnum {
    public enum  ZkEventType{
        NodeCreated("创建节点", 1),
        NodeDeleted("删除节点", 2),
        NodeDataChanged("节点数据变化", 3),
        NodeChildrenChanged("创建子节点", 4);

        private String name;

        private int index;
        private ZkEventType(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public int getIndex() {
            return index;
        }


    }
    ///job 运行状态
   public enum JobRunModel
   {
       //并行
       Parallel("Parallel", 0),
       ///串行
       SerialCalculation("SerialCalculation", 1);

       private String name;
       private int index;

       private JobRunModel(String name, int index) {
           this.name = name;
           this.index = index;
       }

       public String getName() {
           return name;
       }

       public int getIndex() {
           return index;
       }
   }
    public enum BillType {
        //所有账单
        All("All", 0),
        ///消费类账单
        Consumption("Consumption", 1),
        ///退款账单
        Refund("Refund", 2);
        private String name;
        private int index;

        private BillType(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public int getIndex() {
            return index;
        }

    }
    

    /**
     * 页面的操作类型
     */
    public enum ActionType {
        //添加数据
        Add("Add", 0),
        ///编辑
        Edit("Edit", 1);
        private String name;
        private int index;

        private ActionType(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public int getIndex() {
            return index;
        }
    }
    /**
     * Zookeeper的节点类型
     */
    public enum ZkNodeType {
        //临时节点
        Ephemeral("Ephemeral",1),
        //临时有序节点
        EphemeralSequential("EphemeralSequential",2),
        //持久化节点
        Persistent("Persistent",3),
        //持久有序节点
        PersistentSequential("PersistentSequential",4);

        private String name;
        private int index;

        private ZkNodeType(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public int getIndex() {
            return index;
        }
    }

    public enum ZkRunStatus
    {
        ///默认值
        None("None",0),
        //正常
        Normal("Normal", 1),
        //异常
        Error("Error",2);
        private String name;
        private int index;

        private ZkRunStatus(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public int getIndex() {
            return index;
        }
    }

    public enum RowStatus
    {
        ///默认值
        Normal("Normal",0),
        //正常
        Dleted("Dleted", 1);

        private String name;
        private int index;

        private RowStatus(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public int getIndex() {
            return index;
        }
    }
}
