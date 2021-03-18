package hotel.repository;

import hotel.model.Apartment;
import hotel.model.ApartmentCategory;
import hotel.model.ApartmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    Apartment findById(long apartmentId);

    List<Apartment> findAllByStatus(ApartmentStatus status);

    List<Apartment> findAllByGuestsNumberAndStatusAndCategory(ApartmentStatus status,
                                                              long guestsNumber,
                                                              ApartmentCategory category);
}
