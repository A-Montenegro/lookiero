package com.lookiero.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public record UserDto (
        Long id,
        @NotNull @NotBlank @Size(min = 8) @Pattern(regexp = "^[A-Za-z0-9]*$") String username,
        @NotNull @Min(18) Integer age,
        @NotNull @NotBlank String password,
        @NotNull LocalDate birthDate,
        @DecimalMin(value = "1.00") @DecimalMax(value = "2.30") BigDecimal height,
        @Min(40) @Max(150) Integer weight) {

    @JsonIgnore
    public BigDecimal getBmi() {
        return BigDecimal.valueOf(weight).divide(height.pow(2), 2, RoundingMode.HALF_UP);
    }
}