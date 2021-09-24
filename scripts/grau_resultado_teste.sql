delimiter $$
create function grau_resultado_teste(resultadoTeste text)
returns int
deterministic
begin
	   case resultadoTeste
			when 'Positivo' then return 3; 
            when 'Negativo' then return 2; 
            when 'Inconclusivo ou Indeterminado' then return 1;
            else return 0;
		end case;
end
$$
delimiter ;

drop function grau_resultado_teste;

select grau_resultado_teste('Positivo');
select grau_resultado_teste('Negativo');
select grau_resultado_teste('Inconclusivo ou Indeterminado');
select grau_resultado_teste('');