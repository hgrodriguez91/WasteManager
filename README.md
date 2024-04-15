# Prueba Técnica : Backend developer Java
## Requerimientos
- Java 17
- Spring Boot
- Maven
## Descripción del problema
Dadas las siguientes entidades
![Diagrama sin título drawio](https://github.com/hgrodriguez91/WasteManager/assets/53783242/50e686e4-9c4e-428c-a62c-4abf5436c82f)
El objetivo principal es crear y actualizar un objeto WasteManagerEntity , asumiendo que un objeto 
WasteManagerEntity contiene a suvez un objeto WasteManagerAddressEntity y una lista de 
WasteCenterAuthorizationEntity. 
Los datos correspondientes a WasteManagerAddressEntity serán necesario que el servicio 
WasteManagerAddressService los gestione. Es decir, deberá disponer de su correspondiente CRUD y que 
WasteManagerService haga uso de el. 
Finalmente será necesario consultar los datos insertados previamente, entendiendo que parte de los campos 
utilizados para la creacióndel objeto no son de interés para un usuario final. 
### Ademas crear 3 microservicios:
- CloudConfigServer
- NamingService
- GatewayService
  
El objetivo es llamar a los endpoints creados en WasteManagerService a través del GatewayService. 

## Propuesta de solución
Una vez analisado el problema se plantea una arquitectura basada en Spring Cloud como se muestra a continuacion.
![Arquitectura](https://github.com/hgrodriguez91/WasteManager/assets/53783242/095bde4d-6669-4226-a0af-17a80306f9f4)
- NamingService se implementa con un servidor Eureka para el registro y descubrimiento de miccroservicios, además de balanceo de carga 
y tolerancia a fallos.
- El microservicio CloudConfigService permite exteriorizar y centralizar la configuración de los módulos en un solo lugar utilizando Git como repositorio para centralizar la configuración de cada uno.
- GatewayService se encarga de proporcionar un punto de entrada a nuestro ecosistema de microservicios, proporcionando capacidades de enrutamiento dinámico, seguridad y monitorización de las llamadas que se realicen.
- WasteManagerAddressService y WasteManagerService por su parte se encargaran de la gestion de los datos relacionados a las entidades WasteManagerEntity, WasteManagerAddressEntity y WasteCenterAuthorizationEntity utilizando Spring Data Jpa y la base de datos en memoria H2. Además de implementar clientes Feing para la comunicacion entre ambos microservicios.

### CloudConfigService
http://localhost:8888/waste-manager-address-service/dev

### NamingService ( Eureka )
http://localhost:8761/eureka

### GatewayService
http://localhost:8080

### WasteManagerService

### FindById ( GET )

```curl --location 'http://localhost:8080/api/waste-manager/1```
### Create ( POST )

```
curl --location 'http://localhost:8080/api/waste-manager' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Jennifer Aniston",
    "nif": "Lorem ipsum",
    "version":1,
    "isEnabled": true,
    "wasteManagerAddress": {
        "address": "Calle 51",
        "isEnabled": true,
        "version": 1
    },
    "wasteCenterAuthorizations": ["123456","866324"]
}'
```

### Update ( PUT )
```
curl --location --request PUT 'http://localhost:8080/api/waste-manager/1' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Jennifer Aniston LaLoca",
    "nif": "Lorem ipsum",
    "version":1,
    "isEnabled": false,
    "wasteManagerAddress": {
        "address": "Calle 24",
        "isEnabled": true,
        "version": 1
    },
    "wasteCenterAuthorizations": ["123453","866323"]
}'
```

### WasteManagerAddressService

   ### GetAll ( GET )
```
curl --location 'http://localhost:8080/api/manager-address'
```

### Get BY Id ( GET )
```
curl --location 'http://localhost:8080/api/manager-address/1'
```

### Create ( POST )
```
curl --location 'http://localhost:8080/api/manager-address' \
--header 'Content-Type: application/json' \
--data '{
    "address":"Street",
    "version": 1,
    "isEnabled": true
}
'
```

### Update ( PUT )
```
curl --location --request PUT 'http://localhost:8080/api/manager-address/1' \
--header 'Content-Type: application/json' \
--data '{
    "address":"Street 2",
    "version": 2,
    "isEnabled": true
}
'
```

## Orden de ejecución

Primeramente se debe iniciar el microservicio CloudConfigService que es el que se va a encargar de entragar las configuraciones necesarias para el funcionamiento de los demas microservicioa. Luego debe iniciar el microservicio NamingService el cual por su parte se encarga de registrar los microservicios. Una vez que estos microservicios esten corriento ya sera el momento de iniciar los microservicios WasteManagerService, WasteManagerAddressService y GatewayService respectivamente.
