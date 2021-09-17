-- total de notificacões
select count(id) from notificacao;

-- total de registros com cpf
select count(id) from notificacao where cpf != '';

-- total de registros sem cpf
select count(id) from notificacao where cpf = '';

-- total de registros com nomeCompleto
select count(id) from notificacao where nomeCompleto != '';

-- total de registros com dataNascimento
select count(id) from notificacao where dataNascimento is not null;

-- identificar notificações duplicadas por nomeCompleto e dataNascimento
select numeroNotificacao, nomeCompleto, dataNascimento
from notificacao n1
where n1.nomeCompleto != '' 
and n1.dataNascimento is not null 
and exists (
select *
from notificacao n2
where n2.nomeCompleto = n1.nomeCompleto 
and n2.dataNascimento = n1.dataNascimento
and n2.numeroNotificacao != n1.numeroNotificacao
);

-- identificar notificações duplicadas por nomeCompleto e cpf
select numeroNotificacao, nomeCompleto, cpf
from notificacao n1
where n1.nomeCompleto != '' 
and n1.cpf != '' 
and exists (
select *
from notificacao n2
where n2.nomeCompleto = n1.nomeCompleto 
and n2.cpf = n1.cpf
and n2.numeroNotificacao != n1.numeroNotificacao
);

-- identificar notificações duplicadas por nomeCompleto e cpf com distinct
select distinct nomeCompleto, cpf
from notificacao n1
where n1.nomeCompleto != '' 
and n1.cpf != '' 
and exists (
select *
from notificacao n2
where n2.nomeCompleto = n1.nomeCompleto 
and n2.cpf = n1.cpf
and n2.numeroNotificacao != n1.numeroNotificacao
);

-- exemplo de notificações duplicadas por nomeCompleto e cpf
select numeroNotificacao, nomeCompleto, cpf
from notificacao 
where nomeCompleto = 'ANA CAROLINA BATISTA DO NASCIMENTO'
and cpf = '188.517.687-22'
