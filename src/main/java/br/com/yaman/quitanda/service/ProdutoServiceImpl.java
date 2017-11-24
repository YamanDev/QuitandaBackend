package br.com.yaman.quitanda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.yaman.quitanda.dao.entity.Produto;
import br.com.yaman.quitanda.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
    private ProdutoRepository repository;
	
	@Override
	public List<Produto> findAll() {
		return repository.findAll();
	}

	@Override
	public Produto save(Produto t) {
		return repository.save(t);
	}

	@Override
	public Produto findOne(Integer id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Produto t) {
		repository.delete(t);
	}

}
