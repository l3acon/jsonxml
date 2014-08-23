JFLAGS = -g # -Xlint:unchecked
JC = javac
JAR = jar
PCONV = com/caanes/converters
JARS = -cp javax.json-1.0.4.jar 

OUT = out.jar

CLASSES = \
	$(PCONV)/ConverterFactory.java \
	$(PCONV)/XMLJSONConverterI.java \
	ConvertJSONtoXML.java

default: classes 

classes:
	$(JC) $(JARS) $(JFLAGS) $(CLASSES) 

jar: classes
	$(JAR) cfm $(OUT) MANIFEST.MF $(CLASSES:.java=.class) 

clean:
	$(RM) *.class
	$(RM) $(OUT).jar

