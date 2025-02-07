package com.marcosxavier.assembleia.domain.dto.voto;

import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CustomVotoCollectionDTO<T> {
    private List<T> votos;
    private final Links _links;

    public CustomVotoCollectionDTO(CollectionModel<T> collection) {
        this.votos = collection.getContent().stream().collect(Collectors.toList());
        this._links = collection.getLinks();
    }
}
