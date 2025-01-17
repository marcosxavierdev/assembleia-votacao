package com.marcosxavier.assembleia.voto.domain.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "Voto")
public class Voto {
    @Id
    private Long id;
    private Long idPauta;
    private Long idEleitor;
    private Boolean aprovacao;
}
