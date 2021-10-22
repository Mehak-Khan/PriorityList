/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author mehakkhan
 */
public class MyQueue {

    //DO NOT CHANGE OR ADD VARIABLES
    private Customer[] customers;
    private int length;
    private int front = 0;
    private int back = 0;

    public MyQueue() {
        this.customers = new Customer[10];
        this.length = 0;
    }

    //IMPLEMENT THIS METHOD
    public void resizeUp() {

        int newLength = (this.length * (3)) / 2 + 1;

        if (newLength < 10) {

        } else {

            Customer[] temp = new Customer[newLength];
            for (int i = 0; i < length; i++) {

                temp[i] = customers[front + i];

            }
            customers = temp;
            front = 0;
            back = getLength();

        }

    }

    public void resizeDown() {

        int newLength;
        if (this.length > 5) {
            newLength = this.length * 4; //NEW LENGTH
        } else {
            newLength = 24;
        }

        Customer[] temp = new Customer[newLength];
        for (int i = 0; i < length; i++) {

            temp[i] = customers[front + i];

        }

        customers = temp;

        front = 0;
        back = getLength();

    }

    //DO NOT CHANGE THIS METHOD
    public void add(Customer value) {

        if (length == customers.length) {
            resizeUp();
        }
        customers[back] = value;
        length++;
        if (back == customers.length - 1) {
            back = 0;
        } else {
            back++;
        }
    }

    //ONLY CHANGE THE ONE LINE
    public Customer remove() {

        Customer customer = customers[front];
        if (front == customers.length - 1) {
            front = 0;
        } else {
            front++;
        }
        length--;
        if (customers.length > this.length * 4) {//CHANGE THIS SINGLE LINE OF CODE
            resizeDown();

        }
        return customer;
    }

    //DO NOT CHANGE THIS METHOD
    public Customer peek() {
        return customers[front];
    }

    //DO NOT CHANGE THIS METHOD
    public boolean isEmpty() {
        return length == 0;
    }

    //DO NOT CHANGE THIS METHOD
    public int getLength() {
        return length;
    }


}
