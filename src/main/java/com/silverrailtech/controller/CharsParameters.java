package com.silverrailtech.controller;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class CharsParameters {
    @Length(min = 1, max = 1)
    @Pattern(regexp = "[0-9a-zA-Z]+")
    private String character;
    @Min(value = 1)
    @Max(value = 9)
    private Integer amount;

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
