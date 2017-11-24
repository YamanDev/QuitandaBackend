package br.com.yaman.quitanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface JpaCustomRepository<T> extends JpaRepository<T, Integer>,
        JpaSpecificationExecutor<T> {
}
