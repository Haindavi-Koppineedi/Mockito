package org.example;

public interface RentalService {

    double getRentalPrice(Movie movie);
    double calculateLateFee(double amount, int daysLate);

}
