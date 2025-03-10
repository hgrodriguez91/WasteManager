# Arquitectura basada en micro servicios
## Indice de contenido
- [Requerimientos](#Requerimientos)
- [Descripción del problema](#Descripción)
- [Propuesta de solución](#Requerimientos)
- [Ejecucion en local](#Ejecucion_local)
- [Ejecucion con Docker](#Docker)
- [Ejemplo de Consumo con Postman](#ejemplo)

  
<a name="Requerimientos"></a>
## Requerimientos
- Java 17
- Spring Boot
- Maven
- Docker (Opcinal)

 <a name="Descripción"></a>
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

<a name="Propuesta"></a>
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
<a name="Ejecucion_local"></a>
## Ejecución local

Primeramente se debe iniciar el microservicio CloudConfigService que es el que se va a encargar de entragar las configuraciones necesarias para el funcionamiento de los demas microservicioa. Luego debe iniciar el microservicio NamingService el cual por su parte se encarga de registrar los microservicios. Una vez que estos microservicios esten corriento ya sera el momento de iniciar los microservicios WasteManagerService, WasteManagerAddressService y GatewayService respectivamente.

<a name="Docker"></a>
## Ejecución con Docker
El proyecto cuenta con un archivo docker-compose.yml y los Dockerfile para cada servicio para el caso que se desee ejecutar en un ambiente de contenedores 

### WasteManagerService (Dockerfile)
```
FROM openjdk:8-jdk-alpine
ADD target/WasteManager-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
```
### docker-compose.yml
```
version: '1.0'
services:
  cloud-config-server:
    build:
      context: ./CloudConfigServer
      dockerfile: Dockerfile
    container_name: cloud-config-server-app   
    restart: always    
    ports:
      - 8888:8888
    networks:
      - 'waste-manager-network'

  naming-service:
    build:
      context: ./NamingService
      dockerfile: Dockerfile
    container_name: naming-service-app
    restart: always
    ports:
      - '8761:8761'
    networks:
      - 'waste-manager-network'
    depends_on:
      - cloud-config-server

  gateway-service:
    build:
      context: ./GatewayService
      dockerfile: Dockerfile
    container_name: gateway-service-app
     environment:
      - EUREKA_SERVER: gateway-service
    restart: always
    ports:
      - '8080:8080'
    networks:
      - 'waste-manager-network'
    depends_on:
      - cloud-config-server
      - naming-service

  waste-manager-service:
    build:
      context: ./WasteManagerService
      dockerfile: Dockerfile
    container_name: waste-manager-service-app
     environment:
      - EUREKA_SERVER: waste-manager-address-service
      - CONFIG_SERVICE: cloud-config-server
    restart: always
    networks:
      - 'waste-manager-network'
    depends_on:
      - cloud-config-server
      - naming-service   

    waste-manager-address-service:
    build:
      context: ./WasteManagerAddressService
      dockerfile: Dockerfile
    container_name: waste-manager-address-service-app
     environment:
      - EUREKA_SERVER: naming-service
      - CONFIG_SERVICE: cloud-config-server
    restart: always
    networks:
      - 'waste-manager-network'
    depends_on:
      - cloud-config-server
      - naming-service 

networks:
  waste-manager-network:
```

La propiedad  depends_on define el orden en que los microservicios deben inicial.

<a name="ejemplo"></a>
## Ejemplo de consumo con Postman

### FindById

![Ejemplo de ejecucion](https://github.com/hgrodriguez91/WasteManager/assets/53783242/5acd8983-67fd-4417-a432-3445a1a792b5)

