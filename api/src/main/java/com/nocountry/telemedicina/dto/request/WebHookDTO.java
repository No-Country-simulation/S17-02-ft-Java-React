package com.nocountry.telemedicina.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class WebHookDTO {
    private String action;
    private String api_version;
    private Map<String, String> data = new HashMap<>();
    private OffsetDateTime date_created;
    private Long id;
    private Boolean live_mode;
    private String type;
    private Long user_id;
}
