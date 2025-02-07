package com.marcosxavier.assembleia.domain.dto.pauta;

import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CustomPautaCollectionDTO<T> {
    private List<T> pautas;
    private final Links _links;

    public CustomPautaCollectionDTO(CollectionModel<T> collection) {
        this.pautas = collection.getContent().stream().collect(Collectors.toList());
        this._links = collection.getLinks();
    }
}
