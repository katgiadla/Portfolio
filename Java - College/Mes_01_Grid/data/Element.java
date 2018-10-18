package data;

import java.util.Arrays;

public class Element {
    private Node[] nodeArray;

    public Element(){
        nodeArray  = new Node[4];
    }

    public void setNodes(Node elem1, Node elem2, Node elem3, Node elem4){
        nodeArray[0] = elem1;
        nodeArray[1] = elem2;
        nodeArray[2] = elem3;
        nodeArray[3] = elem4;
    }

    public Node getNode1()
    {
        return nodeArray[0];
    }
    public Node getNode2()
    {
        return nodeArray[1];
    }
    public Node getNode3()
    {
        return nodeArray[2];
    }
    public Node getNode4()
    {
        return nodeArray[3];
    }

    @Override
    public String toString() {
        return  this.getNode1() + "\n " + this.getNode2() + "\n " + this.getNode3() + "\n " + this.getNode4() + "\t";
    }
}