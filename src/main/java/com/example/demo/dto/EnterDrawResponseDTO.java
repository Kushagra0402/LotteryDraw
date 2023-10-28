package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EnterDrawResponseDTO {

    private String contestantName;

    private Long contestantId;

    private Long ticketId;

}
