# Tutorial: IoC, DI y Beans en Spring Boot
## 1. ¿Qué es la Inversión de Control (IoC)?
La Inversión de Control (IoC) es un principio de diseño de software en el cual la responsabilidad del flujo de ejecución de un programa no recae en el programador, sino que se delega en un contenedor o marco de trabajo externo

### Ventajas de la IoC
- Implementar este principio ofrece beneficios clave para el desarrollo de aplicaciones:
- Desacoplamiento: Los objetos no necesitan conocer los detalles de creación de sus dependencias
- Facilidad para las pruebas (Testing): Al delegar el control, es mucho más sencillo sustituir componentes reales por objetos simulados (mocks) durante las pruebas unitarias
- Flexibilidad mediante interfaces: Permite cambiar implementaciones fácilmente sin afectar al resto del sistema

## 2. Inyección de Dependencias (DI)
La Inyección de Dependencias es una forma específica de implementar el principio de IoC. En este modelo, una clase declara una lista de requisitos (objetos que necesita) para llevar a cabo su función, pero no es responsable de crear las instancias de dichos objetos
En su lugar, estos objetos son proporcionados desde fuera de la clase. Por ejemplo, en lugar de que una clase cree internamente una dependencia con el operador new, la recibe directamente, a menudo a través de su constructor. Sus ventajas son las mismas que las de IoC: desacoplamiento, flexibilidad y facilidad de testeo.

## 3. Implementación con Spring: Los Beans
En el ecosistema de Spring, los objetos que forman la columna vertebral de la aplicación y que son gestionados por el contenedor de IoC se denominan Beans.
Un Bean es esencialmente la combinación de:
- Un objeto Java estándar (POJO).
- Metadatos de configuración que indican a Spring cómo gestionarlo.
El Contenedor IoC de Spring utiliza estos metadatos para crear las instancias de los objetos y realizar la inyección de dependencias entre beans de forma automática.

## 4. Configuración de Beans con Anotaciones de Estereotipo
Existen diversas formas de transformar una clase Java en un bean de Spring, siendo el uso de anotaciones una de las más comunes y directas
Las anotaciones de estereotipo se aplican a nivel de clase para indicar que dicha clase debe ser detectada y gestionada por el contenedor de Spring:
- @Component: Estereotipo genérico para cualquier componente gestionado por Spring
- @Service: Indica que la clase cumple el rol de servicio (lógica de negocio)
- @Repository: Se utiliza para clases que manejan el acceso a datos (capa de persistencia)
- @Controller: Marca la clase como un controlador, generalmente en el contexto de aplicaciones web MVC

Al utilizar estas anotaciones, Spring escanea las clases y las registra automáticamente como beans, permitiendo que puedan ser inyectadas donde se necesiten.
