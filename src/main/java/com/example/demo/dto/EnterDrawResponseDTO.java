package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class EnterDrawResponseDTO {

    private String contestantName;

    private Long contestantId;

    private Long ticketId;

    private String ticketSerialNumber;

}
