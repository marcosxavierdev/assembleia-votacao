package com.marcosxavier.assembleia.adapters.outbound.databaseentities;

import com.marcosxavier.assembleia.domain.dto.voto.VotoUpdateDTO;
import com.marcosxavier.assembleia.utils.enums.AprovacaoEnum;
import com.marcosxavier.assembleia.domain.dto.voto.VotoRequestDTO;
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
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "Voto")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name="id", description="id do voto", example = "8f5f19f5-8fcb-450a-acb9-8719aec42058")
    private String id;

    @Schema(name="idPauta", description="id da pauta", example = "8f5f19f5-8fcb-450a-acb9-8719aec42058")
    private String idPauta;

    @Schema(name="idEleitor", description="id do eleitor", example = "8f5f19f5-8fcb-450a-acb9-8719aec42058")
    private String idEleitor;

    @Schema(name="aprovacao", description="Aprovacao do Voto", example = "SIM ou NAO")
    private AprovacaoEnum aprovacao;

    public Voto(VotoRequestDTO request) {
        this.id = UUID.randomUUID().toString();
        this.idPauta = request.getIdPauta();
        this.idEleitor = request.getIdEleitor();
        this.aprovacao = request.getAprovacao();
    }

    public Voto(VotoUpdateDTO request) {
        this.id = request.getIdEleitor();
        this.idPauta = request.getIdPauta();
        this.idEleitor = request.getIdEleitor();
        this.aprovacao = request.getAprovacao();
    }

    public Voto(Voto voto) {
        this.id = voto.getId();
        this.idPauta = voto.getIdPauta();
        this.idEleitor = voto.getIdEleitor();
        this.aprovacao = voto.getAprovacao();
    }
}
