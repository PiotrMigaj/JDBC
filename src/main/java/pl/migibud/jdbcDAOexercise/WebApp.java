package pl.migibud.jdbcDAOexercise;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.component.AbstractLifeCycle;
import org.eclipse.jetty.util.component.LifeCycle;
import org.eclipse.jetty.webapp.*;
import pl.migibud.jdbcDAOexercise.dbconnection.MySQLDBConnection;

public class WebApp {
	public static void main(String[] args) throws Exception {
		WebAppContext webApp = new WebAppContext();
		webApp.setResourceBase("src/main/webapp");
		webApp.setContextPath("/");
		webApp.setConfigurations(new Configuration[]
				{
						new AnnotationConfiguration(),
						new WebInfConfiguration(),
						new WebXmlConfiguration(),
						new MetaInfConfiguration(),
						new FragmentConfiguration(),
						new EnvConfiguration(),
						new PlusConfiguration(),
						new JettyWebXmlConfiguration()
				});
		webApp.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/classes/.*");
		Server server = new Server(8080);
		server.setHandler(webApp);
		server.addLifeCycleListener(new AbstractLifeCycle.AbstractLifeCycleListener() {
			@Override
			public void lifeCycleStopped(LifeCycle event) {
				MySQLDBConnection.MY_SQL.closeConnection();
			}
		});
		server.start();
		server.join();
	}
}
