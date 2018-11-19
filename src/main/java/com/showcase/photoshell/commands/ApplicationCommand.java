package com.showcase.photoshell.commands;

import com.showcase.photoshell.global.PhotoShellConstants.PhotoShellParams;
import com.showcase.photoshell.service.ShellService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * Created by jskinner on 11/17/2018.
 * Purpose: Custom shell commands created to return photos by specified param
 */
@ShellComponent
public class ApplicationCommand {

    private ShellService shellService;

    public ApplicationCommand(ShellService shellService) {
        this.shellService = shellService;
    }

    @ShellMethod(value = "Return by photo Id", key = "photo")
    public String getByPhotoId(@ShellOption String id) {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put(PhotoShellParams.PHOTOID.text, id);
        return shellService.photoService(requestParams);
    }

    @ShellMethod(value = "Return photos by album Id", key = "album")
    public String getByAlbum(@ShellOption String id) {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put(PhotoShellParams.ALBUMID.text, id);
        return shellService.photoService(requestParams);
    }

    @ShellMethod(value = "Return all photos", key = "dump")
    public String getAllPhotos() {
        Map<String, String> requestParams = new HashMap<>();
        return shellService.photoService(requestParams);
    }

    @ShellMethod(value = "Return all photos or filter", key = "return")
    public String filterPhotos(@ShellOption(defaultValue = ShellOption.NULL) String album,
      @ShellOption(defaultValue = ShellOption.NULL) String photo) {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put(PhotoShellParams.ALBUMID.text, album);
        requestParams.put(PhotoShellParams.PHOTOID.text, photo);
        return shellService.photoService(requestParams);
    }
}
