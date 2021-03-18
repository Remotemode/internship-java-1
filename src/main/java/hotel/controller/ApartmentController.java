package hotel.controller;

import hotel.controller.dto.CreateApartmentRequest;
import hotel.controller.dto.UpdateApartmentRequest;
import hotel.model.Apartment;
import hotel.model.ApartmentCategory;
import hotel.model.ApartmentStatus;
import hotel.service.ApartmentService;
import hotel.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/apartment")
public class ApartmentController {

    private final ApartmentService apartmentService;
    private final PermissionService permissionService;

    public ApartmentController(ApartmentService apartmentService, PermissionService permissionService) {
        this.apartmentService = apartmentService;
        this.permissionService = permissionService;
    }

    @PostMapping("/{userId}")
    void createApartment(@PathVariable long userId, @RequestBody CreateApartmentRequest request) {
        permissionService.checkPermission(userId);
        apartmentService.createApartment(request);
    }

    @PutMapping("/status/{apartmentId}/{userId}")
    Apartment editApartmentStatus(@PathVariable long userId,
                                  @PathVariable long apartmentId,
                                  @RequestParam ApartmentStatus status) {
        permissionService.checkPermission(userId);
        return apartmentService.editApartmentStatus(apartmentId, status);
    }

    @PutMapping("/{apartmentId}/{userId}")
    Apartment editApartmentInfo(@PathVariable long userId,
                           @PathVariable long apartmentId,
                           @RequestBody UpdateApartmentRequest request) {
        permissionService.checkPermission(userId);
        return apartmentService.editApartmentInfo(apartmentId, request);
    }

    @GetMapping("/id/{id}")
    Apartment findApartmentById(@PathVariable long id) {
        return apartmentService.findApartmentById(id);
    }

    @GetMapping("/{userId}")
    List<Apartment> findAllApartments(@PathVariable long userId) {
        permissionService.checkPermission(userId);
        return apartmentService.findAllApartments();
    }

    @GetMapping("/status/{userId}")
    List<Apartment> findApartmentsByStatus(@PathVariable long userId,
                                          @RequestParam ApartmentStatus status) {
        permissionService.checkPermission(userId);
        return apartmentService.findApartmentsByStatus(status);
    }

    @GetMapping("/available/{guestsNumber}/{category}")
    List<Apartment> findAvailableApartmentsByGuestsNumberAndCategory(@PathVariable long guestsNumber,
                                                                     @PathVariable ApartmentCategory category) {
        return apartmentService.findAvailableApartmentsByGuestsNumberAndCategory(guestsNumber, category);
    }
}
