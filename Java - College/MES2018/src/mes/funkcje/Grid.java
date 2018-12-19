package mes.funkcje;

import java.util.LinkedList;
import java.util.List;

public class Grid {
    private int amountNodesAtLengthOfGrid, amountNodesAtHeightOfGrid, elementsAmount,nodesAmount;
    double length, height;
    private double deltaLength, deltaHeight, elementsIndex;
    private List<Element> elementsCollection;

    public Grid(double length, double height, int amountNodesAtLengthOfGrid, int amountNodesAtHeightOfGrid) {
        this.length = length;
        this.height = height;
        this.amountNodesAtLengthOfGrid = amountNodesAtLengthOfGrid;
        this.amountNodesAtHeightOfGrid = amountNodesAtHeightOfGrid;
        elementsAmount = (amountNodesAtHeightOfGrid -1)*(amountNodesAtLengthOfGrid -1);
        nodesAmount = amountNodesAtHeightOfGrid * amountNodesAtLengthOfGrid;
        elementsCollection = new LinkedList<>();
        deltaHeight = height / (amountNodesAtHeightOfGrid -1);
        deltaLength = length / (amountNodesAtLengthOfGrid -1);
        this.createGrid();
    }

    private void createGrid() {
        int elementIndex = 0;
        for(int x = 0; x< amountNodesAtLengthOfGrid -1; x++){
            for(int y = 0; y< amountNodesAtHeightOfGrid -1; y++) {
                double xPattern = ((x* length)*10) /((amountNodesAtLengthOfGrid -1)*10);
                double yPattern = ((y* height)*10) /((amountNodesAtHeightOfGrid -1)*10);

                Node[] elementNodes = {
                        new Node(x*amountNodesAtHeightOfGrid+y,           xPattern,                  yPattern               , 20,this.checkIfBorderNode(xPattern,                   yPattern)),
                        new Node((x+1)*amountNodesAtHeightOfGrid+y,    xPattern+ deltaLength,     yPattern               , 20,this.checkIfBorderNode(xPattern+ deltaLength,   yPattern)),
                        new Node((x+1)*amountNodesAtHeightOfGrid+(y+1),xPattern+ deltaLength,  yPattern+ deltaHeight  , 20,this.checkIfBorderNode(xPattern+ deltaLength,yPattern+ deltaHeight)),
                        new Node(x*amountNodesAtHeightOfGrid+(y+1),       xPattern,               yPattern+ deltaHeight  , 20,this.checkIfBorderNode(xPattern,                yPattern+ deltaHeight))};

                int []  nodesID = {elementNodes[0].getId(),elementNodes[1].getId(),elementNodes[2].getId(),elementNodes[3].getId()};

                Element element = new Element(++elementIndex,nodesID,elementNodes);
                //System.out.println( "element: "+elementIndex+" "+  element.toString());
                elementsCollection.add(element);
            }
        }

    }

    private boolean checkIfBorderNode(double x, double y){
        if(x - deltaLength<0   || x + deltaLength>length){return true;}
        if(y - deltaHeight<0   || y + deltaHeight>height){return true;}
        return false;
    }

    public List<Element> getElementsCollection() {
        return elementsCollection;
    }
}
