CREATE OR REPLACE FUNCTION salvar_historico()
RETURNS TRIGGER AS $$
BEGIN
INSERT INTO HISTORICOPOSTO (data_alteracao, combustivel_id, posto_id, preco)
VALUES (now(), NEW.combustivel_id, NEW.posto_id, NEW.preco);
RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trg_historico_posto
AFTER INSERT OR UPDATE ON combustivel_posto
FOR EACH ROW
EXECUTE FUNCTION salvar_historico();
