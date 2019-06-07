package br.senaigo.mobile.controller;

import br.senaigo.mobile.entities.Order;
import br.senaigo.mobile.entities.People;
import br.senaigo.mobile.interfaces.GenericOperationsController;
import br.senaigo.mobile.service.PeopleService;
import br.senaigo.mobile.service.impl.PeopleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/peoples")
public class PeopleController implements GenericOperationsController<People> {

	Logger logger = LoggerFactory.getLogger(PeopleController.class);
	
	@Autowired private PeopleServiceImpl peopleService;

	@Override
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			produces = {MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE,
					MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public Resource<People> post(@RequestBody People entity) {
		try {
			peopleService.post(entity);
			logger.info("Registro inserido");

			Link link = linkTo(OrderController.class).slash(entity.getIdPeople()).withSelfRel();
			Resource<People> result = new Resource<People>(entity, link);
			return result;
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o metodo POST.\nMensagem: %s",e.getMessage()));
		}
		return null;
	}

	@Override
	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, 
							 MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public void put(@RequestBody People people) {
		try {
			peopleService.put(people);
			logger.info(String.format("Registro atualizado: %s",people.toString()));
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o metodo PUT.\nMensagem: %s",e.getMessage()));
		}
	}

	@Override
	@DeleteMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@RequestBody People entity) {
		try {
			peopleService.delete(entity);
			logger.info(String.format("Registro(s) deletado(s): %s",entity.toString()));
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o metodo PUT.\nMensagem: %s",e.getMessage()));
		}
	}

	@Override
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, 
							 MediaType.APPLICATION_XML_VALUE,
							 MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Resources<People> get() {
		try {
			List<People> peoples = peopleService.get();

			logger.info(String.format("Registro(s) recuperados(s): %s",peoples.toString()));

			for (final People people : peoples) {
				Link selfLink = linkTo(PeopleController.class)
						.slash(people.getIdPeople())
						.withSelfRel();
				people.add(selfLink);
			}
			Link link = linkTo(PeopleController.class).withSelfRel();
			Resources<People> result = new Resources<>(peoples, link);

			return result;

		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o método GET.\nMensagem: %s",e.getMessage()));
		}
		return null;
	}

	@Override
	@GetMapping(value="/{id}",
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			produces = {MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE,
					MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Resource<People> get(@PathVariable Long id) {
		try {
			People people = peopleService.get(People.builder().idPeople(id).build());
			logger.info(String.format("Registro recuperado: %s",people.toString()));
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o metodo GET.\nMensagem: %s",e.getMessage()));
		}
		return null;
	}

	@Override
	@PatchMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void patch(People entity) {
		try {
			peopleService.patch(entity);
			logger.info(String.format("Registro atualizado: %s",entity.toString()));
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o metodo PATCH.\nMensagem: %s",e.getMessage()));
		}
	}

}
