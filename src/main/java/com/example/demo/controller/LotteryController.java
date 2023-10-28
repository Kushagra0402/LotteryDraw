package com.example.demo.controller;


import com.example.demo.dto.DrawResponseDTO;
import com.example.demo.dto.EnterDrawResponseDTO;
import com.example.demo.model.Contestant;
import com.example.demo.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lottery")
public class LotteryController {

    @Autowired
    private LotteryService lotteryService;


    @PostMapping("/enter-draw")
    public ResponseEntity<EnterDrawResponseDTO> enterDraw(
            @RequestParam String name
    ){
       return ResponseEntity.ok(lotteryService.enterDraw(name));
    }


    @PostMapping("/start-draw")
    public ResponseEntity<String> initiateDraw(){
        lotteryService.startDraw();
        return ResponseEntity.ok("Draw initiated. Contestants can start joining");
    }


    @GetMapping("/winner")
    public ResponseEntity<DrawResponseDTO> getWinner(){
        return ResponseEntity.ok(lotteryService.getWinner());
    }


}
