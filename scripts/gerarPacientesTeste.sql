create table paciente2 (
	id bigint primary key auto_increment,
    numeroNotificacao text,
    nomeCompleto text, 
    cpf text,
    dataNascimento datetime(6),
    resultadoTeste text,
    evolucaoCaso text
);

delimiter $$
create procedure gerarPacientesTeste(in inicio int , in fim int)
begin
	declare v_contador int default inicio;
    
    declare v_copias int default 0;
    
     declare v_id int default 0;
    
    declare v_numero_notificacao, v_nome_completo, v_cpf, v_resultado_teste, v_evolucao_caso text;
    declare v_data_nascimento datetime(6);
    
    declare v_grau1 int default 0;
    declare v_grau2 int default 0;
	
    declare c_notificacoes cursor for (
		select numeroNotificacao, nomeCompleto, 
               cpf, dataNascimento, resultadoTeste,
               evolucaoCaso
        from notificacao 
        where id between inicio and fim
    );
    
    open c_notificacoes;
    
    repeat 
        fetch c_notificacoes into v_numero_notificacao, v_nome_completo, v_cpf, v_data_nascimento, v_resultado_teste, v_evolucao_caso;
        
        select count(p.numeroNotificacao) into v_copias
		from paciente2 p
		where p.nomeCompleto = v_nome_completo
		and (p.cpf = v_cpf or p.dataNascimento = v_data_nascimento);

        if v_contador <= fim and v_copias = 0  then
			insert into paciente2 (numeroNotificacao, nomeCompleto, cpf, dataNascimento, resultadoTeste) 
			values (v_numero_notificacao, v_nome_completo, v_cpf, v_data_nascimento, v_resultado_teste);
                    
            /*select p.id into v_id
            from paciente2 p
            where p.numeroNotificacao = v_numero_notificacao
            and p.nomeCompleto = v_nome_completo
			and p.cpf = v_cpf
            and p.dataNascimento = v_data_nascimento;
            
            update notificacao
				set paciente_id = v_id
				where nomeCompleto = v_nome_completo
				  and (cpf = v_cpf or dataNascimento = v_data_nascimento);*/
        end if;
        
        if v_copias > 0 then
			update paciente2
				set resultadoTeste = v_resultado_teste
				where nomeCompleto = v_nome_completo
				  and (cpf = v_cpf or dataNascimento = v_data_nascimento)
				  and grau_resultado_teste(v_resultado_teste) > grau_resultado_teste(resultadoTeste);
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

call gerarPacientesTeste(1, 10);

