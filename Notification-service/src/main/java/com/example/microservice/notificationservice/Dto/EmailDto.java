package com.example.microservice.notificationservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
