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
	PerformanceEval.java \
	LinHashMap.java \
	TestTupleGenerator.java \
	TupleGenerator.java \
	TupleGeneratorImpl.java \
	

default: run

run: classes \
     execute

execute: 
	$(JVM) PerformanceEval
classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

