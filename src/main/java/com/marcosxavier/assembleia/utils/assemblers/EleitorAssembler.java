package com.marcosxavier.assembleia.utils.assemblers;

import com.marcosxavier.assembleia.adapters.inbound.controllers.EleitorRestController;
import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Eleitor;
import com.marcosxavier.assembleia.domain.dto.eleitor.CustomEleitorCollectionDTO;
import com.marcosxavier.assembleia.domain.dto.eleitor.EleitorRequestDTO;
import com.marcosxavier.assembleia.domain.dto.eleitor.EleitorResponseDTO;
import com.marcosxavier.assembleia.domain.dto.eleitor.EleitorUpdateDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EleitorAssembler implements RepresentationModelAssembler<Eleitor, EleitorResponseDTO> {

    public  EleitorResponseDTO toResponseDTO(Eleitor eleitor){
        EleitorResponseDTO response = new EleitorResponseDTO();
        response.setId(eleitor.getId());
        response.setCpf(eleitor.getCpf());
        response.setStatus(eleitor.getStatus());
        return response;
    }

    @Override
    public EleitorResponseDTO toModel(Eleitor eleitor) {
        EleitorResponseDTO response = new EleitorResponseDTO(eleitor);
        response.add(linkTo(methodOn(EleitorRestController.class).buscaPorId(eleitor.getId())).withRel("busca eleitor por id").withType("GET"));
        response.add(linkTo(methodOn(EleitorRestController.class).buscaPorCpf(eleitor.getCpf())).withRel("busca eleitor por cpf").withType("GET"));
        response.add(linkTo(methodOn(EleitorRestController.class).criaEleitor(new EleitorRequestDTO())).withRel("cria um eleitor").withType("POST"));
        response.add(linkTo(methodOn(EleitorRestController.class).atualizaEleitor(new EleitorUpdateDTO())).withRel("atualiza um eleitor").withType("PUT"));
        response.add(linkTo(methodOn(EleitorRestController.class).deletaEleitor(eleitor.getId())).withRel("deleta um eleitor").withType("DELETE"));
        response.add(linkTo(methodOn(EleitorRestController.class).buscaTodosEleitores()).withRel("lista todos eleitores").withType("GET"));
        return response;
    }

    public EleitorResponseDTO toModelBuscaPorId(Eleitor eleitor) {
        EleitorResponseDTO response = new EleitorResponseDTO(eleitor);
        response.add(linkTo(methodOn(EleitorRestController.class).buscaPorId(eleitor.getId())).withSelfRel().withType("GET"));
        response.add(linkTo(methodOn(EleitorRestController.class).buscaPorCpf(eleitor.getCpf())).withRel("busca eleitor por cpf").withType("GET"));
        response.add(linkTo(methodOn(EleitorRestController.class).criaEleitor(new EleitorRequestDTO())).withRel("cria um eleitor").withType("POST"));
        response.add(linkTo(methodOn(EleitorRestController.class).atualizaEleitor(new EleitorUpdateDTO())).withRel("atualiza um eleitor").withType("PUT"));
        response.add(linkTo(methodOn(EleitorRestController.class).deletaEleitor(eleitor.getId())).withRel("deleta um eleitor").withType("DELETE"));
        response.add(linkTo(methodOn(EleitorRestController.class).buscaTodosEleitores()).withRel("lista todos eleitores").withType("GET"));
        return response;
    }

    public EleitorResponseDTO toModelBuscaPorCpf(Eleitor eleitor) {
        EleitorResponseDTO response = new EleitorResponseDTO(eleitor);
        response.add(linkTo(methodOn(EleitorRestController.class).buscaPorCpf(eleitor.getCpf())).withSelfRel().withType("GET"));
        response.add(linkTo(methodOn(EleitorRestController.class).buscaPorId(eleitor.getId())).withRel("busca eleitor por id").withType("GET"));
        response.add(linkTo(methodOn(EleitorRestController.class).criaEleitor(new EleitorRequestDTO())).withRel("cria um eleitor").withType("POST"));
        response.add(linkTo(methodOn(EleitorRestController.class).atualizaEleitor(new EleitorUpdateDTO())).withRel("atualiza um eleitor").withType("PUT"));
        response.add(linkTo(methodOn(EleitorRestController.class).deletaEleitor(eleitor.getId())).withRel("deleta um eleitor").withType("DELETE"));
        response.add(linkTo(methodOn(EleitorRestController.class).buscaTodosEleitores()).withRel("lista todos eleitores").withType("GET"));
        return response;
    }

    public EleitorResponseDTO toModelCriaEleitor(Eleitor eleitor) {
        EleitorResponseDTO response = new EleitorResponseDTO(eleitor);
        response.add(linkTo(methodOn(EleitorRestController.class).criaEleitor(new EleitorRequestDTO())).withSelfRel().withType("POST"));
        response.add(linkTo(methodOn(EleitorRestController.class).buscaPorId(eleitor.getId())).withRel("busca eleitor por id").withType("GET"));
        response.add(linkTo(methodOn(EleitorRestController.class).buscaPorCpf(eleitor.getCpf())).withRel("busca eleitor por cpf").withType("GET"));
        response.add(linkTo(methodOn(EleitorRestController.class).atualizaEleitor(new EleitorUpdateDTO())).withRel("atualiza um eleitor").withType("PUT"));
        response.add(linkTo(methodOn(EleitorRestController.class).deletaEleitor(eleitor.getId())).withRel("deleta um eleitor").withType("DELETE"));
        response.add(linkTo(methodOn(EleitorRestController.class).buscaTodosEleitores()).withRel("lista todos eleitores").withType("GET"));
        return response;
    }

    public EleitorResponseDTO toModelAtualizaEleitor(Eleitor eleitor) {
        EleitorResponseDTO response = new EleitorResponseDTO(eleitor);
        response.add(linkTo(methodOn(EleitorRestController.class).atualizaEleitor(new EleitorUpdateDTO())).withSelfRel().withType("PUT"));
        response.add(linkTo(methodOn(EleitorRestController.class).buscaPorId(eleitor.getId())).withRel("busca eleitor por id").withType("GET"));
        response.add(linkTo(methodOn(EleitorRestController.class).buscaPorCpf(eleitor.getCpf())).withRel("busca eleitor por cpf").withType("GET"));
        response.add(linkTo(methodOn(EleitorRestController.class).criaEleitor(new EleitorRequestDTO())).withRel("cria um eleitor").withType("POST"));
        response.add(linkTo(methodOn(EleitorRestController.class).deletaEleitor(eleitor.getId())).withRel("deleta um eleitor").withType("DELETE"));
        response.add(linkTo(methodOn(EleitorRestController.class).buscaTodosEleitores()).withRel("lista todos eleitores").withType("GET"));
        return response;
    }

    public CustomEleitorCollectionDTO<EleitorResponseDTO> toCollectionModel(List<Eleitor> eleitores) {
        List<EleitorResponseDTO> responses =  eleitores.stream().map(this::toModel).toList();
        CollectionModel<EleitorResponseDTO> collection = CollectionModel.of(responses);
        collection.add(linkTo(methodOn(EleitorRestController.class).buscaTodosEleitores()).withSelfRel().withType("GET"));
        return new CustomEleitorCollectionDTO<>(collection);
    }
}
