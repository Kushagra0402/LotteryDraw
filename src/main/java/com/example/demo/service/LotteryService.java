package com.example.demo.service;

import com.example.demo.dto.DrawResponseDTO;
import com.example.demo.dto.EnterDrawResponseDTO;
import com.example.demo.model.Contestant;
import com.example.demo.model.Draw;
import com.example.demo.model.Ticket;
import com.example.demo.repository.ContestantRepository;
import com.example.demo.repository.DrawRepository;
import com.example.demo.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.constant.Constable;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class LotteryService {
    private static final long DRAW_INTERVAL_SECONDS = 120 ;
    @Autowired
    private DrawRepository drawRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ContestantRepository contestantRepository;

    private Timer drawTimer;


    List<Draw> allDraws=new ArrayList<>();

    List<Ticket> allTickets=new ArrayList<>();


    //Contestants register and
    public EnterDrawResponseDTO enterDraw(String name){

        Draw currentDraw = drawRepository.findFirstByIsEndedOrderByStartTimeDesc(false);
        if(currentDraw==null){
            throw new RuntimeException("No draws in place. First inittiate a draw");
        }
        //Add contestant in the db
        Contestant contestant=contestantRepository.findByName(name);

        if(contestant==null) {
            contestant = new Contestant();
            contestant.setName(name);
            contestantRepository.save(contestant);
        }

        // find current ongoing draw.


        //Attach ticket to contestant
        Ticket ticket=new Ticket();
        ticket.setContestant(contestant);
        ticket.setDraw(currentDraw);
        ticket.setIsValid(true);
        ticket.setSerialCode(UUID.randomUUID().toString());
        ticketRepository.save(ticket);
        allTickets.add(ticket);

        EnterDrawResponseDTO enterDrawResponseDTO=new EnterDrawResponseDTO();
        enterDrawResponseDTO.setTicketId(ticket.getId());
        enterDrawResponseDTO.setContestantId(contestant.getId());
        enterDrawResponseDTO.setContestantName(contestant.getName());
        enterDrawResponseDTO.setTicketSerialNumber(ticket.getSerialCode());
        return enterDrawResponseDTO;
    }


    //start the draw
    public void startDraw(){

        Draw draw=new Draw();
        draw.setStartTime(LocalDateTime.now());
        draw.setEnded(false);
        drawRepository.save(draw);
        System.out.println("FIRST DRAW HAS STARTED "+draw);
        allDraws.add(draw);
        scheduleNextDraw();
    }


    //

    public void endDraw(){
        Draw draw = drawRepository.findFirstByIsEndedOrderByStartTimeDesc(false);
        List<Ticket> allTicketsInDraw= ticketRepository.findAllByDraw(draw);
        Random random=new Random();
        if(allTicketsInDraw.size()>0) {
            int winnerTicketIndex = random.nextInt(allTicketsInDraw.size());
            Ticket winningTicket = allTicketsInDraw.get(winnerTicketIndex);
            draw.setWinningTicket(winningTicket);
            for (Ticket ticket : allTicketsInDraw) {
                ticket.setIsValid(false); // Set the valid flag to false for all tickets
                ticketRepository.save(ticket);
            }
        }
        draw.setEnded(true);
        drawRepository.save(draw);
    }


    public void scheduleNextDraw(){
        drawTimer = new Timer();
        drawTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                endDraw();
                startDraw();
            }
        }, DRAW_INTERVAL_SECONDS * 1000);
    }

    public DrawResponseDTO getWinner() {
        Draw draw = drawRepository.findFirstByIsEndedOrderByStartTimeDesc(true);
        Ticket winningTicket = draw.getWinningTicket();
        if(winningTicket==null){
            throw new RuntimeException("No one participated in the draw");
        }
                Contestant winner = winningTicket.getContestant();
                DrawResponseDTO drawResponseDTO = new DrawResponseDTO();
                drawResponseDTO.setWinnerId(winner.getId());
                drawResponseDTO.setDrawId(draw.getId());
                drawResponseDTO.setWinnerName(winner.getName());
                drawResponseDTO.setTicketId(winningTicket.getId());
                drawResponseDTO.setSerialCode(winningTicket.getSerialCode());
                return drawResponseDTO;



}}
