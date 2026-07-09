# Proyecto_SemestralDOO
Integrantes: -Lucas Benjamín Fierro Orellana -Javier Sebastián Alonso Molina Díaz -Joaquín Andrés Torres Zavala

Tema 2:  Simulador de Tienda de Mascotas Virtual (Referente: Angie Ramirez)

Este simulador ofrecerá al usuario la experiencia de gestionar su propia tienda de mascotas virtual. El jugador comenzará con un presupuesto inicial para adquirir diferentes tipos de mascotas (ej. perros, gatos, peces, pájaros), cada una con atributos y necesidades específicas como alimentación (tipo de comida, frecuencia), higiene, nivel de felicidad, tipo de recinto y salud. El usuario deberá gestionar el inventario de mascotas y de suministros (comida, medicinas). Las interacciones incluirán alimentar a las mascotas, limpiar sus hábitats, jugar con ellas para mantener su felicidad, y atender su salud. El estado de cada mascota deberá ser visible y cambiará según los cuidados recibidos. El sistema también simulará la llegada de clientes virtuales interesados en adoptar mascotas, permitiendo al jugador venderlas y así obtener ingresos para continuar gestionando y expandiendo la tienda.


Diagrama de casos de uso.


Captura de pantalla de la interfaz.


Diagrama de clases UML.


Una sección que detalla los patrones de diseño implementados y su justificación, incluyendo una lista de las clases utilizadas por cada patrón.

Los patrones de diseño usados fueron State y Observer.
State: Los animales dentro del simulador fluctúan continuamente en sus estados, usando State encapsulamos las condiciones en su propia clase, permite a los animales transicionar fluidamente entre estados (triste, hambriendo, etc.).
Clases involucradas: Animal, EstadoAnimal y clases de estados como Sano, Enfermo, Hambriento, Sucio, Triste, Saciado, Feliz.
Observer: Se usó para tener un bajo acoplamiento entre la lógica del negocio y la lógica gráfica, así las clases de lógica importantes son "observadas" por la lógica gráfica, así hay más fluidez entre la interacción de la lógica intena y la grpafica. 
Clases involucradas: AnimalObserver


Una breve sección que describe las decisiones importantes que el equipo tomó a lo largo del proyecto, especialmente aquellas relacionadas con los cambios y mejoras aportados a la temática central.

Decidimos poder reembolsar los hábitats con click derecho, también el precio de venta de los animales bajará en función de la cantidad de  condiciones adversas y bajará cada 10 de estado. Además, considerando la lógica de la tienda, hicimos un inventario que funciona como "sala de espera" para los animales, y al tener un habitat valido, este será colocado automáticamente en su habitat, para ahorrar trabajo al usuario.

Una breve sección que presenta los problemas identificados durante el proyecto y una autocrítica sobre su gestión.

Durante las primeras fases, tuvimos problemas sobre como implementar los animales y los habitats, al final decidiendo hacer dos clases madre por separado, habitat y animales, luego relacionando cada animal con su habitat con la logica de la tienda. En la implementación de la logica gráfica, se tuvo problemas con la lógica interna, teniando que crear nuevas funciones o  propiedades en las clases.
Como autocrítica a considerar es la falta de comunicación entre los integrantes del grupo.
