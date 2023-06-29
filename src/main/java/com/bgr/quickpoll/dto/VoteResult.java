package com.bgr.quickpoll.dto;

import java.util.ArrayList;
import java.util.List;

public class VoteResult {
    private Long totalVotes;
    private List<OptionCount> optionCounts;

    public Long getTotalVotes() {
        return totalVotes;
    }

    public VoteResult() {
        this.optionCounts = new ArrayList<>();
    }

    public void setTotalVotes(Long totalVotes) {
        this.totalVotes = totalVotes;
    }

    public List<OptionCount> getOptionCounts() {
        return optionCounts;
    }

    public void setOptionCounts(List<OptionCount> optionCounts) {
        this.optionCounts = optionCounts;
    }

    public void addOptionCount(Long id) {
        OptionCount optionCount = optionCounts.stream()
                .filter(op -> op.getOptionId().equals(id))
                .findFirst()
                .orElse(null);
        if (optionCount == null) {
            optionCounts.add(new OptionCount(id, 1L));
        } else {
            optionCount.setOptionCount(optionCount.getOptionCount() + 1);
        }
    }
}
