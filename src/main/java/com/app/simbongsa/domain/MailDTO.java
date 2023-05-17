package com.app.simbongsa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
public class MailDTO {
    private String address;
    private String title;
    private String message;
}
