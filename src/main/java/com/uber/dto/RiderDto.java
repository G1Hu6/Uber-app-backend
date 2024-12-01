package com.uber.dto;

import com.uber.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiderDto {

    private User user;

    private Double ratting;
}
