package org.spring.simplybrewing.dto;

import lombok.Data;
import java.util.List;

/**
 * The type Response list dto.
 */
@Data
public class ResponseListDto {
    /**
     * The Data.
     */
    public List<?> data;
    /**
     * The Total count.
     */
    public long totalCount;
}
