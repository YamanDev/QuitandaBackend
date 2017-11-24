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
@Table(name = "ESTOQUE")
@SequenceGenerator(name = "EstoqueSeq", sequenceName = "SEQ_COD_ESTOQUE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estoque {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EstoqueSeq")
    @Column(name = "COD_ESTOQUE")
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "COD_PRODUTO")
    private Produto produto;

    @Column(name = "QTD_DISPONIVEL")
    private Integer quantidadeDisponivel;
	
    @Column(name = "QTD_TOTAL")
    private Integer quantidadeTotal;
    
}
