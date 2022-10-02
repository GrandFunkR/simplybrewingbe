package org.spring.simplybrewing.controller;

import com.fasterxml.jackson.core.PrettyPrinter;
import lombok.extern.slf4j.Slf4j;
import org.spring.simplybrewing.dto.HopsDto;
import org.spring.simplybrewing.dto.ResponseInsertDto;
import org.spring.simplybrewing.dto.ResponseListDto;
import org.spring.simplybrewing.entity.CatalogHops;
import org.spring.simplybrewing.entity.Hops;
import org.spring.simplybrewing.entity.User;
import org.spring.simplybrewing.service.HopsService;
import org.spring.simplybrewing.user.UserContextHolder;
import org.spring.simplybrewing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

/**
 * The type Hops controller.
 */
@Slf4j
@Controller
@RequestMapping("api/warehouse")
public class HopsController {

    private UserService userService;

    /**
     * Instantiates a new Hops controller.
     *
     * @param userService the user service
     */
    @Autowired
    public HopsController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private HopsService hopsService;

    /**
     * Gets catalog hops.
     *
     * @param _page  the page
     * @param _limit the limit
     * @param _sort  the sort
     * @param _order the order
     * @return the catalog hops
     */
    @GetMapping("/paged-catalog-hops")
    public ResponseEntity getCatalogHops(@RequestParam int _page, @RequestParam int _limit, @RequestParam Optional<String> _sort, @RequestParam Optional<String> _order) {
        ResponseListDto retVal = new ResponseListDto();


        Page<CatalogHops> pageHops = hopsService.findPageAll(_page-1, _limit, _sort.orElse("Id"), _order.orElse("ASC"));
        retVal.setData(pageHops.toList());
        retVal.setTotalCount(pageHops.getTotalElements());
        return ok(retVal);

    }

    /**
     * Gets catalog hops.
     *
     * @return the catalog hops
     */
    @GetMapping("/catalog-hops")
    public ResponseEntity getCatalogHops() {
        ResponseListDto retVal = new ResponseListDto();

        List<CatalogHops> hops = hopsService.findAllCatalogHops();
        if (hops == null) {
            hops = new ArrayList<>();
        }

        retVal.setData(hops);
        retVal.setTotalCount(hops.size());
        return ok(retVal);
    }

    /**
     * Gets hops.
     *
     * @return the hops
     */
    @GetMapping("/hops")
    public ResponseEntity getHops() {
        ResponseListDto retVal = new ResponseListDto();

        List<HopsDto> hops = hopsService.findAllHops();
        if (hops == null) {
            hops = new ArrayList<>();
        }

        retVal.setData(hops);
        retVal.setTotalCount(hops.size());
        return ok(retVal);
    }

    /**
     * Post hops response entity.
     *
     * @param hops the hops
     * @return the response entity
     */
    @PostMapping("/hops")
    public ResponseEntity postHops(@RequestBody @Valid HopsDto hops) {
        ResponseInsertDto retVal = new ResponseInsertDto();
        try {
            Hops newHops = hopsService.insertHops(hops);
            if (newHops != null) {
                retVal.setId(newHops.getId());
            }
        } catch (Exception e) {
            log.error("Error putHops");
        }
        return ResponseEntity.ok(retVal);
    }

    /**
     * Delete hops response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/hops/{id}")
    public ResponseEntity deleteHops(@PathVariable Long id) {
        ResponseInsertDto retVal = new ResponseInsertDto();
        try {
            hopsService.deleteHops(id);
            retVal.setId(id);
        } catch (Exception e) {
            log.error("Error putHops");
        }
        return ResponseEntity.ok(retVal);
    }
}
