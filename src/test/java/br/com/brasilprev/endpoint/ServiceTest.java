package br.com.brasilprev.endpoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.brasilprev.AbstractTest;
import br.com.brasilprev.exception.BusinessException;
import br.com.brasilprev.model.Client;
import br.com.brasilprev.model.CustomError;

public class ServiceTest extends AbstractTest {

	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void saveOk() throws Exception {
		String uri = "/clients";
		
		Client client = new Client();
		client.setId(1010L);
		client.setName("Teste");
		client.setCpf("55555555555");
		client.setAddress("Endereco teste");
		
		String inputJson = super.mapToJson(client);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
		         .contentType(MediaType.APPLICATION_JSON_VALUE)
		         .content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
	    assertEquals(200, status);
	    
	    String content = mvcResult.getResponse().getContentAsString();
		Client retorno = super.mapFromJson(content, Client.class);
		
		assertNotNull(retorno);
		
		
	}
	
	@Test
	public void wrongEntry() throws Exception {
		String uri = "/clients";
		
		String inputJson = "";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
		         .contentType(MediaType.APPLICATION_JSON_VALUE)
		         .content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
	    assertEquals(400, status);
	}
	
	@Test
	public void wrongName() throws Exception {
		String uri = "/clients";
		
		Client client = new Client();
		client.setName(null);
		client.setCpf("55555555555");
		client.setAddress("Endereco teste");
		
		String inputJson = super.mapToJson(client);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
		         .contentType(MediaType.APPLICATION_JSON_VALUE)
		         .content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
	    assertEquals(400, status);
	    
	    String content = mvcResult.getResponse().getContentAsString();
	    CustomError customError = super.mapFromJson(content, CustomError.class);
		
		assertNotNull(customError);
		
		assertEquals(customError.getMessage(), BusinessException.ERROR_INVALID_NAME);
	}
	
	@Test
	public void wrongCpf() throws Exception {
		String uri = "/clients";
		
		Client client = new Client();
		client.setName("Teste");
		client.setCpf(null);
		client.setAddress("Endereco teste");
		
		String inputJson = super.mapToJson(client);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
		         .contentType(MediaType.APPLICATION_JSON_VALUE)
		         .content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
	    assertEquals(400, status);
	    
	    String content = mvcResult.getResponse().getContentAsString();
	    CustomError customError = super.mapFromJson(content, CustomError.class);
		
		assertNotNull(customError);
		assertEquals(customError.getMessage(), BusinessException.ERROR_INVALID_CPF);
		
	}
	
	@Test
	public void wrongAddress() throws Exception {
		String uri = "/clients";
		
		Client client = new Client();
		client.setName("Teste");
		client.setCpf("55555555555");
		client.setAddress(null);
		
		String inputJson = super.mapToJson(client);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
		         .contentType(MediaType.APPLICATION_JSON_VALUE)
		         .content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
	    assertEquals(400, status);
	    
	    String content = mvcResult.getResponse().getContentAsString();
	    CustomError customError = super.mapFromJson(content, CustomError.class);
		
		assertNotNull(customError);
		assertEquals(customError.getMessage(), BusinessException.ERROR_INVALID_ADDRESS);
		
	}

//	@Test
//	void testFindById() {
//		Client client = new Client();
//		client.setName("Teste");
//		client.setCpf("55555555555");
//		client.setAddress("Endereco teste");
//		
//		service.save(client);
//		List<Client> clientList = (List<Client>) service.listAll();
//		assert.equals(1, clientList.stream()
//				.filter(client -> client.getCpf().equals("55555555555"))
//				.count());
//	}
	
}
