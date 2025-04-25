package org.howard.edu.lsp.lspfinal.question1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for ShoppingCart functionality
 */
public class ShoppingCartTest {
    
    private ShoppingCart cart;
    
    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
    }
    
    @Test
    @DisplayName("Test for adding valid item")
    void testAddValidItem() {
        cart.addItem("Laptop", 999.99);
        assertEquals(1, cart.getCartSize());
        assertEquals(999.99, cart.getTotalCost(), 0.001);
    }
    
    @Test
    @DisplayName("Test for adding item with 0 price (expect exception)")
    void testAddItemWithZeroPrice() {
        // Adding item with 0 price should be allowed
        assertDoesNotThrow(() -> cart.addItem("Free item", 0.0));
        assertEquals(1, cart.getCartSize());
        assertEquals(0.0, cart.getTotalCost(), 0.001);
    }
    
    @Test
    @DisplayName("Test for adding item with negative price (expect exception)")
    void testAddItemWithNegativePrice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            cart.addItem("Invalid item", -10.0));
        assertEquals("Price cannot be negative.", exception.getMessage());
        assertEquals(0, cart.getCartSize());
    }
    
    @Test
    @DisplayName("Test for applying \"SAVE10\"")
    void testApplyingSave10Discount() {
        cart.addItem("Phone", 500.0);
        cart.applyDiscountCode("SAVE10");
        assertEquals(10.0, cart.getDiscountPercentage(), 0.001);
        assertEquals(450.0, cart.getTotalCost(), 0.001);
    }
    
    @Test
    @DisplayName("Test for applying \"SAVE20\"")
    void testApplyingSave20Discount() {
        cart.addItem("Tablet", 300.0);
        cart.applyDiscountCode("SAVE20");
        assertEquals(20.0, cart.getDiscountPercentage(), 0.001);
        assertEquals(240.0, cart.getTotalCost(), 0.001);
    }
    
    @Test
    @DisplayName("Test for applying invalid code (expect exception)")
    void testApplyingInvalidDiscount() {
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> 
            cart.applyDiscountCode("SAVE50"));
        assertEquals("Invalid discount code.", exception1.getMessage());
        
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> 
            cart.applyDiscountCode(""));
        assertEquals("Invalid discount code.", exception2.getMessage());
        
        assertEquals(0.0, cart.getDiscountPercentage(), 0.001);
    }
    
    @Test
    @DisplayName("Test total cost without discount")
    void testTotalCostWithoutDiscount() {
        cart.addItem("Book", 20.0);
        cart.addItem("Pen", 5.0);
        cart.addItem("Notebook", 10.0);
        assertEquals(35.0, cart.getTotalCost(), 0.001);
        assertEquals(0.0, cart.getDiscountPercentage(), 0.001);
    }
    
    @Test
    @DisplayName("Test total cost with discount")
    void testTotalCostWithDiscount() {
        cart.addItem("Headphones", 50.0);
        cart.addItem("Charger", 25.0);
        // Total before discount: 75.0
        
        cart.applyDiscountCode("SAVE20");
        assertEquals(20.0, cart.getDiscountPercentage(), 0.001);
        
        // 20% discount: 15.0
        // Expected total: 60.0
        assertEquals(60.0, cart.getTotalCost(), 0.001);
    }
    
    @Test
    @DisplayName("Test total cost with empty cart")
    void testTotalCostWithEmptyCart() {
        assertEquals(0.0, cart.getTotalCost(), 0.001);
        assertEquals(0, cart.getCartSize());
        
        // Apply discount to empty cart
        cart.applyDiscountCode("SAVE10");
        assertEquals(10.0, cart.getDiscountPercentage(), 0.001);
        assertEquals(0.0, cart.getTotalCost(), 0.001);
    }
    
    @Test
    @DisplayName("Test removing existing items")
    void testRemovingExistingItems() {
        cart.addItem("Laptop", 999.99);
        cart.addItem("Mouse", 29.99);
        assertEquals(2, cart.getCartSize());
        
        boolean removed = cart.removeItem("Laptop");
        assertTrue(removed);
        assertEquals(1, cart.getCartSize());
        assertEquals(29.99, cart.getTotalCost(), 0.001);
    }
    
    @Test
    @DisplayName("Test removing non-existent items")
    void testRemovingNonExistentItems() {
        cart.addItem("Laptop", 999.99);
        assertEquals(1, cart.getCartSize());
        
        boolean removed = cart.removeItem("Phone");
        assertFalse(removed);
        assertEquals(1, cart.getCartSize());
        assertEquals(999.99, cart.getTotalCost(), 0.001);
    }
    
    @Test
    @DisplayName("Test cart size updates correctly after adding/removing")
    void testCartSizeUpdates() {
        assertEquals(0, cart.getCartSize());
        
        cart.addItem("Item1", 10.0);
        assertEquals(1, cart.getCartSize());
        
        cart.addItem("Item2", 20.0);
        assertEquals(2, cart.getCartSize());
        
        cart.removeItem("Item1");
        assertEquals(1, cart.getCartSize());
        
        cart.removeItem("Item2");
        assertEquals(0, cart.getCartSize());
    }
}