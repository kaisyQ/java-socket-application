install:
	mvn clean install

compile:
	mvn compile
	
run:
	java -cp target/framework.jar com.kaisyq.framework.Main

clean-run:
	make install
	make run