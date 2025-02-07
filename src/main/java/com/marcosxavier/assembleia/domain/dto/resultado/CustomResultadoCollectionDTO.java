package com.marcosxavier.assembleia.domain.dto.resultado;

import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CustomResultadoCollectionDTO<T> {
    private List<T> resultados;
    private final Links _links;

    public CustomResultadoCollectionDTO(CollectionModel<T> collection) {
        this.resultados = collection.getContent().stream().collect(Collectors.toList());
        this._links = collection.getLinks();
    }
}
