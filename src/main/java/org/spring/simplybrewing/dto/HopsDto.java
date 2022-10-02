package org.spring.simplybrewing.dto;

import lombok.Data;
import org.spring.simplybrewing.annotations.GenericStringValidation;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * The type Hops dto.
 */
@Data
public class HopsDto {

    /**
     * The Id.
     */
    public Long id;

    /**
     * The Name.
     */
    @GenericStringValidation
    @NotBlank
    @Size(max = 30)
    public String name;

    /**
     * The Notes.
     */
    @GenericStringValidation
    public String notes;

    /**
     * The Alpha.
     */
    public BigDecimal alpha;

    /**
     * The Beta.
     */
    public BigDecimal beta;

    /**
     * The Hsi.
     */
    public BigDecimal HSI;

    /**
     * The Type.
     */
    @GenericStringValidation
    @Size(max = 10)
    public String type;

    /**
     * The Form.
     */
    @GenericStringValidation
    @Size(max = 10)
    public String form;

    /**
     * The Utilization.
     */
    @GenericStringValidation
    @Size(max = 10)
    public String utilization;

    /**
     * The Origin.
     */
    @GenericStringValidation
    @Size(max = 20)
    public String origin;

    /**
     * The Substitutes.
     */
    @GenericStringValidation
    @Size(max = 255)
    public String substitutes;

    /**
     * The Humulene.
     */
    public BigDecimal humulene;

    /**
     * The Carophyllene.
     */
    public BigDecimal carophyllene;

    /**
     * The Cohumulone.
     */
    public BigDecimal cohumulone;

    /**
     * The Myrcene.
     */
    public BigDecimal myrcene;

    /**
     * The Total oil.
     */
    public BigDecimal totalOil;

    /**
     * The Qty.
     */
    public BigDecimal qty;

}