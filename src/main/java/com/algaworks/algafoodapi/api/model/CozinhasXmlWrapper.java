package com.algaworks.algafoodapi.api.model;

import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "cozinhas") // Nome da tag que vai envolver a lista de cozinhas.
public class CozinhasXmlWrapper {
    @JacksonXmlProperty(localName = "cozinha") // Nome da tag que vai envolver cada cozinha.
    @JacksonXmlElementWrapper(useWrapping = false) // Desabilitando o embrulho a lista de cozinhas.
    @NonNull
    private List<Cozinha> cozinhaList;
}
