package com.company;

import java.util.*;

public class HashTable_Chaining {
    static int SIZE = 10;
    static class Node{
        private int data;
        private Node next;

        public Node(int data){
            this.data = data;
            next = null;
        }
    }

    //hashTable
    private final Node[] hashTable = new Node[SIZE];

    public int hashFunction(int key){
        return key%SIZE;
    }

    public void insert(int data){
        int loc = hashFunction(data);
        if(hashTable[loc] == null){
            hashTable[loc] = new Node(data);
        }
        else{
            Node temp = hashTable[loc];
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = new Node(data);
        }
    }

    public void search(int data){
        int loc = hashFunction(data);
        if(hashTable[loc].data == data){
            System.out.println(data + " : Found!");
        }
        else {
            Node temp = hashTable[loc];
            while(temp != null){
                if(temp.data == data){
                    System.out.println(data + " : Found!");
                    break;
                }
                temp = temp.next;
            }
            if(temp == null){
                System.out.println(data + " : Not Found!");
            }
        }
    }

    public void delete(int data){
        int loc = hashFunction(data);
        Node temp = hashTable[loc];
        if(hashTable[loc].data == data){
            hashTable[loc] = temp.next;
            temp.next = null;
        }
        else{
            Node prev = temp;
            while(temp != null){
                if(temp.data == data){
                    while(prev.next != temp){
                        prev = prev.next;
                    }
                    prev.next = temp.next;
                    temp.next = null;
                    break;
                }
                temp = temp.next;
            }
            if(temp == null){
                System.out.println(data + " : Not Found!");
            }
        }
    }

    public void display(){
        for(int i=0; i<SIZE; i++){
            System.out.print(i + " -> ");
            Node temp = hashTable[i];
            while(temp != null){
                System.out.print(temp.data + " -> ");
                temp = temp.next;
            }
            System.out.print("N");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        HashTable_Chaining htc = new HashTable_Chaining();

        int choice;
        int num;
        while(true){
            System.out.println("""
                    \n1)Add Element
                    2)Search Element
                    3)Delete Element
                    4)Display HashTable
                    5)Exit""");
            System.out.print("Enter Choice : ");
            choice = sc.nextInt();
            switch (choice){
                case 1 -> {
                    System.out.print("Enter Value : ");
                    num = sc.nextInt();
                    htc.insert(num);
                }
                case 2 -> {
                    System.out.print("Enter Value : ");
                    num = sc.nextInt();
                    htc.search(num);
                }
                case 3 -> {
                    System.out.print("Enter Value : ");
                    num = sc.nextInt();
                    htc.delete(num);
                }
                case 4 -> {
                    System.out.println("HashTable Representation -> ");
                    htc.display();
                }
                case 5 -> {
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid Choice!");
                }
            }
        }
    }
}
