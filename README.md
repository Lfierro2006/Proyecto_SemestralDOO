# Proyecto_SemestralDOO

GRUPO n°20
Integrantes: -Lucas Benjamín Fierro Orellana -Javier Sebastián Alonso Molina Díaz -Joaquín Andrés Torres Zavala

Tema 2:  Simulador de Tienda de Mascotas Virtual (Referente: Angie Ramirez)

*****ENUNCIADO*****
Este simulador ofrecerá al usuario la experiencia de gestionar su propia tienda de mascotas virtual. El jugador comenzará con un presupuesto inicial para adquirir diferentes tipos de mascotas (ej. perros, gatos, peces, pájaros), cada una con atributos y necesidades específicas como alimentación (tipo de comida, frecuencia), higiene, nivel de felicidad, tipo de recinto y salud. El usuario deberá gestionar el inventario de mascotas y de suministros (comida, medicinas). Las interacciones incluirán alimentar a las mascotas, limpiar sus hábitats, jugar con ellas para mantener su felicidad, y atender su salud. El estado de cada mascota deberá ser visible y cambiará según los cuidados recibidos. El sistema también simulará la llegada de clientes virtuales interesados en adoptar mascotas, permitiendo al jugador venderlas y así obtener ingresos para continuar gestionando y expandiendo la tienda.

******PATRONES DE DISEÑO******


State: Permite que un animal cambie su comportamiento según su estado actual. Cada estado (Feliz, Triste, Hambriento, etc.) encapsula las reglas que determinan qué acciones puede realizar el animal y cómo evolucionan sus estadísticas con el tiempo. Esto evita tener condicionales complejos en la clase Animal.
Clases involucradas: Animal, EstadoAnimal y clases de estados como Sano, Enfermo, Hambriento, Sucio, Triste, Saciado, Feliz.


Observer: Permite que la interfaz gráfica se actualice automáticamente cuando cambia el estado de un animal, sin que la lógica de negocio necesite conocer los detalles de la GUI. Cuando un animal modifica sus estadísticas, notifica a todos los observers registrados, y la GUI se refresca para reflejar los cambios.
Clases involucradas: AnimalObserver, Animal(Es aquel observado), CasillaMascota(Es el observador).

*****DECISIONES IMPORTANTES*****

-Reembolsar Habitat: Decidimos poder reembolsar los hábitats con click derecho, originalmente iban a ser fijos una vez comprados pero resultó ser muy restrictivo.
-Precios de venta dinámicos: El precio de venta de los animales bajará en función de la cantidad de  condiciones adversas y variará segun que tan bien cuidado esté el animal.
-Inventario de Animales: Considerando la lógica de la tienda, hicimos un inventario que funciona como "sala de espera" para los animales, y al tener un habitat valido, este será colocado por el jugador en su habitat, originalmente, en el momento de compra se colocarían automaticamente en un habitat y la compra se realizaría solo si existiece uno válido.

*****AUTOCRÍTICA Y PROBLEMAS ENCONTRADOS*****

Durante las primeras fases, tuvimos problemas sobre como implementar los animales y los habitats, al final decidiendo hacer dos clases madre por separado, habitat y animales, luego relacionando cada animal con su habitat con la logica de la tienda. 


En la implementación de la logica gráfica, se tuvo problemas con la lógica interna, teniando que crear nuevas funciones o  propiedades en las clases.


Como autocrítica a considerar es la falta de comunicación entre los integrantes del grupo y falta de organización.



CONTROLES DEL JUEGO

-Click izquierdo en casilla vacía → Comprar hábitat (Jaula $200, Cama $180, Pecera $190)

-Click izquierdo en casilla con hábitat vacío → Colocar animal del inventario

-Click izquierdo en casilla con animal → Menú de acciones (Alimentar, Curar, Limpiar, Jugar)

-Click derecho en casilla con animal → Vender animal (si hay cliente)

-Click derecho en casilla con hábitat vacío → Desmantelar hábitat

-Click derecho en cualquier botón → Cerrar menú

-Click en sprite de computador → Tiendas (Animales, Comida, Medicina)

-Click en el mostrador → Generar cliente (IMPORTANTE!)

-Pasar mouse sobre casilla con animal → Ver estadísticas (tooltip)
