package employee;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.PathVariable;

import com.google.gson.Gson;
import com.paypal.bfs.test.employee.api.EmployeeservApplication;
import com.paypal.bfs.test.employee.entity.Address;
import com.paypal.bfs.test.employee.entity.Employee;
import com.paypal.bfs.test.employee.service.EmployeeService;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EmployeeservApplication.class)
public class EmployeeServTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;
	Employee mockEmployee = null;
	List<Employee> mockEmployees = new ArrayList<>();

	@Before
	public void setUp() throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dob = sdf.parse("1992-07-26");

		Address mockAddress = new Address(1, "502 Fly St", "", "Deerfield", "FL", "33442");
		mockEmployee = new Employee(1, "Jason", "Bourne", "M", dob, "JasonBourne@rhyta.com", mockAddress);
		mockEmployees.add(mockEmployee);
	}

	String employeeJson = "{\"id\":1,\"firstName\":\"Jason\",\"lastName\":\"Bourne\",\"gender\":\"M\",\"dob\":\"1992-07-26\",\"email\":\"JasonBourne@rhyta.com\",\"address\":{\"id\":1,\"line1\":\"502 Fly St\",\"line2\":\"\",\"city\":\"Deerfield\",\"state\":\"FL\",\"zipCode\":\"33442\"}}";

	@SuppressWarnings("unchecked")
	@Test
	public void getAllEmployees() throws Exception {

		Mockito.when(((OngoingStubbing<List<Employee>>) employeeService.getAllEmployees()).thenReturn(mockEmployees));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/bfs/employees")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());

		Gson gson = new Gson();
		String mockEmployeesJson = gson.toJson(mockEmployees);
		JSONAssert.assertEquals(mockEmployeesJson, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void getEmployeeById() throws Exception {
		Mockito.when(employeeService.getEmployeeById(Mockito.anyInt())).thenReturn(mockEmployee);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/bfs/employees")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());

		JSONAssert.assertEquals(employeeJson, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void createEmployee(Employee employee) throws Exception {

		Mockito.when(employeeService.createEmployee(Mockito.any(Employee.class))).thenReturn(mockEmployee);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/bfs/employees")
				.accept(MediaType.APPLICATION_JSON).content(employeeJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost:8080/v1/bfs/employees", response.getHeader(HttpHeaders.LOCATION));
	}

	@Test
	public void deleteEmployeeById(@PathVariable("id") Integer id) throws Exception {
		Mockito.when(employeeService.deleteEmployeeById(Mockito.anyInt())).thenReturn("Success");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/bfs/employees")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "Success";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
}
