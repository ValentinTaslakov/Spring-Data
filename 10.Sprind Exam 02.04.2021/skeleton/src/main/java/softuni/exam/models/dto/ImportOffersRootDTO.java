package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "offers")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportOffersRootDTO {

    @XmlElement(name = "offer")
    private List<ImportOfferDTO> offers;

    public ImportOffersRootDTO() {}

    public List<ImportOfferDTO> getOffers() {
        return offers;
    }
}
