-- selecionar notificações
select id, numeroNotificacao, nomeCompleto, cpf, dataNascimento, resultadoTeste, paciente_id
from notificacao; 

-- total de notificacões
select count(id) from notificacao;

-- total de registros com cpf
select count(id) from notificacao where cpf != '';

-- total de registros com cpf em branco
select count(id) from notificacao where cpf = '';

-- total de registros com cpf nulo
select count(id) from notificacao where cpf is null;

-- total de registros com nomeCompleto
select count(id) from notificacao where nomeCompleto != '';

-- total de registros com dataNascimento
select count(id) from notificacao where dataNascimento is not null;

-- total de registros sem dataNascimento
select count(id) from notificacao where dataNascimento is null;

-- tipos de resultadoTeste
select distinct(resultadoTeste)
from notificacao;

-- notificações duplicadas
select id, numeroNotificacao, nomeCompleto, cpf, dataNascimento, resultadoTeste, paciente_id
from notificacao 
where nomeCompleto = 'maria das graças silva';

select id, numeroNotificacao, nomeCompleto, cpf, dataNascimento, resultadoTeste, paciente_id
from notificacao 
where nomeCompleto = 'LEANDRO BRAGA PORTES';

select id, numeroNotificacao, nomeCompleto, cpf, dataNascimento, resultadoTeste, paciente_id
from notificacao 
where nomeCompleto = 'LUCAS MUNIZ';

select id, numeroNotificacao, nomeCompleto, cpf, dataNascimento, resultadoTeste, paciente_id
from notificacao 
where nomeCompleto = 'CAMILA DE CARVALHO MACHADO';

select id, numeroNotificacao, nomeCompleto, cpf, dataNascimento, resultadoTeste, paciente_id
from notificacao 
where nomeCompleto = 'MARCIO DORIA DE SIQUEIRA';

select id, numeroNotificacao, nomeCompleto, cpf, dataNascimento, resultadoTeste, paciente_id
from notificacao 
where nomeCompleto = 'ABRAÃO BENICIO DE OLIVEIRA';

