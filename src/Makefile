JFLAGS = -g # -Xlint:unchecked
JC = javac
PCONV = com/caanes/converters
OUT = out

.SUFFIXES: .java .class


CLASSES = \
	$(PCONV)/ConverterFactory.java \
	$(PCONV)/XMLJSONConverterI.java \
	ConvertJSONtoXML.java

default: classes 

classes:
	$(JC) $(JFLAGS) $(CLASSES) 


jar: classes
	jar cfm $(OUT).jar MANIFEST.MF $(CLASSES:.java=.class) $(PLIB)/*.class 

clean:
	$(RM) *.class
	$(RM) $(OUT).jar

