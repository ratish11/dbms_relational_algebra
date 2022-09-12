JFLAGS = -g
JC = javac
RM = rm -rf
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java


CLASSES = \
	Table.java \
	ArrayUtil.java \
	KeyType.java \
	MovieDB.java
	

default: run

run: classes \
     execute

execute: 
	$(JVM) MovieDB
classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

