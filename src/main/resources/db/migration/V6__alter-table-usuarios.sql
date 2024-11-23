ALTER TABLE usuarios
    ALTER COLUMN ativo SET DATA TYPE boolean
    USING CASE WHEN ativo = 1 THEN true ELSE false END;