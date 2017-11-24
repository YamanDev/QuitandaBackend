package br.com.yaman.quitanda.repository;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.yaman.quitanda.dao.entity.Produto;

/**
 * 
 * @author marcus.martins
 *
 */
@Repository
@Transactional
public interface ProdutoRepository extends JpaCustomRepository<Produto> {
	
}
