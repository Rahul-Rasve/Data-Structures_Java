package com.company;

import java.util.Scanner;

public class LinkedList {
    Node head;
    public LinkedList(){
        this.head = null;
    }
    static class Node{
        private final int data;
        private Node next;

        public Node(int data){
            this.data = data;
            next = null;
        }
    }

    public void insertLast(int data){
        if(head == null){
            head = new Node(data);
        }
        else{
            Node temp = head;
            while(temp.next != null){
                temp = temp.next;
            }//reached the end of LL
            //add element
            temp.next = new Node(data);
        }
    }

    public void insertFront(int data){
        if(head == null){
            head = new Node(data);
        }
        else{
            Node pNew = new Node(data);
            pNew.next = head;
            head = pNew;
        }
    }

    public void search(int data){
        Node temp = head;
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

    public void delete(int data){
        Node temp=head;
        Node prev=head;
        if(head == null){
            System.out.println("Linked List Not Created!");
        }
        else if(head.data == data){
            temp = head.next;
            head.next = null;
            head = temp;
        }
        else{
            while(temp != null){
                if(temp.data == data){
                    while(prev.next != temp){
                        prev = prev.next;
                    }//reached prev.next = temp
                    prev.next = temp.next; //break the link
                    temp.next = null; //delete element
                    System.out.println(data + " : Element Deleted");
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
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.print("N"); //shows null at end
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        LinkedList l1 = new LinkedList();

        int choice;
        int num;
        while(true){
            System.out.println("""
                    \n1)Add Element in Front
                    2)Add Element in Last
                    3)Search
                    4)Delete
                    5)Display
                    6)Exit""");
            System.out.print("Enter Choice : ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Value : ");
                    num = sc.nextInt();
                    l1.insertFront(num);
                }
                case 2 -> {
                    System.out.print("Enter Value : ");
                    num = sc.nextInt();
                    l1.insertLast(num);
                }
                case 3 -> {
                    System.out.print("Enter Value : ");
                    num = sc.nextInt();
                    l1.search(num);
                }
                case 4 -> {
                    System.out.print("Enter Value : ");
                    num = sc.nextInt();
                    l1.delete(num);
                }
                case 5 -> {
                    System.out.println("LinkedList Representation ->");
                    l1.display();
                }
                case 6 -> System.exit(0);
                default -> System.out.println("Invalid Choice!");
            }
        }
    }
}