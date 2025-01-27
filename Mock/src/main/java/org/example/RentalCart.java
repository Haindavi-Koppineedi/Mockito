package org.example;

import java.util.List;

public class RentalCart {

    private RentalService rentalService;

    public RentalService getRentalService() {
        return rentalService;
    }

    public void setRentalService(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    private List<Movie> movies;


    public double getTotalPrice() {
        double totalAmount = 0.0;

        for (Movie movie : movies) {
            totalAmount += rentalService.getRentalPrice(movie);
            totalAmount += rentalService.calculateLateFee(totalAmount, movie.getDaysLate());
        }


        return totalAmount;
    }

}
