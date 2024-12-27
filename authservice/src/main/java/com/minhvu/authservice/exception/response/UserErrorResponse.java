package com.minhvu.authservice.exception.response;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserErrorResponse {
    private int Status;
    private String message;
    private long timeStamp;
}
