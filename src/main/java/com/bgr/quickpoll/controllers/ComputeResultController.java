package com.bgr.quickpoll.controllers;

import com.bgr.quickpoll.domain.Vote;
import com.bgr.quickpoll.dto.VoteResult;
import com.bgr.quickpoll.repository.VoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComputeResultController {

    private final VoteRepository voteRepository;

    public ComputeResultController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @GetMapping("/computeresult")
    public ResponseEntity<VoteResult> computeResult(@RequestParam Long pollId) {
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);
        VoteResult voteResult = new VoteResult();
        voteResult.setTotalVotes(allVotes.spliterator().getExactSizeIfKnown());
        allVotes.forEach(vote -> {
            voteResult.addOptionCount(vote.getOption().getId());
        });
        return new ResponseEntity<>(voteResult, null, HttpStatus.OK);
    }
}
