package com.marcosxavier.assembleia.eleitor.entities;

import com.marcosxavier.assembleia.eleitor.dtos.EleitorRequestDTO;
import com.marcosxavier.assembleia.eleitor.dtos.EleitorUpdateDTO;
import com.marcosxavier.assembleia.eleitor.enums.EleitorStatusEnum;
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
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "Eleitor")
public class Eleitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name="id", description="id do eleitor", required=true, example = "8f5f19f5-8fcb-450a-acb9-8719aec42058")
    private String id;

    @Schema(name="cpf", description="cpf do eleitor", required=true, example = "11122233344")
    private String cpf;

    @Schema(name="status", description="status da situação do eleitor", example = "ABLE_TO_VOTE ou UNABLE_TO_VOTE")
    private EleitorStatusEnum status;

    public Eleitor(EleitorRequestDTO request) {
        this.id = UUID.randomUUID().toString();
        this.cpf = request.getCpf();
        this.status = request.getStatus();
    }

    public Eleitor(EleitorUpdateDTO update) {
        this.id = update.getId();
        this.cpf = update.getCpf();
        this.status = update.getStatus();
    }

    public Eleitor(Eleitor eleitor) {
        this.id = eleitor.getId();
        this.cpf = eleitor.getCpf();
        this.status = eleitor.getStatus();
    }
}
