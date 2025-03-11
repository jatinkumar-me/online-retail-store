package com.wipro.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;
    
    @Column(nullable=false)
    @NotNull(message="ProductId cannot be null")
    private Long productId;
    
    @Column(nullable=false)
    @DecimalMin(value="0", inclusive=false,message = "Quantity must be greater than 0")
    @NotNull(message="Quantity cannot be null")
    private Integer quantity;
    

    
}