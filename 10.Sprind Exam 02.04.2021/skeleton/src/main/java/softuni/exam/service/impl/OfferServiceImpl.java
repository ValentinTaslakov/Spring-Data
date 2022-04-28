package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportApartmentRootDTO;
import softuni.exam.models.dto.ImportOfferDTO;
import softuni.exam.models.dto.ImportOffersRootDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Offer;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.OfferService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final Path path =
            Path.of("src", "main", "resources", "files", "xml", "offers.xml");
    private final OfferRepository offerRepository;
    private final ApartmentRepository apartmentRepository;
    private final AgentRepository agentRepository;

    private final Unmarshaller unmarshaller;
    private final Validator validator;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepository offerRepository, ApartmentRepository apartmentRepository, AgentRepository agentRepository) throws JAXBException {
        this.offerRepository = offerRepository;
        this.apartmentRepository = apartmentRepository;
        this.agentRepository = agentRepository;

        JAXBContext context = JAXBContext.newInstance(ImportApartmentRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.modelMapper = new ModelMapper();
        this.modelMapper.addConverter(ctx -> LocalDate.parse(ctx.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                String.class, LocalDate.class);
    }

    @Override
    public boolean areImported() {
        return offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importOffers() throws IOException, JAXBException {


        ImportOffersRootDTO offersDTOs =
                (ImportOffersRootDTO) this.unmarshaller
                        .unmarshal(new FileReader(path.toAbsolutePath().toString()));



        return offersDTOs.getOffers()
                .stream()
                .map(this::importOffer)
                .collect(Collectors.joining("\n"));

    }

    @Override
    public String exportOffers() throws FileNotFoundException, JAXBException {
        return null;
    }

    private String importOffer(ImportOfferDTO dto) {

        Set<ConstraintViolation<ImportOfferDTO>> errors =
                this.validator.validate(dto);

        if (!errors.isEmpty()){
            return "Invalid offer";
        }

        Optional<Agent> otpAgent = this.agentRepository.findByFirstName(dto.getAgent());

        if (otpAgent.isEmpty()){
            return "Invalid offer";
        }
        Optional<Apartment> optApartment = this.apartmentRepository.findById(dto.getId());

        Offer offer = this.modelMapper.map(dto, Offer.class);

        offer.setAgent(otpAgent.get());
        offer.setApartment(optApartment.get());

        this.offerRepository.save(offer);

        return String.format("Successfully imported offer %.2f",offer.getPrice());

    }
}
