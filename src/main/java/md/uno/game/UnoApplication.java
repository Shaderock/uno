package md.uno.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UnoApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(UnoApplication.class, args);
    }

/*
    + Singleton для памяти
    - ObjectPool для выделения столов
    ~ Builder для создания разных типов колод

    + Facade как интерфейс для сайта
    + Decorator для свойств карт

    + Strategy для махинаций с картами
    + Mediator для общения на столе
    ~ Chain of responsibility для ответов
*/
}
