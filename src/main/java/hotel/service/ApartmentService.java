package hotel.service;

import hotel.controller.dto.CreateApartmentRequest;
import hotel.controller.dto.UpdateApartmentRequest;
import hotel.model.Apartment;
import hotel.model.ApartmentCategory;
import hotel.model.ApartmentStatus;
import hotel.repository.ApartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public long createApartment(CreateApartmentRequest request) {
        long apartmentNumber = request.getApartmentNumber();
        long guestNumber = request.getGuestsNumber();
        ApartmentCategory category = request.getCategory();
        double price = request.getPrice();

        Apartment apartment = new Apartment(apartmentNumber, guestNumber, category, ApartmentStatus.AVAILABLE, price);

        return apartmentRepository.save(apartment).getId();
    }

    public Apartment editApartmentStatus(long apartmentId, ApartmentStatus status) {
        Apartment apartment = findApartmentById(apartmentId);
        if (apartment != null) {
            apartment.setStatus(status);
            apartment = apartmentRepository.save(apartment);
        }
        return apartment;
    }

    public Apartment editApartmentInfo(long apartmentId, UpdateApartmentRequest request) {
        Apartment apartment = findApartmentById(apartmentId);
        if (apartment != null) {
            apartment.setApartmentNumber(request.getApartmentNumber());
            apartment.setGuestsNumber(request.getGuestsNumber());
            apartment.setCategory(request.getCategory());
            apartment.setStatus(request.getStatus());
            apartment.setPrice(request.getPrice());
            apartment = apartmentRepository.save(apartment);
        }
        return apartment;
    }

    public Apartment findApartmentById(long id) {
        return apartmentRepository.findById(id);
    }

    public List<Apartment> findAllApartments() {
        return apartmentRepository.findAll();
    }

    public List<Apartment> findApartmentsByStatus(ApartmentStatus status) {
        return apartmentRepository.findAllByStatus(status);
    }

    public List<Apartment> findAvailableApartmentsByGuestsNumberAndCategory(long guestsNumber, ApartmentCategory category) {
        return apartmentRepository.findAllByGuestsNumberAndStatusAndCategory(ApartmentStatus.AVAILABLE, guestsNumber, category);
    }
}
