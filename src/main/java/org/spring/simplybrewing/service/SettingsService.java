package org.spring.simplybrewing.service;

import org.spring.simplybrewing.dto.SettingsDto;
import org.spring.simplybrewing.entity.Settings;
import org.spring.simplybrewing.entity.User;
import org.spring.simplybrewing.mapper.SettingsMapper;
import org.spring.simplybrewing.repository.SettingsRepository;
import org.spring.simplybrewing.repository.UserRepository;
import org.spring.simplybrewing.user.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * The type Settings service.
 *
 * @author Dario Iannaccone
 */
@Service
public class SettingsService {

    @Autowired
    private SettingsRepository settingsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SettingsMapper settingsMapper;

    /**
     * Gets settings by user id.
     *
     * @param id the id
     * @return the settings by user id
     */
    public Settings getSettingsByUserId(Long id) {
        return settingsRepository.findByUserId(id);
    }

    /**
     * Gets current settings.
     *
     * @return the current settings
     */
    public SettingsDto getCurrentSettings() {
        User currentUser = UserContextHolder.getUser();
        Settings settings = getSettingsByUserId(currentUser.getId());
        return settingsMapper.SettingsToDto(settings);
    }

    /**
     * Update current settings settings dto.
     *
     * @param settingsDTO the settings dto
     * @return the settings dto
     */
    public SettingsDto updateCurrentSettings(SettingsDto settingsDTO) {
        SettingsDto retVal = new SettingsDto();
        User user = UserContextHolder.getUser();

        if (settingsDTO.getThemeName() != null) {
            user.getSettings().setThemeName(settingsDTO.getThemeName());
        }
        if (settingsDTO.getFullName() != null) {
            user.setUserName(settingsDTO.getFullName());
        }



        userRepository.save(user);
        retVal.setFullName(user.getUserName());
        retVal.setThemeName(user.getSettings().getThemeName());

        return retVal;
    }
}
