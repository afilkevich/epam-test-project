This project can to control depo and wagon.

Start REST: cd rest-app mvn jetty:run

Start WEB app: cd js-client firefox  depo.html

curl -v "localhost:8088/depo/getAll"

curl -H "Content-Type: application/json" -X POST -d '{"name":"Vitebsk"}' -v localhost:8088/depo/add

curl -H "Content-Type: application/json" -X PUT -d '{"depoId":"2","name":"Mogilev"}' -v localhost:8088/depo/update

curl -X DELETE -v localhost:8088/depo/delete/2

curl -v localhost:8088/wagon/getAllWagon

curl -H "Content-Type: application/json" -X POST -d '{"id":"14152","type":"closed type","depoId":"2","countOfSeat":"38","dateOfBuilder":"2009-10-24"}' -v localhost:8088/wagon/add

curl -H "Content-Type: application/json" -X PUT -d '{"id":"14176","type":"closed type","depoId":"1","countOfSeat":"38","dateOfBuilder":"2009-10-24"}' -v localhost:8088/wagon/update

curl -X DELETE -v localhost:8088/wagon/delete/14176

curl -v localhost:8088/wagon/getByDate/2015-08-01/2016-09-01