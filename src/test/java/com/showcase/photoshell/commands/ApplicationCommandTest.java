package com.showcase.photoshell.commands;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

import com.showcase.photoshell.service.ShellService;
import com.showcase.photoshell.testutil.ShellServiceTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ShellServiceTestConfig.class})
@TestPropertySource(locations = "classpath:application.properties")
public class ApplicationCommandTest {

    private ApplicationCommand shell;

    @Autowired
    public void setApplicationCommand(ApplicationCommand shell) {
        this.shell = shell;
    }

    @Test
    public void shouldReturnPhotoId160() {
        assertTrue(shell.getByPhotoId("160").contains("ID : 160"));
    }

    @Test
    public void shouldReturnPhotoAlbum20() {
        assertTrue(shell.getByAlbum("20").contains("AlbumId : 20"));
    }

    @Test
    public void shouldReturnAllPhotos() {
        int count = StringUtils.countOccurrencesOf(shell.getAllPhotos(), "ID");
        assertEquals(5000, count);
    }

    @Test
    public void shouldReturnAlbum1Photo49() {
        String response = shell.filterPhotos("1", "49");
        assertTrue(response.contains("AlbumId : 1"));
        assertTrue(response.contains("ID : 49"));
    }
}
