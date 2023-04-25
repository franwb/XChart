package org.knowm.xchart.demo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

public class sortingMethods {
    public static void main(String[] args){
        List<Integer> arrayL = new ArrayList();
        long begintime = System.nanoTime(); 
        for (int i = 0; i < 10; i++) {
            //here is where you would insert the method and the n
            //removeLast(100000);
        }
        long stopTime = System.nanoTime();
        System.out.println("The time was: " + (stopTime-begintime)/10);
    }

    // method to add to the beginning of the list
    public static void beginningAdd (List<Integer> arrayL, int n){
        for (int i = 0; i < n; i++) {
            arrayL.add(0, 0);        
        }
    }

    // method to add to the middle of the list
    public static void middleAdd(List<Integer> arrayL, int n){
        for (int i = 0; i <= n; i++) {
            arrayL.add(arrayL.size()/2, 1);     
        }
    }

    // method to add to the end of the list
    public static void lastAdd (List<Integer> arrayL, int n){
        Integer last = (arrayL.size()-1);
        for (int i = 0; i <= last; i++) {
            arrayL.add(last,n);
            last = (arrayL.size()-1);         
        }
    }

    // creates a new list that we can use in the remove methods
    public static List<Integer> newList(int n){
        List<Integer> arrayL = new ArrayList();
        for (int i = 0; i < n; i++) {
            arrayL.add(1);      
        }
        return arrayL;
    }

    // method to remove from the front of the list
    public static void removeFront (int n){
        List<Integer> arrayL = newList(n);
        for (int i = 0; i <= arrayL.size()-1; i++) {
            arrayL.remove(0);        
        }
    }

    // method to remove from the middle of the list
    public static void removeMiddle(int n){
        List<Integer> arrL = newList(n);
        Integer middle = ((arrL.size()/2));
        Integer midPosition = middle;
        for (int i = 0; i <= arrL.size(); i++) {
            Math.round(midPosition);
            arrL.remove(midPosition);
            middle = ((arrL.size()-1/2));
            midPosition = middle;      
        }
    }

    // method to remove from the end of the list
    public static void removeLast (int n){
        List<Integer> arrayList = newList(n);
        Integer last = (arrayList.size()-1);
        for (int i = 0; i <= last; i++) {
            arrayList.remove(last);
            last = (arrayList.size()-1);         
        }
    }
}
    
