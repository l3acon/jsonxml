JFLAGS = -g 
JC = javac
JAR = jar
# main converter directory
CONPATH = com/caanes/converters

# set build class-path  to include JSON library jar
JARS = -cp jar/javax.json-1.0.4.jar

# output format
OUT = convJSONtoXML.jar

# classes to be built
CLASSES = cli.java \
					$(CONPATH)/ConverterFactory.java \
					$(CONPATH)/XMLJSONConverterI.java \
					$(CONPATH)/ConvertJSONtoXML.java

default: classes 

classes:
	$(JC) $(JARS) $(JFLAGS) $(CLASSES) 

jar: classes
	$(JAR) cfm $(OUT) MANIFEST.MF $(CLASSES:.java=.class) 

clean:
	$(RM) $(CLASSES:.java=.class)
	$(RM) $(OUT)

