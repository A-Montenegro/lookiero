package com.lookiero.data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import java.math.BigDecimal;

public record UpdateUserDto (
        Long id,
        @DecimalMin(value = "1.00") @DecimalMax(value = "2.30") BigDecimal height,
        @Min(40) @Max(150) Integer weight) { }