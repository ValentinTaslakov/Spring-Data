package softuni.exam.models.dto;

import softuni.exam.models.entity.ApartmentType;

import javax.validation.constraints.DecimalMin;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportApartmentDTO {

    @XmlElement
    private ApartmentType apartmentType;

    @XmlElement
    @DecimalMin("40.00")
    private BigDecimal area;

    @XmlElement
    private String town;

    public ImportApartmentDTO() {}

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public String getTown() {
        return town;
    }
}
