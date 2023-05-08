package com.app.simbongsa.domain.search.admin;

import com.app.simbongsa.type.RequestType;
import lombok.Data;

@Data
public class AdminSupportRequestSearch {
    private String userEmail;
    private RequestType requestType;
}
