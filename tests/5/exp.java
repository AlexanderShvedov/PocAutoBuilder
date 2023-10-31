package org.example;

import org.granite.config.GraniteConfig;
import org.granite.config.flex.Destination;
import org.granite.context.SimpleGraniteContext;
import org.granite.gravity.adapters.JMSServiceAdapter;
import org.granite.messaging.service.EjbServiceFactory;
import org.granite.messaging.service.EjbServiceInvoker;
import org.granite.tide.ejb.EjbServiceContext;
import org.granite.util.XMap;
import org.xml.sax.SAXException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws NamingException, IOException, SAXException {
        SimpleGraniteContext.createThreadInstance(new GraniteConfig(null,
                        null,
                        null,
                        null),
                null,
                null);
        Destination destination = new Destination("ldap://127.0.0.1:8080",
                new ArrayList<>(),
                new XMap(Files.newInputStream(Paths.get("Graniteds-dest-config-PoC.xml"))),
                null,
                null,
                null
        );
        EjbServiceFactory factory = new EjbServiceFactory();
        factory.configure(new XMap(Files.newInputStream(Paths.get("Graniteds-factory-PoC.xml"))));
        EjbServiceInvoker invoker = new EjbServiceInvoker(destination, factory);
    }
}