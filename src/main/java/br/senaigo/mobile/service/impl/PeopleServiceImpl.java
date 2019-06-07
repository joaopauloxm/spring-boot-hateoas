package br.senaigo.mobile.service.impl;

import br.senaigo.mobile.entities.People;
import br.senaigo.mobile.repositories.PeopleRepository;
import br.senaigo.mobile.service.PeopleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {

	Logger logger = LoggerFactory.getLogger(PeopleServiceImpl.class);
	
	@Autowired
	public PeopleRepository peopleRepository;
	
	@Override
	@Transactional
	public People post(People people) {
		try {
			logger.debug("\tMétodo POST executado.");
			logger.debug("\tMétodo POST invocado");
			logger.debug(String.format("\tValor recebido: %s",people.toString()));
			
			peopleRepository.save(people);
			
			logger.info(String.format("\tValor persistido: %s",people.toString()));
			return people;
		} catch (Exception e) {
			logger.error(String.format("Error ao persistir registro. \nMensagem:%s",e.getMessage()));
		}
		return null;
	}

	@Override
	public People get(People people) {
		try {
			peopleRepository.getOne(people.getIdPeople());
		} catch (Exception e) {
			logger.error("Error ao recuperar método GET.");
		}
		return null;
	}

	@Override
	public List<People> get() {
		try {
			return peopleRepository.findAll();
		} catch (Exception e) {
			logger.error("Error ao recuperar método GET.");
		}
		return null;
	}

	@Override
	@Transactional
	public void put(People people) {
		try {
			peopleRepository.getOne(people.getIdPeople());
		} catch (Exception e) {
			logger.error("Error ao recuperar método GET.");
		}
	}

	@Override
	@Transactional
	public void delete(People entity) {
		try {
			peopleRepository.delete(entity);
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o metodo PATCH.\nMensagem: %s", e.getMessage()));
		}
	}

	@Override
	@Transactional
	public void patch(People entity) {
		try {
			peopleRepository.save(entity);
			logger.info(String.format("Registro atualizado: %s", entity.toString()));
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o metodo PATCH.\nMensagem: %s", e.getMessage()));
		}
	}

	@Override
	@Transactional
	public List<People> post(List<People> entities) {
		try {
			entities.forEach(entity -> {
				peopleRepository.save(entity);
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
	public void put(List<People> entities) {
		try {
			entities.forEach(entity -> {
				peopleRepository.save(entity);
				logger.info(String.format("Registro atualizado: %s", entity.toString()));
			});
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o metodo PUT.\nMensagem: %s",e.getMessage()));
		}
	}

	@Override
	@Transactional
	public void delete(List<People> entities) {
		try {
			entities.forEach(entity -> {
				peopleRepository.delete(entity);
			});
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o metodo DELETE.\nMensagem: %s",e.getMessage()));
		}
	}

	@Override
	@Transactional
	public void patch(List<People> entities) {
		try {
			entities.forEach(entity -> {
				peopleRepository.save(entity);
				logger.info(String.format("Registro atualizado: %s", entity.toString()));
			});
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o metodo PATCH.\nMensagem: %s",e.getMessage()));
		}
	}

}
