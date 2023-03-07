package Caviar;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Node {
    public String name;
    public List<Node> children;

    //构造函数
    public Node(String n) {
    children = new ArrayList<Node>();
    name = n;
}


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Node> getChildren() {
        return children;
    }
    public void setChildren(List<Node> children) {
        this.children = children;
    }



    //遍历
    public static void traverseDepthFirst(Node n) {
        if (n.getChildren().isEmpty()) {
            //System.out.println();

        } else {
            List<Node> children = n.getChildren();
            for (Node c : children) {
                //System.out.print(c + "/");
                traverseDepthFirst(c);
            }

        }
    }
    public static void traverseBreathFirst(Node n) {
        if (n.getChildren().isEmpty()) {

        } else {
            List<Node> children = n.getChildren();
            for (Node c : children) {
                System.out.println(c);
            }
            for (Node c : children) {
                traverseDepthFirst(c);
            }

        }
    }


    //所有完全路径
    public Stack<String> path = new Stack<String>();
    public ArrayList<Stack<String>> allPath=new ArrayList<>();
    public void traverseFullPath(Node n) {
        for (Node c : n.getChildren()) {
            path.push(c.name);
            if (c.getChildren().isEmpty()) {
                //System.out.println(path);
                allPath.add((Stack<String>) path.clone());
            } else {
                traverseFullPath(c);
            }
            path.pop();
        }


    }


    @Override
    public String toString() {
        return name;
    }
}
