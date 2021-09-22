create table paciente2 (
	id bigint primary key auto_increment,
    numeroNotificacao text,
    nomeCompleto text, 
    cpf text,
    dataNascimento datetime(6)
);

delimiter $$
create procedure gerarPacientesTeste(in inicio int , in fim int)
begin
    declare v_fim_loop int default 0;
    
	declare v_contador int default inicio;
    
    declare v_numero_notificacao, v_nome_completo, v_cpf text;
    declare v_data_nascimento datetime(6);
	
    declare c_notificacoes cursor for (
		select numeroNotificacao, nomeCompleto, cpf, dataNascimento from notificacao where id between inicio and fim
    );
    
    open c_notificacoes;
    
    repeat 
        fetch c_notificacoes into v_numero_notificacao, v_nome_completo, v_cpf, v_data_nascimento;

        if v_contador <= fim then
			insert into paciente2 (numeroNotificacao, nomeCompleto, cpf, dataNascimento) 
			values (v_numero_notificacao, v_nome_completo, v_cpf, v_data_nascimento);
        end if;
		
        set v_contador = v_contador + 1;
    until v_contador > fim end repeat; 
    
    close c_notificacoes;
end
$$
delimiter ;

drop procedure gerarPacientesTeste;
truncate paciente2;
select * from paciente2;
select id, numeroNotificacao, nomeCompleto, cpf, dataNascimento from notificacao where id between 1 and 100;
call gerarPacientesTeste(1, 100000);
