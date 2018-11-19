package com.showcase.photoshell.service;

import static junit.framework.TestCase.assertEquals;

import com.showcase.photoshell.global.PhotoShellConstants.PhotoShellParams;
import com.showcase.photoshell.testutil.ShellServiceTestConfig;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ShellServiceTestConfig.class})
@TestPropertySource(locations = "classpath:application.properties")
public class ShellServiceTest {

    private ShellService shellService;
    static String url = "https://jsonplaceholder.typicode.com/photos";

    @Autowired
    public void setShellService(ShellService shellService) {
        this.shellService = shellService;
    }

    Map<String, String> requestParams = new HashMap<>();

    @Test
    public void invalidPhotoId() {
        requestParams.put(PhotoShellParams.PHOTOID.text, "Must be a number");
        assertEquals("Photo Id must be a number between 1 and 5000", shellService.photoService(requestParams));
        requestParams.put(PhotoShellParams.PHOTOID.text, "5001");
        assertEquals("Photo Id must be a number between 1 and 5000", shellService.photoService(requestParams));
    }

    @Test
    public void invalidAlbumId() {
        requestParams.put(PhotoShellParams.ALBUMID.text, "Must be a number");
        assertEquals("Album Id must be a number between 1 and 100", shellService.photoService(requestParams));
        requestParams.put(PhotoShellParams.ALBUMID.text, "301");
        assertEquals("Album Id must be a number between 1 and 100", shellService.photoService(requestParams));
    }

    @Test
    public void buildUrlTest() {
        requestParams.put(PhotoShellParams.PHOTOID.text, "2002");
        assertEquals(url + "?id=2002", shellService.buildUrl(requestParams).toString());
    }

    @Test
    public void buildUrlWithAlbumAndPhoto() {
        requestParams.put(PhotoShellParams.PHOTOID.text, "50");
        requestParams.put(PhotoShellParams.ALBUMID.text, "1");
        assertEquals(url + "?albumId=1&id=50", shellService.buildUrl(requestParams).toString());
    }

    @Test
    public void photoIdNotInAlbumTest() {
        requestParams.put(PhotoShellParams.PHOTOID.text, "51");
        requestParams.put(PhotoShellParams.ALBUMID.text, "1");
        assertEquals("Photo ID not found in specified album", shellService.photoService(requestParams));
    }
}
