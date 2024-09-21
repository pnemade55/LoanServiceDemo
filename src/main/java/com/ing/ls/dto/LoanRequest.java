package com.ing.ls.dto;

import com.ing.ls.entity.Customer;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoanRequest {
        
        @NotNull
        private Customer customer;
}
