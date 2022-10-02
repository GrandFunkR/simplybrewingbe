package org.spring.simplybrewing.service;

import lombok.extern.slf4j.Slf4j;
import org.spring.simplybrewing.dto.HopsDto;
import org.spring.simplybrewing.entity.CatalogHops;
import org.spring.simplybrewing.entity.Hops;
import org.spring.simplybrewing.entity.User;
import org.spring.simplybrewing.mapper.HopsMapper;
import org.spring.simplybrewing.repository.CatalogHopsRepository;
import org.spring.simplybrewing.repository.HopsRepository;
import org.spring.simplybrewing.user.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Hops service.
 */
@Service
@Slf4j
public class HopsService {
    @Autowired
    private CatalogHopsRepository catalogHopsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private HopsRepository hopsRepository;

    @Autowired
    private HopsMapper hopsMapper;

    /**
     * Find page all page.
     *
     * @param pageNumber the page number
     * @param limit      the limit
     * @param sort       the sort
     * @param order      the order
     * @return the page
     */
    public Page<CatalogHops> findPageAll(int pageNumber, int limit, String sort, String order) {

        Sort sortBy;
        if (sort != null) {
            if ("DESC".equals(order)) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
        } else {
            sortBy = Sort.by("id").ascending();
        }

        Pageable page = PageRequest.of(pageNumber, limit, sortBy);

        Page<CatalogHops> retVal = catalogHopsRepository.findAll(page);


        return retVal;
    }

    /**
     * Find all catalog hops list.
     *
     * @return the list
     */
    public List<CatalogHops> findAllCatalogHops() {
        List<CatalogHops> retVal = catalogHopsRepository.findAll();
        return retVal;
    }

    /**
     * Find all hops list.
     *
     * @return the list
     */
    public List<HopsDto> findAllHops() {
        User user = UserContextHolder.getUser();

        List<Hops> hops = hopsRepository.findAllByUserId(user.getId());
        if (hops != null) {
            return hopsMapper.getHops(hops);
        }
        return null;
    }

    /**
     * Insert hops hops.
     *
     * @param hopsDto the hops dto
     * @return the hops
     */
    public Hops insertHops(HopsDto hopsDto) {
        try {
            Hops hops = hopsMapper.dtoToHops(hopsDto);
            User user = UserContextHolder.getUser();
            hops.setUser(userService.getUserById(user.getId()));
            return hopsRepository.save(hops);
        } catch (Exception e) {
            log.error("Error in insertHops");
        }
        return null;
    }

    /**
     * Delete hops.
     *
     * @param id the id
     */
    public void deleteHops(Long id) {
        try {
            hopsRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error in deleteHops");
        }
    }
}
