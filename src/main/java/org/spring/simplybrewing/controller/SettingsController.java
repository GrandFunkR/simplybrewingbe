package org.spring.simplybrewing.controller;

import org.spring.simplybrewing.dto.SettingsDto;
import org.spring.simplybrewing.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Controller for managing user settings
 */
@Controller
@RequestMapping("api/settings")
public class SettingsController {
    private SettingsService settingsService;

    /**
     * Instantiates a new Settings controller.
     *
     * @param settingsService the settings service
     */
    @Autowired
    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    /**
     * Get current user settings
     *
     * @return current settings data
     */
    @GetMapping("/current")
    public ResponseEntity getCurrentUserSettings() {
        return ok(settingsService.getCurrentSettings());
    }

    /**
     * Update current user settings
     *
     * @param settingsDTO updated settings data
     * @return updated settings data
     */
    @PutMapping("/current")
    public ResponseEntity updateCurrentUserSettings(@Valid @RequestBody SettingsDto settingsDTO) {
        SettingsDto updatedSettings = settingsService.updateCurrentSettings(settingsDTO);
        return ok(updatedSettings);
    }
}
