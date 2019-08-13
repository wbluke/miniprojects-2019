package com.woowacourse.zzazanstagram.model.member.vo;

import java.net.HttpURLConnection;
import java.net.URL;

public class Profile {
    private final String url;

    private Profile(final String url) {
        validateUrl(url);
        this.url = validateUrl(url);
    }

    public static Profile of(final String url) {
        return new Profile(url);
    }

    private String validateUrl(final String url) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("HEAD");
            if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IllegalArgumentException("리소스가 존재하지 않습니다");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("리소스 경로가 올바르지 않습니다");
        }
        return url;
    }

    public Profile updateUrl(String url) {
        return new Profile(url);
    }

    public String getUrl() {
        return url;
    }
}
