package com.bgr.quickpoll.controllers;

import com.bgr.quickpoll.domain.Vote;
import com.bgr.quickpoll.repository.VoteRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class VoteController {

    private final VoteRepository voteRepository;

    public VoteController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }


    @RequestMapping(path = "/polls/{pollId}/votes", method = RequestMethod.POST)
    public ResponseEntity<?> createVote(@PathVariable String pollId, @RequestBody Vote vote) {
        Vote savedVote = voteRepository.save(vote);
        HttpHeaders httpHeaders = new HttpHeaders();
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(savedVote.getId())
                .toUri();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/polls/{pollId}/votes", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Vote>> getAllVotes(@PathVariable Long pollId) {
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);
        return new ResponseEntity<>(allVotes, HttpStatus.OK);
    }


}
