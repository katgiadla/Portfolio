package data;

import java.util.LinkedList;
import java.util.List;

public class Grid {
    private int length, height, nodesAtLength, nodesAtHeight, elementsAmount,nodesAmount;
    private int deltaLength, deltaHeight;
    private List<Node> nodesCollection;
    private List<Element> elementsCollection;

    public Grid(int length, int height, int nodesAtLength, int nodesAtHeight) {
        this.length = length;
        this.height = height;
        this.nodesAtLength = nodesAtLength;
        this.nodesAtHeight = nodesAtHeight;
        elementsAmount = (nodesAtHeight -1)*(nodesAtLength -1);
        nodesAmount = nodesAtHeight * nodesAtLength;
        nodesCollection = new LinkedList<>();
        elementsCollection = new LinkedList<>();
        deltaHeight = height / (nodesAtHeight-1);
        deltaLength = length / (nodesAtLength-1);
        this.createGrid();
    }

    private void createGrid(){
        for(int x = 0; x< nodesAtLength -1; x++){
            for(int y = 0; y< nodesAtHeight -1; y++){

                int xPattern = x* length /(nodesAtLength -1);
                int yPattern = y* height /(nodesAtHeight -1);

                Element exampleElement = new Element();
                exampleElement.setNodes(
                        new Node(xPattern,                   yPattern               ,xPattern + yPattern ),
                        new Node(xPattern+ deltaLength,   yPattern               ,xPattern + deltaLength + yPattern ),
                        new Node(xPattern+ deltaLength,yPattern+ deltaHeight  ,xPattern + deltaLength + yPattern + deltaHeight),
                        new Node(xPattern,                yPattern+ deltaHeight  ,xPattern + yPattern + deltaHeight)
                );
                elementsCollection.add(exampleElement);
            }
        }
        this.collectNodes();
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
}