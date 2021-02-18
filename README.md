# JSF_PRIMEFACES_JPA_POSTGRES
Projeto Java web para demonstração de conceitos avançados de OO - Orientação a Objetos

# Pré-requisitos

O maven deve está instalado, caso não esteja segue o link [maven](https://dicasdejava.com.br/como-instalar-o-maven-no-windows/)

Instalar e configurar o java, caso não esteja segue o link [java](https://medium.com/beelabacademy/configurando-vari%C3%A1veis-de-ambiente-java-home-e-maven-home-no-windows-e-unix-d9461f783c26)

Instalar e configurar banco de dados postgersql, caso não esteja segue o link [postgresql](https://www.youtube.com/watch?v=FoqXi0wpX4c)

Instalar e configurar o ApacheTomCat 8, caso não esteja segue o link [TomCat](https://www.devmedia.com.br/instalacao-e-configuracao-do-apache-tomcat-no-eclipse/27360)
 
Baixar e configurar a biblioteca lombok no IDE-ECLIPSE, caso não esteja segue o link [Lombok](https://medium.com/danielpadua/java-lombok-7e364df75080)


# Tecnologias utilizadas e outros

 1- Java 8 [Sobre](https://www.java.com/pt-BR/download/help/java8_pt-br.html)

 2- PostgresSql 12 [Sobre](https://www.postgresql.org/docs/12/index.html)
 
 3- Hibernate/JPA [Sobre](https://angeliski.com.br/2017/03/07/jpa-e-hibernate-existe-diferenca/)
 
 4- Beans Validation [Sobre](https://blog.algaworks.com/validacao-com-bean-validation/)
 
 5- Maven [Sobre](https://www.dclick.com.br/2010/09/15/o-que-e-o-maven-e-seus-primeiros-passos-com-a-ferramenta/)
 
 6- JSF 2 [Sobre](http://fabrica.ms.senac.br/2013/06/o-que-e-jsf-java-server-faces/)
 
 7- Primefaces [Sobre](https://blog.algaworks.com/tecnologia-e-mercado-do-primefaces/)
 
 8- Primefaces responsivo [Sobre](https://cafe.algaworks.com/fn015-wspfresponsivo/)
 
 9- CDI- Conext Injection Dependency [Sobre](http://www.mauda.com.br/?p=1641)
 
 10- ApacheTomCat [Sobre](https://rockcontent.com/br/blog/tomcat/)
 
 11- HTML5 [Sobre](https://www.techtudo.com.br/artigos/noticia/2011/12/o-que-e-html5.html)
 
 12- CSS [Sobre](https://www.tecmundo.com.br/programacao/2705-o-que-e-css-.htm)
 
 13- JS [Sobre](https://canaltech.com.br/internet/O-que-e-e-como-funciona-a-linguagem-JavaScript/)
 
 14- Lombok [Sobre](https://www.devmedia.com.br/uma-visao-sobre-o-projeto-lombok/28321)
 
 15- Facelets [Sobre](https://www.devmedia.com.br/introducao-ao-facelets/5332)
 
 16- Ajax [Sobre](https://www.devmedia.com.br/o-que-e-o-ajax/6702)
 

# Passos para baixa e rodar o projeto
```
 1- git clone https://github.com/thiago-jv/JSF_PRIMEFACES_JPA_POSTGRES.git 
 2- Importe o projeto no IDE-ECLIPSE
 3- Criei o banco de dados, o scrpit de criação está no projeto em resources/doc/migration/V01_criando_banco_de_dados.sql
 4- Ao importar no IDE-ECLIPSE, o download das bibliotecas do projeto irá começar
 5- Importante configurar o Build Path e apontar para o jdk 1.8
 6- No IDE-ECLIPSE botão esquerdo run as clean verify, para verificar se tem algum erro e limpar o projeto
 7- Abrir o pagina Home.xhtml e clicar no botão Run on Server e selecione o tomcat instalado, caso contrario baixar e instalar no IDE-ECLIPSE
```




