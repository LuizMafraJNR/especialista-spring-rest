### Eager Loading
Estrategia **Eager Load** (Many**ToOne** usa essa estrategia Eager que 
significa carregamento ansioso, antecipado) toda vez que carrega
uma instancia de uma entidade, as entidades associadas a ela também
são carregadas. Isso é feito através do uso de JOINs no SQL, o que
pode resultar em uma única consulta SQL para carregar todas as entidades
associadas.(Ele faz alguns selects que as vezes não precisamos. 
Exemplo fazer select de cozinha sendo que não estamos pedindo a 
visualização dela na requisição.)

### Lazy Loading
Carregamento preguiçoso ou seja vai ser utilizado só quando necessario.
Tudo que termina **toMany**(ManyToMany) é Lazy Loading. Então basicamente quando temos
um ManyToMany, ele não vai carregar os dados do outro lado da relação ah não ser que seja
necessario. Isso é bom para performance, pois não carrega dados desnecessarios.