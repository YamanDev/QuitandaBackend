package br.com.yaman.quitanda.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUTO")
@SequenceGenerator(name = "ProdutoSeq", sequenceName = "SEQ_COD_PRODUTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ProdutoSeq")
    @Column(name = "COD_PRODUTO")
    private Integer id;
	
    @Column(name = "NOME")
    private String nome;
    
    @ManyToOne
    @JoinColumn(name = "COD_TIPO_PRODUTO")
    private TipoProduto tipoProduto;
    
    @Column(name = "DESCRICAO")
    private String descricao;
    
    @Column(name = "CALORIAS")
    private Double calorias;
    
    @Column(name = "PESO_MEDIO")
    private Double pesoMedio;
	
}
