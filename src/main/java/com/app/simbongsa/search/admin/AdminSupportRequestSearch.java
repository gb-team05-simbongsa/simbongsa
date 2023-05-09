package com.app.simbongsa.search.admin;

import com.app.simbongsa.type.RequestType;
import lombok.Data;

@Data
public class AdminSupportRequestSearch {
    private String memberEmail;
    private RequestType requestType;
}
