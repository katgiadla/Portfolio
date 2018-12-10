package mes.funkcje;

import java.util.LinkedList;
import java.util.List;

public class Grid {
    private int length, height, amountNodesAtLengthOfGrid, amountNodesAtHeightOfGrid, elementsAmount,nodesAmount;
    private int deltaLength, deltaHeight, elementsIndex;
    private List<Node> nodesCollection;
    private List<Element> elementsCollection;

    public Grid(int length, int height, int amountNodesAtLengthOfGrid, int amountNodesAtHeightOfGrid) {
        this.length = length;
        this.height = height;
        this.amountNodesAtLengthOfGrid = amountNodesAtLengthOfGrid;
        this.amountNodesAtHeightOfGrid = amountNodesAtHeightOfGrid;
        elementsAmount = (amountNodesAtHeightOfGrid -1)*(amountNodesAtLengthOfGrid -1);
        nodesAmount = amountNodesAtHeightOfGrid * amountNodesAtLengthOfGrid;
        nodesCollection = new LinkedList<>();
        elementsCollection = new LinkedList<>();
        deltaHeight = height / (amountNodesAtHeightOfGrid -1);
        deltaLength = length / (amountNodesAtLengthOfGrid -1);
        this.createGrid();
    }

    private void createGrid() {
        int elementIndex = 0;
        for(int x = 0; x< amountNodesAtLengthOfGrid -1; x++){
            for(int y = 0; y< amountNodesAtHeightOfGrid -1; y++) {
                int xPattern = x* length /(amountNodesAtLengthOfGrid -1);
                int yPattern = y* height /(amountNodesAtHeightOfGrid -1);

                Node[] elementNodes = {
                        new Node(x*amountNodesAtHeightOfGrid+y+1,           xPattern,                  yPattern               , 20,this.checkIfBorderNode(xPattern,                   yPattern)),
                        new Node((x+1)*amountNodesAtHeightOfGrid+y+1,    xPattern+ deltaLength,     yPattern               , 20,this.checkIfBorderNode(xPattern+ deltaLength,   yPattern)),
                        new Node((x+1)*amountNodesAtHeightOfGrid+(y+1)+1,xPattern+ deltaLength,  yPattern+ deltaHeight  , 20,this.checkIfBorderNode(xPattern+ deltaLength,yPattern+ deltaHeight)),
                        new Node(x*amountNodesAtHeightOfGrid+(y+1)+1,       xPattern,               yPattern+ deltaHeight  , 20,this.checkIfBorderNode(xPattern,                yPattern+ deltaHeight))};

                int []  nodesID = {elementNodes[0].getId(),elementNodes[1].getId(),elementNodes[2].getId(),elementNodes[3].getId()};

                Element element = new Element(++elementIndex,nodesID,elementNodes);
                //System.out.println(   element.toString());
                elementsCollection.add(element);
            }
        }
        this.collectNodes();
    }

    private boolean checkIfBorderNode(int x, int y){
        if(x - deltaLength<0   || x + deltaLength>length){return true;}
        if(y - deltaHeight<0   || y + deltaHeight>height){return true;}
        return false;
    }

    private void collectNodes(){
        for(Element element: elementsCollection) {
            if (!nodesCollection.contains(element.getNode1())) {
                nodesCollection.add(element.getNode1());
            }
            if (!nodesCollection.contains(element.getNode2())) {
                nodesCollection.add(element.getNode2());
            }
            if (!nodesCollection.contains(element.getNode3())) {
                nodesCollection.add(element.getNode3());
            }
            if (!nodesCollection.contains(element.getNode4())) {
                nodesCollection.add(element.getNode4());
            }
        }
    }

    public void print(List<?> inputObject) {
        int index = 0;
        for (Object element : inputObject) {
            System.out.println((index + 1) + "\n" + element.toString());
            index++;
        }
    }

    public List<Node> getNodesCollection() {
        return nodesCollection;
    }

    public List<Element> getElementsCollection() {
        return elementsCollection;
    }
}

/*private void createGrid(){
        elementsIndex = 0;
        for(int x = 0; x< amountNodesAtLengthOfGrid -1; x++){
            for(int y = 0; y< amountNodesAtHeightOfGrid -1; y++){

                int xPattern = x* length /(amountNodesAtLengthOfGrid -1);
                int yPattern = y* height /(amountNodesAtHeightOfGrid -1);

                Node [] tmp = {new Node(,xPattern,                   yPattern               ,xPattern + yPattern 20,this.checkIfBorderNode(x,y)),
        new Node(,xPattern+ deltaLength,   yPattern               ,xPattern + deltaLength + yPattern 20,),
        new Node(,xPattern+ deltaLength,yPattern+ deltaHeight  ,xPattern + deltaLength + yPattern + deltaHeight 20,),
        new Node(,xPattern,                yPattern+ deltaHeight  ,xPattern + yPattern + deltaHeight 20,)};*/

       /* Element exampleElement = new Element(elementsIndex+1,tmp,);
                /*exampleElement.setNodes(

                );*/
       /* elementsCollection.add(exampleElement);
        elementsIndex++;
        }
        }
        this.collectNodes();
        }



*/
