-- selecionar pacientes
select id, numeroNotificacao, nomeCompleto, cpf, dataNascimento, resultadoTeste
from paciente; 

-- total de pacientes
select count(id) from paciente;

-- pacientes duplicados
select id, numeroNotificacao, nomeCompleto, cpf, dataNascimento, resultadoTeste
from paciente 
where nomeCompleto = 'maria das graças silva';

select id, numeroNotificacao, nomeCompleto, cpf, dataNascimento, resultadoTeste
from paciente 
where nomeCompleto = 'LEANDRO BRAGA PORTES';

select id, numeroNotificacao, nomeCompleto, cpf, dataNascimento, resultadoTeste
from paciente 
where nomeCompleto = 'LUCAS MUNIZ';

select id, numeroNotificacao, nomeCompleto, cpf, dataNascimento, resultadoTeste
from paciente 
where nomeCompleto = 'CAMILA DE CARVALHO MACHADO';

select id, numeroNotificacao, nomeCompleto, cpf, dataNascimento, resultadoTeste
from paciente 
where nomeCompleto = 'MARCIO DORIA DE SIQUEIRA';

select id, numeroNotificacao, nomeCompleto, cpf, dataNascimento, resultadoTeste
from paciente 
where nomeCompleto = 'ABRAÃO BENICIO DE OLIVEIRA';

-- pacientes com mais de uma notificação
select p.id
from paciente p
where exists (
 select count(n.numeroNotificacao), n.paciente_id
 from notificacao n
 where n.paciente_id = p.id
 group by n.paciente_id
 having count(n.numeroNotificacao) > 1
)
 limit 0, 10000;
 
-- total de pacientes com mais de uma notificação 
select count(p.id)
from paciente p
where exists (
 select count(n.numeroNotificacao), n.paciente_id
 from notificacao n
 where n.paciente_id = p.id
 group by n.paciente_id
 having count(n.numeroNotificacao) > 1
);