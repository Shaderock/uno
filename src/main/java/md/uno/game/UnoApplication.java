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

    //Singleton для памяти
    //AbstractFactory для семейств карт
    //Decorator для свойств карт
    //Facade как интерфейс для сайта
    //Mediator для общения на столе
    //Chain of responsibility для ответов
}
