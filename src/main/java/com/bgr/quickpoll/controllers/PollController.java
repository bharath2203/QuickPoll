package com.bgr.quickpoll.controllers;

import com.bgr.quickpoll.domain.Poll;
import com.bgr.quickpoll.repository.PollRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class PollController {

    private final PollRepository pollRepository;

    public PollController(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    @GetMapping("/polls")
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        Iterable<Poll> polls = pollRepository.findAll();
        return ResponseEntity.ok(polls);
    }

    @PostMapping("/polls")
    public ResponseEntity<?> createPoll(@RequestBody Poll poll) {
        poll = pollRepository.save(poll);
        // Set the location header for the newly created resource
        HttpHeaders httpHeaders = new HttpHeaders();
        URI pollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        httpHeaders.setLocation(pollUri);
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/polls/{pollId}")
    public ResponseEntity<Poll> getPoll(@PathVariable Long pollId) {
        Optional<Poll> poll = pollRepository.findById(pollId);
        return poll.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PutMapping("/polls/{pollId}")
    public ResponseEntity<Poll> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
        Poll pollUpdated = pollRepository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/polls/{pollId}")
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        pollRepository.deleteById(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
