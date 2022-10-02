package org.spring.simplybrewing.mapper;

import org.mapstruct.Mapper;
import org.spring.simplybrewing.dto.HopsDto;
import org.spring.simplybrewing.entity.Hops;

import java.util.List;

/**
 * The interface Hops mapper.
 *
 * @author Dario Iannaccone
 */
@Mapper(componentModel = "spring")
public interface HopsMapper {

    /**
     * Hops to dto hops dto.
     *
     * @param hops the hops
     * @return the hops dto
     */
    HopsDto HopsToDto(Hops hops);


    /**
     * Dto to hops hops.
     *
     * @param dto the dto
     * @return the hops
     */
    Hops dtoToHops(HopsDto dto);

    /**
     * Gets hops.
     *
     * @param hops the hops
     * @return the hops
     */
    List<HopsDto> getHops(List<Hops> hops);
}