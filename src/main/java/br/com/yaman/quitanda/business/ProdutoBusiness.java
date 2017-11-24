package br.com.yaman.quitanda.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.yaman.quitanda.dao.entity.Produto;
import br.com.yaman.quitanda.service.ProdutoService;

@Component
public class ProdutoBusiness implements GenericCrudBusiness<Produto> {

	@Autowired
	private ProdutoService service;
	
	@Override
	public List<Produto> findAll() {
		return service.findAll();
	}

	@Override
	public Produto save(Produto t) {
		return service.save(t);
	}

	@Override
	public Produto findOne(Integer id) {
		return service.findOne(id);
	}

	@Override
	public void delete(Produto t) {
		service.delete(t);
	}

}
