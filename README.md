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

### Pool de conexões
#### Aplicação sem pool de conexões
- > Requisição 1 -> Aplicação Web -> Cria nova conexão com o banco -> e fecha conexão
  > 
Varias requisições podem gerar lentidão na API por sobrecarga, como por exemplo 10 conexões simultaneas com o banco.

#### Aplicação com pool de conexões
É um componente de software que mantem um conjunto ou grupo de conexões, quantas? quantas quiser.
- > Aplicação WEB com pool de conexões(3) -> Banco de dados.

Essas conexões ficam prontas já quando aplicação sobe na qual essas conexões podem ser reutilizadas diversas deves.
Fazemos isso pois criar cada requisição e uma conexão para o banco se acaba tornando custoso.
Quando não são usadas ficam o status **"idle"** que ficam só aguardando alguma ordem.

Há um número minimo para a quantidade de conexões que podem ficar aberta e tambem há um número maximo de conexões para quando há mais requisições sendo feitas e a quantidade minima não dá conta.
As requisições que ainda não entraram no pool ficam em uma fila de esperando algum pool ficar com o status idle para fazer a conexão.
É possivel determinar um tempo maximo que essas conexões excedentes podem ficar abertas, depois disso voltara para o número minimo de conexões.

