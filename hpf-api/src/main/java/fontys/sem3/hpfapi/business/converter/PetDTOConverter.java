package fontys.sem3.hpfapi.business.converter;

import fontys.sem3.hpfapi.dto.pet.PetDTO;
import fontys.sem3.hpfapi.repository.entity.Pet;

public class PetDTOConverter {
    private PetDTOConverter() {
    }

    public static PetDTO convertToDTO(Pet pet) {
        return PetDTO.builder()
                .id(pet.getId())
                .customer(CustomerDTOConverter.convertToDTO(pet.getCustomer()))
                .icon(pet.getIcon())
                .type(pet.getType())
                .name(pet.getName())
                .breed(pet.getBreed())
                .ageCategory(pet.getAgeCategory())
                .gender(pet.getGender())
                .size(pet.getSize())
                .color(pet.getColor())
                .description(pet.getDescription())
                .adoptionFee(pet.getAdoptionFee())
                .build();
    }
}
