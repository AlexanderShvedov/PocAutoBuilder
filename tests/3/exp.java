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
        EjbServiceContext context = new EjbServiceContext("ldap://127.0.0.1:8080", new InitialContext());
        context.findComponent("deadbeef", null);
    }
}