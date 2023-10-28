package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DrawResponseDTO {

    private Long winnerId;

    private String winnerName;

    private Long ticketId;

    private Long drawId;

    private String serialCode;


}
