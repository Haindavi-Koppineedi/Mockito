package org.example;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

public class RentalCartTest {

    private RentalService rentalServiceMock;
    private RentalCart rentalCart;

    @Before
    public void setUp() {
        rentalServiceMock = mock(RentalService.class);
        rentalCart = new RentalCart();
        rentalCart.setRentalService(rentalServiceMock);
    }

    @Test
    public void testGetTotalPrice() {
        Movie movie1 = new Movie("Taal", "Old Movie", "1999", 3);
        Movie movie2 = new Movie("Chakram", "Mid Era Movie", "2005", 2);
        Movie movie3 = new Movie("MAD", "Recent Movie", "2023", 1);

        rentalCart.setMovies(Arrays.asList(movie1, movie2, movie3));

        // Define the rental prices
        when(rentalServiceMock.getRentalPrice(movie1)).thenReturn(2.0);
        when(rentalServiceMock.getRentalPrice(movie2)).thenReturn(5.0);
        when(rentalServiceMock.getRentalPrice(movie3)).thenReturn(10.0);

        // Define the late fees
        when(rentalServiceMock.calculateLateFee(2.0, 3)).thenReturn(0.3 * 3); // 5% of $2 per day
        when(rentalServiceMock.calculateLateFee(5.0, 2)).thenReturn(0.5 * 2); // 10% of $5 per day
        when(rentalServiceMock.calculateLateFee(10.0, 1)).thenReturn(2.0 * 1); // 20% of $10 per day

        double totalPrice = rentalCart.getTotalPrice();

        double expectedTotalPrice = 2.0 + (0.3 * 3) + 5.0 + (0.5 * 2) + 10.0 + (2.0 * 1);

        assertEquals(expectedTotalPrice, totalPrice, 0.01);
    }
}
