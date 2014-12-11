package com.econsult.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.hibernate.engine.jdbc.internal.Formatter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

public class SchemaTranslator {
    private Configuration config = null;
     
    public SchemaTranslator() {
        config = new Configuration();
    }
     
    public SchemaTranslator setDialect(String dialect) {
        config.setProperty(AvailableSettings.DIALECT, dialect);
        return this;
    }
     
    /**
     * Method determines classes which will be used for DDL generation. 
     * @param annotatedClasses - entities annotated with Hibernate annotations.
     */
    public SchemaTranslator addAnnotatedClasses(Class< ? >[] annotatedClasses) {
        for (Class< ? > clazz : annotatedClasses)
            config.addAnnotatedClass(clazz);
        return this;
    }
     
    /**
     * Method determines classes which will be used for DDL generation. 
     * @param annotatedClasses - entities annotated with Hibernate annotations.
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public SchemaTranslator addAnnotatedPackages(String packageName) throws ClassNotFoundException, IOException {
    	List<Class> entities = findMyTypes(packageName);
        for (Class< ? > clazz : entities)
            config.addAnnotatedClass(clazz);
        return this;
    }
    
    /**
     * Method performs translation of entities in table schemas.
     * It generates 'CREATE' and 'DELETE' scripts for the Hibernate entities.
     * Current implementation involves usage of {@link #write(FileOutputStream, String[], Formatter)} method.
     * @param outputStream - stream will be used for *.sql file creation.
     * @throws IOException
     */
    public SchemaTranslator translate(FileOutputStream outputStream) throws IOException {
        Dialect requiredDialect = Dialect.getDialect(config.getProperties());
        String[] query = null;
         
     /*   query = config.generateDropSchemaScript(requiredDialect);
        write(outputStream, query, FormatStyle.DDL.getFormatter());*/
         
        query = config.generateSchemaCreationScript(requiredDialect);
        write(outputStream, query, FormatStyle.DDL.getFormatter());
         
        return this;
    }
     
    /**
     * Method writes line by line DDL scripts in the output stream.
     * Also each line logs in the console.
     * @throws IOException
     */
    private void write(FileOutputStream outputStream, String[] lines, Formatter formatter) 
            throws IOException {
        String tempStr = null;
         
        for (String line : lines) {
            tempStr = formatter.format(line)+";";
            System.out.println(tempStr);
            outputStream.write(tempStr.getBytes());
        }
    }
     
    public static void main(String[] args) throws IOException {
        try {
			SchemaTranslator translator = new SchemaTranslator();
			String packageName = "com.econsult.model";
			translator.setDialect("org.hibernate.dialect.HSQLDialect")
			    .addAnnotatedPackages(packageName)
			    .translate(new FileOutputStream(new File("src/main/resources/db-schema.sql")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
    }
    
    
    private List<Class> findMyTypes(String basePackage) throws IOException, ClassNotFoundException
    {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);

        List<Class> candidates = new ArrayList<Class>();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                                   resolveBasePackage(basePackage) + "/" + "**/*.class";
        Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
        for (Resource resource : resources) {
            if (resource.isReadable()) {
                MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                if (isCandidate(metadataReader)) {
                    candidates.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
                }
            }
        }
        return candidates;
    }

    private String resolveBasePackage(String basePackage) {
        return ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage));
    }

    private boolean isCandidate(MetadataReader metadataReader) throws ClassNotFoundException
    {
        try {
            Class c = Class.forName(metadataReader.getClassMetadata().getClassName());
            if (c.getAnnotation(Entity.class) != null) {
                return true;
            }
        }
        catch(Throwable e){
        }
        return false;
    }

 
}
