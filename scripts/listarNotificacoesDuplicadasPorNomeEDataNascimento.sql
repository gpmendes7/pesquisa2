delimiter $$
create procedure listarNotificacoesDuplicadasPorNomeCompletoEDataNascimento(
    in numeroNotificacao text,
	in nomeCompleto text,
    in dataNascimento datetime
)
begin
	select n.numeroNotificacao
	from notificacao n
	where n.nomeCompleto = nomeCompleto 
	and n.dataNascimento = dataNascimento
    and n.numeroNotificacao != numeroNotificacao;
end
$$
delimiter ;

drop procedure listarNotificacoesDuplicadasPorNomeCompletoEDataNascimento;

show procedure status where Db = 'susbase';

call listarNotificacoesDuplicadasPorNomeCompletoEDataNascimento('332043073452', 'JORGE LUIZ DE SOUZA GONCALVES', '1958-10-17 00:00:00.000000');