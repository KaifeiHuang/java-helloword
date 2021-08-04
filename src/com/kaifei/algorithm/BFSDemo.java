package com.kaifei.algorithm;

import org.junit.Test;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 */
public class BFSDemo {

    /**
     * 偶数： 左 -> 右
     *给定一个二叉树，返回其节点值的锯齿形层序遍历。
     * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行
     * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/submissions/
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);
        int count = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {

                // 偶数层： 左-》右
                if (count % 2 == 0) {
                    TreeNode cur = queue.pollFirst();
                    level.add(cur.val);
                    if (cur.left != null) queue.addLast(cur.left);
                    if (cur.right != null) queue.addLast(cur.right);
                } else { // 奇数层： 右往左
                    TreeNode left = queue.pollLast();
                    level.add(left.val);
                    if (left.right != null) queue.addFirst(left.right);
                    if (left.left != null) queue.addFirst(left.left);
                }

            }
            count++;
            res.add(level);
        }

        return res;
    }

    @Test
    public void testTree(){
        TreeNode treeNode3l = new TreeNode(15, null, null);
        TreeNode treeNode3r = new TreeNode(7, null, null);

        TreeNode treeNode2r = new TreeNode(20, treeNode3l, treeNode3r);
        TreeNode treeNode2l = new TreeNode(9, null, null);

        TreeNode treeNode = new TreeNode(3, treeNode2l, treeNode2r);

        List<List<Integer>> lists = zigzagLevelOrder(treeNode);
        System.out.println(lists);


    }

    /**
     * 锯齿
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;
        //用linklist，整个作为队列遍历树，前半部分看成栈1，后半部分看成栈2.做到每一层的节点依次放到不同栈中。
        Deque<TreeNode> dequeue = new LinkedList<>();
        dequeue.addFirst(root); //开始从前面进前面出
        int count = 0;  //计数。从偶数开始
        while (!dequeue.isEmpty()) {    //队列用来遍历树
            int curSize = dequeue.size();   //获取当前层的元素个数
            List<Integer> list =  new ArrayList<>();
            for (int i = 0; i < curSize; i++) { //遍历当前层所有节点
                if (count%2 == 0) { //偶数，从前面栈中取元素。并分别将左子节点和右子节点放到后面栈中
                    TreeNode first = dequeue.pollFirst();
                    list.add(first.val);
                    if (first.left != null) dequeue.addLast(first.left);
                    if (first.right != null) dequeue.addLast(first.right);
                } else {    //奇数，从后面栈中取元素。并分别将右子节点和左子节点放到前面栈中
                    TreeNode right = dequeue.pollLast();
                    list.add(right.val);
                    if (right.right != null) dequeue.addFirst(right.right);
                    if (right.left != null) dequeue.addFirst(right.left);
                }
            }
            count++;    //计数+1
            lists.add(list);    //list添加到总list中
        }
        return lists;
    }


    /**
     * 层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                level.add(cur.val);

                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }

            res.add(level);
        }

        return res;
    }

    @Test
    public void testBFS() {
        System.out.println(1 %2);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


}
