package com.company;

import java.util.Scanner;

public class BST {
    static class Node{
        private int data;
        private Node left;
        private Node right;

        public Node(int data){
            this.data = data;
            left = null;
            right = null;
        }
    }

    private Node root;

    public BST(){
        root = null;
    }

    public Node insert(Node root, int data){
        if(root == null){
            root = new Node(data);
        }
        else if(root.data < data){
            root.right = insert(root.right, data);
        }
        else if(root.data > data){
            root.left = insert(root.left, data);
        }
        else{
            System.out.println("Duplicate Value!");
            return null;
        }
        return root;
    }

    public Node search(Node root, int data){
        if(root == null){
            System.out.println(data + " : Not Found!");
            return root;
        }
        if(root.data < data){
            root.right = search(root.right, data);
        }
        else if(root.data > data){
            search(root.left, data);
        }
        else {
            System.out.println(data + " : Found!");
            return root;
        }
        return root;
    }

    public Node delete(Node root, int data){
        if(root == null){
            System.out.println(data + " : Not Found!");
            return null;
        }
        else if(data > root.data){
            root.right = delete(root.right, data);
        }
        else if(data < root.data){
            root.left = delete(root.left, data);
        }
        else{ //value found
            if(root.right == null){
                return root.left; //if no successor, return the predecessor
            }
            else{
                Node temp = root.right;
                while(temp.left != null){
                    temp = temp.left; //find in-order successor
                }
                root.data = temp.data;
                root.right = delete(root.right, temp.data);
            }
        }
        return root;
    }

    public void inorderTraversal(Node root){
        if(root != null){
            inorderTraversal(root.left);
            System.out.print(root.data + " ");
            inorderTraversal(root.right);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BST b1 = new BST();
        Node root = null;
        System.out.print("Enter Value of Root : ");
        int rt = sc.nextInt();
        root = b1.insert(root, rt);

        int choice;
        int num;
        while(true){
            System.out.println("""
                    \n1)Insert Element
                    2)Search Element
                    3)Delete Element
                    4)Display In-Order Traversal
                    5)Exit""");
            System.out.print("Enter Choice : ");
            choice = sc.nextInt();
            switch (choice){
                case 1 -> {
                    System.out.print("Enter Value : ");
                    num = sc.nextInt();
                    b1.insert(root, num);
                }
                case 2 -> {
                    System.out.print("Enter Value : ");
                    num = sc.nextInt();
                    b1.search(root, num);
                }
                case 3 -> {
                    System.out.print("Enter Value : ");
                    num = sc.nextInt();
                    b1.delete(root, num);
                    b1.inorderTraversal(root);
                }
                case 4 -> {
                    System.out.print("Binary Search Tree -> ");
                    b1.inorderTraversal(root);
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
