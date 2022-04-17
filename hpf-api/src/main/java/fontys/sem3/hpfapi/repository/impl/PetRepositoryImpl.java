package fontys.sem3.hpfapi.repository.impl;

import fontys.sem3.hpfapi.dto.PetDTO;
import fontys.sem3.hpfapi.repository.PetRepository;
import fontys.sem3.hpfapi.repository.TemporaryDatabase;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import static java.util.Collections.sort;

@Primary
@Service
public class PetRepositoryImpl implements PetRepository {
    private final TemporaryDatabase temporaryDatabase = new TemporaryDatabase();

    @Override
    public ArrayList<PetDTO> getPetsBySearch(String search) {
        ArrayList<PetDTO> pets = new ArrayList<>();

        for (PetDTO p : temporaryDatabase.petsList) {
            if (p.getType().contains(search) || p.getName().contains(search) || p.getBreed().contains(search) || p.getAgeCategory().contains(search) || p.getGender().contains(search) || p.getSize().contains(search) || p.getColor().contains(search)) {
                pets.add(p);
            }
        }

        return pets;
    }

    @Override
    public ArrayList<PetDTO> getPetsSortedByName(boolean ascending) {
        ArrayList<PetDTO> pets = this.temporaryDatabase.petsList;

        Comparator<PetDTO> compareByName =
                Comparator.comparing(PetDTO::getName);

        if (ascending) {
            sort(pets, compareByName);
        } else {
            sort(pets, compareByName.reversed());
        }

        return pets;
    }

    @Override
    public PetDTO getPetById(int id) {
        for (PetDTO p : temporaryDatabase.petsList) {
            if (p.getId() == id) {
                return p;
            }
        }

        return null;
    }
}
