package ru.netology.data;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credit {
    private String id;
    private String amount;
    private String status;
}