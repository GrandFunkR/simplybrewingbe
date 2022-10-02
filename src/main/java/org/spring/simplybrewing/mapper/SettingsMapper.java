package org.spring.simplybrewing.mapper;

import org.mapstruct.Mapper;
import org.spring.simplybrewing.dto.SettingsDto;
import org.spring.simplybrewing.entity.Settings;

/**
 * The interface Settings mapper.
 *
 * @author Dario Iannaccone
 */
@Mapper(componentModel = "spring")
public interface SettingsMapper {
    /**
     * Settings to dto settings dto.
     *
     * @param settings the settings
     * @return the settings dto
     */
    SettingsDto SettingsToDto(Settings settings);

    /**
     * Dto to settings settings.
     *
     * @param dto the dto
     * @return the settings
     */
    Settings dtoToSettings(SettingsDto dto);
}
