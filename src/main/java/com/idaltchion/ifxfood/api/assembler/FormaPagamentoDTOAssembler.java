package com.idaltchion.ifxfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.IfxLinks;
import com.idaltchion.ifxfood.api.controller.FormaPagamentoController;
import com.idaltchion.ifxfood.api.model.FormaPagamentoDTO;
import com.idaltchion.ifxfood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoDTOAssembler extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IfxLinks ifxLinks;
	
	public FormaPagamentoDTOAssembler() {
		super(FormaPagamentoController.class, FormaPagamentoDTO.class);
	}
	
	@Override
	public FormaPagamentoDTO toModel(FormaPagamento formaPagamento) {
		FormaPagamentoDTO formaPagamentoDTO = createModelWithId(formaPagamento.getId(), formaPagamento);
		modelMapper.map(formaPagamento, formaPagamentoDTO);
		return formaPagamentoDTO;
	}
	
	public FormaPagamentoDTO toModelWithCollectionRel(FormaPagamento formaPagamento) {
		FormaPagamentoDTO formaPagamentoDTO = toModel(formaPagamento);
		formaPagamentoDTO.add(ifxLinks.linkToFormasPagamento(IanaLinkRelations.COLLECTION_VALUE));
		return formaPagamentoDTO;
	}
	
	@Override
	public CollectionModel<FormaPagamentoDTO> toCollectionModel(Iterable<? extends FormaPagamento> formasPagamento) {
		return super.toCollectionModel(formasPagamento).add(ifxLinks.linkToFormasPagamento(IanaLinkRelations.SELF_VALUE));
	}
	
	public CollectionModel<FormaPagamentoDTO> toCollectionModelWithDesassociarFormaPagamento(Iterable<? extends FormaPagamento> formasPagamento, Long restauranteId) {
		CollectionModel<FormaPagamentoDTO> formasPagamentoCollection = toCollectionModel(formasPagamento);
		formasPagamentoCollection.getContent().forEach(formaPagamento -> 
			formaPagamento.add(ifxLinks.linktoDesassociarFormaPagamentoRestaurante(formaPagamento.getId(), restauranteId, "desassociar")));
		return formasPagamentoCollection;
	}
	
}
