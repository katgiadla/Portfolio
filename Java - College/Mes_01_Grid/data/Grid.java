package data;

public class Grid {
    private int l, h, nL, nH, elementsAmount,nodesAmount;
    private double dL, dH;
    private Node[] nodesArray;
    private Element[] elementsArray;

    public Grid(int l, int h, int nL, int nH) {
        this.l = l;
        this.h = h;
        this.nL = nL;
        this.nH = nH;
        elementsAmount = (nH -1)*(nL-1);
        nodesAmount = nH*nL;
        nodesArray = new Node[nodesAmount];
        elementsArray = new Element[elementsAmount];
        dH = h/nH;
        dL = l/nL;
        this.createGrid();
    }

    private void createGrid(){
        int elementIndex = 0;
        for(int x = 0; x<nL-1;x++){
            for(int y = 0; y<nH-1;y++){
                int exampleTemperature = x + y;
                elementsArray[elementIndex]=new Element();
                elementsArray[elementIndex].setNodes(
                        new Node((double)x*l/(nL-1),         (double)y*h/(nH-1),         exampleTemperature),
                        new Node((double)x*l/(nL-1)+l/(nL-1),(double)y*h/(nH-1),         exampleTemperature),
                        new Node((double)x*l/(nL-1)+l/(nL-1),(double)y*h/(nH-1)+h/(nH-1),exampleTemperature),
                        new Node((double)x*l/(nL-1),         (double)y*h/(nH-1)+h/(nH-1),exampleTemperature));

                elementIndex++;
            }
        }
    }

    public void printGrid(){
        int index = 0;
        for(Element element : elementsArray){
            System.out.println("element nr: "+(index+1)+"\n" + element.toString());
            index++;
        }
    }
}
