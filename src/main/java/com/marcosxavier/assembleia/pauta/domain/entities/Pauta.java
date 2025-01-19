package com.marcosxavier.assembleia.pauta.domain.entities;

import com.marcosxavier.assembleia.enums.PautaStatusEnum;
import com.marcosxavier.assembleia.pauta.domain.dtos.PautaRequestDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.UUID;

@ToString
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "Pauta")
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String tempoMinutos;
    private String assunto;
    private PautaStatusEnum status;

    public Pauta(PautaRequestDTO request) {
        this.id = UUID.randomUUID().toString();
        this.tempoMinutos = request.getTempoMinutos();
        this.assunto = request.getAssunto();
        this.status = PautaStatusEnum.OPEN;
    }
}
