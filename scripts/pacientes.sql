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