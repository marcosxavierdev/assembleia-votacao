package com.marcosxavier.assembleia.pauta.entities;

import com.marcosxavier.assembleia.pauta.enums.PautaStatusEnum;
import com.marcosxavier.assembleia.pauta.dtos.PautaRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.UUID;

@ToString
@Data
@Builder
@NoArgsConstructor()
@AllArgsConstructor()
@Document(collection = "Pauta")
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name="id", description="id da pauta", example = "8f5f19f5-8fcb-450a-acb9-8719aec42058")
    private String id;

    @Schema(name="tempoMinutos", description="tempo em minutos da pauta", example = "8")
    private String tempoMinutos;

    @Schema(name="assunto", description="assunto da pauta", example = "Adesão de novos parceiros")
    private String assunto;

    @Schema(name="status", description="status da pauta", example = "OPEN ou CLOSED")
    private PautaStatusEnum status;

    public Pauta(PautaRequestDTO request) {
        this.id = UUID.randomUUID().toString();
        this.tempoMinutos = request.getTempoMinutos();
        this.assunto = request.getAssunto();
        this.status = PautaStatusEnum.OPEN;
    }
}
