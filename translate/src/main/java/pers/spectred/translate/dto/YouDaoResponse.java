package pers.spectred.translate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class YouDaoResponse {

    private String[] returnPhrase;

    private String[] translation;

    private Basic basic;

    @Data
    public static class Basic {
        private String[] explains;

    }
}
