package com.marcosxavier.assembleia.pauta.domain.entities;

import com.marcosxavier.assembleia.enums.PautaStatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@ToString
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "Pauta")
public class Pauta {
    @Id
    private Long id;
    private LocalDateTime tempoFinal;
    private String assunto;
    private PautaStatusEnum status;
}
