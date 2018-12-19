package mes.funkcje;

public class Element {

    private final double KSI = 1.0 / Math.sqrt(3);

    private int element_ID;
    private int[] nodes_ID;
    private Node[] nodes;
    private boolean[] borders_ID = new boolean[4];
    private double [] borders_length = new double[4];



    private Local2D[] local_2D_Calculation_Points = {
            new Local2D(-KSI, -1),    new Local2D(KSI, -1),//1
            new Local2D(1, -KSI),     new Local2D(1, KSI), //2
            new Local2D(KSI,1),       new Local2D(-KSI,1),//3
            new Local2D(-1, KSI),     new Local2D(-1, -KSI)//4
    };


    public Element(int element_ID, int[] nodes_ID, Node[] nodes) {
        this.element_ID = element_ID;
        this.nodes_ID = nodes_ID;
        this.nodes = nodes;

        for(int i =0;i<borders_ID.length;i++){
            borders_ID[i]=false;
            borders_length[i]=0;
        }
        this.check_borders(nodes);
        this.calculate_borders_length();
    }

    private void check_borders(Node[] inputNode){
        if(inputNode[0].isBorderCondition() && inputNode[1].isBorderCondition()){borders_ID[0]=true;}
        if(inputNode[1].isBorderCondition() && inputNode[2].isBorderCondition()){borders_ID[1]=true;}
        if(inputNode[2].isBorderCondition() && inputNode[3].isBorderCondition()){borders_ID[2]=true;}
        if(inputNode[3].isBorderCondition() && inputNode[0].isBorderCondition()){borders_ID[3]=true;}
    }

    private void calculate_borders_length(){
        for(int i=0;i<borders_length.length;i++){
            if(borders_ID[i])
                borders_length[i]=Math.sqrt(Math.pow(nodes[(i+1)%borders_length.length].getX()-nodes[i].getX(),2)+Math.pow(nodes[(i+1)%borders_length.length].getY()-nodes[i].getY(),2));
        }
    }

    public Node[] getNodes() {
        return nodes;
    }

    public Node getNode1()
    {
        return nodes[0];
    }
    public Node getNode2()
    {
        return nodes[1];
    }
    public Node getNode3()
    {
        return nodes[2];
    }
    public Node getNode4()
    {
        return nodes[3];
    }

    public boolean isEdge1Border(){
        return borders_ID[0];
    }
    public boolean isEdge2Border(){
        return borders_ID[1];
    }
    public boolean isEdge3Border(){
        return borders_ID[2];
    }
    public boolean isEdge4Border(){
        return borders_ID[3];
    }

    public double getEdge1(){return borders_length[0];
    }
    public double getEdge2(){return borders_length[1];
    }
    public double getEdge3(){return borders_length[2];
    }
    public double getEdge4(){return borders_length[3];
    }

    public Local2D[] getLocal_2D_Calculation_Points() {
        return local_2D_Calculation_Points;
    }

    public double calculateShapeFunction1(double ksi, double eta){
        return 0.25*(1-ksi)*(1-eta);
    }
    public double calculateShapeFunction2(double ksi, double eta){
        return 0.25*(1+ksi)*(1-eta);
    }
    public double calculateShapeFunction3(double ksi, double eta){
        return 0.25*(1+ksi)*(1+eta);
    }
    public double calculateShapeFunction4(double ksi, double eta){
        return 0.25*(1-ksi)*(1+eta);
    }

    @Override
    public String toString() {
        return this.getNode1() +" "+this.getNode2()+" "+this.getNode3()+" "+this.getNode4()+"\n";
    }
}
