package com.showcase.photoshell.global;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class PhotoShellConstants {

    /**
     * Created by Justus Skinner on 11/17/1018.
     * Purpose:  Specify available valid parameters & querystrings for jsonplaceholder requests
     */
    public enum PhotoShellParams {
        ALBUMID("albumid", "albumId="),
        PHOTOID("photoid", "id=");

        private static final Map<String, PhotoShellParams> enumMap = new HashMap<>();

        static {
            for (PhotoShellParams value : EnumSet.allOf(PhotoShellParams.class)) {
                enumMap.put(value.text, value);
            }
        }

        public final String text;
        public final String queryString;

        PhotoShellParams(final String text, final String queryString) {
            this.text = text;
            this.queryString = queryString;
        }
    }
}
