package fontys.sem3.hpf.repository;

import fontys.sem3.hpf.model.Pet;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class PetsRepositoryImpl implements PetsRepository{

    private FakeDataStore fakeData = new FakeDataStore();
    @Override
    public Pet getPet() {
        return null;
    }
}
