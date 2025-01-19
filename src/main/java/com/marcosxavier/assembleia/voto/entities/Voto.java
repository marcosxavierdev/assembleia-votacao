package com.marcosxavier.assembleia.voto.entities;

import com.marcosxavier.assembleia.voto.enums.AprovacaoEnum;
import com.marcosxavier.assembleia.voto.dtos.VotoRequestDTO;
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
    private String id;
    private String idPauta;
    private String idEleitor;
    private AprovacaoEnum aprovacao;

    public Voto(VotoRequestDTO request) {
        this.id = UUID.randomUUID().toString();
        this.idPauta = request.getIdPauta();
        this.idEleitor = request.getIdEleitor();
        this.aprovacao = request.getAprovacao();
    }
}
