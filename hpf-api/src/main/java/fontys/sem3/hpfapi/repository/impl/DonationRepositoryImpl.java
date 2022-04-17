package fontys.sem3.hpfapi.repository.impl;

import fontys.sem3.hpfapi.dto.DonationDTO;
import fontys.sem3.hpfapi.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
@Qualifier("donations")
@Primary
public class DonationRepositoryImpl implements DonationRepository {
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public List<DonationDTO> getDonations() {
        final List<DonationDTO> donationsList = new ArrayList<>();
        try {
            donationsList.add(new DonationDTO(1, 2, 40.00, sdf.parse("17.04.2022"), null));
            donationsList.add(new DonationDTO(2, 3, 30.00, sdf.parse("17.04.2022"), "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et."));
            donationsList.add(new DonationDTO(3, 4, 20.00, sdf.parse("11.04.2022"), "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab."));
            donationsList.add(new DonationDTO(4, 0, 5.75, sdf.parse("17.04.2022"), null));
            donationsList.add(new DonationDTO(5, 0, 9.64, sdf.parse("11.04.2022"), "Li Europan lingues es membres del sam familie. Lor separat existentie es un myth. Por scientie, musica, sport etc, litot."));
            donationsList.add(new DonationDTO(6, 0, 24.56, sdf.parse("05.04.2022"), null));
            donationsList.add(new DonationDTO(7, 0, 8.32, sdf.parse("15.03.2022"), "The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog. Junk MTV quiz."));
            donationsList.add(new DonationDTO(8, 0, 3.32, sdf.parse("06.03.2022"), "Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated."));
            donationsList.add(new DonationDTO(9, 0, 67.00, sdf.parse("27.02.2022"), null));
            donationsList.add(new DonationDTO(10, 0, 94.00, sdf.parse("22.02.2022"), "One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin."));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return donationsList;
    }
}
