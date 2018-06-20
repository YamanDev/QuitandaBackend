package br.com.yaman.Quitanda;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.yaman.quitanda.controller.ProdutoController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {
	
	@Autowired
	private ProdutoController controller;
	
	@Test
	public void contextLoads() throws Exception{
		assertThat(controller).isNotNull();
	}	

}
