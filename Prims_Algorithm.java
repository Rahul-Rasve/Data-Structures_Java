package com.company;

import java.util.*;

public class Prims_Algorithm {
     static class VertexNode{
         private final int data;
         private EdgeNode next;

         public VertexNode(int data){
             this.data = data;
             next = null;
         }
     }
     static class EdgeNode{
         private final int data;
         private final int weight;
         private EdgeNode next;

         public EdgeNode(int data, int weight){
             this.data = data;
             this.weight = weight;
             next = null;
         }
     }

     //prims algorithm
     private final ArrayList<VertexNode> vArray = new ArrayList<>(0);

     public void insertVertex(int data){
         VertexNode vertex = new VertexNode(data);
         vArray.add(vertex);
     }

     public void insertEdge(int src, int des, int weight){
         try{
             EdgeNode temp = null;
             VertexNode y = null;
             for(VertexNode x : vArray){
                 if(x.data == src){
                     temp = x.next;
                     if(temp == null){
                         x.next = new EdgeNode(des,weight);
                         break;
                     }
                     break;
                 }
             }
             assert temp != null;
             while(temp.next != null){
                 temp = temp.next;
             }
             temp.next = new EdgeNode(des,weight);
         }
         catch (Exception e){
             System.out.println(src + " : Not Found!");
         }
     }

     public void PrimsAlgorithm(){
         int minCost = 0;

         Set<Integer> included = new HashSet<>(0);
         Set<Integer> vertices = new HashSet<>(0);

         included.add(vArray.get(0).data); //add the first node
         for(int i=1; i<vArray.size(); i++){
             vertices.add(vArray.get(i).data); //include unvisited vertices
         }

         VertexNode vtx = vArray.get(0);
         EdgeNode temp = vArray.get(0).next;
         while(included.size() != vArray.size()){
             int min = 99;  //to compare with
             EdgeNode ptr = null;
             while(temp != null){
                 if(vertices.contains(temp.data) && temp.weight < min){
                     min = temp.weight;
                     ptr = temp;
                 }
                 temp = temp.next;
             }
             minCost += min; //add the weight to minCost
             //remove min weight vertex from 'vertices' and add into 'included'
             assert ptr != null;
             included.add(ptr.data);
             vertices.remove(ptr.data);

             //find the latest added vertex in 'vArray'
             for(VertexNode x : vArray){
                 if(x.data == ptr.data){
                     temp = x.next;
                     vtx = x;
                     break;
                 }
             }
         }
         System.out.println("Minimum Cost of the Graph = " + minCost);
     }

     public void displayGraph(){
         for(VertexNode x : vArray){
             System.out.print(x.data + " => ");
             EdgeNode temp = x.next;
             while(temp != null){
                 System.out.print(temp.data + "(" + temp.weight + ")" + " -> ");
                 temp = temp.next;
             }
             System.out.print("N");
             System.out.println();
         }
     }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Prims_Algorithm pa = new Prims_Algorithm();

        int nVertex,nEdges;
        System.out.print("Enter No. of Vertices : ");
        nVertex = sc.nextInt();
        System.out.println("Enter Vertices =>");
        int vt;
        for(int i=0; i<nVertex; i++){
            System.out.print("Enter Vertex : ");
            vt = sc.nextInt();
            pa.insertVertex(vt);
        }
        System.out.print("\nEnter No. of Edges : ");
        nEdges = sc.nextInt();
        int src,des,wt;
        System.out.println("Enter Edges (source,destination,weight) =>");
        for(int i=0; i<nEdges; i++){
            System.out.print("Src , Des , Wt : ");
            src = sc.nextInt(); des = sc.nextInt(); wt = sc.nextInt();
            pa.insertEdge(src,des,wt);
            pa.insertEdge(des,src,wt);
        }

        int ch;
        int v,e,w;
        while(true){
            System.out.println();
            System.out.println("""
                    1)Add Node & Edges
                    2)Prims Algorithm
                    3)Display Graph
                    4)Exit""");
            System.out.print("\nEnter Choice : ");
            ch = sc.nextInt();
            switch (ch){
                case 1 -> {
                    System.out.print("Enter Vertex Value : ");
                    v = sc.nextInt();
                    int x;
                    System.out.print("Enter No. Connecting Edges : ");
                    x = sc.nextInt();
                    while(x > 0){
                        System.out.print("Enter Destination and Weight : ");
                        e = sc.nextInt(); w = sc.nextInt();
                        pa.insertEdge(v,e,w);
                        pa.insertEdge(e,v,w);
                        --x;
                    }
                }
                case 2 -> {
                    pa.PrimsAlgorithm();
                }
                case 3 -> {
                    System.out.print("\nAdjacency List Representation =>");
                    pa.displayGraph();
                }
                case 4 -> {
                    System.exit(0);
                }
                default -> {
                    System.out.print("\nInvalid Input!");
                }
            }
        }
    }
}

/*DRY RUN
for(int i=0; i<6; i++){
    pa.insertVertex(sc.nextInt());
}
for(int i=0; i<9; i++){
    int src = sc.nextInt();
    int des = sc.nextInt();
    int wt = sc.nextInt();
    pa.insertEdge(src,des,wt);
    pa.insertEdge(des,src,wt);
}
pa.displayGraph();
System.out.println();
pa.PrimsAlgorithm();
*/
