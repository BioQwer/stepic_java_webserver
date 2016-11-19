package resources;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResourceServlet extends HttpServlet{

	private TestResource _testResource;

	public ResourceServlet(TestResource testResource) {
		_testResource = testResource;
	}


	@Override
	protected void doPost(HttpServletRequest req,
	                      HttpServletResponse resp) throws ServletException, IOException {
		String pathToResource = req.getParameter("path");
		readObject(pathToResource);
	}

	public void readObject(String pathToConfig) {
		try (ObjectInputStream input =
				     new ObjectInputStream(
						     new BufferedInputStream(
								     new FileInputStream(pathToConfig)))) {
			TestResource testResource = (TestResource) input.readObject();
			_testResource = testResource;
		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public String getName() {
		return _testResource.getName();
	}

	public int getAge() {
		return _testResource.getAge();
	}
}
