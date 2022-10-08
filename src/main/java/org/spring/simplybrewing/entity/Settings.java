package org.spring.simplybrewing.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "settings")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Settings implements Serializable {

    public static final long serialVersionUID = 2168089762510982363L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "theme_name", nullable = false)
    public String themeName;

    @OneToOne(mappedBy = "settings")
    public User user;


}
