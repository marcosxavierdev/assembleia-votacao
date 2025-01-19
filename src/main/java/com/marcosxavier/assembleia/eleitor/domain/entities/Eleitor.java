package com.marcosxavier.assembleia.eleitor.domain.entities;

import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorRequestDTO;
import com.marcosxavier.assembleia.eleitor.enums.EleitorStatusEnum;
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
@Document(collection = "Eleitor")
public class Eleitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String cpf;
    private EleitorStatusEnum status;

    public Eleitor(EleitorRequestDTO request) {
        this.id = UUID.randomUUID().toString();
        this.cpf = request.getCpf();
        this.status = request.getStatus();
    }
}
