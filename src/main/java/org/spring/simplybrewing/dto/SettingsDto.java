package org.spring.simplybrewing.dto;

import lombok.Data;
import org.spring.simplybrewing.annotations.GenericStringValidation;

import javax.validation.constraints.Size;

/**
 * The type Settings dto.
 */
@Data
public class SettingsDto {
    /**
     * The Theme name.
     */
    @Size(max=20)
    @GenericStringValidation
    public String themeName;

    /**
     * The Full name.
     */
    @Size(max=20)
    @GenericStringValidation
    public String fullName;
}
