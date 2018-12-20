@echo off
set hsqlPath=d:/roadrantz/roadrantz
set hsqlName=roadrantz
java -cp lib/hsqldb-2.4.0.jar org.hsqldb.Server -database.0 %hsqlPath% -dbname.0 %hsqlName% &

