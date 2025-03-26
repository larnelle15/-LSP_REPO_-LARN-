package org.howard.edu.lsp.assignment5;

public class Driver {
    public static void main(String[] args) {
        // Demonstrating basic set creation and operations
        System.out.println("SET OPERATIONS DEMONSTRATION");
        System.out.println("============================");

        // Set 1 Creation and Basic Operations
        System.out.println("\n1. SET CREATION AND BASIC OPERATIONS");
        IntegerSet set1 = new IntegerSet();
        System.out.println("Initial Set1: Empty Set");
        
        set1.add(1);
        set1.add(2);
        set1.add(3);
        System.out.println("After adding elements: Set1 = " + set1);
        
        System.out.println("Set1 Length: " + set1.length());
        System.out.println("Is Set1 Empty? " + set1.isEmpty());
        System.out.println("Smallest Value in Set1: " + set1.smallest());
        System.out.println("Largest Value in Set1: " + set1.largest());
        System.out.println("Does Set1 contain 2? " + set1.contains(2));

        // Set 2 Creation
        System.out.println("\n2. SECOND SET CREATION");
        IntegerSet set2 = new IntegerSet();
        set2.add(3);
        set2.add(4);
        set2.add(5);
        System.out.println("Set2 = " + set2);

        // Union Operation
        System.out.println("\n3. UNION OPERATION");
        System.out.println("Set1 before union: " + set1);
        System.out.println("Set2 to union: " + set2);
        set1.union(set2);
        System.out.println("Set1 after union: " + set1);

        // Intersection Operation
        System.out.println("\n4. INTERSECTION OPERATION");
        IntegerSet set3 = new IntegerSet();
        set3.add(1);
        set3.add(2);
        set3.add(3);
        IntegerSet set4 = new IntegerSet();
        set4.add(3);
        set4.add(4);
        set4.add(5);
        System.out.println("Set3: " + set3);
        System.out.println("Set4: " + set4);
        set3.intersect(set4);
        System.out.println("Set3 after intersection with Set4: " + set3);

        // Difference Operation
        System.out.println("\n5. DIFFERENCE OPERATION");
        IntegerSet set5 = new IntegerSet();
        set5.add(1);
        set5.add(2);
        set5.add(3);
        IntegerSet set6 = new IntegerSet();
        set6.add(3);
        set6.add(4);
        set6.add(5);
        System.out.println("Set5: " + set5);
        System.out.println("Set6: " + set6);
        set5.diff(set6);
        System.out.println("Set5 after removing Set6 elements: " + set5);

        // Equality Check
        System.out.println("\n6. EQUALITY CHECK");
        IntegerSet set7 = new IntegerSet();
        set7.add(1);
        set7.add(2);
        IntegerSet set8 = new IntegerSet();
        set8.add(2);
        set8.add(1);
        System.out.println("Set7: " + set7);
        System.out.println("Set8: " + set8);
        System.out.println("Are Set7 and Set8 equal? " + set7.equals(set8));

        // Complement Operation
        System.out.println("\n7. COMPLEMENT OPERATION");
        IntegerSet set9 = new IntegerSet();
        set9.add(1);
        set9.add(2);
        set9.add(3);
        IntegerSet set10 = new IntegerSet();
        set10.add(3);
        set10.add(4);
        System.out.println("Set9: " + set9);
        System.out.println("Set10: " + set10);
        set9.complement(set10);
        System.out.println("Set9 after complement operation: " + set9);
    }
}