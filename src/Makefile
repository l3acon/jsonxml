JFLAGS = -g # -Xlint:unchecked
JC = javac
PCONV = com/caanes/converters
PLIB = org/json
OUT = out

.SUFFIXES: .java .class


.java.class:
	$(JC) $(JFLAGS) $*.java 

CLASSES = \
	ConvertJSONtoXML.java \
	$(PCONV)/ConverterFactory.java \
	$(PCONV)/XMLJSONConverterI.java \

default: classes 

libs: 
	$(JC)  $(PLIB)/*.java 


classes: libs $(CLASSES:.java=.class) 


jar: classes
	jar cfm $(OUT).jar MANIFEST.MF $(CLASSES:.java=.class) $(PLIB)/*.class

clean:
	$(RM) *.class
	$(RM) $(OUT).jar

