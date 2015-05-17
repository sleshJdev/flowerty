#Flowerty
Flowers store service with web ui on Java.

###version
1.0.0

### stack of technologies:

* [Hibernate] - great ORM for Java
* [AngularJS] - HTML enhanced for web apps!
* [Twitter Bootstrap] - great UI boilerplate for modern web apps
* [Spring] - IoC container for manage of bean.

### setup ActiveMQ
1. download distribution: http://activemq.apache.org/activemq-5111-release.html
2. extract to any folder
3. open terminal
4. in terminal: move to 'extract-activemq-folder'/bin
5. execute command: java -jar activemq.jar start
activemq must start without errors

>note: to shutdown activemq need press Ctrl+C

### setup Apache Solr

initial setup:

1. download Solr from http://www.apache.org/dyn/closer.cgi/lucene/solr/5.1.0
2. extract it to any folder you prefer
3. visit http://localhost:8983/solr/ to see it's going ok
4. put "solr" folder to solr-5.1.0/server
5. put "cloud" folder to solr-5.1.0/example

> note: (you don't need them in the project folder anymore)

6. go to solr-5.1.0/bin and execute:

> solr start -e cloud -noprompt

7. when it asks about replacing log files, choose all

> it opens two windows. don't close them until you don't need solr anymore!

>note: if somesing doesn't work try running "solr restart -all" from the same bin folder to stop it run solr stop -all or close those two windows

after update:

1. start solr by "solr start" command from bin folder
2. copy solr-update.bat to the same bin folder.execute it
3. copy solr folder to the same place as the last time (solr-5.1.0/server)


### solr update(multicore)

to add a multicore for solr, execute, .bat the file as follow below:
```
solr create_core -c "flowerty-contact" -d basic_configs -p 8983
solr create_core -c "flowerty-purchase" -d basic_configs -p 8983
pause
```

