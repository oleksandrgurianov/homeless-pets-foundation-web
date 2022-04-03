package fontys.sem3.hpf.business;

import fontys.sem3.hpf.repository.PetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetsServiceImpl implements PetsService{
    private final PetsRepository petsRepository;
}
