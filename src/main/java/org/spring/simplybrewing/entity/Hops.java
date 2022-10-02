package org.spring.simplybrewing.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "hops")
public class Hops {

    @Id
    @GeneratedValue
    public Long id;

    @NotBlank
    @Size(max = 30)
    public String name;

    @Lob
    public String notes;

    @Digits(integer=2, fraction=9)
    public BigDecimal alpha;

    @Digits(integer=2, fraction=9)
    public BigDecimal beta;

    @Digits(integer=3, fraction=1)
    public BigDecimal HSI;

    @Size(max = 10)
    public String type;

    @Size(max = 10)
    public String form;

    @Size(max = 10)
    public String utilization;

    @Size(max = 20)
    public String origin;

    @Size(max = 255)
    public String substitutes;

    @Digits(integer=2, fraction=9)
    public BigDecimal humulene;

    @Digits(integer=2, fraction=9)
    public BigDecimal carophyllene;

    @Digits(integer=2, fraction=9)
    public BigDecimal cohumulone;

    @Digits(integer=3, fraction=1)
    public BigDecimal myrcene;

    @Digits(integer=2, fraction=9)
    public BigDecimal totalOil;

    @Digits(integer=2, fraction=9)
    public BigDecimal qty;

    @ManyToOne
    @JoinColumn(name="mu_id")
    private MeasurementUnit mu;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}