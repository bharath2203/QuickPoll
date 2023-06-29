package com.bgr.quickpoll.dto;

public class OptionCount {
    private Long optionId;
    private Long optionCount;

    public OptionCount(Long optionId, Long optionCount) {
        this.optionId = optionId;
        this.optionCount = optionCount;
    }

    public OptionCount() {
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public Long getOptionCount() {
        return optionCount;
    }

    public void setOptionCount(Long optionCount) {
        this.optionCount = optionCount;
    }
}
