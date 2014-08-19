JFLAGS = -g # -Xlint:unchecked
JC = javac
PCONV = com/caanes/converters
PLIB = org/jml
OUT = out

.SUFFIXES: .java .class


CLASSES = \
	$(PLIB)/Jml.java \
	$(PLIB)/JMLTree.java \
	$(PCONV)/ConverterFactory.java \
	$(PCONV)/XMLJSONConverterI.java \
	ConvertJSONtoXML.java

default: classes 

libs: 
	$(JC)  $(PLIB)/*.java 


classes: libs 
	$(JC) $(JFLAGS) $(CLASSES)


jar: classes
	jar cfm $(OUT).jar MANIFEST.MF $(CLASSES:.java=.class) $(PLIB)/*.class

clean:
	$(RM) *.class
	$(RM) $(OUT).jar

