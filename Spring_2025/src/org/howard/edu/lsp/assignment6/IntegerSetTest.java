/**
 * worked through this using claude.ai
 */

 package org.howard.edu.lsp.assignment6;

 import static org.junit.jupiter.api.Assertions.*;
 import org.junit.jupiter.api.*;
 
 public class IntegerSetTest {
 
     private IntegerSet set1;
     private IntegerSet set2;
 
     @BeforeEach
     public void setUp() {
         set1 = new IntegerSet();
         set2 = new IntegerSet();
     }
 
     // Tests will go here
     @Test
     @DisplayName("Test add()")
     public void testAddAndToString() {
         set1.add(5);
         set1.add(10);
         String output = set1.toString();
         assertTrue(output.contains("5"));
         assertTrue(output.contains("10"));
     }
     
     @Test
     @DisplayName("Test isEmpty() on new and cleared set")
     public void testIsEmpty() {
         // Newly created set should be empty
         assertTrue(set1.isEmpty());
         
         // After adding elements, it shouldn't be empty
         set1.add(5);
         assertFalse(set1.isEmpty());
         
         // After clearing, it should be empty
         set1.clear();
         assertTrue(set1.isEmpty());
     }
     
     @Test
     @DisplayName("Test toString() on various set states")
     public void testToString() {
         // Empty set
         assertEquals("[]", set1.toString());
         
         // Single element
         set1.add(10);
         String str1 = set1.toString();
         assertTrue(str1.startsWith("["));
         assertTrue(str1.endsWith("]"));
         assertTrue(str1.contains("10"));
         
         // Multiple elements
         set1.add(20);
         set1.add(30);
         String str2 = set1.toString();
         assertTrue(str2.contains("10"));
         assertTrue(str2.contains("20"));
         assertTrue(str2.contains("30"));
         assertTrue(str2.startsWith("["));
         assertTrue(str2.endsWith("]"));
     }
     
     @Test
     @DisplayName("Test clear()")
     public void testClearAndIsEmpty() {
         set1.add(1);
         set1.add(2);
         set1.clear();
         assertTrue(set1.isEmpty());
     }
     
     @Test
     @DisplayName("Test length()")
     public void testLength() {
         set1.add(1);
         set1.add(2);
         set1.add(2); // should not be added twice
         assertEquals(2, set1.length());
     }
     
     @Test
     @DisplayName("Test equals()")
     public void testEquals() {
         set1.add(1);
         set1.add(2);
         set2.add(2);
         set2.add(1);
         assertTrue(set1.equals(set2));
     }
     
     @Test
     @DisplayName("Test contains()")
     public void testContains() {
         set1.add(3);
         assertTrue(set1.contains(3));
         assertFalse(set1.contains(99));
     }
     
     @Test
     @DisplayName("Test largest() with values and exception")
     public void testLargest() throws IntegerSetException {
         set1.add(5);
         set1.add(99);
         set1.add(12);
         assertEquals(99, set1.largest());
         
         set1.clear();
         assertThrows(IntegerSetException.class, () -> set1.largest());
     }
 
     @Test
     @DisplayName("Test smallest() with values and exception")
     public void testSmallest() throws IntegerSetException {
         set1.add(7);
         set1.add(-3);
         set1.add(22);
         assertEquals(-3, set1.smallest());
         
         set1.clear();
         assertThrows(IntegerSetException.class, () -> set1.smallest());
     }
     
     @Test
     @DisplayName("Test remove()")
     public void testRemove() {
         set1.add(10);
         set1.add(20);
         set1.remove(10);
         assertFalse(set1.contains(10));
         assertTrue(set1.contains(20));
         
         // Removing something that does not exist
         set1.remove(999);
         assertTrue(set1.contains(20)); // Still in the set
     }
     
     @Test
     @DisplayName("Test union()")
     public void testUnion() {
         set1.add(1);
         set1.add(2);
         set2.add(2);
         set2.add(3);
         
         set1.union(set2); // set1 becomes union of set1 and set2
         
         assertTrue(set1.contains(1));
         assertTrue(set1.contains(2));
         assertTrue(set1.contains(3));
         assertEquals(3, set1.length());
     }
     
     @Test
     @DisplayName("Test intersect()")
     public void testIntersect() {
         set1.add(1);
         set1.add(2);
         set1.add(3);
         set2.add(2);
         set2.add(3);
         set2.add(4);
         
         set1.intersect(set2); // set1 becomes intersection of set1 and set2
         
         assertTrue(set1.contains(2));
         assertTrue(set1.contains(3));
         assertFalse(set1.contains(1));
         assertFalse(set1.contains(4));
         assertEquals(2, set1.length());
     }
     
     @Test
     @DisplayName("Test diff()")
     public void testDiff() {
         set1.add(1);
         set1.add(2);
         set1.add(3);
         set2.add(2);
         set2.add(4);
         
         set1.diff(set2); // set1 = set1 - set2
         
         assertTrue(set1.contains(1));
         assertTrue(set1.contains(3));
         assertFalse(set1.contains(2));
         assertFalse(set1.contains(4));
     }
     
     @Test
     @DisplayName("Test complement()")
     public void testComplement() {
         set1.add(1);
         set1.add(2);
         set2.add(1);
         set2.add(2);
         set2.add(3);
         set2.add(4);
         
         set1.complement(set2); // set1 becomes set2 - set1
         
         assertTrue(set1.contains(3));
         assertTrue(set1.contains(4));
         assertFalse(set1.contains(1));
         assertFalse(set1.contains(2));
     }
 }