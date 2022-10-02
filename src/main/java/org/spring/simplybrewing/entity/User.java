/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
public class User implements Serializable {

    public static final long serialVersionUID = -4214325494311301431L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "user_name", nullable = false)
    @NotEmpty(message = "Please, provide an user name")
    public String userName;

    @Column(name = "email", nullable = false)
    @NotEmpty(message = "Please, provide an email")
    public String email;

    @Column(name = "age")
    public Integer age;

    @Column(name = "password_hash", nullable = false)
    @NotEmpty(message = "Please, provide a password")
    public String passwordHash;

    @Column(name = "is_deleted", nullable = false)
    public boolean isDeleted;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    public Set<Role> roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "settings_id", referencedColumnName = "id")
    public Settings settings;


    @Column(name = "address_street")
    public String street;

    @Column(name = "address_city")
    public String city;

    @Column(name = "address_zip_code")
    public String zipCode;

    @Column(name = "address_lat")
    public Double lat;

    @Column(name = "address_lng")
    public Double lng;

    @CreationTimestamp
    @Column(name = "created_at")
    public Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    public LocalDateTime updatedAt;
}
