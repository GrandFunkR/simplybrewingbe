package org.spring.simplybrewing.repository;

import org.spring.simplybrewing.entity.Hops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Hops repository.
 *
 * @author Dario Iannaccone
 */
@Repository
public interface HopsRepository extends JpaRepository<Hops, Long>, JpaSpecificationExecutor<Hops> {
    List<Hops> findAllByUserId(Long userId);
}
