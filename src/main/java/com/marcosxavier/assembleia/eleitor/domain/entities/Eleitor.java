package com.marcosxavier.assembleia.eleitor.domain.entities;

import com.marcosxavier.assembleia.enums.EleitorStatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@ToString
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "Eleitor")
public class Eleitor {
    @Id
    private Long id;
    private String cpf;
    private EleitorStatusEnum status;
}
