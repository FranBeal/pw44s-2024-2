package br.edu.utfpr.pb.pw44s.server.validation;

// Importa o contexto da aplicação do Spring, que contém os beans e suas dependências
import org.springframework.context.ApplicationContext;

// Importa a anotação para definir um metodo como um fornecedor de beans gerenciados pelo Spring
import org.springframework.context.annotation.Bean;

// Importa a anotação para indicar que esta classe fornece configurações de contexto do Spring
import org.springframework.context.annotation.Configuration;

// Importa a classe para configurar a integração entre validação do Bean Validation e o Spring
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

// Indica que esta classe contém configurações do Spring
@Configuration
public class ValidationConfig {

    // O contexto da aplicação é injetado automaticamente pelo Spring
    private final ApplicationContext applicationContext;

    // Construtor que recebe o contexto da aplicação como dependência
    public ValidationConfig(ApplicationContext applicationContext) {

        this.applicationContext = applicationContext;
    }

    // Define um bean para o LocalValidatorFactoryBean, que integra o Bean Validation com o Spring
    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean() {
        // Cria uma instância de LocalValidatorFactoryBean
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        // Configura o contexto da aplicação para permitir a injeção de dependências nos validadores
        factoryBean.setApplicationContext(applicationContext);
        // Retorna o bean configurado
        return factoryBean;
    }
}
