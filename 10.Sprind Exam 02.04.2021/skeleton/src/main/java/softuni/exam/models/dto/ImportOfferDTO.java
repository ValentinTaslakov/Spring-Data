package softuni.exam.models.dto;

import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDate;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportOfferDTO {

    @XmlElement
    @Positive
    private BigDecimal price;

    @XmlElement
    private String agent;

    @XmlElement
    private long id;

    @XmlElement
    private LocalDate publishedOn;

    public ImportOfferDTO() {}

    public BigDecimal getPrice() {
        return price;
    }

    public String getAgent() {
        return agent;
    }

    public long getId() {
        return id;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }
}
