package br.senaigo.mobile.service.impl;

import br.senaigo.mobile.entities.Product;
import br.senaigo.mobile.entities.Product;
import br.senaigo.mobile.repositories.ProductRepository;
import br.senaigo.mobile.service.ProductService;
import br.senaigo.mobile.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

	Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	public ProductRepository productRepository;
	
	@Override
	@Transactional
	public Product post(Product product) {
		try {
			logger.debug("\tMétodo POST executado.");
			logger.debug("\tMétodo POST invocado");
			logger.debug(String.format("\tValor recebido: %s",product.toString()));

			productRepository.save(product);
			
			logger.info(String.format("\tValor persistido: %s",product.toString()));
			return product;
		} catch (Exception e) {
			logger.error(String.format("Error ao persistir registro. \nMensagem:%s",e.getMessage()));
		}
		return null;
	}

	@Override
	public Product get(Product product) {
		try {
			return productRepository.getOne(product.getIdProduct());
		} catch (Exception e) {
			logger.error("Error ao recuperar método GET.");
		}
		return null;
	}

	@Override
	public List<Product> get() {
		try {
			return productRepository.findAll();
		} catch (Exception e) {
			logger.error("Error ao recuperar método GET.");
		}
		return null;
	}

	@Override
	@Transactional
	public void put(Product product) {
		try {
			productRepository.getOne(product.getIdProduct());
		} catch (Exception e) {
			logger.error("Error ao recuperar método GET.");
		}
	}

	@Override
	@Transactional
	public void delete(Product entity) {
		try {
			productRepository.delete(entity);
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o metodo PATCH.\nMensagem: %s", e.getMessage()));
		}
	}

	@Override
	@Transactional
	public void patch(Product entity) {
		try {
			productRepository.save(entity);
			logger.info(String.format("Registro atualizado: %s", entity.toString()));
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o metodo PATCH.\nMensagem: %s", e.getMessage()));
		}
	}

	@Override
	@Transactional
	public List<Product> post(List<Product> entities) {
		try {
			entities.forEach(entity -> {
				productRepository.save(entity);
				logger.info(String.format("Registro atualizado: %s", entity.toString()));
			});
			return entities;
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o metodo POST.\nMensagem: %s", e.getMessage()));
		}
		return entities;
	}

	@Override
	@Transactional
	public void put(List<Product> entities) {
		try {
			entities.forEach(entity -> {
				productRepository.save(entity);
				logger.info(String.format("Registro atualizado: %s", entity.toString()));
			});
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o metodo PUT.\nMensagem: %s",e.getMessage()));
		}
	}

	@Override
	@Transactional
	public void delete(List<Product> entities) {
		try {
			entities.forEach(entity -> {
				productRepository.delete(entity);
			});
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o metodo DELETE.\nMensagem: %s",e.getMessage()));
		}
	}

	@Override
	@Transactional
	public void patch(List<Product> entities) {
		try {
			entities.forEach(entity -> {
				productRepository.save(entity);
				logger.info(String.format("Registro atualizado: %s", entity.toString()));
			});
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o metodo PATCH.\nMensagem: %s",e.getMessage()));
		}
	}

}
