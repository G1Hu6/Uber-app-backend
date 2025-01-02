package com.uber.dto;

import com.uber.entities.User;
import com.uber.entities.WalletTransaction;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletDto {

    private Long id;

    private double balance;

    private UserDto user;

    private List<WalletTransactionDto> transactions;
}
