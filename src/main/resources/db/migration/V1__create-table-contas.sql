CREATE TABLE contas (
    id UUID PRIMARY KEY,
    usuario_id UUID UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    role VARCHAR(50)
);