package com.showcase.photoshell.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.showcase.photoshell.global.PhotoShellConstants.PhotoShellParams;
import com.showcase.photoshell.model.Photos;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.stereotype.Service;

/**
 * Created by Justus Skinner on 11/17/2018.
 * Purpose: Service created for retrieving & returning photos.
 */
@Service
public class ShellService {

    ObjectMapper objectMapper = new ObjectMapper();
    StringBuilder sb = new StringBuilder();

    /**
     * Read from application.properties.
     */
    @Value("${photoUrl}")
    public String photoUrl;

    public String photoService(Map<String, String> requestParams) {
        sb.setLength(0);

        if (validate(requestParams)) {
            URL url = buildUrl(requestParams);
            return callApi(url);
        } else {
            return AnsiOutput.toString(AnsiColor.RED, sb.toString(), AnsiColor.DEFAULT);
        }
    }

    /**
     * Validate photoId & albumId are numbers within range.
     * @param requestParams map of request params.
     * @return boolean
     */
    public boolean validate(Map<String, String> requestParams) {
        int albumId;
        int photoId;
        String errMsg = "Id must be a number between 1 and ";

        if (requestParams.get(PhotoShellParams.ALBUMID.text) != null) {
            try {
                albumId = Integer.parseInt(requestParams.get(PhotoShellParams.ALBUMID.text));
            } catch (NumberFormatException e) {
                sb.append("Album " + errMsg + "100");
                return false;
            }
            if (albumId > 100 || albumId < 1) {
                sb.append("Album " + errMsg + "100");
                return false;
            }
        }

        if (requestParams.get(PhotoShellParams.PHOTOID.text) != null) {
            try {
                photoId = Integer.parseInt(requestParams.get(PhotoShellParams.PHOTOID.text));
            } catch (NumberFormatException e) {
                sb.append("Photo " + errMsg + "5000");
                return false;
            }
            if (photoId > 5000 || photoId < 1) {
                sb.append("Photo " + errMsg + "5000");
                return false;
            }
        }
        return true;
    }

    /**
     * Form URL based on supplied request parameters.
     * @param requestParams map of request params.
     * @return URL
     */
    public URL buildUrl(Map<String, String> requestParams) {

        String urlString = photoUrl;
        URL url = null;
        String x = "?";

        if (requestParams.get(PhotoShellParams.ALBUMID.text) != null) {
            urlString = urlString + x + PhotoShellParams.ALBUMID.queryString +
              requestParams.get(PhotoShellParams.ALBUMID.text);
            x = "&";
        }

        if (requestParams.get(PhotoShellParams.PHOTOID.text) != null) {
            urlString = urlString + x + PhotoShellParams.PHOTOID.queryString +
              requestParams.get(PhotoShellParams.PHOTOID.text);
        }

        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * Call jsonplaceholder API to retrieve photos.
     * @param url custom url based on supplied params.
     * @return API response
     */
    public String callApi(URL url) {
        Photos[] photos = new Photos[0];

        try {
            photos = objectMapper.readValue(url, Photos[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(photos.length == 0) {
            sb.append("Photo ID not found in specified album");
            return AnsiOutput.toString(AnsiColor.YELLOW, sb.toString(), AnsiColor.DEFAULT);
        }
        for (Photos photo : photos) {
            sb.append(photo.toString());
        }
        return AnsiOutput.toString(AnsiColor.BLUE, sb.toString(), AnsiColor.DEFAULT);
    }
}
