package org.spring.simplybrewing.repository;


import org.spring.simplybrewing.entity.CatalogHops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogHopsRepository extends JpaRepository<CatalogHops, Long>, JpaSpecificationExecutor<CatalogHops> {
}
