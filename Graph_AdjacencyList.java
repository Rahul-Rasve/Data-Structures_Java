package com.company;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Graph_AdjacencyList {
    static class Node{
        private final int data;
        private Node next;

        public Node(int data){
            this.data = data;
            next = null;
        }
    }

    private final ArrayList<Node> graph;

    public Graph_AdjacencyList(){
        graph = new ArrayList<>(0);  //initialized
    }

    public void insertVertex(int data){
        Node vertex = new Node(data);
        graph.add(vertex);
    }

    public void insertEdge(int src, int des){
        try {
            Node temp = null;
            for(Node x : graph){
                if(x.data == src){
                    temp = x;
                    break;
                }
            }
            assert temp != null; //assumption
            if (temp.next != null) {
                while (temp.next != null) {
                    temp = temp.next;
                }
            }
            temp.next = new Node(des);
            System.out.println("Edged Created : " + src + "->" + des);
        }
        catch (Exception e){
            System.out.println("Source Vertex  [" + src + "] : Not Found!");
        }
    }

    public void breadthFirstTraversal(int startVertex){
        ArrayDeque<Integer> que = new ArrayDeque<>(0);
        ArrayList<Integer> visited = new ArrayList<>(0);

        que.addLast(startVertex);
        visited.add(startVertex);
        int popVertex;
        System.out.print("Start");
        while(!que.isEmpty()){
            popVertex = que.getFirst();
            System.out.print(" => " + popVertex); //print the element
            que.pop();
            //find adjacent elements
            try {
                Node temp = null;
                for (Node x : graph) {
                    if (x.data == popVertex) {
                        temp = x.next;
                        break;
                    }
                }
                while(temp != null){
                    if(!visited.contains(temp.data)) {
                        que.addLast(temp.data); //add adjacent elements to queue
                        visited.add(temp.data);  //mark them visited
                    }
                    temp = temp.next;
                }
            }
            catch (Exception e){
                System.out.println("Vertex Not Found in Graph!");
                break;
            }
        }
    }

    public void depthFirstTraversal(int startVertex){
        Stack<Integer> stk = new Stack<>();
        ArrayList<Integer> visited = new ArrayList<>(0);

        stk.push(startVertex);
        visited.add(startVertex);
        int popVertex;
        System.out.print("Start");
        while(!stk.isEmpty()){
            popVertex = stk.peek();
            System.out.print(" => " + popVertex);
            stk.pop();
            try{
                Node temp = null;
                for(Node x : graph){
                    if(x.data == popVertex){
                        temp = x.next;
                        break;
                    }
                }
                while(temp != null){
                    if(!visited.contains(temp.data)){
                        stk.push(temp.data);
                        visited.add(temp.data);
                    }
                    temp = temp.next;
                }
            }
            catch (Exception e){
                System.out.println("Vertex Not Found!");
                break;
            }
        }
    }

    public void displayGraph(){
        for(Node v : graph){
            System.out.print(v.data + " => ");
            Node temp = v.next;
            while(temp != null){
                System.out.print(temp.data + " -> ");
                temp = temp.next;
            }
            System.out.print("N\n");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Graph_AdjacencyList g1 = new Graph_AdjacencyList();

        int choice;
        int num;
        while(true){
            System.out.print("""
                    \n1)Add 'N' Nodes and Edges
                    2)Add Node
                    3)BFS Traversal
                    4)DFS Traversal
                    5)Display Graph
                    6)Exit""");
            System.out.print("\nEnter Choice : ");
            choice = sc.nextInt();
            switch(choice) {
                case 1 -> {
                    System.out.print("\nEnter No. of Vertices to Add : ");
                    int n = sc.nextInt();
                    for(int i=0; i<n; i++){
                        System.out.print("Enter Value : ");
                        num = sc.nextInt();
                        g1.insertVertex(num);
                    }
                    System.out.print("\nEnter No. of Edges : ");
                    int e = sc.nextInt();
                    for(int i=0; i<e; i++){
                        System.out.print("Enter Source and Destination : ");
                        int src = sc.nextInt(); int des = sc.nextInt();
                        g1.insertEdge(src, des);
                        g1.insertEdge(des, src);
                    }
                }
                case 2 -> {
                    System.out.print("\nEnter Value : ");
                    num = sc.nextInt();
                    g1.insertVertex(num);
                    System.out.print("\nEnter No. Edges from Vertex " + num + " : ");
                    int e = sc.nextInt();
                    for(int i=0; i<e; i++){
                        System.out.print("Enter Destination : ");
                        int d = sc.nextInt();
                        g1.insertEdge(num, d);
                        g1.insertEdge(d, num);
                    }
                }
                case 3 -> {
                    System.out.print("\nEnter Start Vertex : ");
                    int sv = sc.nextInt();
                    System.out.println("BFS Traversal of Graph -> ");
                    g1.breadthFirstTraversal(sv);
                    System.out.println();
                }
                case 4-> {
                    System.out.print("\nEnter Start Vertex : ");
                    int sv = sc.nextInt();
                    System.out.println("DFS Traversal of Graph -> ");
                    g1.depthFirstTraversal(sv);
                    System.out.println();
                }
                case 5 -> {
                    System.out.println("Graph Representation as Adjacency List -> ");
                    g1.displayGraph();
                    System.out.println();
                }
                case 6 -> {
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid Choice!");
                }
            }
        }
    }
}

/*DRY RUN*/
//        g1.insertVertex(0);
//        g1.insertVertex(1);
//        g1.insertVertex(2);
//        g1.insertVertex(3);
//        g1.insertVertex(4);
//        g1.insertVertex(5);
//        g1.insertVertex(6);
//        g1.insertVertex(7);
//        g1.insertEdge(0,1);
//        g1.insertEdge(1,0);
//        g1.insertEdge(0,2);
//        g1.insertEdge(2,0);
//        g1.insertEdge(0,3);
//        g1.insertEdge(3,0);
//        g1.insertEdge(1,4);
//        g1.insertEdge(4,1);
//        g1.insertEdge(4,7);
//        g1.insertEdge(7,4);
//        g1.insertEdge(5,7);
//        g1.insertEdge(7,5);
//        g1.insertEdge(2,5);
//        g1.insertEdge(5,2);
//        g1.insertEdge(7,6);
//        g1.insertEdge(6,7);
//        g1.insertEdge(3,6);
//        g1.insertEdge(6,3);
//        g1.breadthFirstTraversal(0);
//        System.out.println();
//        g1.depthFirstTraversal(0);
//        System.out.println();
//        g1.displayGraph();