/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author mehakkhan
 */
public class PriorityList {

    //ONLY IMPLEMENT/WRITE CODE IN THE METHODS WITH "TODO"
    //DO NOT MODIFY/WRITE CODE ANYWHERE ELSE
    private PriorityNode head;
    private int numberOfCustomers;

    public PriorityList() {
        numberOfCustomers = 0;
    }

    public void addCustomer(Customer customer) {

        //FIRST WE HAVE TO CHECK WHICH NODE WE WANT THE CUSTOMER TO BE IN ACCORDING TO PRIOROTY LEVEL
        if (isEmpty()) {
            PriorityNode n = new PriorityNode();
            n.getQeueue().add(customer);
            numberOfCustomers++;
            head = n;
        } else {

            PriorityNode current = head;

            if (current.getQeueue().peek().priority == customer.priority) {
                //use current as the node;
            } else if (current.getQeueue().peek().priority > customer.priority) {

                PriorityNode n0 = new PriorityNode();
                n0.next = head;
                head = n0;
                current = n0; //if the front priority is greater then just add a new node at the front and make it head

            } else {

                //gonna check if the front of the priority is higher ok
                try {

                    while (current.next.getQeueue().peek().priority < customer.priority) {

                        if (current.next == null) {
                            break;
                        }

                        current = current.next;

                    }
                } catch (Exception e) {

                }
                try {
                    if (current.next.getQeueue().peek().priority == customer.priority) {

                        current = current.next;
                    } else {

                        PriorityNode n1 = new PriorityNode();
                        n1.next = current.next;
                        current.next = n1;

                        current = n1;

                    }
                } catch (Exception e) {

                    PriorityNode n1 = new PriorityNode();
                    current.next = n1;
                    current = n1;

                }
            }

            if (current.getQeueue().isEmpty()) {

                current.getQeueue().add(customer);
                numberOfCustomers++;
            } else {
                current.getQeueue().add(customer);
                numberOfCustomers++;
            }

        }
    }

    public Customer getNextCustomer() {
        //TODO
        if (isEmpty()) {
            return null;
        }
        Customer toRemove = head.getQeueue().remove();
        numberOfCustomers--;
        if (head.getQeueue().isEmpty()) {
            head = head.next;
        }
        return toRemove;

    }

    public void deletePriorityLevel(int k) {
        if (isEmpty()) {

        } else {
            PriorityNode current = head;

            if (current.getQeueue().peek().priority == k) {
                int x = head.getQeueue().getLength();
                numberOfCustomers = numberOfCustomers - x;
                head = current.next;

            } else {
                boolean found = false;

                while (current.next != null) {
                    if (current.next.getQeueue().peek().priority == k) {
                        int x = current.next.getQeueue().getLength();
                        numberOfCustomers = numberOfCustomers - x;
                        found = true;
                        break; //delete the current node

                    }
                    current = current.next;
                }

                if (found) {
                    current.next = current.next.next;

                }
            }

        }
    }

    public void truncateDownTo(int k) {

        while (numberOfCustomers > k) {
            PriorityNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            deletePriorityLevel(current.getQeueue().peek().priority);
        }

    }

    public PriorityList mergeLists(PriorityList listToMerge) {
        PriorityList merged = new PriorityList();

        Customer ogCust;
        Customer mgCust;

        int customerOG = getNumberOfCustomers();
        int customersMG = listToMerge.getNumberOfCustomers();

        ArrayList<Customer> og = new ArrayList<>();
        ArrayList<Customer> in = new ArrayList<>();

        if (this.head == listToMerge.head) {
            Customer cus;

            while (customerOG > 0) {
                cus = this.getNextCustomer();
                og.add(cus);
                customerOG--;
            }

            for (int i = 0; i < og.size(); i++) {
                merged.addCustomer(og.get(i));
                merged.addCustomer(og.get(i));
                this.addCustomer(og.get(i));
            }

        } else {
            while (customerOG > 0 && customersMG > 0) {

                ogCust = this.getNextCustomer();
                mgCust = listToMerge.getNextCustomer();

                og.add(ogCust);
                in.add(mgCust);

                customerOG--;
                customersMG--;
            }

            if (customerOG == 0) {
                while (customersMG > 0) {
                    mgCust = listToMerge.getNextCustomer();
                    in.add(mgCust);
                    customersMG--;
                }
            }

            if (customersMG == 0) {
                while (customerOG > 0) {
                    ogCust = this.getNextCustomer();
                    og.add(ogCust);
                    customerOG--;
                }
            }

            for (int i = 0; i < og.size(); i++) {
                merged.addCustomer(og.get(i));
                this.addCustomer(og.get(i));

                Customer help = help(in, og.get(i).priority);

                if (help != null) {

                    merged.addCustomer(help);
                    listToMerge.addCustomer(help);
                    in.remove(help);
                }
            }
            for (int i = 0; i < in.size(); i++) {
                merged.addCustomer(in.get(i));
                listToMerge.addCustomer(in.get(i));
            }
        }
        return merged;
    }

    private Customer help(ArrayList<Customer> cus, int priority) {
        for (int i = 0; i < cus.size(); i++) {
            if (cus.get(i).priority == priority) {
                return cus.get(i);
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getNumberOfNodes() {
        int numberOfNodes = 0;
        PriorityNode current = head;
        while (current != null) {
            numberOfNodes++;
            current = current.next;
        }
        return numberOfNodes;
    }

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public PriorityNode getFrontNode() {
        return head;
    }

}
