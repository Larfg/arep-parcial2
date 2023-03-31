# arep-parcial2

# Instalación

Se necesita maven y java 8.
Para realizar la instalación clone el repositorio, compile con maven, "mvn package", y luego corralo con
"java -cp "./target/classes;./target/dependency/*" edu.escuelaing.edu.CollatzService
una vez este corriendo puede acceder a localhost:5001 y puede usar el servicio desde ahí.

# Descripción

Se realizo una aplicación con spark en donde por un cliente web y el input de un usuario, calculamos la secuencia de
collatz y la entregamos por un json, luego dockerizamos la aplicación y la desplegamos en una maquina EC2 en aws.

Cuando corremos el programa local se ve así:
![corriendo local](https://user-images.githubusercontent.com/72176664/229224090-42420f9d-f59c-4b23-8a1a-232c19e46689.png)

En el cliente web a nivel interno manejamos un path que mapeamos por spark, el cual recive a tráves de un query param
el valor del número que queremos calcular la secuencia.

# Correrlo en AWS

Para esto tenemos un archivo Dockerfile en nuestra carpeta:
![container config](https://user-images.githubusercontent.com/72176664/229224458-fbff67e3-a65c-44ae-876b-038c78a503c7.png)
En donde especificamos la clase main y la ubicación de las dependencias.

Luego creamos la imagen de la siguiente manera:
![image creation](https://user-images.githubusercontent.com/72176664/229224574-ace45aec-5b6d-44e2-9f64-059e2c1c7f59.png)

La pusheamos dentro de nuestro repositorio de docker hub:
![image push](https://user-images.githubusercontent.com/72176664/229224637-cdd42402-23d5-44c5-9274-62dd87094457.png)

Creamos una instancia básica de EC2 en AWS:
![instancia](https://user-images.githubusercontent.com/72176664/229224692-b5abad98-1bce-4ba4-908f-34e8919c0283.png)

Dentro de la maquina que creamos descargamos docker:
![image](https://user-images.githubusercontent.com/72176664/229224832-3607fc4f-12d3-4e8e-b7ca-eca2c0bcb1b6.png)

Corremos el servicio de docker en nuestra maquina:
![docker running](https://user-images.githubusercontent.com/72176664/229224908-85b7c1b6-412c-4d2f-8432-e904ba9dd3b4.png)

Le damos permisos para poderlo ejecutar:
![permisos](https://user-images.githubusercontent.com/72176664/229224940-725ce2ef-d830-4606-a5ef-83202f8f07a0.png)

Corremos el docker desde nuestro repositorio larfg/collatz:
![runing on aws](https://user-images.githubusercontent.com/72176664/229225038-06501cc3-bc3b-4e46-b92a-3d9cafba4b7b.png)

Abrimos el puerto en donde corre nuestro servicio:
![open ports](https://user-images.githubusercontent.com/72176664/229225085-3303fdad-f6d5-4594-80e2-648963093dd1.png)

Y corremos el servicio desde la maquina de amazon:
![connected to amazon](https://user-images.githubusercontent.com/72176664/229225146-68da18c5-f15e-4e7b-adc9-0294e261f425.png)

Por ultimo puede ver los logs:
![docker logs aws](https://user-images.githubusercontent.com/72176664/229225194-1df5fc42-a3a6-45b3-a174-cef166dd66a8.png)

# Video del funcionamiento
[video](https://pruebacorreoescuelaingeduco.sharepoint.com/:v:/s/msteams_b1fe54/EdRELvVNxBlLpYZk_QLEhUgBhWl7o800aj0S7BHthCK91A?e=kuRzmr)
