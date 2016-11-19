package resources;

public class ResourceServerController implements ResourceServerControllerMBean {

	private ResourceServlet  _resourceServlet;

	public ResourceServerController() {
	}

	public ResourceServerController(ResourceServlet resourceServlet) {
		_resourceServlet = resourceServlet;
	}

	@Override
	public String getName() {
		return _resourceServlet.getName();
	}

	@Override
	public int getAge() {
		return _resourceServlet.getAge();
	}
}
