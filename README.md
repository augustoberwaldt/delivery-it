# delivery-it

#####Exemplo requisição para criação de Account

curl -X POST \
  http://localhost:8080/v1/account/newAccount \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 368d409f-19c5-41e4-acc9-0cf2f7d9f2d1' \
  -H 'cache-control: no-cache' \
  -d '{

	"name": "Augusto Marlon Berwaldt",
	"originalValue": 100,
	"dtMaturity": "2019-10-05",
	"dtPayment": "2019-10-05"

}'


#####Exemplo requisição para consulta de Accounts

curl -X GET \
  http://localhost:8080/v1/account/getAccounts \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 1938562c-6fb7-419e-ba4e-7383c9c9f07e' \
  -H 'cache-control: no-cache'
  

#####Tecnologias Utilizadas
- Linguagem de Programação Java
- Spring Boot
- Banco de dados H2