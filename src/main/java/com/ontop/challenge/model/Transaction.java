package com.ontop.challenge.model;

import com.ontop.challenge.model.base.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction extends BaseModel {

    /* ------------------------- Properties -------------------- */

    @Column(name = "amount")
    private BigDecimal amount;

    /* ------------------------- Relationships ----------------- */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
